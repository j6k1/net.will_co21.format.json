package net.will_co21.format.json;

import java.util.Arrays;

public class JsonNumberParser implements IJsonParser {
	public final static boolean[] headChars;

	static {
		headChars = new boolean[128];
		Arrays.fill(headChars, false);
		headChars[(int)'-'] = true;
		headChars[(int)'0'] = true;
		headChars[(int)'1'] = true;
		headChars[(int)'2'] = true;
		headChars[(int)'3'] = true;
		headChars[(int)'4'] = true;
		headChars[(int)'5'] = true;
		headChars[(int)'6'] = true;
		headChars[(int)'7'] = true;
		headChars[(int)'8'] = true;
		headChars[(int)'9'] = true;
	}

	@Override
	public Pair<IJsonValue, Integer> parseJson(String json, int start) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
