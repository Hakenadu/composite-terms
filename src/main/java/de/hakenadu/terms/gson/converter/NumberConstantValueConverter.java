package de.hakenadu.terms.gson.converter;

import java.util.Optional;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public class NumberConstantValueConverter implements ConstantValueConverter<Number> {

	@Override
	public JsonElement toJson(final Number value) {
		return new JsonPrimitive(value);
	}

	@Override
	public Number fromJson(final JsonElement jsonElement) {
		return getAsNumber(jsonElement)
				.orElseThrow(() -> new JsonParseException("not a number member: " + jsonElement));
	}

	@Override
	public String getTypeName() {
		return "number";
	}

	@Override
	public Class<Number> getTypeClass() {
		return Number.class;
	}

	static final Optional<Number> getAsNumber(final JsonElement jsonElement) {
		return Optional.of(jsonElement)//
				.filter(JsonElement::isJsonPrimitive)//
				.map(JsonElement::getAsJsonPrimitive)//
				.filter(JsonPrimitive::isNumber)//
				.map(JsonPrimitive::getAsNumber);
	}
}
