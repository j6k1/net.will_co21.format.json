package net.will_co21.format.json;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.TreeMap;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonObjectSerializableTest {

	@Test
	public void testJsonObjectSerializableValueOfNull() {
		try {
			JsonObjectSerializable serializer = (new JsonObjectSerializable(null, null));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonObjectSerializable type constructor."));
		}
	}

	@Test
	public void testJsonObjectSerializableOptionsOfNull() {
		IPrettyJsonSerializable item = (new JsonInt(0)).toJsonSource(new JsonOptions(new JsonOption[] {}), new CircularReferenceDetector());
		TreeMap<String, IPrettyJsonSerializable> arr = new TreeMap<String, IPrettyJsonSerializable>();
		arr.put("aaaa", item);

		try {
			JsonObjectSerializable serializer = new JsonObjectSerializable(arr, null);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The value was passed of the option to the constructor of JsonObjectSerializable is null."));
		}
	}
}
