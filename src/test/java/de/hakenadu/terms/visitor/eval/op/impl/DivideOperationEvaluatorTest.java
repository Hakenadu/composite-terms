package de.hakenadu.terms.visitor.eval.op.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * unit tests for the {@link DivideOperationEvaluator}
 * 
 * @author Manuel Seiche
 * @since 21.01.2020
 */
public final class DivideOperationEvaluatorTest {

	@Test
	public void testEvaluate() {
		final Object result = new DivideOperationEvaluator().evaluate(Arrays.asList(30, 6));
		assertTrue(result instanceof Number, "result not a number");
		assertEquals(5, ((Number) result).intValue(), "wrong result of division");
	}
}
