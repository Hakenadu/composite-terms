package de.hakenadu.terms.visitor.eval.op;

import java.util.List;

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

			if (result instanceof Integer && operandValue instanceof Integer) {
				result = result.intValue() - ((Number) operandValue).intValue();
			}

			if (operandValue instanceof Double) {
				result = result.doubleValue() - ((Number) operandValue).doubleValue();
			}

		}

		return result;
	}
}
