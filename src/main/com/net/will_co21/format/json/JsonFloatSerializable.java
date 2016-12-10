package net.will_co21.format.json;

public class JsonFloatSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final float value;

	public JsonFloatSerializable(float value, JsonOptions options)
	{
		this.value = value;
		this.options = options;
	}

	@Override
	public String toJson()
	{
		return this.value + "";
	}

	@Override
	public String toJson(int indent)
	{
		return toJson();
	}
}