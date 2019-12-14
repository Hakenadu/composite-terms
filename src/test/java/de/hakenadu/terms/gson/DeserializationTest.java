package de.hakenadu.terms.gson;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.hakenadu.terms.Term;
import de.hakenadu.terms.visitor.eval.EvaluationContext;
import de.hakenadu.terms.visitor.eval.SimpleEvaluationVisitor;

/**
 * unit tests for the deserialization of terms from json
 */
public final class DeserializationTest {

	private static SimpleEvaluationVisitor evaluationVisitor;

	@BeforeAll
	public static void beforeAll() {
		evaluationVisitor = new SimpleEvaluationVisitor();
		evaluationVisitor.getVariableValues().put("x", -163);
	}

	/**
	 * <pre>
	 * t = 1337 - (500 + x)
	 * x = -163 => t = 1000
	 * </pre>
	 */
	@Test
	public void testDeserializeExample01() throws IOException {
		final Term term;
		try (final InputStreamReader reader = new InputStreamReader(
				getClass().getResourceAsStream("/terms/example-01.json"))) {
			term = TermsGson.createGson().fromJson(reader, Term.class);
		}
		assertNotNull(term, "no term deserialized");

		final EvaluationContext context = new EvaluationContext();
		term.accept(evaluationVisitor, context);

		final Object result = context.getValue();
		assertNotNull(result, "no result from context");
		assertTrue(result instanceof Number, "result is not an instance of number");

		assertEquals(1000, ((Number) result).intValue(), "wrong value resulted from term evaluation");
	}
}
