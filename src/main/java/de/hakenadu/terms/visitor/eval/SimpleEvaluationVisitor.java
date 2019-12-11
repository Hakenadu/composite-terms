package de.hakenadu.terms.visitor.eval;

import java.util.HashMap;
import java.util.Map;

import de.hakenadu.terms.Variable;

public class SimpleEvaluationVisitor extends EvaluationVisitor {

	private final Map<String, Object> variableValues = new HashMap<>();

	@Override
	public void visit(final Variable variable, final EvaluationContext context) {
		context.setValue(variableValues.get(variable.getName()));
	}

	public final Map<String, Object> getVariableValues() {
		return variableValues;
	}
}
