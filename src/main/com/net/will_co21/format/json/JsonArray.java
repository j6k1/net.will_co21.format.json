package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class JsonArray extends JsonValue {
	protected final ArrayList<IJsonValue> arr;

	public JsonArray()
	{
		this.arr = new ArrayList<IJsonValue>();
	}

	public JsonArray(IJsonValue[] values)
	{
		this();

		if(values == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonArray type constructor.");

		for(IJsonValue v: values)
		{
			if(v == null)
				throw new TypeOfNullableNotAllowedException("Items in the initialization list contain null values.");

			this.arr.add(v);
		}
	}

	public JsonArray(ArrayList<IJsonValue> values)
	{
		this();

		if(values == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonArray type constructor.");

		for(IJsonValue v: values)
		{
			if(v == null)
				throw new TypeOfNullableNotAllowedException("Items in the initialization list contain null values.");

			this.arr.add(v);
		}
	}

	public JsonArray(int[] values)
	{
		this();

		if(values == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonArray type constructor.");

		for(int v: values)
		{
			this.arr.add(new JsonInt(v));
		}
	}

	public JsonArray(long[] values)
	{
		this();

		if(values == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonArray type constructor.");

		for(long v: values)
		{
			this.arr.add(new JsonLong(v));
		}
	}

	public JsonArray(float[] values)
	{
		this();

		if(values == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonArray type constructor.");

		for(float v: values)
		{
			this.arr.add(new JsonFloat(v));
		}
	}

	public JsonArray(double[] values)
	{
		this();

		if(values == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonArray type constructor.");

		for(double v: values)
		{
			this.arr.add(new JsonDouble(v));
		}
	}

	public JsonArray(BigInteger[] values)
	{
		this();

		if(values == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonArray type constructor.");

		for(BigInteger v: values)
		{
			if(v == null)
				throw new TypeOfNullableNotAllowedException("Items in the initialization list contain null values.");

			this.arr.add(new JsonBigInteger(v));
		}
	}

	public JsonArray(BigDecimal[] values)
	{
		this();

		if(values == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonArray type constructor.");

		for(BigDecimal v: values)
		{
			if(v == null)
				throw new TypeOfNullableNotAllowedException("Items in the initialization list contain null values.");

			this.arr.add(new JsonBigDecimal(v));
		}
	}

	public JsonArray(String[] values)
	{
		this();

		if(values == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonArray type constructor.");

		for(String v: values)
		{
			if(v == null)
				throw new TypeOfNullableNotAllowedException("Items in the initialization list contain null values.");

			this.arr.add(new JsonString(v));
		}
	}

	public JsonArray(boolean[] values)
	{
		this();

		if(values == null)
			throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonArray type constructor.");

		for(boolean v: values)
		{
			this.arr.add(new JsonBoolean(v));
		}
	}

	public int size()
	{
		return this.arr.size();
	}

	public IJsonValue get(int index)
	{
		return this.arr.get(index);
	}

	public Optional<IJsonValue> getOptional(int index)
	{
		return Optional.of(get(index));
	}

	public IJsonValue set(int index, IJsonValue v)
	{
		return this.arr.set(index, v);
	}

	public boolean add(IJsonValue v)
	{
		return this.arr.add(v);
	}

	public boolean add(int index, IJsonValue v)
	{
		this.arr.add(index, v);

		return true;
	}

	public IJsonValue removeAt(int index)
	{
		return this.arr.remove(index);
	}

	public boolean remove(IJsonValue o)
	{
		return this.arr.remove(o);
	}

	public ArrayList<IJsonValue> getValues()
	{
		return this.arr;
	}

	public void each(Consumer<KeyValue<Integer, IJsonValue>> func)
	{
		int i = 0;

		for(IJsonValue v: this.arr)
		{
			func.accept(new KeyValue<Integer, IJsonValue>(i, v));
			++i;
		}
	}

	public <T> ArrayList<T> map(Function<KeyValue<Integer, IJsonValue>, T> func)
	{
		ArrayList<T> result = new ArrayList<T>();

		int i = 0;

		for(IJsonValue v: this.arr)
		{
			result.add(func.apply(new KeyValue<Integer, IJsonValue>(i, v)));
			++i;
		}

		return result;
	}

	public <T> T fold(BiFunction<T, KeyValue<Integer, IJsonValue>, T> func, T accumulator)
	{
		int i = 0;

		for(IJsonValue v: this.arr)
		{
			accumulator = func.apply(accumulator, new KeyValue<Integer, IJsonValue>(i, v));
			++i;
		}

		return accumulator;
	}

	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return null;
	}


	public boolean equals(JsonArray o)
	{
		return this.arr.equals(o.arr);
	}

	@Override
	public int hashCode()
	{
		return this.arr.hashCode();
	}
}
