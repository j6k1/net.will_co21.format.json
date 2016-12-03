package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public interface IJsonValue extends IJsonSource, IJsonSerializable, IJsonSerializableOptional {
	public int getInt();
	public Optional<Integer> getOptionalInt();
	public long getLong();
	public Optional<Long> getOptionalLong();
	public Optional<BigInteger> getOptionalBigInteger();
	public BigInteger getBigInteger();
	public float getFloat();
	public Optional<Float> getOptionalFloat();
	public double getDouble();
	public Optional<Double> getOptionalDouble();
	public BigDecimal getBigDecimal();
	public Optional<BigDecimal> getOptionalBigDecimal();
	public String getString();
	public Optional<String> getOptionalString();
	public boolean getBoolean();
	public Optional<Boolean> getOptionalBoolean();
	public IJsonValue get(int index);
	public IJsonValue get(String key);
	public Optional<IJsonValue> getOptional(int index);
	public Optional<IJsonValue> getOptional(String key);
	public <T> T toObject(IDeserializeJson<T> deserializer);
}
