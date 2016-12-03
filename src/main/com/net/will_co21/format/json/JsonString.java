package net.will_co21.format.json;

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

	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return null;
	}
}
