package net.will_co21.format.json;

import java.math.BigDecimal;

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
	public BigDecimal getBigDecimal() {
		return BigDecimal.valueOf(this.value);
	}

	@Override
	public String getString() {
		return this.value + "";
	}

	@Override
	public boolean getBoolean() {
		return (int)this.value != 0;
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
