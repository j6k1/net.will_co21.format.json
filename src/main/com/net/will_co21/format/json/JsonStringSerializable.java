package net.will_co21.format.json;

public class JsonStringSerializable implements IJsonSerializable {
	protected final JsonOptions options;
	protected final String value;

	public JsonStringSerializable(String value, JsonOptions options)
	{
		this.value = value;
		this.options = options;
	}

	@Override
	public String toJson()
	{
		return null;
	}
}
