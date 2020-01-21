package de.hakenadu.terms.visitor.eval.op.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * unit tests for the {@link AndOperationEvaluator}
 * 
 * @author Manuel Seiche
 * @since 21.01.2020
 */
public final class AndOperationEvaluatorTest {

	@Test
	public void testEvaluate() {
		final Boolean expectedFalseResult = new AndOperationEvaluator()
				.evaluate(Arrays.asList(Boolean.TRUE, Boolean.FALSE));
		assertFalse(expectedFalseResult, "wrong result of true AND false");

		final Boolean expectedTrueResult = new AndOperationEvaluator()
				.evaluate(Arrays.asList(Boolean.TRUE, Boolean.TRUE));
		assertTrue(expectedTrueResult, "wrong result of true AND true");
	}
}
