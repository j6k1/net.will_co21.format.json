package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public class JsonLong extends JsonNumber {
	protected final long value;

	public JsonLong(long value)
	{
		this.value = value;
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
		if(options.hasNumberOfString())
		{
			return new JsonStringSerializable(this.value + "", options);
		}
		else if(options.hasBigIntAsString() && this.value > Integer.MAX_VALUE)
		{
			return new JsonStringSerializable(this.value + "", options);
		}
		else if(options.hasBigIntAsString() && this.value < Integer.MIN_VALUE)
		{
			return new JsonStringSerializable(this.value + "", options);
		}
		else
		{
			return new JsonLongSerializable(this.value, options);
		}
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof JsonLong)) return false;
		return this.value == ((JsonLong)o).value;
	}

	@Override
	public int hashCode()
	{
		return Long.hashCode(this.value);
	}
}