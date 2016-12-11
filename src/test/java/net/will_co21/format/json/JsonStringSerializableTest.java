package net.will_co21.format.json;

import static org.junit.Assert.*;

import org.junit.Test;

public class JsonStringSerializableTest {
	@Test
	public void testJsonStringSerializableNullValue() {
		try {
			new JsonStringSerializable(null, null);
		} catch (TypeOfNullableNotAllowedException e) {
			assertEquals("null value was passed in to the JsonStringSerializable type constructor.", e.getMessage());
		}
	}

	@Test
	public void testJsonStringSerializableNullOptions() {
		try {
			new JsonStringSerializable("", null);
		} catch (TypeOfNullableNotAllowedException e) {
			assertEquals("The value was passed of the option to the constructor of JsonStringSerializable is null.", e.getMessage());
		}
	}

	@Test
	public void testToJsonNoneEscaped() {
		String str = "!#$%&()-=^~|@`[{;+:*]},.?_";

		String json = (new JsonStringSerializable(str, new JsonOptions(new JsonOption[] {}))).toJson();
		assertEquals("\"!#$%&()-=^~|@`[{;+:*]},.?_\"", json);
	}
}
