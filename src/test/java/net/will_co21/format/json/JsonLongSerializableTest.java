package net.will_co21.format.json;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonLongSerializableTest {

	@Test
	public void testJsonLongSerializableOptionsOfNull() {
		try {
			JsonLongSerializable serializer = (new JsonLongSerializable(0L, null));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The value was passed of the option to the constructor of JsonLongSerializable is null."));
		}
	}
}
