package net.will_co21.format.json;

import java.math.BigDecimal;

public class JsonBigDecimalSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final BigDecimal value;

	public JsonBigDecimalSerializable(BigDecimal value, JsonOptions options)
	{
		this.value = value;
		this.options = options;
	}

	@Override
	public String toJson()
	{
		return this.value.toString();
	}

	@Override
	public String toJson(int indent)
	{
		return toJson();
	}
}
