package net.will_co21.format.json;

import java.util.Optional;

public class JsonString extends JsonValue {
	protected final String value;

	public JsonString(String value)
	{
		if(value == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonString type constructor.");

		this.value = value;
	}

	@Override
	public String getString() {
		return this.value;
	}

	@Override
	public Optional<String> getOptionalString() {
		return Optional.of(getString());
	}

	public IPrettyJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return new JsonStringSerializable(this.value, options);
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof JsonString)) return false;
		else return this.value.equals(((JsonString)o).value);
	}

	@Override
	public int hashCode()
	{
		return this.value.hashCode();
	}

	@Override
	public String toString()
	{
		return (new JsonStringSerializable(this.value, new JsonOptions(new JsonOption[] {}))).toJson();
	}
}
