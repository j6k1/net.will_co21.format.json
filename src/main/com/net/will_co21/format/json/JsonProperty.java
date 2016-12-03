package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonProperty {
	public final String key;
	public final IJsonValue value;

	// 外部からのインスタンス生成防止。定義してるだけで実際には利用しない。
	private JsonProperty()
	{
		this.key = null;
		this.value = null;
	}

	private JsonProperty(String key, IJsonValue value)
	{
		this.key = key;
		this.value = value;
	}

	public static JsonProperty create(String key, IJsonValue value)
	{
		if(value == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonProperty type constructor.");

		return new JsonProperty(key, value);
	}

	public static <T> JsonProperty create(String key, T obj, IJsonSourceDelegate<T> sourceCreator)
	{
		if(obj == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonProperty type constructor.");
		else if(sourceCreator == null)
			throw new TypeOfNullableNotAllowedException("IJsonSourceDelegate type object is null.");

		return new JsonProperty(key, new JsonSource<T>(obj, sourceCreator));
	}

	public static JsonProperty create(String key, int value)
	{
		return new JsonProperty(key, new JsonInt(value));
	}

	public static JsonProperty create(String key, long value)
	{
		return new JsonProperty(key, new JsonLong(value));
	}

	public static JsonProperty create(String key, float value)
	{
		return new JsonProperty(key, new JsonFloat(value));
	}

	public static JsonProperty create(String key, double value)
	{
		return new JsonProperty(key, new JsonDouble(value));
	}

	public static JsonProperty create(String key, BigInteger value)
	{
		if(value == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonProperty type constructor.");

		return new JsonProperty(key, new JsonBigInteger(value));
	}

	public static JsonProperty create(String key, BigDecimal value)
	{
		if(value == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonProperty type constructor.");

		return new JsonProperty(key, new JsonBigDecimal(value));
	}

	public static JsonProperty create(String key, String value)
	{
		if(value == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonProperty type constructor.");

		return new JsonProperty(key, new JsonString(value));
	}

	public static JsonProperty create(String key, boolean value)
	{
		return new JsonProperty(key, new JsonBoolean(value));
	}
}
