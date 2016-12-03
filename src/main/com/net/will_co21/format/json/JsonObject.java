package net.will_co21.format.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class JsonObject extends JsonValue {
	protected final TreeMap<String, IJsonValue> map;

	public JsonObject()
	{
		map = new TreeMap<String, IJsonValue>();
	}

	public JsonObject(JsonProperty[] properties)
	{
		this();

		if(properties == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonObject type constructor.");

		for(JsonProperty prop: properties)
		{
			map.put(prop.key, prop.value);
		}
	}

	public JsonObject(List<JsonProperty> properties)
	{
		this();

		if(properties == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonObject type constructor.");

		for(JsonProperty prop: properties)
		{
			map.put(prop.key, prop.value);
		}
	}

	public JsonObject(Map<String, IJsonValue> properties)
	{
		this();

		if(properties == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonObject type constructor.");

		for(Map.Entry<String, IJsonValue> entry: properties.entrySet())
		{
			map.put(entry.getKey(), entry.getValue());
		}
	}

	public IJsonValue get(String key)
	{
		return map.get(key);
	}

	public IJsonValue put(String key, IJsonValue value)
	{
		return map.put(key, value);
	}

	public IJsonValue remove(String key)
	{
		return map.remove(key);
	}

	public boolean containsKey(String key)
	{
		return map.containsKey(key);
	}

	public boolean containsValue(IJsonValue value)
	{
		return map.containsValue(value);
	}

	public int size()
	{
		return map.size();
	}

	public Set<Map.Entry<String, IJsonValue>> entrySet()
	{
		return map.entrySet();
	}

	public void each(Consumer<KeyValue<String, IJsonValue>> func)
	{
		for(Map.Entry<String, IJsonValue> entry: map.entrySet())
		{
			func.accept(new KeyValue<String, IJsonValue>(entry.getKey(), entry.getValue()));
		}
	}

	public <T> ArrayList<T> map(Function<KeyValue<String, IJsonValue>, T> func)
	{
		ArrayList<T> result = new ArrayList<T>();

		for(Map.Entry<String, IJsonValue> entry: map.entrySet())
		{
			result.add(func.apply(new KeyValue<String, IJsonValue>(entry.getKey(), entry.getValue())));
		}

		return result;
	}

	public <T> T fold(BiFunction<T, KeyValue<String, IJsonValue>, T> func, T accumulator)
	{
		for(Map.Entry<String, IJsonValue> entry: map.entrySet())
		{
			accumulator = func.apply(accumulator, new KeyValue<String, IJsonValue>(entry.getKey(), entry.getValue()));
		}

		return accumulator;
	}

	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return null;
	}

	public boolean equals(JsonObject o)
	{
		return this.map.equals(o.map);
	}

	@Override
	public int hashCode()
	{
		return this.map.hashCode();
	}
}
