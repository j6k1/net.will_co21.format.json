package net.will_co21.format.json;

public interface IJsonSource {
	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector);
}
