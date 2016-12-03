package net.will_co21.format.json;

public interface IJsonSerializableOptional {
	public String toJson(JsonOptions options);
	public String toJson(JsonOption[] options);
}
