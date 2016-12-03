package net.will_co21.format.json;

import java.math.BigDecimal;

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
	public String getString() {
		return this.value.toString();
	}

	@Override
	public boolean getBoolean() {
		return this.value.compareTo(new BigDecimal(0)) == 0;
	}

	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return null;
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
