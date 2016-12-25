package net.will_co21.format.json;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonBigDecimalTest {
	@Test
	public void testJsonBigDecimalNullString() {
		try {
			String value = null;
			new JsonBigDecimal(value);
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonBigDecimal type constructor."));
		}
	}

	@Test
	public void testJsonBigDecimalNullBigInteger() {
		try {
			BigInteger value = null;
			new JsonBigDecimal(value);
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonBigDecimal type constructor."));
		}
	}

	@Test
	public void testJsonBigDecimalNullBigDecimal() {
		try {
			BigDecimal value = null;
			new JsonBigDecimal(value);
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonBigDecimal type constructor."));
		}
	}

	@Test
	public void testGetBigDecimal() {
		String strValue = "1.7976931348623158E308";
		assertThat((new JsonBigDecimal(strValue)).getBigDecimal(), is(new BigDecimal(strValue)));
	}

	@Test
	public void testGetOptionalBigDecimal() {
		String strValue = "1.7976931348623158E308";
		assertThat((new JsonBigDecimal(strValue)).getOptionalBigDecimal().get(), is(new BigDecimal(strValue)));
	}

	@Test
	public void testGetString() {
		String strValue = "1.7976931348623158E+308";
		assertThat((new JsonBigDecimal(strValue)).getString(), is(strValue));
	}

	@Test
	public void testGetOptionalString() {
		String strValue = "1.7976931348623158E+308";
		assertThat((new JsonBigDecimal(strValue)).getOptionalString().get(), is(strValue));
	}

	@Test
	public void testGetBooleanTrue() {
		String strValue = "1.0";

		assertThat((new JsonBigDecimal(strValue)).getBoolean(), is(true));
	}

	@Test
	public void testGetBooleanFalse() {
		String strValue = "0.0";

		assertThat((new JsonBigDecimal(strValue)).getBoolean(), is(false));
	}

	@Test
	public void testGetOptionalBooleanTrue() {
		String strValue = "1.0";

		assertThat((new JsonBigDecimal(strValue)).getOptionalBoolean().get(), is(true));
	}

	@Test
	public void testGetOptionalBooleanFalse() {
		String strValue = "0.0";

		assertThat((new JsonBigDecimal(strValue)).getOptionalBoolean().get(), is(false));
	}

	@Test
	public void testToJsonSource()
	{
		String strValue = "1.7976931348623158E+308";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {}),
				new CircularReferenceDetector()).toJson(), is(strValue));
	}

	@Test
	public void testToJsonSourceOptionNumberOfString()
	{
		String strValue = "1.7976931348623158E+308";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.NUMBER_TO_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigDoubleAsStringCaseGreaterDoubleMax()
	{
		String strValue = "1.7976931348623158E+308";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGDOUBLE_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigDoubleAsStringCaseLessDoubleMin()
	{
		String strValue = "-1.7976931348623158E+308";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGDOUBLE_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigDoubleAsStringCaseGreaterMantissaPart()
	{
		String strValue = "179769313486231580793728971405303415079934132710037826936173778980444968292764750946649017977587207096330286416692887910946555547851940402630657488671505820681908902000708383676273854845817711531764475730270069855571366959622842914819860834936475292719074168444365510704342711559699508093042880177904174497792";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGDOUBLE_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseGreaterFloatMax()
	{
		String strValue = "3.4028236E+38";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseLessFloatMin()
	{
		String strValue = "-3.4028236E+38";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseGreaterMantissaPart()
	{
		String strValue = "3.40282351E+37";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigDoubleAsStringCaseDoubleMax()
	{
		String strValue = "1.7976931348623157E+308";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGDOUBLE_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(strValue));
	}

	@Test
	public void testToJsonSourceOptionBigDoubleAsStringCaseDoubleMin()
	{
		String strValue = "-1.7976931348623157E+308";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGDOUBLE_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(strValue));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseFloatMax()
	{
		String strValue = "3.4028235E+38";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(strValue));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseFloatMin()
	{
		String strValue = "-3.4028235E+38";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(strValue));
	}

	@Test
	public void testToJsonSourceOptionBigLongAsStringCaseGreaterLongMax()
	{
		String strValue = "9223372036854775808";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGLONG_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigLongAsStringCaseLessLongMin()
	{
		String strValue = "-9223372036854775809";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGLONG_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigIntAsStringCaseGreaterIntMax()
	{
		String strValue = "2147483648";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGINT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigIntAsStringCaseLessIntMin()
	{
		String strValue = "-2147483649";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGINT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + strValue + "\""));
	}


	@Test
	public void testToJsonSourceOptionBigLongAsStringCaseLongMax()
	{
		String strValue = "9223372036854775807";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGLONG_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(strValue));
	}

	@Test
	public void testToJsonSourceOptionBigLongAsStringLongMin()
	{
		String strValue = "-9223372036854775808";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGLONG_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(strValue));
	}

	@Test
	public void testToJsonSourceOptionBigIntAsStringCaseIntMax()
	{
		String strValue = "2147483647";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGINT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(strValue));
	}

	@Test
	public void testToJsonSourceOptionBigIntAsStringCaseIntMin()
	{
		String strValue = "-2147483648";

		assertThat((new JsonBigDecimal(strValue)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGINT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(strValue));
	}
}
