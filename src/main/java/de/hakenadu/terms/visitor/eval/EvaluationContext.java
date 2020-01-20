package de.hakenadu.terms.visitor.eval;

public class EvaluationContext {

	private Object value;

	public final void setValue(final Object value) {
		this.value = value;
	}

	public final Object getValue() {
		return value;
	}
}
