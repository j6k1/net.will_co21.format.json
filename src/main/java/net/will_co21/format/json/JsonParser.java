package net.will_co21.format.json;

public class JsonParser extends JsonContainerParser implements IJsonParser {
	private final static IJsonParser numberParser = new JsonNumberParser();
	private final static IJsonParser stringParser = new JsonStringParser();
	private final static IJsonParser booleanParser = new JsonBooleanParser();
	private final static IJsonParser nullParser = new JsonNullParser();
	private final static IJsonParser arrayParser = new JsonArrayParser();
	private final static IJsonParser objectParser = new JsonObjectParser();

	@Override
	public Pair<IJsonValue, Integer> parseJson(String json, int start) {
		int index = skipWhiteSpace(json, start);

		char c = json.charAt(index);
		Pair<IJsonValue, Integer> ret;

		if(c >= 128) throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");

		if(JsonNumberParser.headChars[(int)c])
		{
			ret = numberParser.parseJson(json, index);
		}
		else if(JsonStringParser.headChars[(int)c])
		{
			ret = stringParser.parseJson(json, index);
		}
		else if(JsonBooleanParser.headChars[(int)c])
		{
			ret = booleanParser.parseJson(json, index);
		}
		else if(JsonNullParser.headChars[(int)c])
		{
			ret = nullParser.parseJson(json, index);
		}
		else if(JsonArrayParser.headChars[(int)c])
		{
			ret = arrayParser.parseJson(json, index);
		}
		else if(JsonObjectParser.headChars[(int)c])
		{
			ret = objectParser.parseJson(json, index);
		}
		else
		{
			throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");
		}

		index = ret.snd;

		index = skipWhiteSpace(json, index);

		if(index < json.length())
		{
			throw new JsonFormatErrorException("unexpected character \"" + json.charAt(index) + "\" was found.");
		}

		return ret;
	}

	public static IJsonValue parse(String json)
	{
		return (new JsonParser()).parseJson(json, 0).fst;
	}
}
