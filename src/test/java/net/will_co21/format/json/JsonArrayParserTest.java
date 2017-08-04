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

public class JsonArrayParserTest {
	private static String[] jsons;

	@BeforeClass
	public static void loadJsons() throws UnsupportedCharsetException, UnsupportedEncodingException, FileNotFoundException, IOException
	{
		String currentDir = System.getProperty("user.dir");

		String path = String.join(File.separator, new String[] { currentDir, "testdata", "test_jsonarrayparser_jsons.txt" });

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
		JsonArrayParser parser = new JsonArrayParser();
		String json = ",123]";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \",\" was found."));
		}
	}

	@Test
	public void testParseJsonEndsWithStartSymbol() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json array format."));
		}
	}

	@Test
	public void testParseJsonEndsWithStartSymbolAndBlank() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[  ";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json array format."));
		}
	}

	@Test
	public void testParseJsonFirstCharacterOfValueIsNotAscii() {
		JsonArrayParser parser = new JsonArrayParser();
		String json = "[あ]";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"あ\" was found."));
		}
	}

	@Test
	public void testParseJsonInvalidFirstCharacterOfValue() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[a]";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonIsNotTerminatedCaseValueCountIs1() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[123";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json array format."));
		}
	}

	@Test
	public void testParseJsonIsNotTerminatedCaseValueCountIs2() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[123,\"aaaa\"";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json array format."));
		}
	}

	@Test
	public void testParseJsonIsNotTerminatedCaseValueCountIs1AndHasTrailingBlank() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[123 \n\r";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json array format."));
		}
	}

	@Test
	public void testParseJsonIsNotTerminatedCaseValueCountIs2AndHasTrailingBlank() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[123,\"aaaa\" \n\r";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json array format."));
		}
	}


	@Test
	public void testParseJsonIsNotTerminatedCaseValueCountIs1AndContainWhiteSpace() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[   \r\n123";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json array format."));
		}
	}

	@Test
	public void testParseJsonIsNotTerminatedCaseValueCountIs2AndContainWhiteSpace() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[  \r\n123 \r\n, \r\n\"aaaa\"";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json array format."));
		}
	}

	@Test
	public void testParseJsonIsNotTerminatedCaseValueCountIs1AndHasTrailingBlankAndContainWhiteSpace() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[  \r\n 123 \n\r";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json array format."));
		}
	}

	@Test
	public void testParseJsonIsNotTerminatedCaseValueCountIs2AndHasTrailingBlankAndContainWhiteSpace() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[  \r\n 123 , \"aaaa\" \n\r";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json array format."));
		}
	}

	@Test
	public void testParseJsonInvalidCharacterAfterValueCaseValueCountIs1() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[123;";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \";\" was found."));
		}
	}

	@Test
	public void testParseJsonInvalidCharacterAfterValueCaseValueCountIs2() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[123,1324;";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \";\" was found."));
		}
	}

	@Test
	public void testParseJsonInvalidCharacterAfterValueCaseValueCountIs1AndContainWhiteSpace() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[  \r\n 123 ;  ";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \";\" was found."));
		}
	}

	@Test
	public void testParseJsonInvalidCharacterAfterValueCaseValueCountIs2AndContainWhiteSpace() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[  \r\n  123 ,    1324 ; \n";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \";\" was found."));
		}
	}

	@Test
	public void testParseJsonCaseValueCountIs1AndTerminatedWithComma() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[123,";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json array format."));
		}
	}

	@Test
	public void testParseJsonCaseValueCountIs2AndTerminatedWithComma() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[123,5678,";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json array format."));
		}
	}

	@Test
	public void testParseJsonCaseValueCountIs1AndTerminatedWithCommaAndContainWhiteSpace() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[ \r\n123  ,  \r\n";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json array format."));
		}
	}

	@Test
	public void testParseJsonCaseValueCountIs2AndTerminatedWithCommaAndContainWhiteSpace() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[  123,5678  ,  \r\n";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json array format."));
		}
	}

	@Test
	public void testParseJsonSecondValueFirstCharacterIsNotAscii() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[123,あ";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"あ\" was found."));
		}
	}

	@Test
	public void testParseJsonSecondValueFirstCharacterIsInvalid() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[123,a";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonSecondValueFirstCharacterIsNotAsciiCaseContainWhiteSpace() {
		JsonArrayParser parser = new JsonArrayParser();
		String json ="[ 123  ,  あ ";
		try {
			parser.parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"あ\" was found."));
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
	public void testParseJsonEmptyArrayNotPrettyJson() {
		JsonArrayParser parser = new JsonArrayParser();
		String json = "[]";

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonArray(), json.length())));
	}

	@Test
	public void testParseJsonNotPrettyJson() {
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[0];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonString("aaaa"),
						new JsonBoolean(true),
						new JsonBoolean(false),
						new JsonNull() }), json.length())));
	}

	@Test
	public void testParseJsonNestedInArrayNotPrettyJson() {
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[1];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonArray(new IJsonValue[] {
							new JsonString("aaaa"),
							new JsonBoolean(true),
							new JsonBoolean(false),
							new JsonNull()
						}),
						new JsonInt(123)
				}), json.length())));
	}


	@Test
	public void testParseJsonDoubleNestedInArrayNotPrettyJson() {
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[2];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonArray(new IJsonValue[] {
							new JsonInt(12345),
							new JsonArray(new IJsonValue[] {
								new JsonString("aaaa"),
								new JsonBoolean(true),
								new JsonBoolean(false),
								new JsonNull()
							})
						}),
						new JsonInt(123)
				}), json.length())));
	}

	@Test
	public void testParseJsonPrettyJson() {
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[3];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonString("aaaa"),
						new JsonBoolean(true),
						new JsonBoolean(false),
						new JsonNull() }), json.length())));
	}

	@Test
	public void testParseJsonEmptyArrayJson() {
		JsonArrayParser parser = new JsonArrayParser();
		String json = "[" + "\n" + "\t" +"\n" + "]\n";

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonArray(), json.length())));
	}
	
	@Test
	public void testParseJsonNestedInArrayPrettyJson() {
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[4];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonArray(new IJsonValue[] {
							new JsonString("aaaa"),
							new JsonBoolean(true),
							new JsonBoolean(false),
							new JsonNull()
						}),
						new JsonInt(123)
				}), json.length())));
	}


	@Test
	public void testParseJsonDoubleNestedInArrayPrettyJson() {
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[5];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonArray(new IJsonValue[] {
							new JsonInt(12345),
							new JsonArray(new IJsonValue[] {
								new JsonString("aaaa"),
								new JsonBoolean(true),
								new JsonBoolean(false),
								new JsonNull()
							})
						}),
						new JsonInt(123)
				}), json.length())));
	}


	@Test
	public void testParseJsonPrettyJsonContainWhiteSpace() {
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[6];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonString("aaaa"),
						new JsonBoolean(true),
						new JsonBoolean(false),
						new JsonNull() }), json.length())));
	}

	@Test
	public void testParseJsonNestedInArrayPrettyJsonContainWhiteSpace() {
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[7];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonArray(new IJsonValue[] {
							new JsonString("aaaa"),
							new JsonBoolean(true),
							new JsonBoolean(false),
							new JsonNull()
						}),
						new JsonInt(123)
				}), json.length())));
	}

	@Test
	public void testParseJsonDoubleNestedInArrayPrettyJsonContainWhiteSpace() {
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[8];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonArray(new IJsonValue[] {
							new JsonInt(12345),
							new JsonArray(new IJsonValue[] {
								new JsonString("aaaa"),
								new JsonBoolean(true),
								new JsonBoolean(false),
								new JsonNull()
							})
						}),
						new JsonInt(123)
				}), json.length())));
	}

	@Test
	public void testParseJsonDoubleNestedInArrayPrettyJsonContainWhiteSpaceAndDoubleNL() throws IOException {
		JsonArrayParser parser = new JsonArrayParser();
		String json = loadJson("test_jsonarrayparser_json1.txt");

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat(result, is(new Pair<IJsonValue, Integer>(
				new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonArray(new IJsonValue[] {
							new JsonInt(12345),
							new JsonArray(new IJsonValue[] {
								new JsonString("aaaa"),
								new JsonBoolean(true),
								new JsonBoolean(false),
								new JsonNull()
							})
						}),
						new JsonInt(123)
				}), json.length())));
	}
}
