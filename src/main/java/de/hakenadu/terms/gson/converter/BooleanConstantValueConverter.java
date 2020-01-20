package de.hakenadu.terms.gson.converter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

public final class BooleanConstantValueConverter implements ConstantValueConverter<Boolean> {

	@Override
	public JsonElement toJson(final Boolean value) {
		return new JsonPrimitive(value);
	}

	@Override
	public Boolean fromJson(final JsonElement jsonElement) {
		return jsonElement.getAsJsonPrimitive().getAsBoolean();
	}

	@Override
	public String getTypeName() {
		return "boolean";
	}

	@Override
	public Class<Boolean> getTypeClass() {
		return Boolean.class;
	}
}
