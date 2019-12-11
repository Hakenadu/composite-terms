package de.hakenadu.terms.visitor;

import de.hakenadu.terms.Constant;
import de.hakenadu.terms.Operation;
import de.hakenadu.terms.Term;
import de.hakenadu.terms.Variable;

/**
 * {@link Term Terms} are evaluated using a visitor pattern. This interface is
 * implemented by concrete visitors.
 * 
 * @author Manuel Seiche
 * @since 11.12.2019
 * @param <C> type of the context which is passed to every accept method
 */
public interface Visitor<C> {

	/**
	 * Fallback if none of the other visit-Methods is called
	 * 
	 * @param term    The visited {@link Term}
	 * @param context The Visitor-Context
	 */
	void visit(Term term, C context);

	/**
	 * This method is called by an instance of {@link Operation} which is visited by
	 * this {@link Visitor}.
	 * 
	 * @param operation The visited {@link Operation}
	 * @param context   The Visitor-Context
	 */
	void visit(Operation operation, C context);

	/**
	 * This method is called by an instance of {@link Constant} which is visited by
	 * this {@link Visitor}.
	 * 
	 * @param constant The visited {@link Constant}
	 * @param context  The Visitor-Context
	 */
	void visit(Constant constant, C context);

	/**
	 * This method is called by an instance of {@link Variable} which is visited by
	 * this {@link Visitor}.
	 * 
	 * @param variable The visited {@link Variable}
	 * @param context  The Visitor-Context
	 */
	void visit(Variable variable, C context);
}
