package de.hakenadu.terms.visitor.eval.op.impl;

import java.util.List;

import de.hakenadu.terms.visitor.eval.op.NumberOperationEvaluator;

/**
 * evaluates the division operation
 * 
 * @author Manuel Seiche
 * @since 20.01.2020
 */
public final class DivideOperationEvaluator implements NumberOperationEvaluator {

	@Override
	public Object evaluateNumbers(final List<Number> operandValues) {
		if (operandValues.size() < 2) {
			throw new IllegalArgumentException("less than 2 operands passed to divide operation");
		}

		return operandValues.stream().mapToDouble(Number::doubleValue).reduce((a, b) -> a / b)
				.orElseThrow(() -> new IllegalArgumentException("empty list of operands passed to divide operation"));
	}
}
