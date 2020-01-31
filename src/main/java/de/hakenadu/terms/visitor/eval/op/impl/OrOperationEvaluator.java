package de.hakenadu.terms.visitor.eval.op.impl;

import java.util.List;

import de.hakenadu.terms.visitor.eval.op.OperationEvaluator;

/**
 * evaluates the or operation
 * 
 * @author Manuel Seiche
 * @since 31.01.2020
 */
public final class OrOperationEvaluator implements OperationEvaluator {

	@Override
	public Boolean evaluate(final List<Object> operandValues) {
		return operandValues.stream().map(Boolean.TRUE::equals).reduce(Boolean::logicalOr)
				.orElseThrow(() -> new IllegalArgumentException("empty list of operands passed to or operation"));
	}
}
