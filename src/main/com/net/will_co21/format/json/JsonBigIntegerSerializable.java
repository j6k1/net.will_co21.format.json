package net.will_co21.format.json;

import java.math.BigInteger;

public class JsonBigIntegerSerializable {
	protected final JsonOptions options;
	protected final BigInteger value;

	public JsonBigIntegerSerializable(BigInteger value, JsonOptions options)
	{
		this.value = value;
		this.options = options;
	}

	public String toJson()
	{
		return null;
	}
}
