package net.will_co21.format.json;

public class JsonBooleanSerializable {
	protected final JsonOptions options;
	protected final boolean value;

	public JsonBooleanSerializable(boolean value, JsonOptions options)
	{
		this.value = value;
		this.options = options;
	}

	public String toJson()
	{
		return null;
	}
}
