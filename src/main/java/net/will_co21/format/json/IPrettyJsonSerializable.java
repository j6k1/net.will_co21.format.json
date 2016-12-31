package net.will_co21.format.json;

public interface IPrettyJsonSerializable {
	public String toPrettyJson(int indent);
	public String toJson();
}
