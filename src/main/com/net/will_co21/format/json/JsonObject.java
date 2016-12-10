package net.will_co21.format.json;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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
			if(prop == null) throw new TypeOfNullableNotAllowedException("Items in the initialization list contain null values.");
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
			if(entry.getValue() == null) throw new TypeOfNullableNotAllowedException("Items in the initialization list contain null values.");
			map.put(entry.getKey(), entry.getValue());
		}
	}

	public IJsonValue get(String key)
	{
		if(!map.containsKey(key)) throw new TypeOfNullableNotAllowedException("Reference by this key is invalid.");
		else return map.get(key);
	}

	public Optional<IJsonValue> getOptional(String key)
	{
		if(!map.containsKey(key)) return Optional.ofNullable(null);
		else return Optional.of(get(key));
	}

	public IJsonValue put(String key, IJsonValue value)
	{
		if(value == null) throw new TypeOfNullableNotAllowedException("null reference was passed as the value of the element.");
		else return map.put(key, value);
	}

	public IJsonValue remove(String key)
	{
		if(key == null) return null;
		return map.remove(key);
	}

	public boolean containsKey(String key)
	{
		return key != null && map.containsKey(key);
	}

	public boolean containsValue(IJsonValue value)
	{
		return value != null && map.containsValue(value);
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
		if(func == null) throw new TypeOfNullableNotAllowedException("The callback function is a null reference.");

		for(Map.Entry<String, IJsonValue> entry: map.entrySet())
		{
			func.accept(new KeyValue<String, IJsonValue>(entry.getKey(), entry.getValue()));
		}
	}

	public <T> TreeMap<String, T> map(Function<KeyValue<String, IJsonValue>, T> func)
	{
		if(func == null) throw new TypeOfNullableNotAllowedException("The callback function is a null reference.");

		TreeMap<String, T> result = new TreeMap<String, T>();

		for(Map.Entry<String, IJsonValue> entry: this.map.entrySet())
		{
			result.put(entry.getKey(), func.apply(new KeyValue<String, IJsonValue>(entry.getKey(), entry.getValue())));
		}

		return result;
	}

	public <T> T fold(BiFunction<T, KeyValue<String, IJsonValue>, T> func, T accumulator)
	{
		if(func == null) throw new TypeOfNullableNotAllowedException("The callback function is a null reference.");

		for(Map.Entry<String, IJsonValue> entry: this.map.entrySet())
		{
			accumulator = func.apply(accumulator, new KeyValue<String, IJsonValue>(entry.getKey(), entry.getValue()));
		}

		return accumulator;
	}

	public IPrettyJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		detector.detect(this);
		detector.push(this);

		TreeMap<String, IPrettyJsonSerializable> map = new TreeMap<String, IPrettyJsonSerializable>();

		for(Map.Entry<String, IJsonValue> entry: this.map.entrySet())
		{
			map.put(entry.getKey(), entry.getValue().toJsonSource(options, detector));
		}

		if(detector.pop() != this)
		{
			throw new InvalidDetectorStateException("CircularReferenceDetector Internal state inconsistency detected.");
		}

		return new JsonObjectSerializable(map, options);
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
