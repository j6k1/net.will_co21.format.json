package net.will_co21.format.json;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

public class JsonFloatTest {
	@Test
	public void testGetFloat() {
		float value = 3.4028235E+38f;
		assertThat((new JsonFloat(value)).getFloat(), is(value));
	}

	@Test
	public void testGetOptionalFloat() {
		float value = 3.4028235E+38f;
		assertThat((new JsonFloat(value)).getOptionalFloat().get(), is(value));
	}

	@Test
	public void testGetDouble() {
		float value = 3.4028235E+38f;
		assertThat((new JsonFloat(value)).getDouble(), is((double)value));
	}

	@Test
	public void testGetOptionalDouble() {
		float value = 3.4028235E+38f;
		assertThat((new JsonFloat(value)).getOptionalDouble().get(), is((double)value));
	}

	@Test
	public void testGetBigDecimal() {
		float value = 3.4028235E+38f;
		assertThat((new JsonFloat(value)).getBigDecimal(), is(BigDecimal.valueOf(value)));
	}

	@Test
	public void testGetOptionalBigDecimal() {
		float value = 3.4028235E+38f;
		assertThat((new JsonFloat(value)).getOptionalBigDecimal().get(), is(BigDecimal.valueOf(value)));
	}

	@Test
	public void testGetString() {
		float value = 3.4028235E+38f;
		assertThat((new JsonFloat(value)).getString(), is(Float.toString(value)));
	}

	@Test
	public void testGetOptionalString() {
		float value = 3.4028235E+38f;
		assertThat((new JsonFloat(value)).getOptionalString().get(), is(Float.toString(value)));
	}

	@Test
	public void testGetBooleanTrue() {
		float value = 1.0f;

		assertThat((new JsonFloat(value)).getBoolean(), is(true));
	}

	@Test
	public void testGetBooleanFalse() {
		float value = 0.0f;

		assertThat((new JsonFloat(value)).getBoolean(), is(false));
	}

	@Test
	public void testGetOptionalBooleanTrue() {
		float value = 1.0f;

		assertThat((new JsonFloat(value)).getOptionalBoolean().get(), is(true));
	}

	@Test
	public void testGetOptionalBooleanFalse() {
		float value = 0.0f;

		assertThat((new JsonFloat(value)).getOptionalBoolean().get(), is(false));
	}

	@Test
	public void testToJsonSource()
	{
		float value = 3.4028235E+38f;

		assertThat((new JsonFloat(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {}),
				new CircularReferenceDetector()).toJson(), is(Float.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionNumberOfString()
	{
		float value = 3.4028235E+38f;

		assertThat((new JsonFloat(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.NUMBER_TO_STRING
					}), new CircularReferenceDetector()).toJson(), is("\"" + value + "\""));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseFloatMax()
	{
		float value = 3.4028235E+38f;

		assertThat((new JsonFloat(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Float.toString(value)));
	}

	@Test
	public void testToJsonSourceOptionBigFloatAsStringCaseFloatOneGreaterThanMinimum()
	{
		float value = -3.4028235E+38f;

		assertThat((new JsonFloat(value)).toJsonSource(
				new JsonOptions(new JsonOption[] {
						JsonOption.BIGFLOAT_AS_STRING
					}), new CircularReferenceDetector()).toJson(), is(Float.toString(value)));
	}
}
