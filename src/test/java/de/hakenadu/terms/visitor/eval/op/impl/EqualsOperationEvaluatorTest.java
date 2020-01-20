package de.hakenadu.terms.visitor.eval.op.impl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * unit tests for the {@link EqualsOperationEvaluator}
 * 
 * @author Manuel Seiche
 * @since 14.12.2019
 */
public final class EqualsOperationEvaluatorTest {

	@Test
	public void testEvaluateNumbers() {
		final Object result = new EqualsOperationEvaluator().evaluate(Arrays.asList(6, 6, 6));
		assertTrue(result instanceof Boolean, "result not a boolean");
		assertTrue((Boolean) result, "wrong result");
	}

	@Test
	public void testEvaluateStrings() {
		final Object result = new EqualsOperationEvaluator().evaluate(Arrays.asList("A", "A", "A"));
		assertTrue(result instanceof Boolean, "result not a boolean");
		assertTrue((Boolean) result, "wrong result");
	}
}
