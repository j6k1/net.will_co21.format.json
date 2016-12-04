package net.will_co21.format.json;

import java.util.TreeMap;

public class JsonObjectSerializable implements IJsonSerializable, IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final TreeMap<String, IJsonValue> value;

	public JsonObjectSerializable(TreeMap<String, IJsonValue> value, JsonOptions options) {
		this.options = options;
		this.value = value;
	}

	@Override
	public String toJson() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String toJson(int indent) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}
