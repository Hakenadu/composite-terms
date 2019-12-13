package de.hakenadu.terms;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import de.hakenadu.terms.visitor.Visitor;

/**
 * This class realizes the composite pattern as it contains multiple {@link Term
 * terms} which may as well be {@link Operation operations}
 * 
 * @author Manuel Seiche
 */
public final class Operation implements Term {

	/**
	 * The operator is applied to all associated {@link #operands}. It is not
	 * realized as enumeration to maintain extensibility.
	 */
	private final String operator;

	/**
	 * An {@link Operation} consists of multiple {@link Term terms} which
	 */
	private final List<Term> operands;

	/**
	 * @param operator {@link #operator}
	 * @param operands {@link #operands}
	 */
	public Operation(final String operator, final Term... operands) {
		this(operator, Arrays.asList(operands));
	}

	/**
	 * @param operator {@link #operator}
	 * @param operands {@link #operands}
	 */
	public Operation(final String operator, final List<Term> operands) {
		Objects.requireNonNull(operator, "no operator passed to constructor");
		Objects.requireNonNull(operands, "no operands passed to constructor");

		this.operator = operator;
		this.operands = operands;
	}

	/**
	 * @return {@link #operator}
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @return {@link #operands}
	 */
	public List<Term> getOperands() {
		return operands;
	}

	@Override
	public <C> void accept(final Visitor<C> visitor, final C context) {
		visitor.visit(this, context);
	}
}
