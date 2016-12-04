package net.will_co21.format.json;

public class JsonBooleanSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final boolean value;

	public JsonBooleanSerializable(boolean value, JsonOptions options)
	{
		this.value = value;
		this.options = options;
	}

	@Override
	public String toJson()
	{
		return (this.value) ? "true" : "false";
	}

	@Override
	public String toJson(int indent)
	{
		return toJson();
	}
}
