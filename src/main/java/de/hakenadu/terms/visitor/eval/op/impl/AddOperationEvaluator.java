package de.hakenadu.terms.visitor.eval.op.impl;

import java.util.List;

import de.hakenadu.terms.visitor.eval.op.NumberOperationEvaluator;

/**
 * evaluates the addition operation
 * 
 * @author Manuel Seiche
 * @since 14.12.2019
 */
public final class AddOperationEvaluator implements NumberOperationEvaluator {

	@Override
	public Object evaluateNumbers(final List<Number> operandValues) {
		if (operandValues.size() < 2) {
			throw new IllegalArgumentException("less than 2 operands passed to add operation");
		}

		return operandValues.stream().mapToDouble(Number::doubleValue).reduce((a, b) -> a + b)
				.orElseThrow(() -> new IllegalArgumentException("empty list of operands passed to add operation"));
	}
}
