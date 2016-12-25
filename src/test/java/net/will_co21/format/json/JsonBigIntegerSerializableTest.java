package net.will_co21.format.json;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonBigIntegerSerializableTest {

	@Test
	public void testJsonBigIntegerSerializableValueOfNull() {
		try {
			JsonBigIntegerSerializable serializer = (new JsonBigIntegerSerializable(null, null));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonBigIntegerSerializable type constructor."));
		}
	}

	@Test
	public void testJsonBigIntegerSerializableOptionsOfNull() {
		try {
			JsonBigIntegerSerializable serializer = (new JsonBigIntegerSerializable(BigInteger.valueOf(0), null));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The value was passed of the option to the constructor of JsonBigIntegerSerializable is null."));
		}
	}
}
