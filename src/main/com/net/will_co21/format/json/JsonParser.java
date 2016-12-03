package net.will_co21.format.json;

public class JsonParser implements IJsonParser {
	@Override
	public Pair<IJsonValue, Integer> parseJson(String json, int start) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public static IJsonValue parse(String json)
	{
		return (new JsonParser()).parseJson(json, 0).fst;
	}
}
