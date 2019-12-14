package de.hakenadu.terms.visitor.eval.op.impl;

import java.util.List;

import de.hakenadu.terms.visitor.eval.op.NumberOperationEvaluator;

/**
 * evaluates the multiplication operation
 * 
 * @author Manuel Seiche
 * @since 14.12.2019
 */
public final class MultiplyOperationEvaluator extends NumberOperationEvaluator {

	@Override
	protected Object evaluateNumbers(final List<Number> operandValues) {
		return operandValues.stream().mapToDouble(Number::doubleValue).reduce((a, b) -> a * b).orElse(0);
	}
}
