package net.will_co21.format.json;

public interface IJsonSourceDelegate<T> {
	public IPrettyJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector, T targetObject);
}
