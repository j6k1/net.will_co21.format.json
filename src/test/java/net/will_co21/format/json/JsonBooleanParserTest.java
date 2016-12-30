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
}
