package net.will_co21.format.json;

import java.math.BigDecimal;

public class JsonBigDecimalSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final BigDecimal value;

	public JsonBigDecimalSerializable(BigDecimal value, JsonOptions options)
	{
		if(value == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonBigDecimalSerializable type constructor.");
		else if(options == null)
			throw new TypeOfNullableNotAllowedException("The value was passed of the option to the constructor of JsonBigDecimalSerializable is null.");

		this.value = value;
		this.options = options;
	}

	@Override
	public String toJson()
	{
		return this.value.toString();
	}

	@Override
	public String toPrettyJson(int indent)
	{
		return toJson();
	}
}
