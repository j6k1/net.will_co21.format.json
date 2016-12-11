package net.will_co21.format.json;

import java.util.Arrays;

public abstract class JsonContainerParser implements IJsonParser {
	private final static boolean[] spaceList;

	static {
		spaceList = new boolean[128];
		Arrays.fill(spaceList, false);

		spaceList[(int)' '] = true;
		spaceList[(int)'\t'] = true;
		spaceList[(int)'\r'] = true;
		spaceList[(int)'\n'] = true;
	}

	protected int skipWhiteSpace(String json, int start)
	{
		if(start >= json.length()) return start;

		for(int i = start, l = json.length(); i < l; i++)
		{
			char c = json.charAt(i);

			if(c >= 128 || !spaceList[(int)c])
			{
				return i;
			}
		}

		return json.length() - 1;
	}
}
