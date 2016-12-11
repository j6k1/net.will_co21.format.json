package net.will_co21.format.json;

public interface IJsonSource {
	public IPrettyJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector);
}
