package net.will_co21.format.json;

public interface IJsonSerializableOptional {
	public String toJson(JsonOption[] options);
	public String toPrettyJson(JsonOptions options);
	public String toPrettyJson(JsonOption[] options);
}
