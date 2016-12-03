package net.will_co21.format.json;

public class JsonStringSerializable {
	protected final JsonOptions options;
	protected final String value;

	public JsonStringSerializable(String value, JsonOptions options)
	{
		this.value = value;
		this.options = options;
	}

	public String toJson()
	{
		return null;
	}
}
