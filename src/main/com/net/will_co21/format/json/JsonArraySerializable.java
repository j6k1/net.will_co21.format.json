package net.will_co21.format.json;

import java.util.ArrayList;

public class JsonArraySerializable extends JsonContainerSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final ArrayList<IPrettyJsonSerializable> value;

	public JsonArraySerializable(ArrayList<IPrettyJsonSerializable> value, JsonOptions options) {
		this.options = options;
		this.value = value;
	}

	@Override
	public String toJson() {
		String[] values = new String[this.value.size()];

		int i = 0;

		for(IPrettyJsonSerializable v: this.value)
		{
			values[i++] = v.toJson();
		}

		return "[" + String.join(",", values) + "]";
	}

	@Override
	public String toJson(int indent) {
		if(this.value.size() == 0) return "[]";

		StringBuilder sb = new StringBuilder();

		sb.append('[');

		for(IPrettyJsonSerializable v: this.value)
		{
			String elm = v.toJson(indent + 1);
			sb.append(strRepeat("    ", indent + 1) + elm + ",\n");
		}
		sb.deleteCharAt(sb.length() - 2);

		sb.append(strRepeat("    ", indent) + "]");

		return sb.toString();
	}
}
