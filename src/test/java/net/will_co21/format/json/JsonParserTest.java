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

public class JsonParserTest {
	private static String[] jsons;

	@BeforeClass
	public static void loadJsons() throws UnsupportedCharsetException, UnsupportedEncodingException, FileNotFoundException, IOException
	{
		String currentDir = System.getProperty("user.dir");

		String path = String.join(File.separator, new String[] { currentDir, "testdata", "test_jsonparser_jsons.txt" });

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
		String json = jsons[0];
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"あ\" was found."));
		}
	}

	@Test
	public void testParseJsonInvalidStartSymbolContainWhiteSpace() {
		String json = jsons[1];
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"あ\" was found."));
		}
	}

	@Test
	public void testParseJsonInvalidStartToken() {
		String json = jsons[2];
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonInvalidStartTokenContainWhiteSpace() {
		String json = jsons[3];
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonNumber() {
		String json = jsons[4];
		
		assertThat(JsonParser.parse(json), is(new JsonInt(1234567)));
	}

	@Test
	public void testParseJsonString() {
		String json = jsons[5];
		
		assertThat(JsonParser.parse(json), is(new JsonString("ああああああ")));
	}

	@Test
	public void testParseJsonBooleanTrue() {
		String json = jsons[6];
		
		assertThat(JsonParser.parse(json), is(new JsonBoolean(true)));
	}

	@Test
	public void testParseJsonBooleanFalse() {
		String json = jsons[7];
		
		assertThat(JsonParser.parse(json), is(new JsonBoolean(false)));
	}

	@Test
	public void testParseJsonNull() {
		String json = jsons[8];
		
		assertThat(JsonParser.parse(json), is(new JsonNull()));
	}

	@Test
	public void testParseJsonNumberContainWhiteSpace() {
		String json = jsons[9];
		
		assertThat(JsonParser.parse(json), is(new JsonInt(1234567)));
	}

	@Test
	public void testParseJsonStringContainWhiteSpace() {
		String json = jsons[10];
		
		assertThat(JsonParser.parse(json), is(new JsonString("ああああああ")));
	}

	@Test
	public void testParseJsonBooleanTrueContainWhiteSpace() {
		String json = jsons[11];
		
		assertThat(JsonParser.parse(json), is(new JsonBoolean(true)));
	}

	@Test
	public void testParseJsonBooleanFalseContainWhiteSpace() {
		String json = jsons[12];
		
		assertThat(JsonParser.parse(json), is(new JsonBoolean(false)));
	}

	@Test
	public void testParseJsonNullContainWhiteSpace() {
		String json = jsons[13];
		
		assertThat(JsonParser.parse(json), is(new JsonNull()));
	}

	@Test
	public void testParseJsonNumberExtraStringAtEnd() {
		String json = jsons[14];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \",\" was found."));
		}
	}

	@Test
	public void testParseJsonStringExtraStringAtEnd() {
		String json = jsons[15];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"1\" was found."));
		}
	}

	@Test
	public void testParseJsonBooleanTrueExtraStringAtEnd() {
		String json = jsons[16];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \";\" was found."));
		}
	}

	@Test
	public void testParseJsonBooleanFalseExtraStringAtEnd() {
		String json = jsons[17];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \";\" was found."));
		}
	}

	@Test
	public void testParseJsonNullExtraStringAtEnd() {
		String json = jsons[18];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \".\" was found."));
		}
	}

	@Test
	public void testParseJsonNumberTrailingWhitespaceAndExtraString() {
		String json = jsons[19];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \",\" was found."));
		}
	}

	@Test
	public void testParseJsonStringTrailingWhitespaceAndExtraString() {
		String json = jsons[20];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"1\" was found."));
		}
	}

	@Test
	public void testParseJsonBooleanTrueTrailingWhitespaceAndExtraString() {
		String json = jsons[21];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \";\" was found."));
		}
	}

	@Test
	public void testParseJsonBooleanFalseTrailingWhitespaceAndExtraString() {
		String json = jsons[22];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \";\" was found."));
		}
	}

	@Test
	public void testParseJsonNullTrailingWhitespaceAndExtraString() {
		String json = jsons[23];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \".\" was found."));
		}
	}

	@Test
	public void testParseJsonNumberExtraStringAtEndContainWhiteSpace() {
		String json = jsons[24];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \",\" was found."));
		}
	}

	@Test
	public void testParseJsonStringExtraStringAtEndContainWhiteSpace() {
		String json = jsons[25];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"1\" was found."));
		}
	}

	@Test
	public void testParseJsonBooleanTrueExtraStringAtEndContainWhiteSpace() {
		String json = jsons[26];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \";\" was found."));
		}
	}

	@Test
	public void testParseJsonBooleanFalseExtraStringAtEndContainWhiteSpace() {
		String json = jsons[27];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \";\" was found."));
		}
	}

	@Test
	public void testParseJsonNullExtraStringAtEndContainWhiteSpace() {
		String json = jsons[28];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \".\" was found."));
		}
	}

	@Test
	public void testParseJsonObjectTrailingWhitespaceAndExtraString() {
		String json = jsons[29];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \",\" was found."));
		}
	}

	@Test
	public void testParseJsonArrayTrailingWhitespaceAndExtraString() {
		String json = jsons[30];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \",\" was found."));
		}
	}

	@Test
	public void testParseJsonObjectExtraStringAtEndContainWhiteSpace() {
		String json = jsons[31];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"1\" was found."));
		}
	}

	@Test
	public void testParseJsonArrayExtraStringAtEndContainWhiteSpace() {
		String json = jsons[32];
		
		try {
			JsonParser.parse(json);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"1\" was found."));
		}
	}
	
	@Test
	public void testParseJsonObjectMixedElementsNotPrettyJson()
	{
		String json = jsons[33];
		
		assertThat(JsonParser.parse(json), is(new JsonObject(new JsonProperty[] {
			JsonProperty.create("array", new JsonArray(new IJsonValue[] {
				new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F"),
				new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F"),
				new JsonBigInteger("9223372036854775808"),
				new JsonBigInteger("-9223372036854775809"),
				new JsonLong(9223372036854775807L),
				new JsonLong(-9223372036854775808L),
				new JsonLong(2147483648L),
				new JsonLong(-2147483649L),
				new JsonInt(2147483647),
				new JsonInt(-2147483648),
				new JsonBigDecimal("1.7976931348623158E308"),
				new JsonBigDecimal("-1.7976931348623158E308"),
				new JsonBigDecimal("89884656743115790396864485702651707539967066355018913468086889490222484146382375473324508988793603548165143208346443955473277773925970201315328744335752910340954451000354191838136927422908855765882237865135034927785683479811421457409930417468237646359537084222182755352171355779849754046521440088952087248896.0"),
				new JsonBigDecimal("2.225073858507201259573821257020768020077017763406988739288376763306013328417497570685406341460323054239108249322037716056011260300124027377191834796392769721437078990836532798904431849864732504110467273084696977812028716236556967935895657351868202788722494811530151317616366333296945953431369222190308053787694940411743707809822580740988880551617907119002148759401915892151482081924890263312702257321184750771861452224096212631698623638776860141838061165702263776640907648194435536054336373727978014593100678660492117516784908521511159767373323339191983221326853519128338784891913380715532840971003878993627240686726663397609149834349831344879676653469091559130189899114521124782380547341009775590676096291585949697743018930811385869272811532937339507043361663818359375E-308"),
				new JsonDouble(1.7976931348623157E308d),
				new JsonDouble(-1.7976931348623157E308d),
				new JsonDouble(3.4028234663852886E38d),
				new JsonDouble(3.4028236E38d),
				new JsonDouble(-3.4028236E38d),
				new JsonBigDecimal("170141178389866830818769697729071284224.0"),
				new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38"),
				new JsonDouble(3.40282351E37d),
				new JsonDouble(3.4028235E38d),
				new JsonDouble(-3.4028235E38d)
			})),
			JsonProperty.create("object", new JsonObject(new JsonProperty[] {
				JsonProperty.create("StringNonOption", new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
				JsonProperty.create("StringFullOption", new JsonString("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
					JsonProperty.create("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F", new JsonString("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
				JsonProperty.create("testParseJsonGreaterLongMax", new JsonBigInteger("9223372036854775808")),
				JsonProperty.create("testParseJsonLessLongMin", new JsonBigInteger("-9223372036854775809")),
				JsonProperty.create("testParseJsonLongMax", new JsonLong(9223372036854775807L)),
				JsonProperty.create("testParseJsonLongMin", new JsonLong(-9223372036854775808L)),
				JsonProperty.create("testParseJsonGreaterIntMax", new JsonLong(2147483648L)),
				JsonProperty.create("testParseJsonLessIntMin", new JsonLong(-2147483649L)),
				JsonProperty.create("testParseJsonIntMax", new JsonInt(2147483647)),
				JsonProperty.create("testParseJsonIntMin", new JsonInt(-2147483648)),
				JsonProperty.create("testParseJsonGreaterDoubleMax", new JsonBigDecimal("1.7976931348623158E308")),
				JsonProperty.create("testParseJsonLessDoubleMin", new JsonBigDecimal("-1.7976931348623158E308")),
				JsonProperty.create("testParseJsonMantissaPartOf53bit", new JsonBigDecimal("89884656743115790396864485702651707539967066355018913468086889490222484146382375473324508988793603548165143208346443955473277773925970201315328744335752910340954451000354191838136927422908855765882237865135034927785683479811421457409930417468237646359537084222182755352171355779849754046521440088952087248896.0")),
				JsonProperty.create("testParseJsonMantissaPartOf53bitCaseMin", new JsonBigDecimal("2.225073858507201259573821257020768020077017763406988739288376763306013328417497570685406341460323054239108249322037716056011260300124027377191834796392769721437078990836532798904431849864732504110467273084696977812028716236556967935895657351868202788722494811530151317616366333296945953431369222190308053787694940411743707809822580740988880551617907119002148759401915892151482081924890263312702257321184750771861452224096212631698623638776860141838061165702263776640907648194435536054336373727978014593100678660492117516784908521511159767373323339191983221326853519128338784891913380715532840971003878993627240686726663397609149834349831344879676653469091559130189899114521124782380547341009775590676096291585949697743018930811385869272811532937339507043361663818359375E-308")),
				JsonProperty.create("testParseJsonDoubleMax", new JsonDouble(1.7976931348623157E308d)),
				JsonProperty.create("testParseJsonDoubleMin", new JsonDouble(-1.7976931348623157E308d)),
				JsonProperty.create("testJsonDoubleCaseFloatMaxValueToDouble", new JsonDouble(3.4028234663852886E38d)),
				JsonProperty.create("testParseJsonGreaterFloatMax", new JsonDouble(3.4028236E38d)),
				JsonProperty.create("testParseJsonLessFloatMin", new JsonDouble(-3.4028236E38d)),
				JsonProperty.create("testParseJsonMantissaPartOf24bit", new JsonBigDecimal("170141178389866830818769697729071284224.0")),
				JsonProperty.create("testParseJsonMantissaPartOf24bitCaseMin", new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38")),
				JsonProperty.create("testParseJsonGreaterMantissaPart", new JsonDouble(3.40282351E37d)),
				JsonProperty.create("testParseJsonFloatMax", new JsonDouble(3.4028235E38d)),
				JsonProperty.create("testParseJsonFloatMin", new JsonDouble(-3.4028235E38d))
			}))
		})));
	}
	
	@Test
	public void testParseJsonObjectMixedElementsPrettyJson()
	{
		String json = jsons[34];
		
		assertThat(JsonParser.parse(json), is(new JsonObject(new JsonProperty[] {
			JsonProperty.create("array", new JsonArray(new IJsonValue[] {
				new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F"),
				new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F"),
				new JsonBigInteger("9223372036854775808"),
				new JsonBigInteger("-9223372036854775809"),
				new JsonLong(9223372036854775807L),
				new JsonLong(-9223372036854775808L),
				new JsonLong(2147483648L),
				new JsonLong(-2147483649L),
				new JsonInt(2147483647),
				new JsonInt(-2147483648),
				new JsonBigDecimal("1.7976931348623158E308"),
				new JsonBigDecimal("-1.7976931348623158E308"),
				new JsonBigDecimal("89884656743115790396864485702651707539967066355018913468086889490222484146382375473324508988793603548165143208346443955473277773925970201315328744335752910340954451000354191838136927422908855765882237865135034927785683479811421457409930417468237646359537084222182755352171355779849754046521440088952087248896.0"),
				new JsonBigDecimal("2.225073858507201259573821257020768020077017763406988739288376763306013328417497570685406341460323054239108249322037716056011260300124027377191834796392769721437078990836532798904431849864732504110467273084696977812028716236556967935895657351868202788722494811530151317616366333296945953431369222190308053787694940411743707809822580740988880551617907119002148759401915892151482081924890263312702257321184750771861452224096212631698623638776860141838061165702263776640907648194435536054336373727978014593100678660492117516784908521511159767373323339191983221326853519128338784891913380715532840971003878993627240686726663397609149834349831344879676653469091559130189899114521124782380547341009775590676096291585949697743018930811385869272811532937339507043361663818359375E-308"),
				new JsonDouble(1.7976931348623157E308d),
				new JsonDouble(-1.7976931348623157E308d),
				new JsonDouble(3.4028234663852886E38d),
				new JsonDouble(3.4028236E38d),
				new JsonDouble(-3.4028236E38d),
				new JsonBigDecimal("170141178389866830818769697729071284224.0"),
				new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38"),
				new JsonDouble(3.40282351E37d),
				new JsonDouble(3.4028235E38d),
				new JsonDouble(-3.4028235E38d)
			})),
			JsonProperty.create("object", new JsonObject(new JsonProperty[] {
				JsonProperty.create("StringNonOption", new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
				JsonProperty.create("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F", new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
				JsonProperty.create("StringFullOption", new JsonString("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
				JsonProperty.create("testParseJsonGreaterLongMax", new JsonBigInteger("9223372036854775808")),
				JsonProperty.create("testParseJsonLessLongMin", new JsonBigInteger("-9223372036854775809")),
				JsonProperty.create("testParseJsonLongMax", new JsonLong(9223372036854775807L)),
				JsonProperty.create("testParseJsonLongMin", new JsonLong(-9223372036854775808L)),
				JsonProperty.create("testParseJsonGreaterIntMax", new JsonLong(2147483648L)),
				JsonProperty.create("testParseJsonLessIntMin", new JsonLong(-2147483649L)),
				JsonProperty.create("testParseJsonIntMax", new JsonInt(2147483647)),
				JsonProperty.create("testParseJsonIntMin", new JsonInt(-2147483648)),
				JsonProperty.create("testParseJsonGreaterDoubleMax", new JsonBigDecimal("1.7976931348623158E308")),
				JsonProperty.create("testParseJsonLessDoubleMin", new JsonBigDecimal("-1.7976931348623158E308")),
				JsonProperty.create("testParseJsonMantissaPartOf53bit", new JsonBigDecimal("89884656743115790396864485702651707539967066355018913468086889490222484146382375473324508988793603548165143208346443955473277773925970201315328744335752910340954451000354191838136927422908855765882237865135034927785683479811421457409930417468237646359537084222182755352171355779849754046521440088952087248896.0")),
				JsonProperty.create("testParseJsonMantissaPartOf53bitCaseMin", new JsonBigDecimal("2.225073858507201259573821257020768020077017763406988739288376763306013328417497570685406341460323054239108249322037716056011260300124027377191834796392769721437078990836532798904431849864732504110467273084696977812028716236556967935895657351868202788722494811530151317616366333296945953431369222190308053787694940411743707809822580740988880551617907119002148759401915892151482081924890263312702257321184750771861452224096212631698623638776860141838061165702263776640907648194435536054336373727978014593100678660492117516784908521511159767373323339191983221326853519128338784891913380715532840971003878993627240686726663397609149834349831344879676653469091559130189899114521124782380547341009775590676096291585949697743018930811385869272811532937339507043361663818359375E-308")),
				JsonProperty.create("testParseJsonDoubleMax", new JsonDouble(1.7976931348623157E308d)),
				JsonProperty.create("testParseJsonDoubleMin", new JsonDouble(-1.7976931348623157E308d)),
				JsonProperty.create("testJsonDoubleCaseFloatMaxValueToDouble", new JsonDouble(3.4028234663852886E38d)),
				JsonProperty.create("testParseJsonGreaterFloatMax", new JsonDouble(3.4028236E38d)),
				JsonProperty.create("testParseJsonLessFloatMin", new JsonDouble(-3.4028236E38d)),
				JsonProperty.create("testParseJsonMantissaPartOf24bit", new JsonBigDecimal("170141178389866830818769697729071284224.0")),
				JsonProperty.create("testParseJsonMantissaPartOf24bitCaseMin", new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38")),
				JsonProperty.create("testParseJsonGreaterMantissaPart", new JsonDouble(3.40282351E37d)),
				JsonProperty.create("testParseJsonFloatMax", new JsonDouble(3.4028235E38d)),
				JsonProperty.create("testParseJsonFloatMin", new JsonDouble(-3.4028235E38d))
			}))
		})));
	}

	@Test
	public void testParseJsonArrayMixedElementsNotPrettyJson()
	{
		String json = jsons[35];
		
		assertThat(JsonParser.parse(json), is(new JsonArray(new IJsonValue[] {
			new JsonArray(new IJsonValue[] {
				new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F"),
				new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F"),
				new JsonBigInteger("9223372036854775808"),
				new JsonBigInteger("-9223372036854775809"),
				new JsonLong(9223372036854775807L),
				new JsonLong(-9223372036854775808L),
				new JsonLong(2147483648L),
				new JsonLong(-2147483649L),
				new JsonInt(2147483647),
				new JsonInt(-2147483648),
				new JsonBigDecimal("1.7976931348623158E308"),
				new JsonBigDecimal("-1.7976931348623158E308"),
				new JsonBigDecimal("89884656743115790396864485702651707539967066355018913468086889490222484146382375473324508988793603548165143208346443955473277773925970201315328744335752910340954451000354191838136927422908855765882237865135034927785683479811421457409930417468237646359537084222182755352171355779849754046521440088952087248896.0"),
				new JsonBigDecimal("2.225073858507201259573821257020768020077017763406988739288376763306013328417497570685406341460323054239108249322037716056011260300124027377191834796392769721437078990836532798904431849864732504110467273084696977812028716236556967935895657351868202788722494811530151317616366333296945953431369222190308053787694940411743707809822580740988880551617907119002148759401915892151482081924890263312702257321184750771861452224096212631698623638776860141838061165702263776640907648194435536054336373727978014593100678660492117516784908521511159767373323339191983221326853519128338784891913380715532840971003878993627240686726663397609149834349831344879676653469091559130189899114521124782380547341009775590676096291585949697743018930811385869272811532937339507043361663818359375E-308"),
				new JsonDouble(1.7976931348623157E308d),
				new JsonDouble(-1.7976931348623157E308d),
				new JsonDouble(3.4028234663852886E38d),
				new JsonDouble(3.4028236E38d),
				new JsonDouble(-3.4028236E38d),
				new JsonBigDecimal("170141178389866830818769697729071284224.0"),
				new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38"),
				new JsonDouble(3.40282351E37d),
				new JsonDouble(3.4028235E38d),
				new JsonDouble(-3.4028235E38d)
			}),
			new JsonObject(new JsonProperty[] {
				JsonProperty.create("StringNonOption", new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
				JsonProperty.create("StringFullOption", new JsonString("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
					JsonProperty.create("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F", new JsonString("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
				JsonProperty.create("testParseJsonGreaterLongMax", new JsonBigInteger("9223372036854775808")),
				JsonProperty.create("testParseJsonLessLongMin", new JsonBigInteger("-9223372036854775809")),
				JsonProperty.create("testParseJsonLongMax", new JsonLong(9223372036854775807L)),
				JsonProperty.create("testParseJsonLongMin", new JsonLong(-9223372036854775808L)),
				JsonProperty.create("testParseJsonGreaterIntMax", new JsonLong(2147483648L)),
				JsonProperty.create("testParseJsonLessIntMin", new JsonLong(-2147483649L)),
				JsonProperty.create("testParseJsonIntMax", new JsonInt(2147483647)),
				JsonProperty.create("testParseJsonIntMin", new JsonInt(-2147483648)),
				JsonProperty.create("testParseJsonGreaterDoubleMax", new JsonBigDecimal("1.7976931348623158E308")),
				JsonProperty.create("testParseJsonLessDoubleMin", new JsonBigDecimal("-1.7976931348623158E308")),
				JsonProperty.create("testParseJsonMantissaPartOf53bit", new JsonBigDecimal("89884656743115790396864485702651707539967066355018913468086889490222484146382375473324508988793603548165143208346443955473277773925970201315328744335752910340954451000354191838136927422908855765882237865135034927785683479811421457409930417468237646359537084222182755352171355779849754046521440088952087248896.0")),
				JsonProperty.create("testParseJsonMantissaPartOf53bitCaseMin", new JsonBigDecimal("2.225073858507201259573821257020768020077017763406988739288376763306013328417497570685406341460323054239108249322037716056011260300124027377191834796392769721437078990836532798904431849864732504110467273084696977812028716236556967935895657351868202788722494811530151317616366333296945953431369222190308053787694940411743707809822580740988880551617907119002148759401915892151482081924890263312702257321184750771861452224096212631698623638776860141838061165702263776640907648194435536054336373727978014593100678660492117516784908521511159767373323339191983221326853519128338784891913380715532840971003878993627240686726663397609149834349831344879676653469091559130189899114521124782380547341009775590676096291585949697743018930811385869272811532937339507043361663818359375E-308")),
				JsonProperty.create("testParseJsonDoubleMax", new JsonDouble(1.7976931348623157E308d)),
				JsonProperty.create("testParseJsonDoubleMin", new JsonDouble(-1.7976931348623157E308d)),
				JsonProperty.create("testJsonDoubleCaseFloatMaxValueToDouble", new JsonDouble(3.4028234663852886E38d)),
				JsonProperty.create("testParseJsonGreaterFloatMax", new JsonDouble(3.4028236E38d)),
				JsonProperty.create("testParseJsonLessFloatMin", new JsonDouble(-3.4028236E38d)),
				JsonProperty.create("testParseJsonMantissaPartOf24bit", new JsonBigDecimal("170141178389866830818769697729071284224.0")),
				JsonProperty.create("testParseJsonMantissaPartOf24bitCaseMin", new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38")),
				JsonProperty.create("testParseJsonGreaterMantissaPart", new JsonDouble(3.40282351E37d)),
				JsonProperty.create("testParseJsonFloatMax", new JsonDouble(3.4028235E38d)),
				JsonProperty.create("testParseJsonFloatMin", new JsonDouble(-3.4028235E38d))
			})
		})));
	}

	@Test
	public void testParseJsonArrayMixedElementsPrettyJson()
	{
		String json = jsons[36];
		
		assertThat(JsonParser.parse(json), is(new JsonArray(new IJsonValue[] {
			new JsonArray(new IJsonValue[] {
				new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F"),
				new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F"),
				new JsonBigInteger("9223372036854775808"),
				new JsonBigInteger("-9223372036854775809"),
				new JsonLong(9223372036854775807L),
				new JsonLong(-9223372036854775808L),
				new JsonLong(2147483648L),
				new JsonLong(-2147483649L),
				new JsonInt(2147483647),
				new JsonInt(-2147483648),
				new JsonBigDecimal("1.7976931348623158E308"),
				new JsonBigDecimal("-1.7976931348623158E308"),
				new JsonBigDecimal("89884656743115790396864485702651707539967066355018913468086889490222484146382375473324508988793603548165143208346443955473277773925970201315328744335752910340954451000354191838136927422908855765882237865135034927785683479811421457409930417468237646359537084222182755352171355779849754046521440088952087248896.0"),
				new JsonBigDecimal("2.225073858507201259573821257020768020077017763406988739288376763306013328417497570685406341460323054239108249322037716056011260300124027377191834796392769721437078990836532798904431849864732504110467273084696977812028716236556967935895657351868202788722494811530151317616366333296945953431369222190308053787694940411743707809822580740988880551617907119002148759401915892151482081924890263312702257321184750771861452224096212631698623638776860141838061165702263776640907648194435536054336373727978014593100678660492117516784908521511159767373323339191983221326853519128338784891913380715532840971003878993627240686726663397609149834349831344879676653469091559130189899114521124782380547341009775590676096291585949697743018930811385869272811532937339507043361663818359375E-308"),
				new JsonDouble(1.7976931348623157E308d),
				new JsonDouble(-1.7976931348623157E308d),
				new JsonDouble(3.4028234663852886E38d),
				new JsonDouble(3.4028236E38d),
				new JsonDouble(-3.4028236E38d),
				new JsonBigDecimal("170141178389866830818769697729071284224.0"),
				new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38"),
				new JsonDouble(3.40282351E37d),
				new JsonDouble(3.4028235E38d),
				new JsonDouble(-3.4028235E38d)
			}),
			new JsonObject(new JsonProperty[] {
				JsonProperty.create("StringNonOption", new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
				JsonProperty.create("StringFullOption", new JsonString("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
					JsonProperty.create("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F", new JsonString("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
				JsonProperty.create("testParseJsonGreaterLongMax", new JsonBigInteger("9223372036854775808")),
				JsonProperty.create("testParseJsonLessLongMin", new JsonBigInteger("-9223372036854775809")),
				JsonProperty.create("testParseJsonLongMax", new JsonLong(9223372036854775807L)),
				JsonProperty.create("testParseJsonLongMin", new JsonLong(-9223372036854775808L)),
				JsonProperty.create("testParseJsonGreaterIntMax", new JsonLong(2147483648L)),
				JsonProperty.create("testParseJsonLessIntMin", new JsonLong(-2147483649L)),
				JsonProperty.create("testParseJsonIntMax", new JsonInt(2147483647)),
				JsonProperty.create("testParseJsonIntMin", new JsonInt(-2147483648)),
				JsonProperty.create("testParseJsonGreaterDoubleMax", new JsonBigDecimal("1.7976931348623158E308")),
				JsonProperty.create("testParseJsonLessDoubleMin", new JsonBigDecimal("-1.7976931348623158E308")),
				JsonProperty.create("testParseJsonMantissaPartOf53bit", new JsonBigDecimal("89884656743115790396864485702651707539967066355018913468086889490222484146382375473324508988793603548165143208346443955473277773925970201315328744335752910340954451000354191838136927422908855765882237865135034927785683479811421457409930417468237646359537084222182755352171355779849754046521440088952087248896.0")),
				JsonProperty.create("testParseJsonMantissaPartOf53bitCaseMin", new JsonBigDecimal("2.225073858507201259573821257020768020077017763406988739288376763306013328417497570685406341460323054239108249322037716056011260300124027377191834796392769721437078990836532798904431849864732504110467273084696977812028716236556967935895657351868202788722494811530151317616366333296945953431369222190308053787694940411743707809822580740988880551617907119002148759401915892151482081924890263312702257321184750771861452224096212631698623638776860141838061165702263776640907648194435536054336373727978014593100678660492117516784908521511159767373323339191983221326853519128338784891913380715532840971003878993627240686726663397609149834349831344879676653469091559130189899114521124782380547341009775590676096291585949697743018930811385869272811532937339507043361663818359375E-308")),
				JsonProperty.create("testParseJsonDoubleMax", new JsonDouble(1.7976931348623157E308d)),
				JsonProperty.create("testParseJsonDoubleMin", new JsonDouble(-1.7976931348623157E308d)),
				JsonProperty.create("testJsonDoubleCaseFloatMaxValueToDouble", new JsonDouble(3.4028234663852886E38d)),
				JsonProperty.create("testParseJsonGreaterFloatMax", new JsonDouble(3.4028236E38d)),
				JsonProperty.create("testParseJsonLessFloatMin", new JsonDouble(-3.4028236E38d)),
				JsonProperty.create("testParseJsonMantissaPartOf24bit", new JsonBigDecimal("170141178389866830818769697729071284224.0")),
				JsonProperty.create("testParseJsonMantissaPartOf24bitCaseMin", new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38")),
				JsonProperty.create("testParseJsonGreaterMantissaPart", new JsonDouble(3.40282351E37d)),
				JsonProperty.create("testParseJsonFloatMax", new JsonDouble(3.4028235E38d)),
				JsonProperty.create("testParseJsonFloatMin", new JsonDouble(-3.4028235E38d))
			})
		})));
	}

	@Test
	public void testParseJsonObjectMixedElementsPrettyJsonContainWhiteSpaceAndDoubleNL() throws IOException {
		String json = loadJson("test_jsonparser_json1.txt");
		
		assertThat(JsonParser.parse(json), is(new JsonObject(new JsonProperty[] {
				JsonProperty.create("array", new JsonArray(new IJsonValue[] {
					new JsonString("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F"),
					new JsonString("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F"),
					new JsonBigInteger("9223372036854775808"),
					new JsonBigInteger("-9223372036854775809"),
					new JsonLong(9223372036854775807L),
					new JsonLong(-9223372036854775808L),
					new JsonLong(2147483648L),
					new JsonLong(-2147483649L),
					new JsonInt(2147483647),
					new JsonInt(-2147483648),
					new JsonBigDecimal("1.7976931348623158E308"),
					new JsonBigDecimal("-1.7976931348623158E308"),
					new JsonBigDecimal("89884656743115790396864485702651707539967066355018913468086889490222484146382375473324508988793603548165143208346443955473277773925970201315328744335752910340954451000354191838136927422908855765882237865135034927785683479811421457409930417468237646359537084222182755352171355779849754046521440088952087248896.0"),
					new JsonBigDecimal("2.225073858507201259573821257020768020077017763406988739288376763306013328417497570685406341460323054239108249322037716056011260300124027377191834796392769721437078990836532798904431849864732504110467273084696977812028716236556967935895657351868202788722494811530151317616366333296945953431369222190308053787694940411743707809822580740988880551617907119002148759401915892151482081924890263312702257321184750771861452224096212631698623638776860141838061165702263776640907648194435536054336373727978014593100678660492117516784908521511159767373323339191983221326853519128338784891913380715532840971003878993627240686726663397609149834349831344879676653469091559130189899114521124782380547341009775590676096291585949697743018930811385869272811532937339507043361663818359375E-308"),
					new JsonDouble(1.7976931348623157E308d),
					new JsonDouble(-1.7976931348623157E308d),
					new JsonDouble(3.4028234663852886E38d),
					new JsonDouble(3.4028236E38d),
					new JsonDouble(-3.4028236E38d),
					new JsonBigDecimal("170141178389866830818769697729071284224.0"),
					new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38"),
					new JsonDouble(3.40282351E37d),
					new JsonDouble(3.4028235E38d),
					new JsonDouble(-3.4028235E38d)
				})),
				JsonProperty.create("object", new JsonObject(new JsonProperty[] {
					JsonProperty.create("StringNonOption", new JsonString("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
					JsonProperty.create("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F", new JsonString("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
					JsonProperty.create("StringFullOption", new JsonString("\u0080\u009F\u007F\u00A0" +
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
							"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
					JsonProperty.create("testParseJsonGreaterLongMax", new JsonBigInteger("9223372036854775808")),
					JsonProperty.create("testParseJsonLessLongMin", new JsonBigInteger("-9223372036854775809")),
					JsonProperty.create("testParseJsonLongMax", new JsonLong(9223372036854775807L)),
					JsonProperty.create("testParseJsonLongMin", new JsonLong(-9223372036854775808L)),
					JsonProperty.create("testParseJsonGreaterIntMax", new JsonLong(2147483648L)),
					JsonProperty.create("testParseJsonLessIntMin", new JsonLong(-2147483649L)),
					JsonProperty.create("testParseJsonIntMax", new JsonInt(2147483647)),
					JsonProperty.create("testParseJsonIntMin", new JsonInt(-2147483648)),
					JsonProperty.create("testParseJsonGreaterDoubleMax", new JsonBigDecimal("1.7976931348623158E308")),
					JsonProperty.create("testParseJsonLessDoubleMin", new JsonBigDecimal("-1.7976931348623158E308")),
					JsonProperty.create("testParseJsonMantissaPartOf53bit", new JsonBigDecimal("89884656743115790396864485702651707539967066355018913468086889490222484146382375473324508988793603548165143208346443955473277773925970201315328744335752910340954451000354191838136927422908855765882237865135034927785683479811421457409930417468237646359537084222182755352171355779849754046521440088952087248896.0")),
					JsonProperty.create("testParseJsonMantissaPartOf53bitCaseMin", new JsonBigDecimal("2.225073858507201259573821257020768020077017763406988739288376763306013328417497570685406341460323054239108249322037716056011260300124027377191834796392769721437078990836532798904431849864732504110467273084696977812028716236556967935895657351868202788722494811530151317616366333296945953431369222190308053787694940411743707809822580740988880551617907119002148759401915892151482081924890263312702257321184750771861452224096212631698623638776860141838061165702263776640907648194435536054336373727978014593100678660492117516784908521511159767373323339191983221326853519128338784891913380715532840971003878993627240686726663397609149834349831344879676653469091559130189899114521124782380547341009775590676096291585949697743018930811385869272811532937339507043361663818359375E-308")),
					JsonProperty.create("testParseJsonDoubleMax", new JsonDouble(1.7976931348623157E308d)),
					JsonProperty.create("testParseJsonDoubleMin", new JsonDouble(-1.7976931348623157E308d)),
					JsonProperty.create("testJsonDoubleCaseFloatMaxValueToDouble", new JsonDouble(3.4028234663852886E38d)),
					JsonProperty.create("testParseJsonGreaterFloatMax", new JsonDouble(3.4028236E38d)),
					JsonProperty.create("testParseJsonLessFloatMin", new JsonDouble(-3.4028236E38d)),
					JsonProperty.create("testParseJsonMantissaPartOf24bit", new JsonBigDecimal("170141178389866830818769697729071284224.0")),
					JsonProperty.create("testParseJsonMantissaPartOf24bitCaseMin", new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38")),
					JsonProperty.create("testParseJsonGreaterMantissaPart", new JsonDouble(3.40282351E37d)),
					JsonProperty.create("testParseJsonFloatMax", new JsonDouble(3.4028235E38d)),
					JsonProperty.create("testParseJsonFloatMin", new JsonDouble(-3.4028235E38d))
				}))
			})));
	}


	@Test
	public void testParseJsonArrayMixedElementsPrettyJsonContainWhiteSpaceAndDoubleNL() throws IOException {
		String json = loadJson("test_jsonparser_json2.txt");
		
		assertThat(JsonParser.parse(json), is(new JsonArray(new IJsonValue[] {
			new JsonArray(new IJsonValue[] {
				new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F"),
				new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F"),
				new JsonBigInteger("9223372036854775808"),
				new JsonBigInteger("-9223372036854775809"),
				new JsonLong(9223372036854775807L),
				new JsonLong(-9223372036854775808L),
				new JsonLong(2147483648L),
				new JsonLong(-2147483649L),
				new JsonInt(2147483647),
				new JsonInt(-2147483648),
				new JsonBigDecimal("1.7976931348623158E308"),
				new JsonBigDecimal("-1.7976931348623158E308"),
				new JsonBigDecimal("89884656743115790396864485702651707539967066355018913468086889490222484146382375473324508988793603548165143208346443955473277773925970201315328744335752910340954451000354191838136927422908855765882237865135034927785683479811421457409930417468237646359537084222182755352171355779849754046521440088952087248896.0"),
				new JsonBigDecimal("2.225073858507201259573821257020768020077017763406988739288376763306013328417497570685406341460323054239108249322037716056011260300124027377191834796392769721437078990836532798904431849864732504110467273084696977812028716236556967935895657351868202788722494811530151317616366333296945953431369222190308053787694940411743707809822580740988880551617907119002148759401915892151482081924890263312702257321184750771861452224096212631698623638776860141838061165702263776640907648194435536054336373727978014593100678660492117516784908521511159767373323339191983221326853519128338784891913380715532840971003878993627240686726663397609149834349831344879676653469091559130189899114521124782380547341009775590676096291585949697743018930811385869272811532937339507043361663818359375E-308"),
				new JsonDouble(1.7976931348623157E308d),
				new JsonDouble(-1.7976931348623157E308d),
				new JsonDouble(3.4028234663852886E38d),
				new JsonDouble(3.4028236E38d),
				new JsonDouble(-3.4028236E38d),
				new JsonBigDecimal("170141178389866830818769697729071284224.0"),
				new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38"),
				new JsonDouble(3.40282351E37d),
				new JsonDouble(3.4028235E38d),
				new JsonDouble(-3.4028235E38d)
			}),
			new JsonObject(new JsonProperty[] {
				JsonProperty.create("StringNonOption", new JsonString("\u0080\u009F\u007F\u00A0" +
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
					"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
				JsonProperty.create("StringFullOption", new JsonString("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
					JsonProperty.create("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F", new JsonString("\u0080\u009F\u007F\u00A0" +
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
						"\uE000\uF8FF\uF900\uFFFF\uDFFF\u0100\b\f\n\r\t<>&'/\"\u001F\u007F")),
				JsonProperty.create("testParseJsonGreaterLongMax", new JsonBigInteger("9223372036854775808")),
				JsonProperty.create("testParseJsonLessLongMin", new JsonBigInteger("-9223372036854775809")),
				JsonProperty.create("testParseJsonLongMax", new JsonLong(9223372036854775807L)),
				JsonProperty.create("testParseJsonLongMin", new JsonLong(-9223372036854775808L)),
				JsonProperty.create("testParseJsonGreaterIntMax", new JsonLong(2147483648L)),
				JsonProperty.create("testParseJsonLessIntMin", new JsonLong(-2147483649L)),
				JsonProperty.create("testParseJsonIntMax", new JsonInt(2147483647)),
				JsonProperty.create("testParseJsonIntMin", new JsonInt(-2147483648)),
				JsonProperty.create("testParseJsonGreaterDoubleMax", new JsonBigDecimal("1.7976931348623158E308")),
				JsonProperty.create("testParseJsonLessDoubleMin", new JsonBigDecimal("-1.7976931348623158E308")),
				JsonProperty.create("testParseJsonMantissaPartOf53bit", new JsonBigDecimal("89884656743115790396864485702651707539967066355018913468086889490222484146382375473324508988793603548165143208346443955473277773925970201315328744335752910340954451000354191838136927422908855765882237865135034927785683479811421457409930417468237646359537084222182755352171355779849754046521440088952087248896.0")),
				JsonProperty.create("testParseJsonMantissaPartOf53bitCaseMin", new JsonBigDecimal("2.225073858507201259573821257020768020077017763406988739288376763306013328417497570685406341460323054239108249322037716056011260300124027377191834796392769721437078990836532798904431849864732504110467273084696977812028716236556967935895657351868202788722494811530151317616366333296945953431369222190308053787694940411743707809822580740988880551617907119002148759401915892151482081924890263312702257321184750771861452224096212631698623638776860141838061165702263776640907648194435536054336373727978014593100678660492117516784908521511159767373323339191983221326853519128338784891913380715532840971003878993627240686726663397609149834349831344879676653469091559130189899114521124782380547341009775590676096291585949697743018930811385869272811532937339507043361663818359375E-308")),
				JsonProperty.create("testParseJsonDoubleMax", new JsonDouble(1.7976931348623157E308d)),
				JsonProperty.create("testParseJsonDoubleMin", new JsonDouble(-1.7976931348623157E308d)),
				JsonProperty.create("testJsonDoubleCaseFloatMaxValueToDouble", new JsonDouble(3.4028234663852886E38d)),
				JsonProperty.create("testParseJsonGreaterFloatMax", new JsonDouble(3.4028236E38d)),
				JsonProperty.create("testParseJsonLessFloatMin", new JsonDouble(-3.4028236E38d)),
				JsonProperty.create("testParseJsonMantissaPartOf24bit", new JsonBigDecimal("170141178389866830818769697729071284224.0")),
				JsonProperty.create("testParseJsonMantissaPartOf24bitCaseMin", new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38")),
				JsonProperty.create("testParseJsonGreaterMantissaPart", new JsonDouble(3.40282351E37d)),
				JsonProperty.create("testParseJsonFloatMax", new JsonDouble(3.4028235E38d)),
				JsonProperty.create("testParseJsonFloatMin", new JsonDouble(-3.4028235E38d))
			})
		})));
	}
}
