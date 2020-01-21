package de.hakenadu.terms.visitor.eval.op.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * unit tests for the {@link LessThanOperationEvaluator}
 * 
 * @author Manuel Seiche
 * @since 21.01.2020
 */
public final class LessThanOperationEvaluatorTest {

	@Test
	public void testEvaluate() {
		final Object expectedTrueResult = new LessThanOperationEvaluator().evaluate(Arrays.asList(6, 30));
		assertTrue(expectedTrueResult instanceof Boolean, "result not a Boolean");
		assertEquals(Boolean.TRUE, expectedTrueResult, "wrong result of 6 < 30");

		final Object expectedFalseResult = new LessThanOperationEvaluator().evaluate(Arrays.asList(30, 30));
		assertEquals(Boolean.FALSE, expectedFalseResult, "wrong result of 30 < 30");
	}
}
