package net.will_co21.format.json;

import java.util.Arrays;

public class JsonBooleanParser implements IJsonParser {
	public final static boolean[] headChars;

	static {
		headChars = new boolean[128];
		Arrays.fill(headChars, false);
		headChars[(int)'t'] = true;
		headChars[(int)'f'] = true;
	}

	@Override
	public Pair<IJsonValue, Integer> parseJson(String json, int start) {
		if(json.startsWith("true", start))
		{
			return new Pair<IJsonValue, Integer>(new JsonBoolean(true), start + 4);
		}
		else if(json.startsWith("false", start))
		{
			return new Pair<IJsonValue, Integer>(new JsonBoolean(true), start + 5);
		}
		else
		{
			throw new JsonFormatErrorException("The format of this json string is not an json null format.");
		}
	}
}
