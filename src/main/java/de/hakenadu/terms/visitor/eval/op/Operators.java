package de.hakenadu.terms.visitor.eval.op;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import de.hakenadu.terms.visitor.eval.op.impl.AddOperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.impl.EqualsOperationEvaluator;
import de.hakenadu.terms.visitor.eval.op.impl.MultiplyOperationEvaluator;
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
	public static final String SUBTRACT = "subtract";
	public static final String MULTIPLY = "multiply";
	public static final String EQUALS = "equals";

	public static final Map<String, OperationEvaluator> DEFAULTS = Collections
			.unmodifiableMap(new HashMap<String, OperationEvaluator>() {

				private static final long serialVersionUID = 1337L;

				{
					put(ADD, new AddOperationEvaluator());
					put(SUBTRACT, new SubtractOperationEvaluator());
					put(MULTIPLY, new MultiplyOperationEvaluator());
					put(EQUALS, new EqualsOperationEvaluator());
				}

			});

	private Operators() {
		// not instantiated
	}
}
