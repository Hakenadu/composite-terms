package de.hakenadu.terms.visitor.eval.op.impl;

import java.util.List;
import java.util.Objects;

import de.hakenadu.terms.visitor.eval.op.OperationEvaluator;

/**
 * Returns {@link Boolean#TRUE} if all passed operands are equal. Equality is
 * checked by {@link Objects#equals(Object, Object)}
 * 
 * @author Manuel Seiche
 * @since 14.12.2019
 */
public final class EqualsOperationEvaluator implements OperationEvaluator {

	@Override
	public Boolean evaluate(final List<Object> operandValues) {
		final Object reference = operandValues.get(0);
		return operandValues.stream()//
				.skip(1)//
				.map(o -> Objects.equals(o, reference))//
				.reduce(Boolean::logicalAnd)//
				.orElse(true);
	}
}
