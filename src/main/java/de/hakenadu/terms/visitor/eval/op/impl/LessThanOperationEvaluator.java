package de.hakenadu.terms.visitor.eval.op.impl;

import de.hakenadu.terms.visitor.eval.op.BinaryNumberOperationEvaluator;

/**
 * evaluates the "less than" operation:
 * 
 * (firstOperand &lt; secondOperand)
 * 
 * @author Manuel Seiche
 * @since 21.01.2020
 */
public final class LessThanOperationEvaluator implements BinaryNumberOperationEvaluator {

	@Override
	public Boolean evaluateNumbers(final Number firstOperand, final Number secondOperand) {
		return Double.compare(firstOperand.doubleValue(), secondOperand.doubleValue()) < 0;
	}
}
