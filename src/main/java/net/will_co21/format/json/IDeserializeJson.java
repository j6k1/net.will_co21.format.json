package net.will_co21.format.json;

public interface IDeserializeJson<T> {
	public T deserializeJson(IJsonValue json);
}
