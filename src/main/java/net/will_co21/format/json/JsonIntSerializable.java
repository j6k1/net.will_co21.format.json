package net.will_co21.format.json;

public class JsonIntSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final int value;

	public JsonIntSerializable(int value, JsonOptions options)
	{
		if(options == null)
			throw new TypeOfNullableNotAllowedException("The value was passed of the option to the constructor of JsonIntSerializable is null.");

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
