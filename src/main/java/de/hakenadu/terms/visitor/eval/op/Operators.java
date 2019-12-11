package de.hakenadu.terms.visitor.eval.op;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Operators {

	public static final String ADD = "add";
	public static final String SUBTRACT = "subtract";

	public static final Map<String, OperationEvaluator> DEFAULTS = Collections
			.unmodifiableMap(new HashMap<String, OperationEvaluator>() {

				private static final long serialVersionUID = 1337L;

				{
					put(ADD, new AddOperationEvaluator());
					put(SUBTRACT, new SubtractOperationEvaluator());
				}

			});

	private Operators() {
		// not instantiated
	}
}
