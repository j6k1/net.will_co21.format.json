package net.will_co21.format.json;

import java.math.BigDecimal;
import java.util.Optional;

public class JsonFloat extends JsonNumber {
	protected final float value;

	public JsonFloat(float value)
	{
		this.value = value;
	}

	@Override
	public float getFloat() {
		return this.value;
	}

	@Override
	public Optional<Float> getOptionalFloat() {
		return Optional.of(getFloat());
	}

	@Override
	public double getDouble() {
		return this.value;
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
		return this.value != 0.0f;
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
		else if(options.hasBigLongAsString() && this.value > Long.MAX_VALUE)
		{
			return new JsonStringSerializable(this.value + "", options);
		}
		else if(options.hasBigLongAsString() && this.value < Long.MIN_VALUE)
		{
			return new JsonStringSerializable(this.value + "", options);
		}
		else if(options.hasBigLongAsString() && this.value != (float)(long)this.value)
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
		else if(options.hasBigIntAsString() && this.value != (float)(int)this.value)
		{
			return new JsonStringSerializable(this.value + "", options);
		}
		else
		{
			return new JsonFloatSerializable(this.value, options);
		}
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof JsonFloat)) return false;
		return this.value == ((JsonFloat)o).value;
	}

	@Override
	public int hashCode()
	{
		return Float.hashCode(this.value);
	}

	public String toString()
	{
		return "Float(" + Float.toString(this.value) + ")";
	}
}
