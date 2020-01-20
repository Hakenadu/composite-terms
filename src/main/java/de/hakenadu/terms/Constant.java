package de.hakenadu.terms;

import de.hakenadu.terms.visitor.Visitor;

public final class Constant implements LeafTerm {

	/**
	 * The name of this variable
	 */
	private final Object value;

	/**
	 * @param value {@link #value}
	 */
	public Constant(final Object value) {
		super();
		this.value = value;
	}

	/**
	 * @return {@link #value}
	 */
	public Object getValue() {
		return value;
	}

	@Override
	public <C> void accept(final Visitor<C> visitor, final C context) {
		visitor.visit(this, context);
	}
}
