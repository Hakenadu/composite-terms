package de.hakenadu.terms.visitor.eval.op.impl;

import java.util.List;

import de.hakenadu.terms.visitor.eval.op.OperationEvaluator;

/**
 * evaluates the and operation
 * 
 * @author Manuel Seiche
 * @since 20.01.2020
 */
public final class AndOperationEvaluator implements OperationEvaluator {

	@Override
	public Boolean evaluate(final List<Object> operandValues) {
		return operandValues.stream().map(Boolean.TRUE::equals).reduce(Boolean::logicalAnd)
				.orElseThrow(() -> new IllegalArgumentException("empty list of operands passed to and operation"));
	}
}
