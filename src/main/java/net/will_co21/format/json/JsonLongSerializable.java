package net.will_co21.format.json;

public class JsonLongSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final long value;

	public JsonLongSerializable(long value, JsonOptions options)
	{
		if(options == null)
			throw new TypeOfNullableNotAllowedException("The value was passed of the option to the constructor of JsonLongSerializable is null.");

		this.value = value;
		this.options = options;
	}

	@Override
	public String toJson()
	{
		return this.value + "";
	}

	@Override
	public String toPrettyJson(int indent)
	{
		return toJson();
	}
}
