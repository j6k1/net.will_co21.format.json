package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

public class JsonNull extends JsonValue {

	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return new JsonNullSerializable(options);
	}

	@Override
	public int getInt() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<Integer> getOptionalInt() {
		return Optional.ofNullable(null);
	}

	@Override
	public long getLong() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<Long> getOptionalLong() {
		return Optional.ofNullable(null);
	}

	@Override
	public BigInteger getBigInteger() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<BigInteger> getOptionalBigInteger() {
		return Optional.ofNullable(null);
	}

	@Override
	public float getFloat() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<Float> getOptionalFloat() {
		return Optional.ofNullable(null);
	}

	@Override
	public double getDouble() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<Double> getOptionalDouble() {
		return Optional.ofNullable(null);
	}

	@Override
	public BigDecimal getBigDecimal() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<BigDecimal> getOptionalBigDecimal() {
		return Optional.ofNullable(null);
	}

	@Override
	public String getString() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<String> getOptionalString() {
		return Optional.ofNullable(null);
	}

	@Override
	public boolean getBoolean() {
		throw new TypeOfNullableNotAllowedException("This value cannot be referenced. Invalid reference to object.");
	}

	@Override
	public Optional<Boolean> getOptionalBoolean() {
		return Optional.ofNullable(null);
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
		return Optional.ofNullable(null);
	}

	public Optional<IJsonValue> getOptional(int index)
	{
		return Optional.ofNullable(null);
	}

	public boolean equals(JsonNull o)
	{
		return true;
	}

	@Override
	public int hashCode()
	{
		return Objects.hashCode(null);
	}
}
