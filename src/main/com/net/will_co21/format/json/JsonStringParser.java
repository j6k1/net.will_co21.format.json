package net.will_co21.format.json;

import java.util.Arrays;

public class JsonStringParser implements IJsonParser {
	public final static boolean[] headChars;

	static {
		headChars = new boolean[128];
		Arrays.fill(headChars, false);
		headChars[(int)'"'] = true;
	}

	@Override
	public Pair<IJsonValue, Integer> parseJson(String json, int start) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}


}
