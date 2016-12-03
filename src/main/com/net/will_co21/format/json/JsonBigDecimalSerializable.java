package net.will_co21.format.json;

import java.math.BigDecimal;

public class JsonBigDecimalSerializable {
	protected final JsonOptions options;
	protected final BigDecimal value;

	public JsonBigDecimalSerializable(BigDecimal value, JsonOptions options)
	{
		this.value = value;
		this.options = options;
	}

	public String toJson()
	{
		return null;
	}
}
