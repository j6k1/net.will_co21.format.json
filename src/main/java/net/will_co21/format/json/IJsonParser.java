package net.will_co21.format.json;

public interface IJsonParser {
	public Pair<IJsonValue, Integer> parseJson(String json, int start);
}
