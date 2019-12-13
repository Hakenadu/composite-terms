package de.hakenadu.terms.builder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Objects;

import de.hakenadu.terms.Constant;
import de.hakenadu.terms.LeafTerm;
import de.hakenadu.terms.Operation;
import de.hakenadu.terms.Term;
import de.hakenadu.terms.Variable;

/**
 * <code>
 * <pre>
 * // 1337 - (500 + x)
 * PrefixTermBuilder.create()
 * 	.beginOperation(Operators.SUBTRACT)
 * 	.constant(1337)
 * 	.beginOperation(Operators.ADD)
 * 	.constant(500)
 * 	.variable("x")
 * 	.endOperation()
 * 	.endOperation()
 * 	.build()
 * </pre>
 * </code>
 * 
 * @since 13.12.2019
 */
public final class PrefixTermBuilder implements TermBuilder {

	/**
	 * the {@link PrefixTermBuilder} is only instantiated by this method
	 * 
	 * @return a new instance of the {@link PrefixTermBuilder}
	 */
	public static PrefixTermBuilder create() {
		return new PrefixTermBuilder();
	}

	/**
	 * {@link Operation operations} are added on {@link #beginOperation(String)} and
	 * removed on {@link #endOperation()}
	 */
	private Deque<Operation> operations = new ArrayDeque<>();

	/**
	 * the {@link Term} which is returned on {@link #build()} (may also be a
	 * {@link LeafTerm})
	 */
	private Term result;

	@Override
	public Term build() {
		Objects.requireNonNull(result, "no term added to builder");

		if (!operations.isEmpty()) {
			throw new IllegalStateException("unfinished operation: " + operations.peek().getOperator());
		}

		return result;
	}

	public PrefixTermBuilder leaf(final LeafTerm leafTerm) {
		Objects.requireNonNull(leafTerm, "no LeafTerm passed");

		if (!operations.isEmpty()) {
			operations.peek().getOperands().add(leafTerm);
			return this;
		}

		if (result == null) {
			result = leafTerm;
			return this;
		}

		throw new IllegalStateException("no operation on deque but a LeafTerm was already added");
	}

	public PrefixTermBuilder variable(final String name) {
		return leaf(new Variable(name));
	}

	public PrefixTermBuilder constant(final Object value) {
		return leaf(new Constant(value));
	}

	public PrefixTermBuilder beginOperation(final String operator) {
		final Operation operation = new Operation(operator, new ArrayList<>());

		if (!operations.isEmpty()) {
			operations.peek().getOperands().add(operation);
		}
		operations.push(operation);

		return this;
	}

	public PrefixTermBuilder endOperation() {
		if (operations.isEmpty()) {
			throw new IllegalStateException("no operation on deque");
		}
		result = operations.pop();
		return this;
	}

	private PrefixTermBuilder() {
		// not instantiated
	}
}
