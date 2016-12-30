package net.will_co21.format.json;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonBooleanSerializableTest {

	@Test
	public void testJsonBooleanSerializableOptionsOfNull() {
		try {
			JsonBooleanSerializable serializer = (new JsonBooleanSerializable(true, null));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The value was passed of the option to the constructor of JsonBooleanSerializable is null."));
		}
	}
}
