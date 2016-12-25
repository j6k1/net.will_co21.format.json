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
		String strValue = "22471164185778948222916218155262868027608239412766780263100241258636102756141607129959843456249671444840650018535267579287834082291647314070219057456936246566229799714098366809536004652941487853201097417647192226525594852622831612268360871592331408117651542445559488059018122343636201524867389781720524914688";

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
