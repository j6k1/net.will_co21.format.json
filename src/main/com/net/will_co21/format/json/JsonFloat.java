package net.will_co21.format.json;

import java.math.BigDecimal;

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
}
