package net.will_co21.format.json;

import java.util.Optional;

public class JsonBoolean extends JsonValue {
	protected final boolean value;

	public JsonBoolean(boolean value)
	{
		this.value = value;
	}

	@Override
	public boolean getBoolean() {
		return this.value;
	}

	@Override
	public Optional<Boolean> getOptionalBoolean() {
		return Optional.of(getBoolean());
	}

	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return new JsonBooleanSerializable(this.value, options);
	}

	public boolean equals(JsonBoolean o)
	{
		return this.value == o.value;
	}

	@Override
	public int hashCode()
	{
		return Boolean.hashCode(this.value);
	}
}
