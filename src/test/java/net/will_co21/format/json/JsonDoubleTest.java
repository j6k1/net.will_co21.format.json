package net.will_co21.format.json;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class JsonDoubleTest {
	@Test
	public void testGetDouble() {
		double value = 1.7976931348623157E308;
		assertThat((new JsonDouble(value)).getDouble(), is(value));
	}

	@Test
	public void testGetOptionalDouble() {
		double value = 1.7976931348623157E308;
		assertThat((new JsonDouble(value)).getOptionalDouble().get(), is(value));
	}
	@Test
	public void testGetBigDecimal() {
		double value = 1.7976931348623157E308;
		assertThat((new JsonDouble(value)).getBigDecimal(), is(BigDecimal.valueOf(value)));
	}

	@Test
	public void testGetOptionalBigDecimal() {
		double value = 1.7976931348623157E308;
		assertThat((new JsonDouble(value)).getOptionalBigDecimal().get(), is(BigDecimal.valueOf(value)));
	}

	@Test
	public void testGetString() {
		double value = 1.7976931348623157E308;
		assertThat((new JsonDouble(value)).getString(), is(Double.toString(value)));
	}

	@Test
	public void testGetOptionalString() {
		double value = 1.7976931348623157E308;
		assertThat((new JsonDouble(value)).getOptionalString().get(), is(Double.toString(value)));
	}

	@Test
	public void testGetBooleanTrue() {
		double value = 1.0;

		assertThat((new JsonDouble(value)).getBoolean(), is(true));
	}

	@Test
	public void testGetBooleanFalse() {
		double value = 0.0;

		assertThat((new JsonDouble(value)).getBoolean(), is(false));
	}

	@Test
	public void testGetOptionalBooleanTrue() {
		double value = 1.0;

		assertThat((new JsonDouble(value)).getOptionalBoolean().get(), is(true));
	}

	@Test
	public void testGetOptionalBooleanFalse() {
		double value = 0.0;

		assertThat((new JsonDouble(value)).getOptionalBoolean().get(), is(false));
	}

	@Test
	public void testToJsonSource()
	{
		double value = 1.7976931348623157E308;

		assertThat((new JsonDouble(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {}),
				new CircularReferenceDetector()).toJson(), is(Double.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionNumberOfString()
	{
		double value = 1.7976931348623157E308;

		assertThat((new JsonDouble(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.NUMBER_TO_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + value + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseGreaterFloatMax()
	{
		double value = 3.4028236E+38;

		assertThat((new JsonDouble(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + value + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseLessFloatMin()
	{
		double value = -3.4028236E+38;

		assertThat((new JsonDouble(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + value + "\""));
	}


	@Test
	public void testToJsonSourceOptionBigDoubleAsStringCaseDoubleMax()
	{
		double value = 1.7976931348623157E308;

		assertThat((new JsonDouble(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGDOUBLE_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Double.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionBigDoubleAsStringDoubleMin()
	{
		double value = -1.7976931348623157E308;

		assertThat((new JsonDouble(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGDOUBLE_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Double.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseFloatMax()
	{
		float value = 3.4028235E+38f;

		assertThat((new JsonDouble((double)value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Double.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseFloatOneGreaterThanMinimum()
	{
		float value = -3.4028235E+38f;

		assertThat((new JsonDouble((double)value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Double.toString(value)));
	}
}
