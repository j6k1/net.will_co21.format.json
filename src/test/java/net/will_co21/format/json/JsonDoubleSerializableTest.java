package net.will_co21.format.json;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonDoubleSerializableTest {

	@Test
	public void testJsonDoubleSerializableOptionsOfNull() {
		try {
			JsonDoubleSerializable serializer = (new JsonDoubleSerializable(0.0, null));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The value was passed of the option to the constructor of JsonDoubleSerializable is null."));
		}
	}
}
