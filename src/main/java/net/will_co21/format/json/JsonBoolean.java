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

	public IPrettyJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return new JsonBooleanSerializable(this.value, options);
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof JsonBoolean)) return false;
		return this.value == ((JsonBoolean)o).value;
	}

	@Override
	public int hashCode()
	{
		return Boolean.hashCode(this.value);
	}
}
