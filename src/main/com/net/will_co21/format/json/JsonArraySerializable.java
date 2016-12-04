package net.will_co21.format.json;

import java.util.ArrayList;

public class JsonArraySerializable implements IJsonSerializable, IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final ArrayList<IJsonValue> value;

	public JsonArraySerializable(ArrayList<IJsonValue> value, JsonOptions options) {
		this.options = options;
		this.value = value;
	}

	@Override
	public String toJson(int index) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String toJson() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
