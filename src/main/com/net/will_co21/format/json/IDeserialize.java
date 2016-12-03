package net.will_co21.format.json;

public interface IDeserialize<T> {
	public T deserializeFromJson(IJsonValue json);
}
