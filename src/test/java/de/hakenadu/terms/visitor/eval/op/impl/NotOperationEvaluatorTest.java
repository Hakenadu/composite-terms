package de.hakenadu.terms.visitor.eval.op.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * unit tests for the {@link NotOperationEvaluator}
 * 
 * @author Manuel Seiche
 * @since 21.01.2020
 */
public final class NotOperationEvaluatorTest {

	@Test
	public void testEvaluate() {
		final Boolean expectedFalseResult = new NotOperationEvaluator().evaluate(Arrays.asList(Boolean.TRUE));
		assertFalse(expectedFalseResult, "wrong result of NOT true");

		final Boolean expectedTrueResult = new NotOperationEvaluator().evaluate(Arrays.asList(Boolean.FALSE));
		assertTrue(expectedTrueResult, "wrong result of NOT false");
	}
}
