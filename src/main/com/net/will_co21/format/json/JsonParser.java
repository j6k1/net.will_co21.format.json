package net.will_co21.format.json;

public class JsonParser implements IJsonParser {
	@Override
	public IJsonValue parseJson(String json, int start) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public static IJsonValue parse(String json)
	{
		return (new JsonParser()).parseJson(json, 0);
	}

	public static <T> T parse(String json, IDeserializeJson<T> deserializer)
	{
		if(deserializer == null)
			throw new TypeOfNullableNotAllowedException("A value of null was passed as the value of the deserializer object.");

		return deserializer.deserializeFromJson(parse(json));
	}
}
