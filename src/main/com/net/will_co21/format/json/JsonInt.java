package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonInt extends JsonNumber {
	protected int value;

	public JsonInt(int value)
	{
		this.value = value;
	}
	@Override
	public int getInt() {
		return this.value;
	}

	@Override
	public long getLong() {
		return this.value;
	}

	@Override
	public BigInteger getBigInteger() {
		return BigInteger.valueOf(this.value);
	}

	@Override
	public float getFloat() {
		return (float)this.value;
	}

	@Override
	public double getDouble() {
		return (double)this.value;
	}

	@Override
	public BigDecimal getBigDecimal() {
		return BigDecimal.valueOf(this.value);
	}

	@Override
	public String getString() {
		return this.value + "";
	}

	@Override
	public boolean getBoolean() {
		return this.value != 0;
	}

	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return null;
	}

	public boolean equals(JsonInt o)
	{
		return this.value == o.value;
	}

	@Override
	public int hashCode()
	{
		return Integer.hashCode(this.value);
	}
}
