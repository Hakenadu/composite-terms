package de.hakenadu.terms.visitor.eval.op;

import java.util.ArrayList;
import java.util.List;

public abstract class NumberOperationEvaluator implements OperationEvaluator {

	protected abstract Object evaluateNumbers(List<Number> operandValues);

	@Override
	public Object evaluate(final List<Object> operandValues) {
		final List<Number> numberOperands = new ArrayList<>();

		for (final Object operandValue : operandValues) {

			if (!(operandValue instanceof Number)) {
				throw new IllegalArgumentException("operandValue not of type Number: " + operandValue);
			}

			numberOperands.add((Number) operandValue);
		}

		return evaluateNumbers(numberOperands);
	}
}
