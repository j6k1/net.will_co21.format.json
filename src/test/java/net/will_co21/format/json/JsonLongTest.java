package net.will_co21.format.json;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

public class JsonLongTest {

	@Test
	public void testGetLong() {
		long value = 9223372036854775807L;
		assertThat((new JsonLong(value)).getLong(),
				is(value));
	}

	@Test
	public void testGetOptionalLong() {
		long value = 9223372036854775807L;
		assertThat((new JsonLong(value)).getOptionalLong().get(),
				is(value));
	}

	@Test
	public void testGetBigInteger() {
		long value = 9223372036854775807L;
		assertThat((new JsonLong(value)).getBigInteger(),
				is(BigInteger.valueOf(value)));
	}

	@Test
	public void testGetOptionalBigInteger() {
		long value = 9223372036854775807L;
		assertThat((new JsonLong(value)).getOptionalBigInteger().get(),
				is(BigInteger.valueOf(value)));
	}

	@Test
	public void testGetBigDecimal() {
		long value = 9223372036854775807L;
		assertThat((new JsonLong(value)).getBigDecimal(),
				is(BigDecimal.valueOf(value)));
	}

	@Test
	public void testGetOptionalBigDecimal() {
		long value = 9223372036854775807L;
		assertThat((new JsonLong(value)).getOptionalBigDecimal().get(),
				is(BigDecimal.valueOf(value)));
	}

	@Test
	public void testGetString() {
		long value = 9223372036854775807L;
		assertThat((new JsonLong(value)).getString(), is(Long.toString(value)));
	}

	@Test
	public void testGetOptionalString() {
		long value = 9223372036854775807L;
		assertThat((new JsonLong(value)).getOptionalString().get(), is(Long.toString(value)));
	}

	@Test
	public void testGetBooleanTrue() {
		long value = 1;

		assertThat((new JsonLong(value)).getBoolean(), is(true));
	}

	@Test
	public void testGetBooleanFalse() {
		long value = 0;

		assertThat((new JsonLong(value)).getBoolean(), is(false));
	}

	@Test
	public void testGetOptionalBooleanTrue() {
		long value = 1;

		assertThat((new JsonLong(value)).getOptionalBoolean().get(), is(true));
	}

	@Test
	public void testGetOptionalBooleanFalse() {
		long value = 0;

		assertThat((new JsonBigInteger(value)).getOptionalBoolean().get(), is(false));
	}


	@Test
	public void testToJsonSource()
	{
		long value = 9223372036854775807L;

		assertThat((new JsonLong(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {}),
				new CircularReferenceDetector()).toJson(), is(Long.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionNumberOfString()
	{
		long value = 9223372036854775807L;

		assertThat((new JsonLong(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.NUMBER_TO_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + value + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigIntAsStringCaseGreaterIntMax()
	{
		long value = 2147483648L;

		assertThat((new JsonLong(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGINT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + value + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigIntAsStringCaseLessIntMin()
	{
		long value = -2147483649L;

		assertThat((new JsonLong(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGINT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + value + "\""));
	}


	@Test
	public void testToJsonSourceOptionBigLongAsStringCaseLongMax()
	{
		long value = 9223372036854775807L;

		assertThat((new JsonLong(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGLONG_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Long.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionBigLongAsStringLongMin()
	{
		long value = -9223372036854775808L;

		assertThat((new JsonLong(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGLONG_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Long.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionBigIntAsStringCaseIntMax()
	{
		long value = 2147483647L;

		assertThat((new JsonLong(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGINT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Long.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionBigIntAsStringCaseIntMin()
	{
		long value = -2147483648L;

		assertThat((new JsonLong(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGINT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Long.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionBigDoubleAsStringCaseGreaterDoubleMantissaPartMax()
	{
		long value = 0x3FFFFFFFFFFFFFL;

		assertThat((new JsonLong(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGDOUBLE_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + value + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigDoubleAsStringCaseDoubleMantissaPartMax()
	{
		long value = 0x1FFFFFFFFFFFFFL;

		assertThat((new JsonLong(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGDOUBLE_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Long.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseGreaterFloatMantissaPartMax()
	{
		long value = 0x1FFFFFFL;

		assertThat((new JsonLong(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + value + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseFloatMantissaPartMax()
	{
		long value = 0xFFFFFFL;

		assertThat((new JsonLong(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Long.toString(value)));
	}
}
