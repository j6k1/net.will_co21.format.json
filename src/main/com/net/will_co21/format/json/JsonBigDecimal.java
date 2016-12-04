package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public class JsonBigDecimal extends JsonNumber {
	protected final BigDecimal value;

	public JsonBigDecimal(BigDecimal value)
	{
		if(value == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonBigDecimal type constructor.");

		this.value = value;
	}

	@Override
	public BigDecimal getBigDecimal() {
		return this.value;
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
		return this.value.compareTo(new BigDecimal(0)) == 0;
	}

	@Override
	public Optional<Boolean> getOptionalBoolean() {
		return Optional.of(getBoolean());
	}

	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		if(options.hasNumberOfString())
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigFloatAsString() && this.value.compareTo(BigDecimal.valueOf((double)Float.MAX_VALUE)) > 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigFloatAsString() && this.value.compareTo(BigDecimal.valueOf((double)Float.MIN_VALUE)) < 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigDoubleAsString() && this.value.compareTo(BigDecimal.valueOf(Double.MAX_VALUE)) > 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigDoubleAsString() && this.value.compareTo(BigDecimal.valueOf(Double.MIN_VALUE)) < 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else
		{
			return new JsonBigDecimalSerializable(this.value, options);
		}
	}

	public boolean equals(JsonBigDecimal o)
	{
		return this.value.equals(o.value);
	}

	@Override
	public int hashCode()
	{
		return this.value.hashCode();
	}
}
