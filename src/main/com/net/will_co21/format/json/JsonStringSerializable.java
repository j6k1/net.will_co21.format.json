package net.will_co21.format.json;

import java.util.Arrays;

public class JsonStringSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final String value;
	private final static String[] escapeMap;
	private final static boolean[] optionalEscapeCharsMap;

	static {
		escapeMap = new String[128];
		escapeMap[(int)'"'] = "\\\"";
		escapeMap[(int)'\\'] = "\\\\";
		escapeMap[(int)'/'] = "\\/";
		escapeMap[(int)'\b'] = "\\b";
		escapeMap[(int)'\f'] = "\\f";
		escapeMap[(int)'\n'] = "\\n";
		escapeMap[(int)'\r'] = "\\r";
		escapeMap[(int)'\t'] = "\\t";
		escapeMap[(int)'<'] = "\\u003C";
		escapeMap[(int)'>'] = "\\u003E";
		escapeMap[(int)'&'] = "\\u0026";
		escapeMap[(int)'\''] = "\\u0027";

		optionalEscapeCharsMap = new boolean[128];
		Arrays.fill(optionalEscapeCharsMap, false);
		optionalEscapeCharsMap[(int)'<'] = true;
		optionalEscapeCharsMap[(int)'>'] = true;
		optionalEscapeCharsMap[(int)'&'] = true;
		optionalEscapeCharsMap[(int)'\''] = true;
	}

	public JsonStringSerializable(String value, JsonOptions options)
	{
		this.value = value;
		this.options = options;
	}

	@Override
	public String toJson()
	{
		char[] chars = this.value.toCharArray();

		StringBuilder sb = new StringBuilder();

		for(int index = 0, length = chars.length; index < length; index++)
		{
			char c = chars[index];

			if(c >= 128)
			{
				sb.append(toUnicodeEscape(c));
			}
			else if(escapeMap[(int)c] != null)
			{
				if(optionalEscapeCharsMap[(int)c])
				{
					switch(c)
					{
						case '<':
							sb.append((this.options.hasHexTag() ? escapeMap[(int)c] : c));
						break;

						case '>':
							sb.append((this.options.hasHexTag() ? escapeMap[(int)c] : c));
						break;

						case '&':
							sb.append((this.options.hasHexAmp() ? escapeMap[(int)c] : c));
						break;

						case '\'':
							sb.append((this.options.hasHexApo() ? escapeMap[(int)c] : c));
						break;
					}
				}
				else
				{
					if(c == '/') sb.append((this.options.hasEscapedSlashes() ? escapeMap[(int)c] : '/'));
					else if(c == '"') sb.append((this.options.hasHexQuot() ? "\\u0022" : escapeMap[(int)c]));
					else sb.append(escapeMap[(int)c]);
				}
			}
			else if(c < 0x20 || c > 0x7E)
			{
				sb.append(toUnicodeEscape(c));
			}
			else
			{
				sb.append(c);
			}
		}

		return "\"" + sb.toString() + "\"";
	}

	protected String toUnicodeEscape(char c)
	{
		int code;

		if(c < 0) code = (int)c + 0xFFFF;
		else code = (int)c;

		String strcode = ("000" + Integer.toHexString(code).toUpperCase());

		return "\\u" + strcode.substring(strcode.length() - 4, strcode.length());
	}

	@Override
	public String toJson(int indent)
	{
		return toJson();
	}
}
