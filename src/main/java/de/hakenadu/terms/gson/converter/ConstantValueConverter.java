package de.hakenadu.terms.gson.converter;

import com.google.gson.JsonElement;

public interface ConstantValueConverter<T> {

	String getTypeName();

	Class<T> getTypeClass();

	JsonElement toJson(T t);

	T fromJson(JsonElement jsonElement);
}
