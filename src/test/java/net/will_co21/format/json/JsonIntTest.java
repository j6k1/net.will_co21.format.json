package net.will_co21.format.json;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

public class JsonIntTest {

	@Test
	public void testGetInt() {
		int value = 2147483647;
		assertThat((new JsonInt(value)).getInt(),
				is(value));
	}

	@Test
	public void testGetOptionalInt() {
		int value = 2147483647;
		assertThat((new JsonInt(value)).getOptionalInt().get(),
				is(value));
	}

	@Test
	public void testGetLong() {
		int value = 2147483647;
		assertThat((new JsonInt(value)).getLong(),
				is((long)value));
	}

	@Test
	public void testGetOptionalLong() {
		int value = 2147483647;
		assertThat((new JsonInt(value)).getOptionalLong().get(),
				is((long)value));
	}

	@Test
	public void testGetDouble() {
		int value = 2147483647;
		assertThat((new JsonInt(value)).getDouble(),
				is((double)value));
	}

	@Test
	public void testGetOptionalDouble() {
		int value = 2147483647;
		assertThat((new JsonInt(value)).getOptionalDouble().get(),
				is((double)value));
	}

	@Test
	public void testGetBigInteger() {
		int value = 2147483647;
		assertThat((new JsonInt(value)).getBigInteger(),
				is(BigInteger.valueOf(value)));
	}

	@Test
	public void testGetOptionalBigInteger() {
		int value = 2147483647;
		assertThat((new JsonInt(value)).getOptionalBigInteger().get(),
				is(BigInteger.valueOf(value)));
	}

	@Test
	public void testGetBigDecimal() {
		int value = 2147483647;
		assertThat((new JsonInt(value)).getBigDecimal(),
				is(BigDecimal.valueOf(value)));
	}

	@Test
	public void testGetOptionalBigDecimal() {
		int value = 2147483647;
		assertThat((new JsonInt(value)).getOptionalBigDecimal().get(),
				is(BigDecimal.valueOf(value)));
	}

	@Test
	public void testGetString() {
		int value = 2147483647;
		assertThat((new JsonInt(value)).getString(), is(Integer.toString(value)));
	}

	@Test
	public void testGetOptionalString() {
		int value = 2147483647;
		assertThat((new JsonInt(value)).getOptionalString().get(), is(Integer.toString(value)));
	}

	@Test
	public void testGetBooleanTrue() {
		int value = 1;

		assertThat((new JsonInt(value)).getBoolean(), is(true));
	}

	@Test
	public void testGetBooleanFalse() {
		int value = 0;

		assertThat((new JsonInt(value)).getBoolean(), is(false));
	}

	@Test
	public void testGetOptionalBooleanTrue() {
		int value = 1;

		assertThat((new JsonInt(value)).getOptionalBoolean().get(), is(true));
	}

	@Test
	public void testGetOptionalBooleanFalse() {
		long value = 0;

		assertThat((new JsonBigInteger(value)).getOptionalBoolean().get(), is(false));
	}


	@Test
	public void testToJsonSource()
	{
		int value = 2147483647;

		assertThat((new JsonInt(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {}),
				new CircularReferenceDetector()).toJson(), is(Integer.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionNumberOfString()
	{
		int value = 2147483647;

		assertThat((new JsonInt(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.NUMBER_TO_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + value + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigIntAsStringCaseIntMax()
	{
		int value = 2147483647;

		assertThat((new JsonInt(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGINT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Integer.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionBigIntAsStringCaseIntMin()
	{
		int value = -2147483648;

		assertThat((new JsonInt(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGINT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Integer.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseGreaterFloatMantissaPartMax()
	{
		int value = 0x1FFFFFF;

		assertThat((new JsonInt(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + value + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseFloatMantissaPartMax()
	{
		int value = 0xFFFFFF;

		assertThat((new JsonInt(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Integer.toString(value)));
	}
}
