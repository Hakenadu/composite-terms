package de.hakenadu.terms.visitor.eval;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import de.hakenadu.terms.Constant;
import de.hakenadu.terms.Operation;
import de.hakenadu.terms.Term;
import de.hakenadu.terms.visitor.VisitorBase;
import de.hakenadu.terms.visitor.eval.op.OperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.Operators;

public abstract class EvaluationVisitor extends VisitorBase<EvaluationContext> {

	private final Map<String, OperationEvaluator> operationEvaluators = new LinkedHashMap<>(Operators.DEFAULTS);

	public final Map<String, OperationEvaluator> getOperationEvaluators() {
		return operationEvaluators;
	}

	@Override
	public void visit(final Operation operation, final EvaluationContext context) {
		final List<Object> operandValues = new ArrayList<>();

		for (final Term operand : operation.getOperands()) {
			final EvaluationContext nestedContext = new EvaluationContext();
			operand.accept(this, nestedContext);
			operandValues.add(nestedContext.getValue());
		}

		if (!operationEvaluators.containsKey(operation.getOperator())) {
			throw new UnsupportedOperationException("operator \"" + operation.getOperator() + "\" is not supported");
		}

		context.setValue(operationEvaluators.get(operation.getOperator()).evaluate(operandValues));
	}

	@Override
	public void visit(final Constant constant, final EvaluationContext context) {
		context.setValue(constant.getValue());
	}
}
