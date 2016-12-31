package net.will_co21.format.json;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class JsonArraySerializableTest {

	@Test
	public void testJsonArraySerializableValueOfNull() {
		try {
			JsonArraySerializable serializer = (new JsonArraySerializable(null, null));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonArraySerializable type constructor."));
		}
	}

	@Test
	public void testJsonArraySerializableOptionsOfNull() {
		IPrettyJsonSerializable item = (new JsonInt(0)).toJsonSource(new JsonOptions(new JsonOption[] {}), new CircularReferenceDetector());
		ArrayList<IPrettyJsonSerializable> arr = new ArrayList<IPrettyJsonSerializable>();
		arr.add(item);

		try {
			JsonArraySerializable serializer = new JsonArraySerializable(arr, null);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The value was passed of the option to the constructor of JsonArraySerializable is null."));
		}
	}
}
