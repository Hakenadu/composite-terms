package de.hakenadu.terms.visitor.eval.op.impl;

import java.util.List;

import de.hakenadu.terms.visitor.eval.op.NumberOperationEvaluator;

/**
 * evaluates the multiplication operation
 * 
 * @author Manuel Seiche
 * @since 14.12.2019
 */
public final class MultiplyOperationEvaluator implements NumberOperationEvaluator {

	@Override
	public Object evaluateNumbers(final List<Number> operandValues) {
		return operandValues.stream().mapToDouble(Number::doubleValue).reduce((a, b) -> a * b)
				.orElseThrow(() -> new IllegalArgumentException("empty list of operands passed to multiply operation"));
	}
}
