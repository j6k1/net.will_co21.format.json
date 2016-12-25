package net.will_co21.format.json;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonBigDecimalSerializableTest {

	@Test
	public void testJsonBigDecimalSerializableValueOfNull() {
		try {
			JsonBigDecimalSerializable serializer = (new JsonBigDecimalSerializable(null, null));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonBigDecimalSerializable type constructor."));
		}
	}

	@Test
	public void testJsonBigDecimalSerializableOptionsOfNull() {
		try {
			JsonBigDecimalSerializable serializer = (new JsonBigDecimalSerializable(BigDecimal.valueOf(0), null));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The value was passed of the option to the constructor of JsonBigDecimalSerializable is null."));
		}
	}
}
