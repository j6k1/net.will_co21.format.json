package net.will_co21.format.json;

public class JsonNull extends JsonValue {

	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return null;
	}
}
