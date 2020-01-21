package de.hakenadu.terms.visitor.eval.op.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * unit tests for the {@link GreaterThanOrEqualOperationEvaluator}
 * 
 * @author Manuel Seiche
 * @since 21.01.2020
 */
public final class GreaterThanOrEqualOperationEvaluatorTest {

	@Test
	public void testEvaluate() {
		final Object expectedTrueResult = new GreaterThanOrEqualOperationEvaluator().evaluate(Arrays.asList(30, 6));
		assertTrue(expectedTrueResult instanceof Boolean, "result not a Boolean");
		assertEquals(Boolean.TRUE, expectedTrueResult, "wrong result of 30 >= 6");

		final Object expectedAlsoTrueResult = new GreaterThanOrEqualOperationEvaluator()
				.evaluate(Arrays.asList(30, 30));
		assertEquals(Boolean.TRUE, expectedAlsoTrueResult, "wrong result of 30 >= 30");

		final Object expectedFalseResult = new GreaterThanOrEqualOperationEvaluator().evaluate(Arrays.asList(30, 31));
		assertEquals(Boolean.FALSE, expectedFalseResult, "wrong result of 30 >= 31");
	}
}
