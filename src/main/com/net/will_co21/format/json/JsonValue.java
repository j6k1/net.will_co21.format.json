package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.TreeMap;

public abstract class JsonValue implements IJsonValue {

	@Override
	abstract public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector);

	public String toJson()
	{
		return this.toJson(new JsonOptions(new JsonOption[] {}));
	}

	public String toJson(JsonOption[] options)
	{
		return this.toJson(new JsonOptions(options));
	}

	public String toJson(JsonOptions options)
	{
		CircularReferenceDetector detector = new CircularReferenceDetector();

		return this.toJsonSource(options, detector).toJson();
	}

	@Override
	public int getInt() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public long getLong() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public BigInteger getBigInteger() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public float getFloat() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public double getDouble() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public BigDecimal getBigDecimal() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public String getString() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public boolean getBoolean() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}
}
