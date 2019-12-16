package de.hakenadu.terms.gson.converter;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public final class DoubleConstantValueConverter implements ConstantValueConverter<Double> {

	@Override
	public JsonElement toJson(final Double value) {
		return new JsonPrimitive(value);
	}

	@Override
	public Double fromJson(final JsonElement jsonElement) {
		return NumberConstantValueConverter.getAsNumber(jsonElement)//
				.map(Number::doubleValue)//
				.orElseThrow(() -> new JsonParseException("not a double member: " + jsonElement));
	}

	@Override
	public String getTypeName() {
		return "double";
	}

	@Override
	public Class<Double> getTypeClass() {
		return Double.class;
	}
}
