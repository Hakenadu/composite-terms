package de.hakenadu.terms.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.hakenadu.terms.Constant;
import de.hakenadu.terms.Operation;
import de.hakenadu.terms.Term;
import de.hakenadu.terms.Variable;

/**
 * Provides utilities for creating {@link Gson} instances
 * 
 * @author Manuel Seiche
 * @since 11.12.2019
 */
public final class TermsGson {

	public static Gson createGson() {
		return createGsonBuilder().create();
	}

	public static GsonBuilder createGsonBuilder() {
		return createGsonBuilder(new ConstantTypeHierarchyAdapter());
	}

	public static Gson createGson(final ConstantTypeHierarchyAdapter constantTypeHierarchyAdapter) {
		return createGsonBuilder(constantTypeHierarchyAdapter).create();
	}

	public static GsonBuilder createGsonBuilder(final ConstantTypeHierarchyAdapter constantTypeHierarchyAdapter) {
		return initializeGsonBuilder(new GsonBuilder(), constantTypeHierarchyAdapter);
	}

	public static GsonBuilder initializeGsonBuilder(final GsonBuilder gsonBuilder,
			final ConstantTypeHierarchyAdapter constantTypeHierarchyAdapter) {
		// polymorphic support
		final RuntimeTypeAdapterFactory<Term> termTypeAdapterFactory = RuntimeTypeAdapterFactory.of(Term.class);
		termTypeAdapterFactory.registerSubtype(Constant.class, "constant");
		termTypeAdapterFactory.registerSubtype(Variable.class, "variable");
		termTypeAdapterFactory.registerSubtype(Operation.class, "operation");

		gsonBuilder.registerTypeAdapterFactory(termTypeAdapterFactory);
		gsonBuilder.registerTypeHierarchyAdapter(Constant.class, constantTypeHierarchyAdapter);
		return gsonBuilder;
	}

	private TermsGson() {
		// not instantiated
	}
}
