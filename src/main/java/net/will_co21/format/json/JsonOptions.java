package net.will_co21.format.json;

public class JsonOptions {
	public final int value;

	public JsonOptions(JsonOption[] options)
	{
		int optionValue = 0;

		for(JsonOption option: options)
		{
			optionValue |= option.value;
		}


		this.value = optionValue;
	}

	public boolean hasHexTag()
	{
		return (this.value & JsonOption.HEX_TAG.value) != 0;
	}

	public boolean hasHexAmp()
	{
		return (this.value & JsonOption.HEX_AMP.value) != 0;
	}

	public boolean hasHexApo()
	{
		return (this.value & JsonOption.HEX_APO.value) != 0;
	}

	public boolean hasHexQuot()
	{
		return (this.value & JsonOption.HEX_QUOT.value) != 0;
	}

	public boolean hasNumberToString()
	{
		return (this.value & JsonOption.NUMBER_TO_STRING.value) != 0;
	}

	public boolean hasBigIntAsString()
	{
		return (this.value & JsonOption.BIGINT_AS_STRING.value) != 0;
	}

	public boolean hasBigLongAsString()
	{
		return (this.value & JsonOption.BIGLONG_AS_STRING.value) != 0;
	}

	public boolean hasBigFloatAsString()
	{
		return (this.value & JsonOption.BIGFLOAT_AS_STRING.value) != 0;
	}

	public boolean hasBigDoubleAsString()
	{
		return (this.value & JsonOption.BIGDOUBLE_AS_STRING.value) != 0;
	}

	public boolean hasPrettyPrint()
	{
		return (this.value & JsonOption.PRETTY_PRINT.value) != 0;
	}

	public boolean hasUnEscapedSlashes()
	{
		return (this.value & JsonOption.UNESCAPED_SLASHES.value) != 0;
	}

	public boolean hasEscapedUnicode()
	{
		return (this.value & JsonOption.ESCAPED_UNICODE.value) != 0;
	}
}
