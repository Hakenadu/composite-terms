package de.hakenadu.terms.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.hakenadu.terms.Constant;
import de.hakenadu.terms.Operation;
import de.hakenadu.terms.Term;
import de.hakenadu.terms.Variable;
import de.hakenadu.terms.visitor.eval.op.Operators;

/**
 * tests for the class {@link PrefixTermBuilder}
 * 
 * @since 13.12.2019
 */
public final class PrefixTermBuilderTest {

	/**
	 * example from the type javadoc of {@link PrefixTermBuilder}
	 */
	@Test
	public void testComposite() {
		final Term result = PrefixTermBuilder.create()//
				.beginOperation(Operators.SUBTRACT)//
				.constant(1337)//
				.beginOperation(Operators.ADD)//
				.constant(500)//
				.variable("x")//
				.endOperation()//
				.endOperation()//
				.build();

		assertNotNull(result, "no result from PrefixTermBuilder");
		assertTrue(result instanceof Operation, "result is not an Operation");

		final Operation operation = (Operation) result;
		assertEquals(Operators.SUBTRACT, operation.getOperator(), "incorrect root Operation");
		assertNotNull(operation.getOperands(), "no operands for first Operation");
		assertEquals(2, operation.getOperands().size(), "wrong amount of operands for first Operation");

		final Term firstLeaf = operation.getOperands().get(0);
		assertTrue(firstLeaf instanceof Constant, "first operand of root Operation is not a Constant");
		assertEquals(1337, ((Constant) firstLeaf).getValue(), "incorrect value of Constant");

		final Term secondChild = operation.getOperands().get(1);
		assertTrue(secondChild instanceof Operation, "second operand of root Operation is not an Operation");

		final Operation secondOperation = (Operation) secondChild;
		assertEquals(Operators.ADD, secondOperation.getOperator(), "incorrect child Operation");
		assertNotNull(secondOperation.getOperands(), "no operands for second Operation");
		assertEquals(2, secondOperation.getOperands().size(), "wrong amount of operands for second Operation");

		final Term secondLeaf = secondOperation.getOperands().get(0);
		assertTrue(secondLeaf instanceof Constant, "second leaf is not a Constant");
		assertEquals(500, ((Constant) secondLeaf).getValue(), "wrong value of second leaf");

		final Term thirdLeaf = secondOperation.getOperands().get(1);
		assertTrue(thirdLeaf instanceof Variable, "third leaf is not a Variable");
		assertEquals("x", ((Variable) thirdLeaf).getName(), "wrong name of third leaf");
	}

	@Test
	public void testLeaf() {
		final PrefixTermBuilder builder = PrefixTermBuilder.create().constant(1337);

		assertThrows(IllegalStateException.class, () -> builder.constant(1));
		assertThrows(IllegalStateException.class, () -> builder.variable("x"));

		final Term result = builder.build();
		assertNotNull(result, "no result from PrefixTermBuilder");
		assertTrue(result instanceof Constant, "result is not a Constant");
		assertEquals(1337, ((Constant) result).getValue(), "value of Constant is incorrect");
	}
}
