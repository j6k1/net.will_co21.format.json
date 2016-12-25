package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;

public abstract class JsonNumber extends JsonValue {

	@Override
	public int getInt() throws CanNotStoreInDataTypeException {
		throw new CanNotStoreInDataTypeException("This value is outside the range of int data type.");
	}

	@Override
	public Optional<Integer> getOptionalInt() throws CanNotStoreInDataTypeException {
		throw new CanNotStoreInDataTypeException("This value is outside the range of int data type.");
	}

	@Override
	public long getLong() throws CanNotStoreInDataTypeException {
		throw new CanNotStoreInDataTypeException("This value is outside the range of long data type.");
	}

	@Override
	public Optional<Long> getOptionalLong() throws CanNotStoreInDataTypeException {
		throw new CanNotStoreInDataTypeException("This value is outside the range of long data type.");
	}

	@Override
	public BigInteger getBigInteger() throws CanNotStoreInDataTypeException {
		throw new CanNotStoreInDataTypeException("This value is outside the range of BigInteger data type.");
	}

	@Override
	public Optional<BigInteger> getOptionalBigInteger() throws CanNotStoreInDataTypeException {
		throw new CanNotStoreInDataTypeException("This value is outside the range of BigInteger data type.");
	}

	@Override
	public float getFloat() throws CanNotStoreInDataTypeException {
		throw new CanNotStoreInDataTypeException("This value is outside the range of float data type.");
	}

	@Override
	public Optional<Float> getOptionalFloat() throws CanNotStoreInDataTypeException {
		throw new CanNotStoreInDataTypeException("This value is outside the range of float data type.");
	}

	@Override
	public double getDouble() throws CanNotStoreInDataTypeException {
		throw new CanNotStoreInDataTypeException("This value is outside the range of double data type.");
	}

	@Override
	public Optional<Double> getOptionalDouble() throws CanNotStoreInDataTypeException {
		throw new CanNotStoreInDataTypeException("This value is outside the range of double data type.");
	}

	@Override
	public BigDecimal getBigDecimal() throws CanNotStoreInDataTypeException {
		throw new CanNotStoreInDataTypeException("This value is outside the range of BigDecimal data type.");
	}

	@Override
	public Optional<BigDecimal> getOptionalBigDecimal() throws CanNotStoreInDataTypeException {
		throw new CanNotStoreInDataTypeException("This value is outside the range of BigDecimal data type.");
	}
}
