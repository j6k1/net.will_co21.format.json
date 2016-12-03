package net.will_co21.format.json;

import java.util.Objects;

public class JsonNull extends JsonValue {

	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return null;
	}

	public boolean equals(JsonNull o)
	{
		return true;
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(null);
	}
}
