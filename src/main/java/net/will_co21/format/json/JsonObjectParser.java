package net.will_co21.format.json;

import java.util.Arrays;

public class JsonObjectParser extends JsonContainerParser implements IJsonParser {
	public final static boolean[] headChars;

	static {
		headChars = new boolean[128];
		Arrays.fill(headChars, false);
		headChars[(int)'{'] = true;
	}

	private final static IJsonParser numberParser = new JsonNumberParser();
	private final static IJsonParser stringParser = new JsonStringParser();
	private final static IJsonParser booleanParser = new JsonBooleanParser();
	private final static IJsonParser nullParser = new JsonNullParser();
	private final static IJsonParser arrayParser = new JsonArrayParser();
	private final static IJsonParser objectParser = new JsonObjectParser();

	@Override
	public Pair<IJsonValue, Integer> parseJson(String json, int start) {
		int index = start;

		char c = json.charAt(index);

		if(json.charAt(index) != '{') throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");

		index = skipWhiteSpace(json, ++index);

		if(index == json.length())
		{
			throw new JsonFormatErrorException("The format of this json string is not an json object format.");
		}

		JsonObject result = new JsonObject();

		while(index < json.length())
		{
			Pair<IJsonValue, Integer> ret;
			Pair<IJsonValue, Integer> keyRet;
			String key = null;

			if(c >= 128) throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");

			if(!JsonStringParser.headChars[(int)c])
			{
				throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");
			}
			else
			{
				keyRet = stringParser.parseJson(json, index);
				key = keyRet.fst.getString();
				index = keyRet.snd;
			}

			if(index == json.length() ||(index = skipWhiteSpace(json, index)) == json.length()) {
				throw new JsonFormatErrorException("The format of this json string is not an json object format.");
			}

			c = json.charAt(index);

			if(c != ':') {
				throw new JsonFormatErrorException("The format of this json string is not an json object format.");
			}

			index = skipWhiteSpace(json, ++index);

			if(index == json.length()) {
				throw new JsonFormatErrorException("The format of this json string is not an json object format.");
			}

			c = json.charAt(index);

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

			result.put(key, ret.fst);

			index = ret.snd;

			if(index == json.length() || (index = skipWhiteSpace(json, index)) == json.length()) {
				throw new JsonFormatErrorException("The format of this json string is not an json object format.");
			}

			c = json.charAt(index);

			if(c != ',' && c != '}')
			{
				throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");
			}
			else if(c == '}')
			{
				++index;
				break;
			}
			else
			{
				index = skipWhiteSpace(json, ++index);

				if(index == json.length())
				{
					throw new JsonFormatErrorException("The format of this json string is not an json object format.");
				}
			}
		}

		return new Pair<IJsonValue, Integer>(result, index);
	}
}
