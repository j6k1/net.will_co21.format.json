package net.will_co21.format.json;

public class JsonDoubleSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final double value;

	public JsonDoubleSerializable(double value, JsonOptions options)
	{
		if(options == null)
			throw new TypeOfNullableNotAllowedException("The value was passed of the option to the constructor of JsonDoubleSerializable is null.");

		this.value = value;
		this.options = options;
	}

	@Override
	public String toJson()
	{
		return Double.toString(this.value);
	}

	@Override
	public String toJson(int indent)
	{
		return toJson();
	}
}
