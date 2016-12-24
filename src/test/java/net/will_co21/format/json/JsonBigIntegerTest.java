package net.will_co21.format.json;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;

public class JsonBigIntegerTest {
	@Test
	public void testJsonBigIntegerNullString() {
		try {
			String value = null;
			new JsonBigInteger(value);
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonBigInteger type constructor."));
		}
	}

	@Test
	public void testJsonBigIntegerNullBigInteger() {
		try {
			BigInteger value = null;
			new JsonBigInteger(value);
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonBigInteger type constructor."));
		}
	}

	@Test
	public void testGetBigDecimal() {
		String strValue = "9223372036854775808";
		assertThat((new JsonBigInteger(strValue)).getBigDecimal(),
				is(new BigDecimal(new BigInteger(strValue))));
	}

	@Test
	public void testGetOptionalBigDecimal() {
		String strValue = "9223372036854775808";
		assertThat((new JsonBigInteger(strValue)).getOptionalBigDecimal().get(),
				is(new BigDecimal(new BigInteger(strValue))));
	}

	@Test
	public void testGetString() {
		String strValue = "9223372036854775808";
		assertThat((new JsonBigInteger(strValue)).getString(), is(strValue));
	}

	@Test
	public void testGetOptionalString() {
		String strValue = "9223372036854775808";
		assertThat((new JsonBigInteger(strValue)).getOptionalString().get(), is(strValue));
	}

	@Test
	public void testGetBooleanTrue() {
		String strValue = "1";

		assertThat((new JsonBigInteger(strValue)).getBoolean(), is(true));
	}

	@Test
	public void testGetBooleanFalse() {
		String strValue = "0";

		assertThat((new JsonBigInteger(strValue)).getBoolean(), is(false));
	}

	@Test
	public void testGetOptionalBooleanTrue() {
		String strValue = "1";

		assertThat((new JsonBigInteger(strValue)).getOptionalBoolean().get(), is(true));
	}

	@Test
	public void testGetOptionalBooleanFalse() {
		String strValue = "0";

		assertThat((new JsonBigInteger(strValue)).getOptionalBoolean().get(), is(false));
	}

	@Test
	public void testToJsonSource()
	{
		String strValue = "9223372036854775808";

		assertThat((new JsonBigInteger(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {}),
				new CircularReferenceDetector()).toJson(), is(strValue));
	}

	@Test
	public void testToJsonSourceOptionNumberOfString()
	{
		String strValue = "9223372036854775808";

		assertThat((new JsonBigInteger(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.NUMBER_TO_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigDoubleAsStringCaseGreaterDoubleMax()
	{
		String strValue = "1.7976931348623158E+308";

		assertThat((new JsonBigInteger((new BigDecimal(strValue)).toBigInteger())).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGDOUBLE_AS_STRING
					}), new CircularReferenceDetector()).toJson(),
				is("\"" + (new BigDecimal(strValue)).toBigInteger().toString() + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigDoubleAsStringCaseLessDoubleMin()
	{
		String strValue = "-1.7976931348623158E+308";

		assertThat((new JsonBigDecimal((new BigDecimal(strValue)).toBigInteger())).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGDOUBLE_AS_STRING
					}), new CircularReferenceDetector()).toJson(),
				is("\"" + (new BigDecimal(strValue)).toBigInteger().toString() + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseGreaterFloatMax()
	{
		String strValue = "3.4028236E+38";

		assertThat((new JsonBigDecimal((new BigDecimal(strValue)).toBigInteger())).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(),
				is("\"" + (new BigDecimal(strValue)).toBigInteger().toString() + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseLessFloatMin()
	{
		String strValue = "-3.4028236E+38";

		assertThat((new JsonBigDecimal((new BigDecimal(strValue)).toBigInteger())).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(),
				is("\"" + (new BigDecimal(strValue)).toBigInteger().toString() + "\""));
	}


	@Test
	public void testToJsonSourceOptionBigDoubleAsStringCaseDoubleMax()
	{
		String strValue = "1.7976931348623157E+308";

		assertThat((new JsonBigDecimal((new BigDecimal(strValue)).toBigInteger())).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGDOUBLE_AS_STRING
					}), new CircularReferenceDetector()).toJson(),
				is((new BigDecimal(strValue)).toBigInteger().toString()));
	}

	@Test
	public void testToJsonSourceOptionBigDoubleAsStringCaseDoubleMin()
	{
		String strValue = "-1.7976931348623157E+308";

		assertThat((new JsonBigDecimal((new BigDecimal(strValue)).toBigInteger())).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGDOUBLE_AS_STRING
					}), new CircularReferenceDetector()).toJson(),
				is((new BigDecimal(strValue)).toBigInteger().toString()));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseFloatMax()
	{
		String strValue = "3.4028235E+38";

		assertThat((new JsonBigDecimal((new BigDecimal(strValue)).toBigInteger())).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(),
				is((new BigDecimal(strValue)).toBigInteger().toString()));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseFloatMin()
	{
		String strValue = "-3.4028235E+38";

		assertThat((new JsonBigDecimal((new BigDecimal(strValue)).toBigInteger())).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(),
				is((new BigDecimal(strValue)).toBigInteger().toString()));
	}

	@Test
	public void testToJsonSourceOptionBigLongAsStringCaseGreaterLongMax()
	{
		String strValue = "9223372036854775808";

		assertThat((new JsonBigInteger(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGLONG_AS_STRING
					}), new CircularReferenceDetector()).toJson(),
				is("\"" + strValue + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigLongAsStringCaseLessLongMin()
	{
		String strValue = "-9223372036854775809";

		assertThat((new JsonBigInteger(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGLONG_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigIntAsStringCaseGreaterIntMax()
	{
		String strValue = "2147483648";

		assertThat((new JsonBigInteger(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGINT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigIntAsStringCaseLessIntMin()
	{
		String strValue = "-2147483649";

		assertThat((new JsonBigInteger(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGINT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}


	@Test
	public void testToJsonSourceOptionBigLongAsStringCaseLongMax()
	{
		String strValue = "9223372036854775807";

		assertThat((new JsonBigInteger(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGLONG_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(strValue));
	}

	@Test
	public void testToJsonSourceOptionBigLongAsStringLongMin()
	{
		String strValue = "-9223372036854775808";

		assertThat((new JsonBigInteger(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGLONG_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(strValue));
	}

	@Test
	public void testToJsonSourceOptionBigIntAsStringCaseIntMax()
	{
		String strValue = "2147483647";

		assertThat((new JsonBigInteger(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGINT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(strValue));
	}

	@Test
	public void testToJsonSourceOptionBigIntAsStringCaseIntMin()
	{
		String strValue = "-2147483648";

		assertThat((new JsonBigInteger(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGINT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(strValue));
	}
}
