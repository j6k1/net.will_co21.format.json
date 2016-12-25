package net.will_co21.format.json;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonFloatSerializableTest {

	@Test
	public void testJsonFloatSerializableOptionsOfNull() {
		try {
			JsonFloatSerializable serializer = (new JsonFloatSerializable(0.0f, null));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The value was passed of the option to the constructor of JsonFloatSerializable is null."));
		}
	}
}
