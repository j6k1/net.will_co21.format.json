package net.will_co21.format.json;

public class JsonIntSerializable implements IJsonSerializable {
	protected final JsonOptions options;
	protected final int value;

	public JsonIntSerializable(int value, JsonOptions options)
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
