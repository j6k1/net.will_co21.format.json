package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public class JsonBigDecimal extends JsonNumber {
	protected final BigDecimal value;

	protected final static BigDecimal BIGDECIMAL_DOUBLE_MAX_VALUE = BigDecimal.valueOf(Double.MAX_VALUE);
	protected final static BigDecimal BIGDECIMAL_DOUBLE_NEGATIVE_MAX_VALUE = BigDecimal.valueOf(-Double.MAX_VALUE);
	protected final static BigDecimal BIGDECIMAL_FLOAT_MAX_VALUE = new BigDecimal(Float.toString(Float.MAX_VALUE));
	protected final static BigDecimal BIGDECIMAL_FLOAT_NEGATIVE_MAX_VALUE = new BigDecimal(Float.toString(-Float.MAX_VALUE));

	public JsonBigDecimal(String strValue)
	{
		if(strValue == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonBigDecimal type constructor.");

		this.value = new BigDecimal(strValue);
	}

	public JsonBigDecimal(long value)
	{
		this(new BigDecimal(BigInteger.valueOf(value)));
	}

	public JsonBigDecimal(BigInteger value)
	{
		if(value == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonBigDecimal type constructor.");

		this.value = new BigDecimal(value);
	}

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
		return this.value.compareTo(new BigDecimal(0)) != 0;
	}

	@Override
	public Optional<Boolean> getOptionalBoolean() {
		return Optional.of(getBoolean());
	}

	public IPrettyJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		double doubleValue = this.value.doubleValue();

		if(options.hasNumberToString())
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigDoubleAsString() && this.value.compareTo(BIGDECIMAL_DOUBLE_MAX_VALUE) > 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigDoubleAsString() && this.value.compareTo(BIGDECIMAL_DOUBLE_NEGATIVE_MAX_VALUE) < 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigDoubleAsString() && this.value.compareTo(BigDecimal.valueOf(doubleValue)) != 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigFloatAsString() && this.value.compareTo(BIGDECIMAL_FLOAT_MAX_VALUE) > 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigFloatAsString() && this.value.compareTo(BIGDECIMAL_FLOAT_NEGATIVE_MAX_VALUE) < 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigFloatAsString() &&
				this.value.compareTo(new BigDecimal(Float.toString((float)doubleValue))) != 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigLongAsString() && this.value.compareTo(BigDecimal.valueOf(Long.MAX_VALUE)) > 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigLongAsString() && this.value.compareTo(BigDecimal.valueOf(Long.MIN_VALUE)) < 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigLongAsString() && this.value.compareTo(BigDecimal.valueOf(this.value.longValue())) != 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigIntAsString() && this.value.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) > 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigIntAsString() && this.value.compareTo(BigDecimal.valueOf(Integer.MIN_VALUE)) < 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else if(options.hasBigIntAsString() &&
				this.value.compareTo(BigDecimal.valueOf((long)(int)this.value.intValue())) != 0)
		{
			return new JsonStringSerializable(this.value.toString(), options);
		}
		else
		{
			return new JsonBigDecimalSerializable(this.value, options);
		}
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof JsonBigDecimal)) return false; 
		return this.value.equals(((JsonBigDecimal)o).value);
	}

	@Override
	public int hashCode()
	{
		return this.value.hashCode();
	}

	public String toString()
	{
		return "BigDecimal(" + this.value.toString() + ")";
	}
}
