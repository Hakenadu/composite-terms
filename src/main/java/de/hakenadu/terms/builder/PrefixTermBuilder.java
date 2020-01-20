package de.hakenadu.terms.builder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import de.hakenadu.terms.Constant;
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

	private static interface TermProvider {
		Term provideTerm();
	}

	private static final class TermProviderImpl implements TermProvider {
		private final Term term;

		public TermProviderImpl(final Term term) {
			super();
			Objects.requireNonNull(term, "no term passed to provider");
			this.term = term;
		}

		@Override
		public Term provideTerm() {
			return term;
		}
	}

	private static final class OperationProvider implements TermProvider {

		/**
		 * {@link Operation#getOperator()}
		 */
		private final String operator;

		/**
		 * {@link Operation#getOperands()}
		 */
		private final List<TermProvider> operands;

		/**
		 * @param operator {@link #operator}
		 * @param operands {@link #operands}
		 */
		public OperationProvider(final String operator, final List<TermProvider> operands) {
			super();

			Objects.requireNonNull(operator, "no operator passed to provider");
			Objects.requireNonNull(operands, "no operands passed to provider");

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
		public List<TermProvider> getOperands() {
			return operands;
		}

		/**
		 * @return created {@link Operation}
		 */
		@Override
		public Operation provideTerm() {
			if (operands.isEmpty()) {
				throw new IllegalStateException("empty list of operands");
			}
			return new Operation(operator,
					operands.stream().map(TermProvider::provideTerm).collect(Collectors.toList()));
		}
	}

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
	private Deque<OperationProvider> operations = new ArrayDeque<>();

	@Override
	public Term build() {
		if (operations.size() != 1) {
			throw new IllegalStateException("unfinished operation: " + operations.peek().getOperator());
		}

		return operations.peek().getOperands().get(0).provideTerm();
	}

	public PrefixTermBuilder variable(final String name) {
		addOperand(new TermProviderImpl(new Variable(name)));

		return this;
	}

	public PrefixTermBuilder constant(final Object value) {
		addOperand(new TermProviderImpl(new Constant(value)));

		return this;
	}

	public PrefixTermBuilder beginOperation(final String operator) {
		final OperationProvider operation = new OperationProvider(operator, new ArrayList<>());

		addOperand(operation);
		operations.push(operation);

		return this;
	}

	public PrefixTermBuilder endOperation() {
		if (operations.size() <= 1) {
			throw new IllegalStateException("no operation on deque");
		}
		operations.pop();
		return this;
	}

	private PrefixTermBuilder() {
		operations.push(new OperationProvider("foo" /* does not matter */, new ArrayList<>()));
	}

	private void addOperand(final TermProvider operand) {
		Objects.requireNonNull(operand, "no operand passed");

		if (operations.size() == 1 && !operations.peek().getOperands().isEmpty()) {
			throw new IllegalStateException("term with more than single root");
		}

		operations.peek().getOperands().add(operand);
	}
}