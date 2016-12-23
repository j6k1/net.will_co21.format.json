package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

public class JsonNull extends JsonValue {

	public IPrettyJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return new JsonNullSerializable(options);
	}

	@Override
	public int getInt() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<Integer> getOptionalInt() {
		return Optional.empty();
	}

	@Override
	public long getLong() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<Long> getOptionalLong() {
		return Optional.empty();
	}

	@Override
	public BigInteger getBigInteger() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<BigInteger> getOptionalBigInteger() {
		return Optional.empty();
	}

	@Override
	public float getFloat() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<Float> getOptionalFloat() {
		return Optional.empty();
	}

	@Override
	public double getDouble() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<Double> getOptionalDouble() {
		return Optional.empty();
	}

	@Override
	public BigDecimal getBigDecimal() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<BigDecimal> getOptionalBigDecimal() {
		return Optional.empty();
	}

	@Override
	public String getString() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<String> getOptionalString() {
		return Optional.empty();
	}

	@Override
	public boolean getBoolean() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<Boolean> getOptionalBoolean() {
		return Optional.empty();
	}

	public IJsonValue get(String key)
	{
		throw new TypeOfNullableNotAllowedException("Members can not be referenced. Invalid reference to container object.");
	}

	public IJsonValue get(int index)
	{
		throw new TypeOfNullableNotAllowedException("Members can not be referenced. Invalid reference to container object.");
	}

	public Optional<IJsonValue> getOptional(String key)
	{
		return Optional.empty();
	}

	public Optional<IJsonValue> getOptional(int index)
	{
		return Optional.empty();
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof JsonNull)) return false;
		else return true;
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(null);
	}
}
