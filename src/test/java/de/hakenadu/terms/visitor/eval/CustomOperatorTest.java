package de.hakenadu.terms.visitor.eval;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.hakenadu.terms.Term;
import de.hakenadu.terms.builder.PrefixTermBuilder;
import de.hakenadu.terms.visitor.eval.op.OperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.Operators;

/**
 * unit tests for registering a custom {@link OperationEvaluator}
 */
public final class CustomOperatorTest {

	/**
	 * this {@link OperationEvaluator} operates on any type of operands and returns
	 * "foo" if at least one {@link Number} operand is passed and "bar" otherwise.
	 */
	public static class FooOperationEvaluator implements OperationEvaluator {

		@Override
		public String evaluate(final List<Object> operandValues) {
			final boolean foo = operandValues.stream().anyMatch(Number.class::isInstance);
			return foo ? "foo" : "bar";
		}
	}

	@Test
	public void testFooOperator() {
		final EvaluationVisitor evaluationVisitor = new SimpleEvaluationVisitor();
		evaluationVisitor.getOperationEvaluators().put("foo", new FooOperationEvaluator());

		// "test" foo (42 - 666)
		final Term myTerm = PrefixTermBuilder.create().beginOperation("foo")//
				.constant("test")//
				.beginOperation(Operators.SUBTRACT)//
				.constant(42)//
				.constant(666)//
				.endOperation()//
				.endOperation()//
				.build();

		final EvaluationContext evaluationContext = new EvaluationContext();
		myTerm.accept(evaluationVisitor, evaluationContext);

		assertEquals("foo", evaluationContext.getValue(), "wrong evaluation result");
	}
}
