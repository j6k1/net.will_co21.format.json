package net.will_co21.format.json;

public class JsonDoubleSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final double value;

	public JsonDoubleSerializable(double value, JsonOptions options)
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
