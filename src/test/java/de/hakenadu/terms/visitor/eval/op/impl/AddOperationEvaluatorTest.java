package de.hakenadu.terms.visitor.eval.op.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * unit tests for the {@link AddOperationEvaluator}
 * 
 * @author Manuel Seiche
 * @since 14.12.2019
 */
public final class AddOperationEvaluatorTest {

	@Test
	public void testEvaluate() {
		final Object result = new AddOperationEvaluator().evaluate(Arrays.asList(1, 3, 3, 7));
		assertTrue(result instanceof Number, "result not a number");
		assertEquals(14, ((Number) result).intValue(), "wrong result of addition");
	}
}
