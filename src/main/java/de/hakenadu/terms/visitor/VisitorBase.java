package de.hakenadu.terms.visitor;

import de.hakenadu.terms.Constant;
import de.hakenadu.terms.Operation;
import de.hakenadu.terms.Term;
import de.hakenadu.terms.Variable;

public abstract class VisitorBase<C> implements Visitor<C> {

	@Override
	public void visit(final Term term, final C context) {
		throw new UnsupportedOperationException("no handling for term of type " + term.getClass().getName());
	}

	@Override
	public void visit(final Operation operation, final C context) {
		visit(((Term) operation), context);
	}

	@Override
	public void visit(final Constant constant, final C context) {
		visit(((Term) constant), context);
	}

	@Override
	public void visit(final Variable variable, final C context) {
		visit(((Term) variable), context);
	}
}
