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
		return (int)this.value != 0.0d;
	}

	@Override
	public Optional<Boolean> getOptionalBoolean() {
		return Optional.of(getBoolean());
	}

	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return null;
	}

	public boolean equals(JsonDouble o)
	{
		return this.value == o.value;
	}

	@Override
	public int hashCode()
	{
		return Double.hashCode(this.value);
	}
}
