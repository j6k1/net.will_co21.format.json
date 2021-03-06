package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public class JsonSource<T> implements IJsonValue {
	private final IJsonSourceDelegate<T> delegate;
	private final T targetObject;

	public JsonSource(T targetObject, IJsonSourceDelegate<T> delegate)
	{
		if(targetObject == null) throw new TypeOfNullableNotAllowedException("null value was passed in to the JsonSource type constructor.");
		else if(delegate == null) throw new TypeOfNullableNotAllowedException("null value was passed as the value of json serialized object.");

		this.targetObject = targetObject;
		this.delegate = delegate;
	}

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

	public String toPrettyJson()
	{
		return this.toPrettyJson(new JsonOptions(new JsonOption[] {}));
	}

	public String toPrettyJson(JsonOption[] options)
	{
		return this.toJson(new JsonOptions(options));
	}

	public String toPrettyJson(JsonOptions options)
	{
		CircularReferenceDetector detector = new CircularReferenceDetector();

		return this.toJsonSource(options, detector).toPrettyJson(0);
	}

	@Override
	public IPrettyJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector) {
		return delegate.toJsonSource(options, detector, targetObject);
	}
	@Override
	public int getInt() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public Optional<Integer> getOptionalInt() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public long getLong() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public Optional<Long> getOptionalLong() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public BigInteger getBigInteger() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public Optional<BigInteger> getOptionalBigInteger() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public float getFloat() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public Optional<Float> getOptionalFloat() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public double getDouble() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public Optional<Double> getOptionalDouble() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public BigDecimal getBigDecimal() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public Optional<BigDecimal> getOptionalBigDecimal() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public String getString() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public Optional<String> getOptionalString() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public boolean getBoolean() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public Optional<Boolean> getOptionalBoolean() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public IJsonValue get(int index) throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public IJsonValue get(String key) throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public Optional<IJsonValue> getOptional(int index) throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	@Override
	public Optional<IJsonValue> getOptional(String key) throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This method is not implemented. This type is only for serialization.");
	}

	public <R> R toObject(IDeserializeJson<R> deserializer)
	{
		if(deserializer == null)
			throw new TypeOfNullableNotAllowedException("A value of null was passed as the value of the deserializer object.");

		return deserializer.deserializeJson(this);
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof JsonSource)) return false;
		return targetObject.equals(((JsonSource)o).targetObject);
	}

	@Override
	public int hashCode()
	{
		return this.targetObject.hashCode();
	}
}
