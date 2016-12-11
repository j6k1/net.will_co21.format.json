package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

public class JsonNumberParser implements IJsonParser {
	public final static boolean[] headChars;

	static {
		headChars = new boolean[128];
		Arrays.fill(headChars, false);
		headChars[(int)'-'] = true;
		headChars[(int)'0'] = true;
		headChars[(int)'1'] = true;
		headChars[(int)'2'] = true;
		headChars[(int)'3'] = true;
		headChars[(int)'4'] = true;
		headChars[(int)'5'] = true;
		headChars[(int)'6'] = true;
		headChars[(int)'7'] = true;
		headChars[(int)'8'] = true;
		headChars[(int)'9'] = true;
}

	@Override
	public Pair<IJsonValue, Integer> parseJson(String json, int start) {
		int index = start;
		int length = json.length();
		boolean isInt = true;

		char c = json.charAt(index);

		if(c != '-' && (c < '0' || c > '9'))
		{
			throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");
		}
		else if(c == '-')
		{
			index++;

			if(index == length) throw new JsonFormatErrorException("The format of this json string is not an json number format.");

			c = json.charAt(index);

			if(c < '0' || c > '9') throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");
		}

		if(c == '0')
		{
			isInt = false;

			if(index + 2 >= length)
			{
				throw new JsonFormatErrorException("The format of this json string is not an json number format.");
			}
			else if(json.charAt(index + 1) != '.')
			{
				throw new JsonFormatErrorException("unexpected character \"" + json.charAt(index + 1) + "\" was found.");
			}

			index += 2;

			c = json.charAt(index);

			if(c < '0' || c > '9') throw new JsonFormatErrorException("The format of this json string is not an json number format.");
			else index++;

			while(index < length)
			{
				c = json.charAt(index);

				if(c < '0' || c > '9') break;

				index++;
			}
		}
		else
		{
			while(index < length)
			{
				c = json.charAt(index);

				if(c >= '0' && c <= '9')
				{
					index++;
				}
				else if(c == '.')
				{
					isInt = false;

					if(index + 1 == length)
					{
						throw new JsonFormatErrorException("The format of this json string is not an json number format.");
					}
					index++;
					break;
				}
				else if(c == 'e' || c == 'E')
				{
					isInt = false;

					if(index + 1 == length)
					{
						throw new JsonFormatErrorException("The format of this json string is not an json number format.");
					}
					else
					{
						index++;

						c = json.charAt(index);

						if(c != '+' && c != '-' && (c < '0' || c > '9'))
							throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");
						else if(c == '+' || c == '-')
							index++;

						if(index + 1 == length)
							throw new JsonFormatErrorException("The format of this json string is not an json number format.");

						break;
					}
				}
			}
		}

		while(index < length)
		{
			c = json.charAt(index);

			if(c < '0' || c > '9') break;

			index++;
		}

		if(isInt)
		{
			BigInteger number = new BigInteger(json.substring(start, index));

			long longValue = number.longValue();

			if(longValue > Long.MAX_VALUE || longValue < Long.MIN_VALUE)
			{
				return new Pair<IJsonValue, Integer>(new JsonBigInteger(number), index);
			}
			else if(longValue > Integer.MAX_VALUE || longValue < Integer.MIN_VALUE)
			{
				return new Pair<IJsonValue, Integer>(new JsonLong(longValue), index);
			}
			else
			{
				return new Pair<IJsonValue, Integer>(new JsonInt((int)longValue), index);
			}
		}
		else
		{
			BigDecimal number = new BigDecimal(json.substring(start, index));

			double doubleValue = number.longValue();

			if(doubleValue > Double.MAX_VALUE || doubleValue < Double.MIN_VALUE || !BigDecimal.valueOf(doubleValue).equals(number))
			{
				return new Pair<IJsonValue, Integer>(new JsonBigDecimal(number), index);
			}
			else if(doubleValue > Float.MAX_VALUE || doubleValue < Float.MIN_VALUE)
			{
				return new Pair<IJsonValue, Integer>(new JsonDouble(doubleValue), index);
			}
			else
			{
				return new Pair<IJsonValue, Integer>(new JsonFloat((float)doubleValue), index);
			}
		}
	}

}
