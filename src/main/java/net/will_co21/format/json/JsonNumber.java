package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public abstract class JsonNumber extends JsonValue {

	@Override
	public int getInt() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This value is outside the range of int data type.");
	}

	@Override
	public Optional<Integer> getOptionalInt() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This value is outside the range of int data type.");
	}

	@Override
	public long getLong() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This value is outside the range of long data type.");
	}

	@Override
	public Optional<Long> getOptionalLong() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This value is outside the range of long data type.");
	}

	@Override
	public BigInteger getBigInteger() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This value is outside the range of BigInteger data type.");
	}

	@Override
	public Optional<BigInteger> getOptionalBigInteger() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This value is outside the range of BigInteger data type.");
	}

	@Override
	public float getFloat() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This value is outside the range of float data type.");
	}

	@Override
	public Optional<Float> getOptionalFloat() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This value is outside the range of float data type.");
	}

	@Override
	public double getDouble() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This value is outside the range of double data type.");
	}

	@Override
	public Optional<Double> getOptionalDouble() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This value is outside the range of double data type.");
	}

	@Override
	public BigDecimal getBigDecimal() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This value is outside the range of BigDecimal data type.");
	}

	@Override
	public Optional<BigDecimal> getOptionalBigDecimal() throws NotSupportedMethodException {
		throw new NotSupportedMethodException("This value is outside the range of BigDecimal data type.");
	}
}
