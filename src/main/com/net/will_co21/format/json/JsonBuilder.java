package net.will_co21.format.json;

public class JsonBuilder {
	public static String toJson(IJsonValue jsonValue, JsonOption[] options)
	{
		return jsonValue.toJson(options);
	}

	public static String toJson(IJsonValue jsonValue)
	{
		return jsonValue.toJson();
	}
}
