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
		if(value == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonStringSerializable type constructor.");
		else if(options == null)
			throw new TypeOfNullableNotAllowedException("The value was passed of the option to the constructor of JsonStringSerializable is null.");

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

			// C1制御コード(0x80~0x9F) 書式制御文字(0x200B~0x200F, 0x2028~0x202F, 0x2060),
			// 不可視の演算子(0x2061~0x2064) 書式制御文字(0x2066~0x2069) 非推奨(0x206A~0x206F)
			// 書字方向(0x200E, 0x200F, 0x202A~0x202E) 特殊記号(0xFFF0~0xFFFF) 私用領域(0xE000~0xF8FF)
			if( (c >= 0x80 && c <= 0x9F) || (c >= 0x200B && c <= 0x200F) || (c >= 0x2028 && c <= 0x202F) ||
				(c >= 0x2060 && c <= 0x2064) || (c >= 0x2066 && c <= 0x206F) ||
				 c == 0x200E || c == 0x200F || (c >= 0x202A && c <= 0x202E) || (c >= 0xFFF0 && c <= 0xFFFF) ||
				(c >= 0xE000 && c <= 0xF8FF))
			{
				if(currentStart < index) sb.append(this.value.substring(currentStart, index));
				currentStart = index + 1;
				sb.append(toUnicodeEscape(c));
			}
			// タグ文字
			else if(c == 0xDB40 && index + 1 < length && chars[index+1] >= 0xDC00 && chars[index+1] <= 0xDC7F)
			{
				if(currentStart < index) sb.append(this.value.substring(currentStart, index));
				currentStart = index + 2;
				sb.append(toUnicodeEscape(c));
				sb.append(toUnicodeEscape(chars[index+1]));
				index++;
			}
			// 補助私用領域A(ユニコードコードポイントF0000~FFFFF,上位サロゲート0xDB00~0xDBBF)
			// 補助私用領域B(ユニコードコードポイント100000~10FFFF,上位サロゲート0xDBC0~0xDBFF)
			else if(c >= 0xDB80 && c <= 0xDBFF  && index + 1 < length)
			{
				if(currentStart < index) sb.append(this.value.substring(currentStart, index));
				currentStart = index + 2;
				sb.append(toUnicodeEscape(c));
				sb.append(toUnicodeEscape(chars[index+1]));
				index++;
			}
			// サロゲートペア
			else if(c >= 0xD800 && c <= 0xDBFF && index + 1 < length)
			{
				if(this.options.hasEscapedUnicode() || chars[index+1] < 0xDC00 || chars[index+1] > 0xDFFF)
				{
					if(currentStart < index) sb.append(this.value.substring(currentStart, index));
					currentStart = index + 2;
					sb.append(toUnicodeEscape(c));
					sb.append(toUnicodeEscape(chars[index+1]));
				}
				index++;
			}
			// 不正なサロゲートペア(上位サロゲートで文字列が終わっているケース)
			else if(c >= 0xD800 && c <= 0xDBFF)
			{
				if(currentStart < index) sb.append(this.value.substring(currentStart, index));
				currentStart = index + 1;
				sb.append(toUnicodeEscape(c));
			}
			// サロゲートペアでないUTF-16文字
			else if((c >= 0x80 && c <= 0xD7FF) || (c >= 0xF900 && c <= 0xFFFF))
			{
				if(this.options.hasEscapedUnicode())
				{
					if(currentStart < index) sb.append(this.value.substring(currentStart, index));
					currentStart = index + 1;
					sb.append(toUnicodeEscape(c));
				}
			}
			// その他の非ascii文字
			else if(c >= 128)
			{
				if(currentStart < index) sb.append(this.value.substring(currentStart, index));
				currentStart = index + 1;
				sb.append(toUnicodeEscape(c));
			}
			else if(escapeMap[(int)c] != null)
			{
				if(optionalEscapeCharsMap[(int)c])
				{
					switch(c)
					{
						case '<':
							if(this.options.hasHexTag())
							{
								if(currentStart < index) sb.append(this.value.substring(currentStart, index));
								currentStart = index + 1;
								sb.append(escapeMap[(int)c]);
							}
						break;

						case '>':
							if(this.options.hasHexTag())
							{
								if(currentStart < index) sb.append(this.value.substring(currentStart, index));
								currentStart = index + 1;
								sb.append(escapeMap[(int)c]);
							}
						break;

						case '&':
							if(this.options.hasHexAmp())
							{
								if(currentStart < index) sb.append(this.value.substring(currentStart, index));
								currentStart = index + 1;
								sb.append(escapeMap[(int)c]);
							}
						break;

						case '\'':
							if(this.options.hasHexApo())
							{
								if(currentStart < index) sb.append(this.value.substring(currentStart, index));
								currentStart = index + 1;
								sb.append(escapeMap[(int)c]);
							}
						break;
					}
				}
				else if(c == '/')
				{
					if(!this.options.hasUnEscapedSlashes())
					{
						if(currentStart < index) sb.append(this.value.substring(currentStart, index));
						currentStart = index + 1;
						sb.append(escapeMap[(int)c]);
					}
				}
				else if(c == '"')
				{
					if(currentStart < index) sb.append(this.value.substring(currentStart, index));
					currentStart = index + 1;
					sb.append((this.options.hasHexQuot() ? "\\u0022" : escapeMap[(int)c]));
				}
				else
				{
					if(currentStart < index) sb.append(this.value.substring(currentStart, index));
					currentStart = index + 1;
					sb.append(escapeMap[(int)c]);
				}
			}
			// asciiの範囲の制御文字
			else if(c <= 0x1F || c == 0x7F)
			{
				if(currentStart < index) sb.append(this.value.substring(currentStart, index));
				currentStart = index + 1;
				sb.append(toUnicodeEscape(c));
			}
		}

		if(currentStart < index) sb.append(this.value.substring(currentStart, index));

		return "\"" + sb.toString() + "\"";
	}

	protected String toUnicodeEscape(char c)
	{
		int code = (int)c;

		String strcode = ("000" + Integer.toHexString(code).toUpperCase());

		return "\\u" + strcode.substring(strcode.length() - 4, strcode.length());
	}

	@Override
	public String toJson(int indent)
	{
		return toJson();
	}
}
