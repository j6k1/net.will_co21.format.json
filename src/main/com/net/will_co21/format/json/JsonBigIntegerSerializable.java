package net.will_co21.format.json;

import java.math.BigInteger;

public class JsonBigIntegerSerializable implements IJsonSerializable {
	protected final JsonOptions options;
	protected final BigInteger value;

	public JsonBigIntegerSerializable(BigInteger value, JsonOptions options)
	{
		this.value = value;
		this.options = options;
	}

	@Override
	public String toJson()
	{
		return null;
	}
}
