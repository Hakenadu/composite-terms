package de.hakenadu.terms.gson.converter;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;

public final class IntegerConstantValueConverter implements ConstantValueConverter<Integer> {

	@Override
	public JsonElement toJson(final Integer value) {
		return new JsonPrimitive(value);
	}

	@Override
	public Integer fromJson(final JsonElement jsonElement) {
		return NumberConstantValueConverter.getAsNumber(jsonElement)//
				.map(Number::intValue)//
				.orElseThrow(() -> new JsonParseException("not a integer member: " + jsonElement));
	}

	@Override
	public String getTypeName() {
		return "integer";
	}

	@Override
	public Class<Integer> getTypeClass() {
		return Integer.class;
	}
}
