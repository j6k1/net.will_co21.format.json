package net.will_co21.format.json;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;

public class JsonStringParserTest {

	@Test
	public void testParseJsonStartNotDoubleQuotes() {
		JsonStringParser parser = new JsonStringParser();
		try {
			parser.parseJson("a\"aaaabbbb\"", 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonEndsWithFirstDoubleQuote() {
		JsonStringParser parser = new JsonStringParser();
		try {
			parser.parseJson("\"", 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json string format."));
		}
	}

	@Test
	public void testParseJsonEndsWithBackslash() {
		JsonStringParser parser = new JsonStringParser();
		try {
			parser.parseJson("\"aa\\", 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json string format."));
		}
	}

	@Test
	public void testParseJsonUnicodeEscapeIsNotTerminated()
	{
		JsonStringParser parser = new JsonStringParser();
		try {
			parser.parseJson("\"aaaああああ\\u679", 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json string format."));
		}
	}

	@Test
	public void testParseJsonInvalidUnicodeEscapeFormat()
	{
		JsonStringParser parser = new JsonStringParser();
		try {
			parser.parseJson("\"aaaああああ\\u0E0m\\uE000aあああいいい\"", 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"m\" was found."));
		}
	}

	@Test
	public void testParseJsonSurrogateSecondUnicodeEscapeIsNotTerminated()
	{
		JsonStringParser parser = new JsonStringParser();
		try {
			parser.parseJson("\"aaaああああ\\uE000\\uD800\\uDC0", 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json string format."));
		}
	}

	@Test
	public void testParseJsonFirstSurrogateUnicodeEscapeLowerBound()
	{
		JsonStringParser parser = new JsonStringParser();
		Pair<IJsonValue, Integer> result = parser.parseJson("\"aaaああああ\\uE000\\uD800aあああいいい\"", 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonString("aaaああああ\uE000\uD800aあああいいい"), 28)));
	}

	@Test
	public void testParseJsonFirstSurrogateUnicodeEscapeUpperBound()
	{
		JsonStringParser parser = new JsonStringParser();
		Pair<IJsonValue, Integer> result = parser.parseJson("\"aaaああああ\\uE000\\uDBFF\\nあああいいい\"", 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonString("aaaああああ\uE000\uDBFF\nあああいいい"), 29)));
	}

	@Test
	public void testParseJsonIncorrectStartOfSecondUnicodeEscape()
	{
		JsonStringParser parser = new JsonStringParser();
		try {
			parser.parseJson("\"aaaああああ\\uE000\\uD800\\aDC00いいい\"", 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonInvalidSurrogateSecondUnicodeEscapeFormat()
	{
		JsonStringParser parser = new JsonStringParser();
		try {
			parser.parseJson("\"aaaああああ\\uD800\\uDC0m\\uE000aあああいいい\"", 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"m\" was found."));
		}
	}

	@Test
	public void testParseJsonInvalidEscapeCharacter()
	{
		JsonStringParser parser = new JsonStringParser();
		try {
			parser.parseJson("\"aaaああああ\\uD800\\gあああいいい\"", 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"g\" was found."));
		}
	}

	@Test
	public void testParseJsonAllEscapeCharacter()
	{
		JsonStringParser parser = new JsonStringParser();
		Pair<IJsonValue, Integer> result = parser.parseJson("\"aaaいいいい\\u3042\\\"\\\\\\/\\b\\f\\n\\r\\taaa\"", 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonString("aaaいいいいあ\"\\/\b\f\n\r\taaa"), 34)));
	}

	@Test
	public void testParseJsonNotSurrogateUnicodeEscape()
	{
		JsonStringParser parser = new JsonStringParser();
		Pair<IJsonValue, Integer> result = parser.parseJson("\"aaaいいいい\\uE000\\uD7FF\\uDC00あああ\"", 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonString("aaaいいいい\uE000\uD7FF\uDC00あああ"), 30)));
	}

	@Test
	public void testParseJsonValidSurrogateUnicodeEscape()
	{
		JsonStringParser parser = new JsonStringParser();
		Pair<IJsonValue, Integer> result = parser.parseJson("\"aaaいいいい\\uD800\\uDC00\\uD800\\uDFFF\\uDBFF\\uDC00\\uDBFF\\uDFFFえええ\"", 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonString("aaaいいいい\uD800\uDC00\uD800\uDFFF\uDBFF\uDC00\uDBFF\uDFFFえええ"), 60)));
	}


	@Test
	public void testParseJsonInValidSurrogateUnicodeEscape()
	{
		JsonStringParser parser = new JsonStringParser();
		Pair<IJsonValue, Integer> result = parser.parseJson("\"\\uD800\\uDBFF\\uD800\\uE000\\uDBFF\\uDBFF\\uDBFF\\uE000えええ\"", 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonString("\uD800\uDBFF\uD800\uE000\uDBFF\uDBFF\uDBFF\uE000えええ"), 53)));
	}
}
