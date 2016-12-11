package net.will_co21.format.json;

public class JsonNullSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;

	public JsonNullSerializable(JsonOptions options)
	{
		if(options == null)
			throw new TypeOfNullableNotAllowedException("The value was passed of the option to the constructor of JsonNullSerializable is null.");

		this.options = options;
	}

	@Override
	public String toJson()
	{
		return "null";
	}

	@Override
	public String toJson(int indent)
	{
		return toJson();
	}
}
