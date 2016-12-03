package net.will_co21.format.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.TreeMap;

public interface IJsonValue extends IJsonSource, IJsonSerializable, IJsonSerializableOptional {
	public int getInt();
	public long getLong();
	public BigInteger getBigInteger();
	public float getFloat();
	public double getDouble();
	public BigDecimal getBigDecimal();
	public String getString();
	public boolean getBoolean();
}
