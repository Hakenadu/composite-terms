package de.hakenadu.terms.visitor.eval;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.hakenadu.terms.Constant;
import de.hakenadu.terms.Operation;
import de.hakenadu.terms.Variable;
import de.hakenadu.terms.visitor.eval.op.Operators;

public final class EvaluationVisitorTest {

	private static SimpleEvaluationVisitor visitor;

	@BeforeAll
	public static void beforeAll() {
		visitor = new SimpleEvaluationVisitor();
		visitor.getVariableValues().put("test1", 1337);
		visitor.getVariableValues().put("test2", 666);
	}

	@Test
	public void testEvaluate() {
		final Operation operation = new Operation(Operators.ADD, new Variable("test1"),
				new Operation(Operators.SUBTRACT, new Constant(667), new Variable("test2")));

		final EvaluationContext context = new EvaluationContext();
		operation.accept(visitor, context);

		assertNotNull(context.getValue(), "no value in EvaluationContext");
		assertEquals(1338, context.getValue(), "erroneous value in EvaluationContext");
	}
}
