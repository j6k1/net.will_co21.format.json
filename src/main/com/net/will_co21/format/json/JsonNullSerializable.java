package net.will_co21.format.json;

public class JsonNullSerializable {
	protected final JsonOptions options;

	public JsonNullSerializable(JsonOptions options)
	{
		this.options = options;
	}

	public String toJson()
	{
		return "null";
	}
}
