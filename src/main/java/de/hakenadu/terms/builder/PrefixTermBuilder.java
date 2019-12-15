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

	@Override
	public Term build() {
		if (operations.size() != 1) {
			throw new IllegalStateException("unfinished operation: " + operations.peek().getOperator());
		}

		return operations.peek().getOperands().get(0);
	}

	public PrefixTermBuilder variable(final String name) {
		addOperand(new Variable(name));
		
    return this;
	}

	public PrefixTermBuilder constant(final Object value) {
		addOperand(new Constant(value));

    return this;
	}

	public PrefixTermBuilder beginOperation(final String operator) {
    final Operation operation = new Operation(operator, new ArrayList<>());

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
    operations.push(
        new Operation("foo" /* does not matter */, new ArrayList<>()));
	}

  private void addOperand(final Term operand) {
    Objects.requireNonNull(operand, "no operand passed");

    if (operations.size() == 1 && ! operations.peek().getOperands().isEmpty()) {
      throw new IllegalStateException("term with more than single root");
    }
    operations.peek().getOperands().add(operand);
  }
  
}