package de.hakenadu.terms.gson.converter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public final class StringConstantValueConverter implements ConstantValueConverter<String> {

	@Override
	public JsonElement toJson(final String value) {
		return new JsonPrimitive(value);
	}

	@Override
	public String fromJson(final JsonElement jsonElement) {
		return jsonElement.getAsJsonPrimitive().getAsString();
	}

	@Override
	public String getTypeName() {
		return "string";
	}

	@Override
	public Class<String> getTypeClass() {
		return String.class;
	}
}
