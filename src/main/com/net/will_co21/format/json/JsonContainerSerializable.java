package net.will_co21.format.json;

public abstract class JsonContainerSerializable implements IPrettyJsonSerializable {
	protected String strRepeat(String str, int rep)
	{
		String result = "";

		for(int i=0; i < rep; i++) result += str;

		return result;
	}
}
