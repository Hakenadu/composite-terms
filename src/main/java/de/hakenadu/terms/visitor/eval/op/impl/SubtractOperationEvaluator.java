package de.hakenadu.terms.visitor.eval.op.impl;

import java.util.List;

import de.hakenadu.terms.visitor.eval.op.OperationEvaluator;

public final class SubtractOperationEvaluator implements OperationEvaluator {

	@Override
	public Number evaluate(final List<Object> operandValues) {
		Number result = null;

		for (final Object operandValue : operandValues) {

			if (!(operandValue instanceof Number)) {
				throw new IllegalArgumentException("operandValue not of type Number: " + operandValue);
			}

			if (result == null) {
				result = (Number) operandValue;
				continue;
			}

			result = result.doubleValue() - ((Number) operandValue).doubleValue();
		}

		return result;
	}
}
