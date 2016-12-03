package net.will_co21.format.json;

public class JsonDoubleSerializable {
	protected final JsonOptions options;
	protected final double value;

	public JsonDoubleSerializable(double value, JsonOptions options)
	{
		this.value = value;
		this.options = options;
	}

	public String toJson()
	{
		return null;
	}
}
