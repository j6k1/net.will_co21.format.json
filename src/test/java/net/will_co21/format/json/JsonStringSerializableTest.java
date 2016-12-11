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

	@Test
	public void testToJsonNoOpt() {
		String str = "\u0080\u009F\u007F\u00A0" +
				"\u200B\u200F\u200A\u2010" +
				"\u2028\u202F\u2027\u2030" +
				"\u2060\u2064\u205F\u2065" +
				"\u2066\u206F\u2070" +
				"\u200E\u200F\u200D\u2010" +
				"\u202A\u202E\u2029\u202F" +
				"\uFFF0\uFFFF\uFFEF" +
				"\uDB40\uDC00\uDB40\uDC7F\uDB3F\uDC00\uDB40\uDBFF\uDB40\uDC80" +
				"\uD800\uDC00\uD800\uDFFF\uDBFF\uDC00\uDBFF\uDFFF" +
				"\uD7FF\uDC00\uD800\uD800\uE000\uDBFF\uDBFF\uDBFF\uE000" +
				"\u0080\uD7FF\u007F\uD800\uE000\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F";

		String json = (new JsonStringSerializable(str, new JsonOptions(new JsonOption[] {}))).toJson();

		assertEquals("\"\\u0080\\u009F\\u007F\u00A0" +
				"\\u200B\\u200F\u200A\u2010" +
				"\\u2028\\u202F\u2027\u2030" +
				"\\u2060\\u2064\u205F\u2065" +
				"\\u2066\\u206F\u2070" +
				"\\u200E\\u200F\\u200D\u2010" +
				"\\u202A\\u202E\\u2029\\u202F" +
				"\\uFFF0\\uFFFF\uFFEF" +
				"\\uDB40\\uDC00\\uDB40\\uDC7F\uDB3F\uDC00\\uDB40\\uDBFF\uDB40\uDC80" +
				"\uD800\uDC00\uD800\uDFFF\uDBFF\uDC00\uDBFF\uDFFF" +
				"\uD7FF\\uDC00\\uD800\\uD800\uE000\\uDBFF\\uDBFF\\uDBFF\\uE000" +
				"\\u0080\uD7FF\\u007F\\uD800\\uE000\\uFFFF\\uDFFF\u0100\\b\\f\\n\\r\\t<>&'\\/\\\"\\u001F\\u007F\"", json);
	}

	@Test
	public void testToUnicodeEscape()
	{
		char c = '腕';

		assertEquals("\\u8155", (new JsonStringSerializable("", new JsonOptions(new JsonOption[] {}))).toUnicodeEscape(c));
	}

	@Test
	public void testToUnicodeEscapeAscii()
	{
		char c = 'A';

		assertEquals("\\u0041", (new JsonStringSerializable("", new JsonOptions(new JsonOption[] {}))).toUnicodeEscape(c));
	}
}
