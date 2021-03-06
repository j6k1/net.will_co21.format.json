package net.will_co21.format.json;

import java.util.ArrayList;

public class JsonArraySerializable extends JsonContainerSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final ArrayList<IPrettyJsonSerializable> value;

	public JsonArraySerializable(ArrayList<IPrettyJsonSerializable> value, JsonOptions options) {
		if(value == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonArraySerializable type constructor.");
		else if(options == null)
			throw new TypeOfNullableNotAllowedException("The value was passed of the option to the constructor of JsonArraySerializable is null.");

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
	public String toPrettyJson(int indent) {
		if(this.value.size() == 0) return "[]";

		StringBuilder sb = new StringBuilder();

		sb.append("[\n");

		for(IPrettyJsonSerializable v: this.value)
		{
			String elm = v.toPrettyJson(indent + 1);
			sb.append(strRepeat("    ", indent + 1) + elm + ",\n");
		}
		sb.deleteCharAt(sb.length() - 2);

		sb.append(strRepeat("    ", indent) + "]");

		return sb.toString();
	}
}
