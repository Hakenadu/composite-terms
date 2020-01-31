package de.hakenadu.terms.visitor.eval.op.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * unit tests for the {@link OrOperationEvaluator}
 * 
 * @author Manuel Seiche
 * @since 31.01.2020
 */
public final class OrOperationEvaluatorTest {

	@Test
	public void testEvaluate() {
		final Boolean expectedFalseResult = new OrOperationEvaluator()
				.evaluate(Arrays.asList(Boolean.FALSE, Boolean.FALSE));
		assertFalse(expectedFalseResult, "wrong result of false OR false");

		final Boolean expectedTrueResult = new OrOperationEvaluator()
				.evaluate(Arrays.asList(Boolean.TRUE, Boolean.FALSE));
		assertTrue(expectedTrueResult, "wrong result of true OR false");
	}
}
