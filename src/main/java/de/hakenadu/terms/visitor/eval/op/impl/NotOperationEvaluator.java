package de.hakenadu.terms.visitor.eval.op.impl;

import java.util.List;

import de.hakenadu.terms.visitor.eval.op.OperationEvaluator;

/**
 * evaluates the not operation
 * 
 * @author Manuel Seiche
 * @since 20.01.2020
 */
public final class NotOperationEvaluator implements OperationEvaluator {

	@Override
	public Boolean evaluate(final List<Object> operandValues) {
		if (operandValues.size() > 1) {
			throw new IllegalArgumentException("more than one operand passed to not operation");
		}

		return Boolean.FALSE.equals(operandValues.get(0));
	}
}
