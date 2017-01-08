package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

abstract public class JsonComposit<T> implements IJsonValue {
	protected T value;
	
	public T get()
	{
		return value;
	}
	
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
	public int getInt() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public Optional<Integer> getOptionalInt() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public long getLong() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public Optional<Long> getOptionalLong() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public Optional<BigInteger> getOptionalBigInteger() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public BigInteger getBigInteger() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public float getFloat() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public Optional<Float> getOptionalFloat() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public double getDouble() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public Optional<Double> getOptionalDouble() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public BigDecimal getBigDecimal() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public Optional<BigDecimal> getOptionalBigDecimal() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public String getString() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public Optional<String> getOptionalString() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public boolean getBoolean() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public Optional<Boolean> getOptionalBoolean() {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public IJsonValue get(int index) {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public IJsonValue get(String key) {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public Optional<IJsonValue> getOptional(int index) {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public Optional<IJsonValue> getOptional(String key) {
		throw new NotImplementedException("this method not implimented.");
	}

	@Override
	public <T> T toObject(IDeserializeJson<T> deserializer) {
		throw new NotImplementedException("this method not implimented.");
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof JsonComposit)) return false;
		else return this.value.equals(((JsonComposit)o).value);
	}
	
	@Override
	public int hashCode()
	{
		return this.value.hashCode();
	}
}
