package net.will_co21.format.json;

public class JsonFloatSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final float value;

	public JsonFloatSerializable(float value, JsonOptions options)
	{
		if(options == null)
			throw new TypeOfNullableNotAllowedException("The value was passed of the option to the constructor of JsonFloatSerializable is null.");

		this.value = value;
		this.options = options;
	}

	@Override
	public String toJson()
	{
		return Float.toString(this.value);
	}

	@Override
	public String toPrettyJson(int indent)
	{
		return toJson();
	}
}
