package net.will_co21.format.json;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class JsonStringSerializableTest {
	@Test
	public void testJsonStringSerializableNullValue() {
		try {
			new JsonStringSerializable(null, null);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonStringSerializable type constructor."));
		}
	}

	@Test
	public void testJsonStringSerializableNullOptions() {
		try {
			new JsonStringSerializable("", null);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The value was passed of the option to the constructor of JsonStringSerializable is null."));
		}
	}

	@Test
	public void testToJsonNoneEscaped() {
		String str = "!#$%&()-=^~|@`[{;+:*]},.?_";

		String json = (new JsonStringSerializable(str, new JsonOptions(new JsonOption[] {}))).toJson();
		assertThat(json, is("\"!#$%&()-=^~|@`[{;+:*]},.?_\""));
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
				"\uE000\uF8FF\uDFFF\uF900" +
				"\uDB40\uDC00\uDB40\uDC7F\uDB3F\uDC00\uDB40\uDBFF\uDB40\uDC80" +
				"\uDB80\uDC00\uDBFF\uDC00\uDB80\uDFFF\uDBFF\uDFFF" +
				"\uDB7F\uDC00\uDB7F\uDFFF\uDC00\uDC00\uDC00\uDFFF\uDB7F\uDC00\uDB7F\uDFFF" +
				"\uD800\uDC00\uD800\uDFFF\uDBFF\uDC00\uDBFF\uDFFF" +
				"\uD7FF\uDC00\uD800\uD800\uE000\uDBFF\uDBFF\uDBFF\uE000" +
				"\u0080\uD7FF\u007F\uD800" +
				"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F";

		String json = (new JsonStringSerializable(str, new JsonOptions(new JsonOption[] {}))).toJson();

		assertThat(json, is("\"\\u0080\\u009F\\u007F\u00A0" +
				"\\u200B\\u200F\u200A\u2010" +
				"\\u2028\\u202F\u2027\u2030" +
				"\\u2060\\u2064\u205F\u2065" +
				"\\u2066\\u206F\u2070" +
				"\\u200E\\u200F\\u200D\u2010" +
				"\\u202A\\u202E\\u2029\\u202F" +
				"\\uFFF0\\uFFFF\uFFEF" +
				"\\uE000\\uF8FF\\uDFFF\uF900" +
				"\\uDB40\\uDC00\\uDB40\\uDC7F\uDB3F\uDC00\\uDB40\\uDBFF\uDB40\uDC80" +
				"\\uDB80\\uDC00\\uDBFF\\uDC00\\uDB80\\uDFFF\\uDBFF\\uDFFF" +
				"\uDB7F\uDC00\uDB7F\uDFFF\\uDC00\\uDC00\\uDC00\\uDFFF\uDB7F\uDC00\uDB7F\uDFFF" +
				"\uD800\uDC00\uD800\uDFFF\\uDBFF\\uDC00\\uDBFF\\uDFFF" +
				"\uD7FF\\uDC00\\uD800\\uD800\\uE000\\uDBFF\\uDBFF\\uDBFF\\uE000" +
				"\\u0080\uD7FF\\u007F\\uD800" +
				"\\uE000\\uF8FF\uF900\\uFFFF\\uDFFF\u0100\\b\\f\\n\\r\\t<>&'\\/\\\"\\u001F\\u007F\""));
	}

	@Test
	public void testEndOfFirstSurrogateLower()
	{
		String str = "あいうえおあお\uDB80";

		String json = (new JsonStringSerializable(str, new JsonOptions(new JsonOption[] {}))).toJson();

		assertThat(json, is("\"あいうえおあお\\uDB80\""));

	}

	@Test
	public void testEndOfFirstSurrogateUpper()
	{
		String str = "あいうえおあお\uDBFF";

		String json = (new JsonStringSerializable(str, new JsonOptions(new JsonOption[] {}))).toJson();

		assertThat(json, is("\"あいうえおあお\\uDBFF\""));

	}

	@Test
	public void testEndOfTagFirstSurrogate()
	{
		String str = "あいうえおあお\uDB40";

		String json = (new JsonStringSerializable(str, new JsonOptions(new JsonOption[] {}))).toJson();

		assertThat(json, is("\"あいうえおあお\\uDB40\""));

	}

	@Test
	public void testEndOfPrivateAreaFirstSurrogateLower()
	{
		String str = "あいうえおあお\uDB80";

		String json = (new JsonStringSerializable(str, new JsonOptions(new JsonOption[] {}))).toJson();

		assertThat(json, is("\"あいうえおあお\\uDB80\""));

	}

	@Test
	public void testEndOfPrivateAreaFirstSurrogateUpper()
	{
		String str = "あいうえおあお\uDBFF";

		String json = (new JsonStringSerializable(str, new JsonOptions(new JsonOption[] {}))).toJson();

		assertThat(json, is("\"あいうえおあお\\uDBFF\""));

	}

	@Test
	public void testToJsonExcludeEscapeUnicodeFullStringOption() {
		String str = "\u0080\u009F\u007F\u00A0" +
				"\u200B\u200F\u200A\u2010" +
				"\u2028\u202F\u2027\u2030" +
				"\u2060\u2064\u205F\u2065" +
				"\u2066\u206F\u2070" +
				"\u200E\u200F\u200D\u2010" +
				"\u202A\u202E\u2029\u202F" +
				"\uFFF0\uFFFF\uFFEF" +
				"\uE000\uF8FF\uDFFF\uF900" +
				"\uDB40\uDC00\uDB40\uDC7F\uDB3F\uDC00\uDB40\uDBFF\uDB40\uDC80" +
				"\uDB80\uDC00\uDBFF\uDC00\uDB80\uDFFF\uDBFF\uDFFF" +
				"\uDB7F\uDC00\uDB7F\uDFFF\uDC00\uDC00\uDC00\uDFFF\uDB7F\uDC00\uDB7F\uDFFF" +
				"\uD800\uDC00\uD800\uDFFF\uDBFF\uDC00\uDBFF\uDFFF" +
				"\uD7FF\uDC00\uD800\uD800\uE000\uDBFF\uDBFF\uDBFF\uE000" +
				"\u0080\uD7FF\u007F\uD800" +
				"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F";

		String json = (new JsonStringSerializable(str, new JsonOptions(new JsonOption[] {
				JsonOption.HEX_TAG,
                JsonOption.HEX_AMP,
                JsonOption.HEX_APO,
                JsonOption.HEX_QUOT,
                JsonOption.PRETTY_PRINT,
                JsonOption.UNESCAPED_SLASHES
		}))).toJson();

		assertThat(json, is("\"\\u0080\\u009F\\u007F\u00A0" +
				"\\u200B\\u200F\u200A\u2010" +
				"\\u2028\\u202F\u2027\u2030" +
				"\\u2060\\u2064\u205F\u2065" +
				"\\u2066\\u206F\u2070" +
				"\\u200E\\u200F\\u200D\u2010" +
				"\\u202A\\u202E\\u2029\\u202F" +
				"\\uFFF0\\uFFFF\uFFEF" +
				"\\uE000\\uF8FF\\uDFFF\uF900" +
				"\\uDB40\\uDC00\\uDB40\\uDC7F\uDB3F\uDC00\\uDB40\\uDBFF\uDB40\uDC80" +
				"\\uDB80\\uDC00\\uDBFF\\uDC00\\uDB80\\uDFFF\\uDBFF\\uDFFF" +
				"\uDB7F\uDC00\uDB7F\uDFFF\\uDC00\\uDC00\\uDC00\\uDFFF\uDB7F\uDC00\uDB7F\uDFFF" +
				"\uD800\uDC00\uD800\uDFFF\\uDBFF\\uDC00\\uDBFF\\uDFFF" +
				"\uD7FF\\uDC00\\uD800\\uD800\\uE000\\uDBFF\\uDBFF\\uDBFF\\uE000" +
				"\\u0080\uD7FF\\u007F\\uD800" +
				"\\uE000\\uF8FF\uF900\\uFFFF\\uDFFF\u0100\\b\\f\\n\\r\\t\\u003C\\u003E\\u0026\\u0027/\\u0022\\u001F\\u007F\""));
	}

	@Test
	public void testToJsonFullStringOption() {
		String str = "\u0080\u009F\u007F\u00A0" +
				"\u200B\u200F\u200A\u2010" +
				"\u2028\u202F\u2027\u2030" +
				"\u2060\u2064\u205F\u2065" +
				"\u2066\u206F\u2070" +
				"\u200E\u200F\u200D\u2010" +
				"\u202A\u202E\u2029\u202F" +
				"\uFFF0\uFFFF\uFFEF" +
				"\uE000\uF8FF\uDFFF\uF900" +
				"\uDB40\uDC00\uDB40\uDC7F\uDB3F\uDC00\uDB40\uDBFF\uDB40\uDC80" +
				"\uDB80\uDC00\uDBFF\uDC00\uDB80\uDFFF\uDBFF\uDFFF" +
				"\uDB7F\uDC00\uDB7F\uDFFF\uDC00\uDC00\uDC00\uDFFF\uDB7F\uDC00\uDB7F\uDFFF" +
				"\uD800\uDC00\uD800\uDFFF\uDBFF\uDC00\uDBFF\uDFFF" +
				"\uD7FF\uDC00\uD800\uD800\uE000\uDBFF\uDBFF\uDBFF\uE000" +
				"\u0080\uD7FF\u007F\uD800" +
				"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F";

		String json = (new JsonStringSerializable(str, new JsonOptions(new JsonOption[] {
				JsonOption.HEX_TAG,
                JsonOption.HEX_AMP,
                JsonOption.HEX_APO,
                JsonOption.HEX_QUOT,
                JsonOption.PRETTY_PRINT,
                JsonOption.UNESCAPED_SLASHES,
                JsonOption.ESCAPED_UNICODE
		}))).toJson();

		assertThat(json, is("\"\\u0080\\u009F\\u007F\\u00A0" +
				"\\u200B\\u200F\\u200A\\u2010" +
				"\\u2028\\u202F\\u2027\\u2030" +
				"\\u2060\\u2064\\u205F\\u2065" +
				"\\u2066\\u206F\\u2070" +
				"\\u200E\\u200F\\u200D\\u2010" +
				"\\u202A\\u202E\\u2029\\u202F" +
				"\\uFFF0\\uFFFF\\uFFEF" +
				"\\uE000\\uF8FF\\uDFFF\\uF900" +
				"\\uDB40\\uDC00\\uDB40\\uDC7F\\uDB3F\\uDC00\\uDB40\\uDBFF\\uDB40\\uDC80" +
				"\\uDB80\\uDC00\\uDBFF\\uDC00\\uDB80\\uDFFF\\uDBFF\\uDFFF" +
				"\\uDB7F\\uDC00\\uDB7F\\uDFFF\\uDC00\\uDC00\\uDC00\\uDFFF\\uDB7F\\uDC00\\uDB7F\\uDFFF" +
				"\\uD800\\uDC00\\uD800\\uDFFF\\uDBFF\\uDC00\\uDBFF\\uDFFF" +
				"\\uD7FF\\uDC00\\uD800\\uD800\\uE000\\uDBFF\\uDBFF\\uDBFF\\uE000" +
				"\\u0080\\uD7FF\\u007F\\uD800" +
				"\\uE000\\uF8FF\\uF900\\uFFFF\\uDFFF\\u0100\\b\\f\\n\\r\\t\\u003C\\u003E\\u0026\\u0027/\\u0022\\u001F\\u007F\""));
	}

	@Test
	public void testToJsonFullOption() {
		String str = "\u0080\u009F\u007F\u00A0" +
				"\u200B\u200F\u200A\u2010" +
				"\u2028\u202F\u2027\u2030" +
				"\u2060\u2064\u205F\u2065" +
				"\u2066\u206F\u2070" +
				"\u200E\u200F\u200D\u2010" +
				"\u202A\u202E\u2029\u202F" +
				"\uFFF0\uFFFF\uFFEF" +
				"\uE000\uF8FF\uDFFF\uF900" +
				"\uDB40\uDC00\uDB40\uDC7F\uDB3F\uDC00\uDB40\uDBFF\uDB40\uDC80" +
				"\uDB80\uDC00\uDBFF\uDC00\uDB80\uDFFF\uDBFF\uDFFF" +
				"\uDB7F\uDC00\uDB7F\uDFFF\uDC00\uDC00\uDC00\uDFFF\uDB7F\uDC00\uDB7F\uDFFF" +
				"\uD800\uDC00\uD800\uDFFF\uDBFF\uDC00\uDBFF\uDFFF" +
				"\uD7FF\uDC00\uD800\uD800\uE000\uDBFF\uDBFF\uDBFF\uE000" +
				"\u0080\uD7FF\u007F\uD800" +
				"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F";

		String json = (new JsonStringSerializable(str, new JsonOptions(new JsonOption[] {
				JsonOption.HEX_TAG,
                JsonOption.HEX_AMP,
                JsonOption.HEX_APO,
                JsonOption.HEX_QUOT,
                JsonOption.NUMBER_TO_STRING,
                JsonOption.BIGINT_AS_STRING,
                JsonOption.BIGLONG_AS_STRING,
                JsonOption.BIGFLOAT_AS_STRING,
                JsonOption.BIGDOUBLE_AS_STRING,
                JsonOption.PRETTY_PRINT,
                JsonOption.UNESCAPED_SLASHES,
                JsonOption.ESCAPED_UNICODE
		}))).toJson();

		assertThat(json, is("\"\\u0080\\u009F\\u007F\\u00A0" +
				"\\u200B\\u200F\\u200A\\u2010" +
				"\\u2028\\u202F\\u2027\\u2030" +
				"\\u2060\\u2064\\u205F\\u2065" +
				"\\u2066\\u206F\\u2070" +
				"\\u200E\\u200F\\u200D\\u2010" +
				"\\u202A\\u202E\\u2029\\u202F" +
				"\\uFFF0\\uFFFF\\uFFEF" +
				"\\uE000\\uF8FF\\uDFFF\\uF900" +
				"\\uDB40\\uDC00\\uDB40\\uDC7F\\uDB3F\\uDC00\\uDB40\\uDBFF\\uDB40\\uDC80" +
				"\\uDB80\\uDC00\\uDBFF\\uDC00\\uDB80\\uDFFF\\uDBFF\\uDFFF" +
				"\\uDB7F\\uDC00\\uDB7F\\uDFFF\\uDC00\\uDC00\\uDC00\\uDFFF\\uDB7F\\uDC00\\uDB7F\\uDFFF" +
				"\\uD800\\uDC00\\uD800\\uDFFF\\uDBFF\\uDC00\\uDBFF\\uDFFF" +
				"\\uD7FF\\uDC00\\uD800\\uD800\\uE000\\uDBFF\\uDBFF\\uDBFF\\uE000" +
				"\\u0080\\uD7FF\\u007F\\uD800" +
				"\\uE000\\uF8FF\\uF900\\uFFFF\\uDFFF\\u0100\\b\\f\\n\\r\\t\\u003C\\u003E\\u0026\\u0027/\\u0022\\u001F\\u007F\""));
	}

	@Test
	public void testToUnicodeEscape()
	{
		char c = '腕';

		assertThat((new JsonStringSerializable("", new JsonOptions(new JsonOption[] {}))).toUnicodeEscape(c), is("\\u8155"));
	}

	@Test
	public void testToUnicodeEscapeAscii()
	{
		char c = 'A';

		assertThat((new JsonStringSerializable("", new JsonOptions(new JsonOption[] {}))).toUnicodeEscape(c), is("\\u0041"));
	}
}
