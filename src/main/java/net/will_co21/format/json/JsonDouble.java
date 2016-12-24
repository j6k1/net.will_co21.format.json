package net.will_co21.format.json;

import java.math.BigDecimal;
import java.util.Optional;

public class JsonDouble extends JsonNumber {
	protected final double value;

	public JsonDouble(double value)
	{
		this.value = value;
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
		return this.value != 0.0d;
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
		else if(options.hasBigFloatAsString() && this.value > Float.MAX_VALUE)
		{
			return new JsonStringSerializable(this.value + "", options);
		}
		else if(options.hasBigFloatAsString() && this.value < -Float.MAX_VALUE)
		{
			return new JsonStringSerializable(this.value + "", options);
		}
		else
		{
			return new JsonDoubleSerializable(this.value, options);
		}
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof JsonDouble)) return false;
		return this.value == ((JsonDouble)o).value;
	}

	@Override
	public int hashCode()
	{
		return Double.hashCode(this.value);
	}

	public String toString()
	{
		return "Double(" + Double.toString(this.value) + ")";
	}
}
