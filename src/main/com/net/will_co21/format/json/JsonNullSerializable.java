package net.will_co21.format.json;

public class JsonNullSerializable implements IJsonSerializable {
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
}
