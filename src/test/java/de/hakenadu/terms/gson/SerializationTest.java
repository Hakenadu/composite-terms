package de.hakenadu.terms.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import de.hakenadu.terms.Constant;
import de.hakenadu.terms.Operation;
import de.hakenadu.terms.Term;
import de.hakenadu.terms.Variable;
import de.hakenadu.terms.visitor.eval.op.Operators;

public final class SerializationTest {

	@Test
	public void testSimple() {
		final Term term = new Operation(Operators.ADD,
				new Operation(Operators.SUBTRACT, new Variable("test"), new Constant(1)));

		final Gson gson = TermsGson.createGson();

		final JsonElement json = gson.toJsonTree(term, Term.class);

		final String jsonString = json.toString();

		final Term restoredTerm = gson.fromJson(jsonString, Term.class);

		final String restoredJsonString = gson.toJson(restoredTerm, Term.class);
		assertEquals(jsonString, restoredJsonString);
	}
}
