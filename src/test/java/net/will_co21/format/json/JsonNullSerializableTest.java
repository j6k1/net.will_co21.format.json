package net.will_co21.format.json;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonNullSerializableTest {

	@Test
	public void testJsonNullSerializableOptionsOfNull() {
		try {
			JsonNullSerializable serializer = (new JsonNullSerializable(null));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The value was passed of the option to the constructor of JsonNullSerializable is null."));
		}
	}
}
