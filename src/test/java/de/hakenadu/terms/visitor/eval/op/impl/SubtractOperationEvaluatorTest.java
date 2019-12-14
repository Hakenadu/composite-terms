package de.hakenadu.terms.visitor.eval.op.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * unit tests for the {@link SubtractOperationEvaluator}
 * 
 * @author Manuel Seiche
 * @since 14.12.2019
 */
public final class SubtractOperationEvaluatorTest {

	@Test
	public void testEvaluate() {
		final Object result = new SubtractOperationEvaluator().evaluate(Arrays.asList(7, 2, -1));
		assertTrue(result instanceof Number, "result not a number");
		assertEquals(6, ((Number) result).intValue(), "wrong result of subtraction");
	}
}
