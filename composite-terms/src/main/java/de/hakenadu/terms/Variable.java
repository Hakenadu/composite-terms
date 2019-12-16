package de.hakenadu.terms;

import de.hakenadu.terms.visitor.Visitor;

public final class Variable implements LeafTerm {

	/**
	 * name of this variable
	 */
	private final String name;

	/**
	 * @param name {@link #name}
	 */
	public Variable(final String name) {
		super();
		this.name = name;
	}

	/**
	 * @return {@link #name}
	 */
	public String getName() {
		return name;
	}

	@Override
	public <C> void accept(final Visitor<C> visitor, final C context) {
		visitor.visit(this, context);
	}
}
