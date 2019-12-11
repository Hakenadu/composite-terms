package de.hakenadu.terms.gson.converter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public final class NumberConstantValueConverter implements ConstantValueConverter<Number> {

	@Override
	public JsonElement toJson(final Number value) {
		return new JsonPrimitive(value);
	}

	@Override
	public Number fromJson(final JsonElement jsonElement) {
		return jsonElement.getAsJsonPrimitive().getAsNumber();
	}

	@Override
	public String getTypeName() {
		return "number";
	}

	@Override
	public Class<Number> getTypeClass() {
		return Number.class;
	}
}
