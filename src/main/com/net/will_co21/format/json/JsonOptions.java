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
}
