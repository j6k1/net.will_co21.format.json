package net.will_co21.format.json;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.UnsupportedCharsetException;
import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonObjectParserTest {
	private static String[] jsons;

	@BeforeClass
	public static void loadJsons() throws UnsupportedCharsetException, UnsupportedEncodingException, FileNotFoundException, IOException
	{
		String currentDir = System.getProperty("user.dir");

		String path = String.join(File.separator, new String[] { currentDir, "testdata", "test_jsonobjectparser_jsons.txt" });

		LinkedList<String> lines = new LinkedList<String>();

		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"))) {

			String line;

			while((line = reader.readLine()) != null)
			{
				lines.add(line);
			}
		}
		jsons = String.join("\n", lines).split("\\r\\n\\r\\n|\\n\\n|\\r\\r");
	}

	public static String loadJson(String filename) throws UnsupportedCharsetException, UnsupportedEncodingException, FileNotFoundException, IOException
	{
		String currentDir = System.getProperty("user.dir");

		String path = String.join(File.separator, new String[] { currentDir, "testdata", filename });

		LinkedList<String> lines = new LinkedList<String>();

		try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"))) {

			String line;

			while((line = reader.readLine()) != null)
			{
				lines.add(line);
			}
		}
		return String.join("\n", lines);
	}

	@Test
	public void testParseJsonInvalidStartSymbol() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = "\"aaaa\": 1}";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"\"\" was found."));
		}
	}

	@Test
	public void testParseJsonEndsWithStartSymbol() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonEndsWithStartSymbolAndBlank() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{  ";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonFirstKeyCharacterOfValueIsNotAscii() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = "{あ:123}";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"あ\" was found."));
		}
	}

	@Test
	public void testParseJsonFirstKeyCharacterNotDoubleQuotes() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = "{'";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"'\" was found."));
		}
	}

	@Test
	public void testParseJsonEndsWithKeyName() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = "{\"aaaa\"";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonEndsWithKeyNameAndBlank() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = "{\"aaaa\"    ";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonNoColonAfterKeyName() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = "{\"aaaa\",";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonNoColonAfterKeyNameContainWhiteSpace() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = "{\"aaaa\"   ,";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonEndsWithColon() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = "{\"aaaa\":";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonEndsWithColonAndBlanck() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = "{\"aaaa\":    ";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonFirstValueCharacterOfValueIsNotAscii() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = "{\"aaaa\": あ";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"あ\" was found."));
		}
	}

	@Test
	public void testParseJsonIsNotTerminatedCasePropertyCountIs1() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{\"aaaa\":123";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonIsNotTerminatedCasePropertyCountIs2() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{\"aaaa\":1234,\"aaaa\":3456";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonIsNotTerminatedCasePropertyCountIs1AndHasTrailingBlank() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{\"aaaa\":123 \n\r";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonIsNotTerminatedCasePropertyCountIs2AndHasTrailingBlank() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{\"aaaa\":123,\"aaaa\": 123456 \n\r";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}


	@Test
	public void testParseJsonIsNotTerminatedCasePropertyCountIs1AndContainWhiteSpace() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{   \r\n \"aaaa\": 123";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonIsNotTerminatedCasePropertyCountIs2AndContainWhiteSpace() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{  \r\n\"aaaa\": 123 \r\n, \r\n\"aaaa\":7890";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonIsNotTerminatedCasePropertyCountIs1AndHasTrailingBlankAndContainWhiteSpace() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{  \r\n \"aaaa\":123 \n\r";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonIsNotTerminatedCasePropertyCountIs2AndHasTrailingBlankAndContainWhiteSpace() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{  \r\n \"aaaa\":123 , \"aaaa\":7890 \n\r";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonInvalidCharacterAfterPropertyCasePropertyCountIs1() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{\"aaaa\":12345;";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \";\" was found."));
		}
	}

	@Test
	public void testParseJsonInvalidCharacterAfterPropertyCasePropertyCountIs2() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{\"aaaa\":123,\"bbbb\":1324;";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \";\" was found."));
		}
	}

	@Test
	public void testParseJsonInvalidCharacterAfterPropertyCasePropertyCountIs1AndContainWhiteSpace() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{  \r\n \"aaaa\":123 ;  ";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \";\" was found."));
		}
	}

	@Test
	public void testParseJsonInvalidCharacterAfterPropertyCasePropertyCountIs2AndContainWhiteSpace() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{  \r\n  \"aaaa\":123 ,    \"bbbb\": 1324 ; \n";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \";\" was found."));
		}
	}

	@Test
	public void testParseJsonCasePropertyCountIs1AndTerminatedWithComma() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{\"aaaa\":123,";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonCasePropertyCountIs2AndTerminatedWithComma() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{\"aaaa\":123,\"bbbb\":5678,";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonCasePropertyCountIs1AndTerminatedWithCommaAndContainWhiteSpace() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{ \r\n\"aaaa\":123  ,  \r\n";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonCasePropertyCountIs2AndTerminatedWithCommaAndContainWhiteSpace() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{  \"aaaa\":123,\"bbbb\":5678  ,  \r\n";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json object format."));
		}
	}

	@Test
	public void testParseJsonSecondKeyFirstCharacterIsNotAscii() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{\"aaaa\":123,あ";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"あ\" was found."));
		}
	}


	@Test
	public void testParseJsonSecondValueFirstCharacterIsNotDoubleQuotes() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{\"aaaa\":123,a";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonSecondValueFirstCharacterIsNotAsciiCaseContainWhiteSpace() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{ \"aaaa\":123  ,  あ ";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"あ\" was found."));
		}
	}

	@Test
	public void testParseJsonSecondValueFirstCharacterIsNotDoubleQuotesCaseContainWhiteSpace() {
		JsonObjectParser parser = new JsonObjectParser();
		String json ="{  \"bbbb\":123 , \ra  \r\n";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}


	@Test
	public void testParseJsonSecondValueFirstCharacterIsInvalidCaseContainWhiteSpace() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[  123 , \ra  \r\n";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonEmptyObjectNotPrettyJson() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = "{}";

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonObject(), json.length())));
	}

	@Test
	public void testParseJsonNotPrettyJson() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = jsons[0];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonObject(new JsonProperty[] {
						JsonProperty.create("aaaa", 12345),
						JsonProperty.create("bbbb", "aaaa"),
						JsonProperty.create("cccc", true),
						JsonProperty.create("dddd", false),
						JsonProperty.create("eeee", new JsonNull()) }), json.length())));
	}

	@Test
	public void testParseJsonNestedInObjectNotPrettyJson() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = jsons[1];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonObject(new JsonProperty[] {
						JsonProperty.create("aaaa", 12345),
						JsonProperty.create("bbbb", new JsonObject(new JsonProperty[] {
							JsonProperty.create("bbbb", "aaaa"),
							JsonProperty.create("cccc", true),
							JsonProperty.create("dddd", false),
							JsonProperty.create("eeee", new JsonNull())
						})),
						JsonProperty.create("cccc", new JsonArray(new IJsonValue[] {
								new JsonInt(123),
								new JsonString("aaaa")
							})),
						JsonProperty.create("ffff", 123)
				}), json.length())));
	}


	@Test
	public void testParseJsonDoubleNestedInObjectNotPrettyJson() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = jsons[2];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonObject(new JsonProperty[] {
						JsonProperty.create("aa", 12345),
						JsonProperty.create("bb", new JsonArray(new IJsonValue[] {
							new JsonInt(12345),
							new JsonObject(new JsonProperty[] {
								JsonProperty.create("cc", "aaaa"),
								JsonProperty.create("d", true),
								JsonProperty.create("e", false),
								JsonProperty.create("fffff", new JsonNull()),
								JsonProperty.create("ef", new JsonArray(new IJsonValue[] {
									new JsonInt(123),
									new JsonString("aaaa")
								}))
							})
						})),
						JsonProperty.create("g", 123)
				}), json.length())));
	}

	@Test
	public void testParseJsonEmptyObjectPrettyJson() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = "{" + "\n" + "\t" + "\n" + "}\n";

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonObject(), json.length())));
	}

	@Test
	public void testParseJsonPrettyJson() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = jsons[3];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonObject(new JsonProperty[] {
						JsonProperty.create("aaaa", 12345),
						JsonProperty.create("bbbb", "aaaa"),
						JsonProperty.create("cccc", true),
						JsonProperty.create("dddd", false),
						JsonProperty.create("eeee", new JsonNull()) }), json.length())));
	}

	@Test
	public void testParseJsonNestedInObjectPrettyJson() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = jsons[4];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonObject(new JsonProperty[] {
						JsonProperty.create("aaaa", 12345),
						JsonProperty.create("bbbb", new JsonObject(new JsonProperty[] {
							JsonProperty.create("bbbb", "aaaa"),
							JsonProperty.create("cccc", true),
							JsonProperty.create("dddd", false),
							JsonProperty.create("eeee", new JsonNull())
						})),
						JsonProperty.create("cccc", new JsonArray(new IJsonValue[] {
							new JsonInt(123),
							new JsonString("aaaa")
						})),
						JsonProperty.create("ffff", 123)
				}), json.length())));
	}


	@Test
	public void testParseJsonDoubleNestedInObjectPrettyJson() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = jsons[5];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonObject(new JsonProperty[] {
						JsonProperty.create("aa", 12345),
						JsonProperty.create("bb", new JsonArray(new IJsonValue[] {
							new JsonInt(12345),
							new JsonObject(new JsonProperty[] {
								JsonProperty.create("cc", "aaaa"),
								JsonProperty.create("d", true),
								JsonProperty.create("e", false),
								JsonProperty.create("fffff", new JsonNull()),
								JsonProperty.create("ef", new JsonArray(new IJsonValue[] {
									new JsonInt(123),
									new JsonString("aaaa")
								}))
							})
						})),
						JsonProperty.create("g", 123)
				}), json.length())));
	}


	@Test
	public void testParseJsonPrettyJsonContainWhiteSpace() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = jsons[6];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonObject(new JsonProperty[] {
						JsonProperty.create("aaaa", 12345),
						JsonProperty.create("bbbb", "aaaa"),
						JsonProperty.create("cccc", true),
						JsonProperty.create("dddd", false),
						JsonProperty.create("eeee", new JsonNull()) }), json.length())));
	}

	@Test
	public void testParseJsonNestedInObjectPrettyJsonContainWhiteSpace() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = jsons[7];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonObject(new JsonProperty[] {
						JsonProperty.create("aaaa", 12345),
						JsonProperty.create("bbbb", new JsonObject(new JsonProperty[] {
							JsonProperty.create("bbbb", "aaaa"),
							JsonProperty.create("cccc", true),
							JsonProperty.create("dddd", false),
							JsonProperty.create("eeee", new JsonNull())
						})),
						JsonProperty.create("cccc", new JsonArray(new IJsonValue[] {
								new JsonInt(123),
								new JsonString("aaaa")
							})),
						JsonProperty.create("ffff", 123)
				}), json.length())));
	}

	@Test
	public void testParseJsonDoubleNestedInObjectPrettyJsonContainWhiteSpace() {
		JsonObjectParser parser = new JsonObjectParser();
		String json = jsons[8];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonObject(new JsonProperty[] {
						JsonProperty.create("aa", 12345),
						JsonProperty.create("bb", new JsonArray(new IJsonValue[] {
							new JsonInt(12345),
							new JsonObject(new JsonProperty[] {
								JsonProperty.create("cc", "aaaa"),
								JsonProperty.create("d", true),
								JsonProperty.create("e", false),
								JsonProperty.create("fffff", new JsonNull()),
								JsonProperty.create("ef", new JsonArray(new IJsonValue[] {
									new JsonInt(123),
									new JsonString("aaaa")
								}))
							})
						})),
						JsonProperty.create("g", 123)
				}), json.length())));
	}

	@Test
	public void testParseJsonDoubleNestedInObjectPrettyJsonContainWhiteSpaceAndDoubleNL() throws IOException {
		JsonObjectParser parser = new JsonObjectParser();
		String json = loadJson("test_jsonobjectparser_json1.txt");

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonObject(new JsonProperty[] {
						JsonProperty.create("aaaa", 12345),
						JsonProperty.create("bbbb", new JsonArray(new IJsonValue[] {
							new JsonInt(12345),
							new JsonObject(new JsonProperty[] {
								JsonProperty.create("bbbb", "aaaa"),
								JsonProperty.create("cccc", true),
								JsonProperty.create("dddd", false),
								JsonProperty.create("eeee", new JsonNull())
							})
						})),
						JsonProperty.create("cccc", 123)
				}), json.length())));
	}
}
