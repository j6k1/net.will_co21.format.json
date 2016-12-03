package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.TreeMap;

public class JsonSource<T> implements IJsonValue {
	private final IJsonSourceDelegate<T> delegate;
	private final T targetObject;

	public JsonSource(T targetObject, IJsonSourceDelegate<T> delegate)
	{
		this.targetObject = targetObject;
		this.delegate = delegate;
	}

	@Override
	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector) {
		return delegate.toJsonSource(options, detector, targetObject);
	}

	@Override
	public int getInt() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public long getLong() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public BigInteger getBigInteger() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public float getFloat() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public double getDouble() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public BigDecimal getBigDecimal() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public String getString() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public boolean getBoolean() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}
}
