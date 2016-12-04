package net.will_co21.format.json;

public class JsonLongSerializable implements IJsonSerializable {
	protected final JsonOptions options;
	protected final long value;

	public JsonLongSerializable(long value, JsonOptions options)
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
