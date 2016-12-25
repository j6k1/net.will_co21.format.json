package net.will_co21.format.json;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonIntSerializableTest {

	@Test
	public void testJsonIntSerializableOptionsOfNull() {
		try {
			JsonIntSerializable serializer = (new JsonIntSerializable(0, null));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The value was passed of the option to the constructor of JsonIntSerializable is null."));
		}
	}
}
