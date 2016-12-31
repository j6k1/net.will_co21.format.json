package net.will_co21.format.json;

import java.util.Map;
import java.util.TreeMap;

public class JsonObjectSerializable extends JsonContainerSerializable implements IPrettyJsonSerializable {
	protected final JsonOptions options;
	protected final TreeMap<String, IPrettyJsonSerializable> value;

	public JsonObjectSerializable(TreeMap<String, IPrettyJsonSerializable> value, JsonOptions options) {
		if(value == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonObjectSerializable type constructor.");
		else if(options == null)
			throw new TypeOfNullableNotAllowedException("The value was passed of the option to the constructor of JsonObjectSerializable is null.");

		this.options = options;
		this.value = value;
	}

	@Override
	public String toJson() {
		String[] entrySet = new String[this.value.size()];

		int i = 0;

		for(Map.Entry<String, IPrettyJsonSerializable> entry: this.value.entrySet())
		{
			entrySet[i++] = new JsonStringSerializable(entry.getKey(), this.options).toJson() + ":" + entry.getValue().toJson();
		}

		return "{" + String.join(",", entrySet) + "}";
	}

	@Override
	public String toPrettyJson(int indent) {
		if(this.value.size() == 0) return "{}";

		StringBuilder sb = new StringBuilder();

		sb.append("{\n");

		for(Map.Entry<String, IPrettyJsonSerializable> entry: this.value.entrySet())
		{
			String kv = new JsonStringSerializable(entry.getKey(), this.options).toJson() + ": " + entry.getValue().toPrettyJson(indent + 1);
			sb.append(strRepeat("    ", indent + 1) + kv + ",\n");
		}

		sb.deleteCharAt(sb.length() - 2);

		sb.append(strRepeat("    ", indent) + "}");

		return sb.toString();
	}
}
