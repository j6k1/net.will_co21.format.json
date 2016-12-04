package net.will_co21.format.json;

public class JsonNullSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;

	public JsonNullSerializable(JsonOptions options)
	{
		this.options = options;
	}

	@Override
	public String toJson()
	{
		return "null";
	}

	@Override
	public String toJson(int indent)
	{
		return toJson();
	}
}
