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

			if(c < '0' || c > '9') throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");
			else index++;

			while(index < length)
			{
				c = json.charAt(index);

				if(c < '0' || c > '9') break;

				index++;
			}
		}
		else if(c < '1' || c > '9')
		{
			throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");
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

					c = json.charAt(index);

					if(c < '0' || c > '9') throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");


					while(index < length)
					{
						c = json.charAt(index);

						if(c < '0' || c > '9') break;

						index++;
					}
				}
				else
				{
					break;
				}
			}
		}

		if(c == 'e' || c == 'E')
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
				{
					throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");
				}
				else if(c == '+' || c == '-')
				{
					index++;

					if(index == length)
						throw new JsonFormatErrorException("The format of this json string is not an json number format.");

					c = json.charAt(index);

					if(c < '0' || c > '9') throw new JsonFormatErrorException("unexpected character \"" + c + "\" was found.");
				}
			}

			while(index < length)
			{
				c = json.charAt(index);

				if(c < '0' || c > '9') break;

				index++;
			}
		}

		if(isInt)
		{
			BigInteger number = new BigInteger(json.substring(start, index));

			long longValue = number.longValue();

			if(number.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0 ||
					number.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) < 0)
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

			double doubleValue = number.doubleValue();
			String strFloatValue = Float.toString(((float)doubleValue));
			

			if(doubleValue > Double.MAX_VALUE || doubleValue < -Double.MAX_VALUE || BigDecimal.valueOf(doubleValue).compareTo(number) != 0)
			{
				return new Pair<IJsonValue, Integer>(new JsonBigDecimal(number), index);
			}
			else if(strFloatValue.equals("Infinity") || strFloatValue.equals("-Infinity") || 
					number.compareTo(new BigDecimal(strFloatValue)) != 0)
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
