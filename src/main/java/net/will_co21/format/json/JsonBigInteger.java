package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

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
	public Optional<BigInteger> getOptionalBigInteger() {
		return Optional.of(getBigInteger());
	}

	@Override
	public BigDecimal getBigDecimal() {
		return new BigDecimal(this.value);
	}

	@Override
	public Optional<BigDecimal> getOptionalBigDecimal() {
		return Optional.of(getBigDecimal());
	}

	@Override
	public String getString() {
		return this.value.toString();
	}

	@Override
	public Optional<String> getOptionalString() {
		return Optional.of(getString());
	}

	@Override
	public boolean getBoolean() {
		return this.value.compareTo(BigInteger.valueOf(0)) == 0;
	}

	@Override
	public Optional<Boolean> getOptionalBoolean() {
		return Optional.of(getBoolean());
	}

	public IPrettyJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		if(options.hasNumberOfString())
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigIntAsString() && this.value.compareTo(BigInteger.valueOf((long)Integer.MAX_VALUE)) > 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigIntAsString() && this.value.compareTo(BigInteger.valueOf((long)Integer.MIN_VALUE)) < 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigLongAsString() && this.value.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigLongAsString() && this.value.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) < 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else
		{
			return new JsonBigIntegerSerializable(this.value, options);
		}
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
