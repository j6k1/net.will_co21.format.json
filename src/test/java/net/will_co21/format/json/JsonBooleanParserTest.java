package net.will_co21.format.json;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonBooleanParserTest {

	@Test
	public void testParseJsonInvalid() {
		String json = "aaaa";

		try {
			Pair<IJsonValue, Integer> result = (new JsonBooleanParser().parseJson(json, 0));
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json boolean format."));
		}
	}

	@Test
	public void testParseJsonTrue() {
		String json = "true";

		Pair<IJsonValue, Integer> result = (new JsonBooleanParser().parseJson(json, 0));

		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBoolean(true), json.length())));
	}

	@Test
	public void testParseJsonFalse() {
		String json = "false";

		Pair<IJsonValue, Integer> result = (new JsonBooleanParser().parseJson(json, 0));

		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBoolean(false), json.length())));
	}

	@Test
	public void testParseJsonInvalidUseOffset() {
		String json = "aaaaaaaaaaaaaa";

		try {
			Pair<IJsonValue, Integer> result = (new JsonBooleanParser().parseJson(json, 10));
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json boolean format."));
		}
	}

	@Test
	public void testParseJsonTrueUseOffset() {
		String json = "aaaaaaaaaatrue";

		Pair<IJsonValue, Integer> result = (new JsonBooleanParser().parseJson(json, 10));

		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBoolean(true), json.length())));
	}

	@Test
	public void testParseJsonFalseUseOffset() {
		String json = "aaaaaaaaaafalse";

		Pair<IJsonValue, Integer> result = (new JsonBooleanParser().parseJson(json, 10));

		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBoolean(false), json.length())));
	}
}
