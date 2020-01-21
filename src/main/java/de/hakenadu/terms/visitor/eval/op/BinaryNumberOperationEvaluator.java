package de.hakenadu.terms.visitor.eval.op;

import java.util.List;

/**
 * convenience interface for {@link NumberOperationEvaluator} implementations
 * which operate on exactly two operands.
 * 
 * @author Manuel Seiche
 * @since 21.01.2020
 */
public interface BinaryNumberOperationEvaluator extends NumberOperationEvaluator {

	Object evaluateNumbers(Number firstOperand, Number secondOperand);

	@Override
	default Object evaluateNumbers(final List<Number> operandValues) {
		if (operandValues.size() < 2) {
			throw new IllegalArgumentException("only one operand passed to binary operation");
		}

		if (operandValues.size() > 2) {
			throw new IllegalArgumentException("more than two operands passed to binary operation");
		}

		return evaluateNumbers(operandValues.get(0), operandValues.get(1));
	}
}
