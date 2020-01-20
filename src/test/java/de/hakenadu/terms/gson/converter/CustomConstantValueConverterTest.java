package de.hakenadu.terms.gson.converter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.hakenadu.terms.Constant;
import de.hakenadu.terms.Operation;
import de.hakenadu.terms.Term;
import de.hakenadu.terms.builder.PrefixTermBuilder;
import de.hakenadu.terms.gson.ConstantTypeHierarchyAdapter;
import de.hakenadu.terms.gson.TermsGson;
import de.hakenadu.terms.visitor.eval.op.Operators;

/**
 * unit test for adding a {@link ConstantValueConverter} for a custom datatype
 */
public final class CustomConstantValueConverterTest {

	public static final class Foo {
		private String bar;
		private String bas;

		public void setBar(final String bar) {
			this.bar = bar;
		}

		public String getBar() {
			return bar;
		}

		public void setBas(final String bas) {
			this.bas = bas;
		}

		public String getBas() {
			return bas;
		}

		@Override
		public boolean equals(final Object other) {
			if (!(other instanceof Foo)) {
				return false;
			}

			final Foo otherFoo = (Foo) other;

			return Objects.equals(getBar(), otherFoo.getBar()) && Objects.equals(getBas(), otherFoo.getBas());
		}
	}

	public static final class FooConstantValueConverter implements ConstantValueConverter<Foo> {

		@Override
		public String getTypeName() {
			return "foo"; // is contained in json as value of the "datatype" member
		}

		@Override
		public Class<Foo> getTypeClass() {
			return Foo.class; // for typesafe deserialization
		}

		@Override
		public JsonElement toJson(final Foo foo) {
			/*
			 * It is also possible to create a JsonObject by hand. In this case we want to
			 * rely on Gson's default serialization.
			 */
			return new Gson().toJsonTree(foo, Foo.class);
		}

		@Override
		public Foo fromJson(final JsonElement jsonElement) {
			// for our custom deserialization we also want to use Gson's default mechanism
			return new Gson().fromJson(jsonElement, Foo.class);
		}
	}

	@Test
	public void testFooDatatype() {
		final ConstantTypeHierarchyAdapter customConstantTypeHierarchyAdapter = new ConstantTypeHierarchyAdapter()
				.withValueConverter(new FooConstantValueConverter());

		final Foo foo1 = new Foo();
		foo1.setBar("bar1");
		foo1.setBas("bas1");

		final Foo foo2 = new Foo();
		foo1.setBar("bar2");
		foo1.setBas("bas2");

		final Term myTerm = PrefixTermBuilder.create()//
				.beginOperation(Operators.EQUALS)//
				.constant(foo1)//
				.constant(foo2)//
				.endOperation()//
				.build();

		final Gson mainGson = TermsGson.createGson(customConstantTypeHierarchyAdapter);

		/*
		 * IMPORTANT:
		 * 
		 * Term.class has to be added as second parameter of toJson if myTerm is
		 * declared as a concrete subtype of Term. This ensures that the correct
		 * TypeHierarchyAdapters are used. In this case myTerm is declared directly as
		 * an instance of its superclass Term. That's why in this case the second
		 * parameter would not have been necessary.
		 */
		final String serializedMyTerm = mainGson.toJson(myTerm, Term.class);

		// deserialize myTerm again
		final Term myTermDeserialized = mainGson.fromJson(serializedMyTerm, Term.class);

		assertNotNull(myTermDeserialized, "no term deserialized");
		assertTrue(myTermDeserialized instanceof Operation, "deserialized term is no Operation");

		final Operation myTermDeserializedOp = (Operation) myTermDeserialized;
		assertEquals(2, myTermDeserializedOp.getOperands().size(), "wrong operand count deserialized");

		final Term firstDeserializedOperand = myTermDeserializedOp.getOperands().get(0);
		assertTrue(firstDeserializedOperand instanceof Constant, "wrong type of first operand");
		assertEquals(foo1, ((Constant) firstDeserializedOperand).getValue(), "first foo doesn't match");

		final Term secondDeserializedOperand = myTermDeserializedOp.getOperands().get(1);
		assertTrue(secondDeserializedOperand instanceof Constant, "wrong type of second operand");
		assertEquals(foo2, ((Constant) secondDeserializedOperand).getValue(), "second foo doesn't match");
	}
}
