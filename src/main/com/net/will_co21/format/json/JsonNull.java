package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

public class JsonNull extends JsonValue {

	public IJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return null;
	}

	@Override
	public Optional<Integer> getOptionalInt() {
		return Optional.ofNullable(null);
	}

	@Override
	public Optional<Long> getOptionalLong() {
		return Optional.ofNullable(null);
	}

	@Override
	public Optional<BigInteger> getOptionalBigInteger() {
		return Optional.ofNullable(null);
	}

	@Override
	public Optional<Float> getOptionalFloat() {
		return Optional.ofNullable(null);
	}

	@Override
	public Optional<Double> getOptionalDouble() {
		return Optional.ofNullable(null);
	}

	@Override
	public Optional<BigDecimal> getOptionalBigDecimal() {
		return Optional.ofNullable(null);
	}

	@Override
	public Optional<String> getOptionalString() {
		return Optional.ofNullable(null);
	}

	@Override
	public Optional<Boolean> getOptionalBoolean() {
		return Optional.ofNullable(null);
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
