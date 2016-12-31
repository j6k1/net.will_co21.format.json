package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public abstract class JsonValue implements IJsonValue {

	@Override
	abstract public IPrettyJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector);

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

		if(options.hasPrettyPrint())
		{
			return this.toJsonSource(options, detector).toPrettyJson(0);
		}
		else
		{
			return this.toJsonSource(options, detector).toJson();
		}
	}

	@Override
	public int getInt() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public Optional<Integer> getOptionalInt() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public long getLong() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public Optional<Long> getOptionalLong() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public BigInteger getBigInteger() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public Optional<BigInteger> getOptionalBigInteger() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public float getFloat() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public Optional<Float> getOptionalFloat() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public double getDouble() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public Optional<Double> getOptionalDouble() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public BigDecimal getBigDecimal() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public Optional<BigDecimal> getOptionalBigDecimal() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public String getString() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public Optional<String> getOptionalString() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public boolean getBoolean() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public Optional<Boolean> getOptionalBoolean() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public IJsonValue get(int index) throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public IJsonValue get(String key) throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public Optional<IJsonValue> getOptional(int index) throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	@Override
	public Optional<IJsonValue> getOptional(String key) throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not supported by this object type.");
	}

	public <R> R toObject(IDeserializeJson<R> deserializer)
	{
		if(deserializer == null)
			throw new TypeOfNullableNotAllowedException("A value of null was passed as the value of the deserializer object.");

		return deserializer.deserializeJson(this);
	}

}
