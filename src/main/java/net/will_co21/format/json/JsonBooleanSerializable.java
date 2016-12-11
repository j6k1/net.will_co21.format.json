package net.will_co21.format.json;

public class JsonBooleanSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final boolean value;

	public JsonBooleanSerializable(boolean value, JsonOptions options)
	{
		if(options == null)
			throw new TypeOfNullableNotAllowedException("The value was passed of the option to the constructor of JsonBooleanSerializable is null.");

		this.value = value;
		this.options = options;
	}

	@Override
	public String toJson()
	{
		return (this.value) ? "true" : "false";
	}

	@Override
	public String toJson(int indent)
	{
		return toJson();
	}
}
