package net.will_co21.format.json;

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

	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return null;
	}
}
