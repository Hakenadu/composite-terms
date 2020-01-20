package de.hakenadu.terms.gson;

import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import de.hakenadu.terms.Constant;
import de.hakenadu.terms.gson.converter.BooleanConstantValueConverter;
import de.hakenadu.terms.gson.converter.ConstantValueConverter;
import de.hakenadu.terms.gson.converter.NumberConstantValueConverter;
import de.hakenadu.terms.gson.converter.StringConstantValueConverter;

public class ConstantTypeHierarchyAdapter implements JsonSerializer<Constant>, JsonDeserializer<Constant> {

	private final List<ConstantValueConverter<?>> valueConverters = new LinkedList<>();

	public ConstantTypeHierarchyAdapter() {
		addDefaultValueConverters();
	}

	protected void addDefaultValueConverters() {
		getValueConverters().add(new BooleanConstantValueConverter());
		getValueConverters().add(new NumberConstantValueConverter());
		getValueConverters().add(new StringConstantValueConverter());
	}

	public ConstantTypeHierarchyAdapter withValueConverter(final ConstantValueConverter<?> valueConverter) {
		Objects.requireNonNull(valueConverter, "no valueConverter passed");

		if (valueConverters.stream().map(ConstantValueConverter::getTypeName)
				.anyMatch(valueConverter.getTypeName()::equals)) {
			throw new IllegalArgumentException("duplicate TypeName \"" + valueConverter.getTypeName() + '\"');
		}

		this.valueConverters.add(valueConverter);

		return this;
	}

	/**
	 * @return {@link #valueConverters}
	 */
	protected final List<ConstantValueConverter<?>> getValueConverters() {
		return valueConverters;
	}

	@Override
	public final Constant deserialize(final JsonElement json, final Type typeOfT,
			final JsonDeserializationContext context) {
		if (!json.isJsonObject()) {
			throw new JsonParseException("passed Constant-json is no JsonObject: " + json);
		}

		final JsonObject jsonObject = json.getAsJsonObject();

		final String datatype = Optional.of(jsonObject.get("datatype")).filter(JsonElement::isJsonPrimitive)
				.map(JsonElement::getAsJsonPrimitive).filter(JsonPrimitive::isString).map(JsonPrimitive::getAsString)
				.orElseThrow(() -> new JsonParseException("invalid or missing \"datatype\" in object: " + jsonObject));

		final ConstantValueConverter<?> valueConverter = valueConverters.stream()
				.filter(v -> datatype.equals(v.getTypeName())).findFirst()
				.orElseThrow(() -> new JsonParseException("no ValueConverter for \"datatype\": " + datatype));

		final Object value = Optional.of(jsonObject.get("value")).map(valueConverter::fromJson)
				.orElseThrow(() -> new JsonParseException("invalid or missing \"value\": " + jsonObject));

		return new Constant(value);
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final JsonElement serialize(final Constant src, final Type typeOfSrc,
			final JsonSerializationContext context) {
		final JsonObject jsonObject = new JsonObject();

		jsonObject.addProperty("type", "constant");

		final Object value = src.getValue();
		if (value == null) {
			throw new JsonParseException("no value in Constant");
		}

		final ConstantValueConverter valueConverter = valueConverters.stream()
				.filter(v -> v.getTypeClass().isAssignableFrom(value.getClass())).findFirst()
				.orElseThrow(() -> new JsonParseException("no ValueConverter for type " + value.getClass().getName()));

		jsonObject.addProperty("datatype", valueConverter.getTypeName());
		jsonObject.add("value", valueConverter.toJson(value));

		return jsonObject;
	}
}
