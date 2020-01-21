package de.hakenadu.terms.visitor.eval.op;

import java.util.List;

/**
 * convenience interface for {@link NumberOperationEvaluator} implementations
 * which operate on exactly one operand.
 * 
 * @author Manuel Seiche
 * @since 21.01.2020
 */
public interface UnaryNumberOperationEvaluator extends NumberOperationEvaluator {

	Object evaluateNumber(Number operand);

	@Override
	default Object evaluateNumbers(final List<Number> operandValues) {
		if (operandValues.size() > 1) {
			throw new IllegalArgumentException("more than one operand passed to unary operation");
		}

		return evaluateNumber(operandValues.get(0));
	}
}
