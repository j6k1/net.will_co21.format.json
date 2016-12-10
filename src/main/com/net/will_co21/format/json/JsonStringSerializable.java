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

		int index;
		int length;
		int currentStart;

		for(index = 0, currentStart = index, length = chars.length; index < length; index++)
		{
			char c = chars[index];

			if( (c >= 0x80 && c <= 0x9F) || (c >= 0x200B && c <= 0x200F) || (c >= 0x2028 && c <= 0x202F) ||
				(c >= 0x2060 && c <= 0x2064) || (c >= 0x2066 && c <= 0x206F) ||
				 c == 0x200E || c == 0x200F || (c >= 0x202A && c <= 0x202E) || (c >= 0xFFF0 && c <= 0xFFFF))
			{
				if(currentStart < index) sb.append(this.value.substring(currentStart, index));
				currentStart = index + 1;
				sb.append(toUnicodeEscape(c));
			}
			else if(c >= 0xD800 && c <= 0xDBFF &&  index + 1 < length && (c == 0xDB40 && chars[index+1] >= 0xDC00 && chars[index+1] <= 0xDC7F))
			{
				if(currentStart < index) sb.append(this.value.substring(currentStart, index));
				currentStart = index + 2;
				sb.append(toUnicodeEscape(c));
				sb.append(toUnicodeEscape(chars[index+1]));
				index++;
			}
			else if(c >= 0xD800 && c <= 0xDBFF && index + 1 < length)
			{
				if(this.options.hasEscapedUnicode())
				{
					if(currentStart < index) sb.append(this.value.substring(currentStart, index));
					currentStart = index + 2;
					sb.append(toUnicodeEscape(c));
					sb.append(toUnicodeEscape(chars[index+1]));
				}
				index++;
			}
			else if(c >= 0xD800 && c <= 0xDBFF)
			{
				if(currentStart < index) sb.append(this.value.substring(currentStart, index));
				currentStart = index + 1;
				sb.append(toUnicodeEscape(c));
			}
			else if((c >= 0x80 && c < 0xD7FF) || (c >= 0xE000 && c <= 0xFFFF))
			{
				if(this.options.hasEscapedUnicode())
				{
					if(currentStart < index) sb.append(this.value.substring(currentStart, index));
					currentStart = index + 1;
					sb.append(toUnicodeEscape(c));
				}
			}
			else if(c >= 128)
			{
				if(currentStart < index) sb.append(this.value.substring(currentStart, index));
				currentStart = index + 1;
				sb.append(toUnicodeEscape(c));
			}
			else if(escapeMap[(int)c] != null)
			{
				if(currentStart < index) sb.append(this.value.substring(currentStart, index));
				currentStart = index + 1;
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
			else if(c <= 0x1F || c == 0x7F)
			{
				sb.append(toUnicodeEscape(c));
				if(currentStart < index) sb.append(this.value.substring(currentStart, index));
				currentStart = index + 1;
			}
		}

		if(currentStart < index) sb.append(this.value.substring(currentStart, index));

		return "\"" + sb.toString() + "\"";
	}

	protected String toUnicodeEscape(char c)
	{
		int code;

		if(c < 0) code = (int)c + 0x10000;
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
