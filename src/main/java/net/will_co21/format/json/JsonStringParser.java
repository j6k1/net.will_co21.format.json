package net.will_co21.format.json;

import java.util.Arrays;

public class JsonStringParser implements IJsonParser {
	public final static boolean[] headChars;
	private final static char[] unEscapeMap;
	static {
		headChars = new boolean[128];
		Arrays.fill(headChars, false);
		headChars[(int)'"'] = true;

		unEscapeMap = new char[128];

		Arrays.fill(unEscapeMap, '\0');

		unEscapeMap[(int)'"'] = '"';
		unEscapeMap[(int)'\\'] = '\\';
		unEscapeMap[(int)'/'] = '/';
		unEscapeMap[(int)'b'] = '\b';
		unEscapeMap[(int)'f'] = '\f';
		unEscapeMap[(int)'n'] = '\n';
		unEscapeMap[(int)'r'] = '\r';
		unEscapeMap[(int)'t'] = '\t';
	}

	@Override
	public Pair<IJsonValue, Integer> parseJson(String json, int start) {
		int index = start;
		int currentStart = start;
		int length = json.length();

		char c = json.charAt(index);

		if(c != '"') throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");

		index++;

		if(index == length) throw new JsonFormatErrorException("The format of this json string is not an json string format.");

		currentStart++;

		StringBuilder sb = new StringBuilder();

		while(index < length && (c = json.charAt(index)) != '"')
		{
			if(c == '\\')
			{
				if(currentStart < index) sb.append(json.substring(currentStart, index));

				index++;

				if(index == length) throw new JsonFormatErrorException("The format of this json string is not an json string format.");

				c = json.charAt(index);

				if(c == 'u')
				{
					if(index + 4 >= length) throw new JsonFormatErrorException("The format of this json string is not an json string format.");

					index++;

					int hexpos = index;

					while(hexpos < index + 4)
					{
						c = json.charAt(hexpos);

						if((c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f')) hexpos++;
						else throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");
					}

					int code = Integer.parseInt(json.substring(index, index + 4), 16);

					if(!(code >= 0xD800 && code <= 0xDBFF) || index + 4 == length)
					{
						sb.append((char)code);
						index += 4;
					}
					else if(index + 5 >= length || c != '\\' || json.charAt(index + 5) != 'u')
					{
						sb.append((char)code);
						index += 4;
					}
					else
					{
						index += 6;

						if(index + 3 >= length)
							throw new JsonFormatErrorException("The format of this json string is not an json string format.");

						c = json.charAt(index);

						hexpos = index;

						while(hexpos < index + 4)
						{
							c = json.charAt(hexpos);

							if((c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f')) hexpos++;
							else throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");
						}

						int secondCode = Integer.parseInt(json.substring(index, index + 4), 16);

						if(secondCode < 0xDC00 || secondCode > 0xDFFF)
						{
							// ユニコードとして不正でもデコードするように(バイナリデータを受け取りたいケースのための実装)
							// throw new JsonFormatErrorException("Unicode escape string value is invalid.");
							sb.append((char)code);
							sb.append((char)secondCode);
						}
						else
						{
							sb.append(new String(new char[] { (char)code, (char)secondCode }));
						}
						index += 4;
					}

					currentStart = index;
				}
				else if(unEscapeMap[(int)c] != '\0')
				{
					sb.append(unEscapeMap[(int)c]);
					index++;
					currentStart = index;
				}
				else
				{
					throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");
				}
			}
			else
			{
				index++;
			}
		}

		if(currentStart < index) sb.append(json.substring(currentStart, index));

		if(index == length) throw new JsonFormatErrorException("The format of this json string is not an json string format.");

		c = json.charAt(index);

		if(c != '"') throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");

		index++;

		return new Pair<IJsonValue, Integer>(new JsonString(sb.toString()), index);
	}
}
