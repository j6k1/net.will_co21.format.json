package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

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
	public Optional<Integer> getOptionalInt() {
		return Optional.of(getInt());
	}

	@Override
	public long getLong() {
		return this.value;
	}

	@Override
	public Optional<Long> getOptionalLong() {
		return Optional.of(getLong());
	}

	@Override
	public BigInteger getBigInteger() {
		return BigInteger.valueOf(this.value);
	}

	@Override
	public Optional<BigInteger> getOptionalBigInteger() {
		return Optional.of(getBigInteger());
	}

	@Override
	public float getFloat() {
		return (float)this.value;
	}

	@Override
	public Optional<Float> getOptionalFloat() {
		return Optional.of(getFloat());
	}

	@Override
	public double getDouble() {
		return (double)this.value;
	}

	@Override
	public Optional<Double> getOptionalDouble() {
		return Optional.of(getDouble());
	}

	@Override
	public BigDecimal getBigDecimal() {
		return BigDecimal.valueOf(this.value);
	}

	@Override
	public Optional<BigDecimal> getOptionalBigDecimal() {
		return Optional.of(getBigDecimal());
	}

	@Override
	public String getString() {
		return this.value + "";
	}

	@Override
	public Optional<String> getOptionalString() {
		return Optional.of(getString());
	}

	@Override
	public boolean getBoolean() {
		return this.value != 0;
	}

	@Override
	public Optional<Boolean> getOptionalBoolean() {
		return Optional.of(getBoolean());
	}

	public IPrettyJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		if(options.hasNumberToString())
		{
			return new JsonStringSerializable(this.value + "", options);
		}
		else
		{
			return new JsonIntSerializable(this.value, options);
		}
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof JsonInt)) return false;
		return this.value == ((JsonInt)o).value;
	}

	@Override
	public int hashCode()
	{
		return Integer.hashCode(this.value);
	}

	public String toString()
	{
		return "Integer(" + Integer.toString(this.value) + ")";
	}
}
