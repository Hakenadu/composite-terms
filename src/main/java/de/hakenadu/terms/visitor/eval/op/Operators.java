package de.hakenadu.terms.visitor.eval.op;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.hakenadu.terms.visitor.eval.op.impl.AddOperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.impl.AndOperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.impl.DivideOperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.impl.EqualsOperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.impl.GreaterThanOperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.impl.GreaterThanOrEqualOperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.impl.LessThanOperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.impl.LessThanOrEqualOperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.impl.MultiplyOperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.impl.NotOperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.impl.OrOperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.impl.SubtractOperationEvaluator;

/**
 * contains all default implementations of {@link OperationEvaluator
 * OperationEvaluators}
 * 
 * @author Manuel Seiche
 * @since 14.12.2019
 */
public final class Operators {

	public static final String ADD = "add";
	public static final String AND = "and";
	public static final String DIVIDE = "divide";
	public static final String EQUALS = "equals";
	public static final String GREATER_THAN = "greaterThan";
	public static final String GREATER_THAN_OR_EQUAL = "greaterThanOrEqual";
	public static final String LESS_THAN = "lessThan";
	public static final String LESS_THAN_OR_EQUAL = "lessThanOrEqual";
	public static final String MULTIPLY = "multiply";
	public static final String NOT = "not";
	public static final String OR = "or";
	public static final String SUBTRACT = "subtract";

	public static final Map<String, OperationEvaluator> DEFAULTS = Collections
			.unmodifiableMap(new HashMap<String, OperationEvaluator>() {

				private static final long serialVersionUID = 1337L;

				{
					put(ADD, new AddOperationEvaluator());
					put(AND, new AndOperationEvaluator());
					put(DIVIDE, new DivideOperationEvaluator());
					put(EQUALS, new EqualsOperationEvaluator());
					put(GREATER_THAN, new GreaterThanOperationEvaluator());
					put(GREATER_THAN_OR_EQUAL, new GreaterThanOrEqualOperationEvaluator());
					put(LESS_THAN, new LessThanOperationEvaluator());
					put(LESS_THAN_OR_EQUAL, new LessThanOrEqualOperationEvaluator());
					put(MULTIPLY, new MultiplyOperationEvaluator());
					put(NOT, new NotOperationEvaluator());
					put(OR, new OrOperationEvaluator());
					put(SUBTRACT, new SubtractOperationEvaluator());
				}

			});

	private Operators() {
		// not instantiated
	}
}
