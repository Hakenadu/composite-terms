package de.hakenadu.terms.visitor.eval.op;

import java.util.List;

@FunctionalInterface
public interface OperationEvaluator {

	Object evaluate(List<Object> operandValues);
}
