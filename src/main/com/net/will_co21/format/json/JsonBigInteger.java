package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonBigInteger extends JsonNumber {
	protected final BigInteger value;

	public JsonBigInteger(BigInteger value)
	{
		if(value == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonBigInteger type constructor.");

		this.value = value;
	}

	@Override
	public BigInteger getBigInteger() {
		return this.value;
	}

	@Override
	public BigDecimal getBigDecimal() {
		return new BigDecimal(this.value);
	}

	@Override
	public String getString() {
		return this.value.toString();
	}

	@Override
	public boolean getBoolean() {
		return this.value.compareTo(BigInteger.valueOf(0)) == 0;
	}

	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return null;
	}

	public boolean equals(JsonBigInteger o)
	{
		return this.value.equals(o.value);
	}

	@Override
	public int hashCode()
	{
		return this.value.hashCode();
	}
}
