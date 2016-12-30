package net.will_co21.format.json;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonNullParserTest {
	@Test
	public void testParseJsonInvalid() {
		String json = "aaaa";

		try {
			Pair<IJsonValue, Integer> result = (new JsonNullParser().parseJson(json, 0));
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json null format."));
		}
	}

	@Test
	public void testParseJsonNull() {
		String json = "null";

		Pair<IJsonValue, Integer> result = (new JsonNullParser().parseJson(json, 0));

		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonNull(), json.length())));
	}

	@Test
	public void testParseJsonInvalidUseOffset() {
		String json = "aaaaaaaaaaaaaa";

		try {
			Pair<IJsonValue, Integer> result = (new JsonNullParser().parseJson(json, 10));
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json null format."));
		}
	}

	@Test
	public void testParseJsonNullUseOffset() {
		String json = "aaaaaaaaaanull";

		Pair<IJsonValue, Integer> result = (new JsonNullParser().parseJson(json, 10));

		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonNull(), json.length())));
	}
}
