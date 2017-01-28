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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonObjectTest {
	private static String[] jsons;

	@BeforeClass
	public static void loadJsons() throws UnsupportedCharsetException, UnsupportedEncodingException, FileNotFoundException, IOException
	{
		String currentDir = System.getProperty("user.dir");

		String path = String.join(File.separator, new String[] { currentDir, "testdata", "test_jsonobjectserializable_jsons.txt" });

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

	@Test
	public void testJsonObject() {
		JsonObject jobj = new JsonObject();
		
		jobj.put("aaaa", new JsonInt(10));
		
		assertThat(jobj, is(new JsonObject(new JsonProperty[] {
			JsonProperty.create("aaaa", 10)
		})));
	}

	@Test
	public void testJsonObjectJsonPropertyArrayIsNull() {
		JsonProperty[] props = null;

		try {
			new JsonObject(props);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonObject type constructor."));
		}
	}

	@Test
	public void testJsonObjectJsonPropertyArrayElementIsNull() {
		JsonProperty[] props = new JsonProperty[10];

		try {
			new JsonObject(props);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("Items in the initialization list contain null values."));
		}
	}

	@Test
	public void testJsonObjectListOfJsonPropertyIsNull() {
		List<JsonProperty> props = null;

		try {
			new JsonObject(props);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonObject type constructor."));
		}
	}

	@Test
	public void testJsonObjectListOfJsonPropertyElementIsNull() {
		List<JsonProperty> props = new ArrayList<JsonProperty>();

		props.add(null);

		try {
			new JsonObject(props);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("Items in the initialization list contain null values."));
		}
	}

	@Test
	public void testJsonObjectListOfJsonStringIJsonValueMapIsNull() {
		Map<String, IJsonValue> props = null;

		try {
			new JsonObject(props);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonObject type constructor."));
		}
	}

	@Test
	public void testJsonObjectListOfStringIJsonValueMapElementIsNull() {
		Map<String, IJsonValue> props = new TreeMap<String, IJsonValue>();

		props.put("hoge", null);

		try {
			new JsonObject(props);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("Items in the initialization list contain null values."));
		}
	}

	@Test
	public void testGetStringNonExistentKey() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		try {
			IJsonValue value = jobj.get("dddd");
			fail();
		} catch (KeyNotFoundException e) {
			assertThat(e.getMessage(), is("Reference by this key is invalid."));
		}
	}

	@Test
	public void testGetString() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.get("cccc"), is(new JsonInt(3)));
	}

	@Test
	public void testGetOptionalStringNonExistentKey() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.getOptional("dddd").isPresent(), is(false));
	}

	@Test
	public void testGetOptionalString() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.getOptional("cccc").get(), is(new JsonInt(3)));
	}

	@Test
	public void testPutKeyIsNull() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		try {
			jobj.put(null, new JsonInt(4));
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null reference was passed as the value of the element."));
		}
	}

	@Test
	public void testPutValueIsNull() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		try {
			jobj.put("aaaa", null);
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null reference was passed as the value of the element."));
		}
	}

	@Test
	public void testPutHasKey() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.put("aaaa", new JsonInt(4)), is(new JsonInt(1)));
		assertThat(jobj, is(new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 4),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		})));
	}

	@Test
	public void testPutNoContainKey() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.put("dddd", new JsonInt(4)), nullValue());
		assertThat(jobj, is(new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3),
				JsonProperty.create("dddd", 4)
		})));
	}


	@Test
	public void testRemoveKeyIsNull() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.remove(null), nullValue());
		assertThat(jobj, is(new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		})));
	}

	@Test
	public void testRemoveHasKey() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.remove("aaaa"), is(new JsonInt(1)));
		assertThat(jobj, is(new JsonObject(new JsonProperty[] {
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		})));
	}

	@Test
	public void testRemoveNoContainKey() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.remove("dddd"), nullValue());
		assertThat(jobj, is(new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		})));
	}

	@Test
	public void testContainsKeyNullKey() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.containsKey(null), is(false));
	}

	@Test
	public void testContainsKeyNoContainKey() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.containsKey("dddd"), is(false));
	}

	@Test
	public void testContainsKeyHashKey() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.containsKey("cccc"), is(true));
	}

	@Test
	public void testContainValueNullValue() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.containsValue(null), is(false));
	}

	@Test
	public void testContainsValueNoContainValue() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.containsValue(new JsonInt(4)), is(false));
	}

	@Test
	public void testContainsValueHashValue() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.containsValue(new JsonInt(3)), is(true));
	}

	@Test
	public void testSize() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.size(), is(3));
	}

	@Test
	public void testToEntrySet() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.entrySet(), is((new TreeMap<String, IJsonValue>() {
		/**
			 *
			 */
			private static final long serialVersionUID = 4487242222443151532L;

		{
			put("aaaa", new JsonInt(1));
			put("bbbb", new JsonInt(2));
			put("cccc", new JsonInt(3));
		}}).entrySet()));
	}

	@Test
	public void testToTreeMap() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.toTreeMap(), is(new TreeMap<String, IJsonValue>() {
		/**
			 *
			 */
			private static final long serialVersionUID = 4487242222443151532L;

		{
			put("aaaa", new JsonInt(1));
			put("bbbb", new JsonInt(2));
			put("cccc", new JsonInt(3));
		}}));
	}

	@Test
	public void testEachNullCallback() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		try {
			jobj.each(null);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The callback function is a null reference."));
		}
	}

	@Test
	public void testEach() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		final List<Integer> arr = new ArrayList<Integer>();

		jobj.each((v) -> arr.add(v.value.getInt()));

		assertThat(arr, is(Arrays.asList(new Integer[] { 1,2,3 })));
	}

	@Test
	public void testMapNullCallback() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		try {
			jobj.map(null);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The callback function is a null reference."));
		}
	}

	@Test
	public void testMap() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		assertThat(jobj.map((v) -> v.value.getInt() * 10), is(new TreeMap<String, Integer>() {/**
			 *
			 */
			private static final long serialVersionUID = 8512281126508579233L;

		{
			put("aaaa", 10);
			put("bbbb", 20);
			put("cccc", 30);
		}}));
	}

	@Test
	public void testFoldNullCallback() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		try {
			jobj.fold(null, null);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The callback function is a null reference."));
		}
	}

	@Test
	public void testFold() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 1),
				JsonProperty.create("bbbb", 2),
				JsonProperty.create("cccc", 3)
		});

		int acc = 0;

		acc = jobj.fold((argAcc, v) -> argAcc + v.value.getInt(), acc);

		assertThat(acc, is(Arrays.asList(new Integer[] { 1,2,3 }).stream().reduce(0, (v1, v2) -> v1 + v2)));
	}

	@Test
	public void testToObject()
	{
		JsonObject jobj = new JsonObject(new JsonProperty[] {
			JsonProperty.create("fst", "aaaa"),
			JsonProperty.create("snd", "bbbb")
		});
		
		assertThat(jobj.toObject((json) -> new Pair<String, String>(json.get("fst").getString(), json.get("snd").getString())), 
				is(new Pair<String, String>("aaaa", "bbbb")));
	}
	
	@Test
	public void testJsonArrayCircularReferenceNested() {
		JsonObject jobj = new JsonObject();
		jobj.put("aaaa", new JsonObject(new JsonProperty[] {
				JsonProperty.create("bbbb", jobj)
		}));

		try {
			jobj.toJson();
			fail();
		} catch (CircularReferenceException e) {
			assertThat(e.getMessage(), is("Circular reference detected."));
		}
	}

	@Test
	public void testJsonSourceNotPrettyJson() {
		String json = jsons[0];

		assertThat((new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 12345),
				JsonProperty.create("bbbb", "aaaa"),
				JsonProperty.create("cccc", true),
				JsonProperty.create("dddd", false),
				JsonProperty.create("eeee", new JsonNull()) })).toJson(), is(json));
	}

	@Test
	public void testJsonSourceNestedInArrayNotPrettyJson() {
		String json = jsons[1];

		assertThat((new JsonObject(new JsonProperty[] {
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
		})).toJson(), is(json));
	}

	@Test
	public void testJsonSourceDoubleNestedInArrayNotPrettyJson() {
		String json = jsons[2];

		assertThat((new JsonObject(new JsonProperty[] {
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
		})).toJson(), is(json));
	}

	@Test
	public void testJsonSourcePrettyPrintOption() {
		String json = jsons[3];

		assertThat((new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", 12345),
				JsonProperty.create("bbbb", "aaaa"),
				JsonProperty.create("cccc", true),
				JsonProperty.create("dddd", false),
				JsonProperty.create("eeee", new JsonNull()) })).toJson(new JsonOptions(new JsonOption[] {
							JsonOption.PRETTY_PRINT
						})), is(json));
	}

	@Test
	public void testJsonSourceNestedInArrayPrintOption() {
		String json = jsons[4];

		assertThat((new JsonObject(new JsonProperty[] {
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
			})).toJson(new JsonOptions (new JsonOption[] {
				JsonOption.PRETTY_PRINT
			})), is(json));
	}


	@Test
	public void testJsonSourceDoubleNestedInArrayPrintOption() {
		String json = jsons[5];

		assertThat((new JsonObject(new JsonProperty[] {
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
				})).toJson(new JsonOptions (new JsonOption[] {
					JsonOption.PRETTY_PRINT
				})), is(json));
	}


	@Test
	public void testJsonSourceSameInstanceOfSiblingElementNotPrettyJson() {
		String json = jsons[6];

		JsonArray arr = new JsonArray(new int[] {1,2,3});

		assertThat((new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", arr),
				JsonProperty.create("bbbb", arr)
			})).toJson(), is(json));
	}

	@Test
	public void testJsonSourceSameInstanceOfSiblingElementPrettyJson() {
		String json = jsons[7];

		JsonArray arr = new JsonArray(new int[] {1,2,3});

		assertThat((new JsonObject(new JsonProperty[] {
				JsonProperty.create("aaaa", arr),
				JsonProperty.create("bbbb", arr)
			})).toPrettyJson(), is(json));
	}

	@Test
	public void testJsonSourceFullStringOptionMixedElementsNotPrettyJson() {
		String json = jsons[8];

		assertThat((new JsonObject(new JsonProperty[] {
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
			JsonProperty.create("testParseJsonDoubleMin", new JsonDouble(-1.7976931348623157E308)),
			JsonProperty.create("testJsonDoubleCaseFloatMaxValueToDouble", new JsonDouble(3.4028234663852886E38d)),
			JsonProperty.create("testParseJsonGreaterFloatMax", new JsonDouble(3.4028236E38d)),
			JsonProperty.create("testParseJsonLessFloatMin", new JsonDouble(-3.4028236E38d)),
			JsonProperty.create("testParseJsonMantissaPartOf24bit", new JsonBigDecimal("170141178389866830818769697729071284224.0")),
			JsonProperty.create("testParseJsonMantissaPartOf24bitCaseMin", new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38")),
			JsonProperty.create("testParseJsonGreaterMantissaPart", new JsonDouble(3.40282351E37d)),
			JsonProperty.create("testParseJsonFloatMax", new JsonFloat(3.4028235E38f)),
			JsonProperty.create("testParseJsonFloatMin", new JsonFloat(-3.4028235E38f))
		})).toJson(new JsonOptions(new JsonOption[] {
				JsonOption.HEX_TAG,
                JsonOption.HEX_AMP,
                JsonOption.HEX_APO,
                JsonOption.HEX_QUOT,
                JsonOption.UNESCAPED_SLASHES,
                JsonOption.ESCAPED_UNICODE
		})), is(json));
	}


	@Test
	public void testJsonSourceFullStringOptionMixedElementsPrettyJson() {
		String json = jsons[9];

		assertThat((new JsonObject(new JsonProperty[] {
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
				JsonProperty.create("testParseJsonDoubleMin", new JsonDouble(-1.7976931348623157E308)),
				JsonProperty.create("testJsonDoubleCaseFloatMaxValueToDouble", new JsonDouble(3.4028234663852886E38d)),
				JsonProperty.create("testParseJsonGreaterFloatMax", new JsonDouble(3.4028236E38d)),
				JsonProperty.create("testParseJsonLessFloatMin", new JsonDouble(-3.4028236E38d)),
				JsonProperty.create("testParseJsonMantissaPartOf24bit", new JsonBigDecimal("170141178389866830818769697729071284224.0")),
				JsonProperty.create("testParseJsonMantissaPartOf24bitCaseMin", new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38")),
				JsonProperty.create("testParseJsonGreaterMantissaPart", new JsonDouble(3.40282351E37d)),
				JsonProperty.create("testParseJsonFloatMax", new JsonFloat(3.4028235E38f)),
				JsonProperty.create("testParseJsonFloatMin", new JsonFloat(-3.4028235E38f))
		})).toPrettyJson(new JsonOptions(new JsonOption[] {
				JsonOption.HEX_TAG,
                JsonOption.HEX_AMP,
                JsonOption.HEX_APO,
                JsonOption.HEX_QUOT,
                JsonOption.UNESCAPED_SLASHES,
                JsonOption.ESCAPED_UNICODE
		})), is(json));
	}
	@Test
	public void testJsonSourceNonOptionAndMixedElementsNotPrettyJson() {
		String json = jsons[10];

		assertThat((new JsonObject(new JsonProperty[] {
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
				JsonProperty.create("testParseJsonDoubleMin", new JsonDouble(-1.7976931348623157E308)),
				JsonProperty.create("testJsonDoubleCaseFloatMaxValueToDouble", new JsonDouble(3.4028234663852886E38d)),
				JsonProperty.create("testParseJsonGreaterFloatMax", new JsonDouble(3.4028236E38d)),
				JsonProperty.create("testParseJsonLessFloatMin", new JsonDouble(-3.4028236E38d)),
				JsonProperty.create("testParseJsonMantissaPartOf24bit", new JsonBigDecimal("170141178389866830818769697729071284224.0")),
				JsonProperty.create("testParseJsonMantissaPartOf24bitCaseMin", new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38")),
				JsonProperty.create("testParseJsonGreaterMantissaPart", new JsonDouble(3.40282351E37d)),
				JsonProperty.create("testParseJsonFloatMax", new JsonFloat(3.4028235E38f)),
				JsonProperty.create("testParseJsonFloatMin", new JsonFloat(-3.4028235E38f))
		})).toJson(new JsonOptions(new JsonOption[] {
		})), is(json));

	}


	@Test
	public void testJsonSourceNonOptionAndMixedElementsPrettyJson() {
		String json = jsons[11];

		assertThat((new JsonObject(new JsonProperty[] {
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
				JsonProperty.create("testParseJsonDoubleMin", new JsonDouble(-1.7976931348623157E308)),
				JsonProperty.create("testJsonDoubleCaseFloatMaxValueToDouble", new JsonDouble(3.4028234663852886E38d)),
				JsonProperty.create("testParseJsonGreaterFloatMax", new JsonDouble(3.4028236E38d)),
				JsonProperty.create("testParseJsonLessFloatMin", new JsonDouble(-3.4028236E38d)),
				JsonProperty.create("testParseJsonMantissaPartOf24bit", new JsonBigDecimal("170141178389866830818769697729071284224.0")),
				JsonProperty.create("testParseJsonMantissaPartOf24bitCaseMin", new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38")),
				JsonProperty.create("testParseJsonGreaterMantissaPart", new JsonDouble(3.40282351E37d)),
				JsonProperty.create("testParseJsonFloatMax", new JsonFloat(3.4028235E38f)),
				JsonProperty.create("testParseJsonFloatMin", new JsonFloat(-3.4028235E38f))
		})).toPrettyJson(new JsonOptions(new JsonOption[] {
		})), is(json));

	}


	@Test
	public void testJsonSourceFullOptionAndMixedElementsNotPrettyJson() {
		String json = jsons[12];

		assertThat((new JsonObject(new JsonProperty[] {
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
				JsonProperty.create("testParseJsonDoubleMin", new JsonDouble(-1.7976931348623157E308)),
				JsonProperty.create("testJsonDoubleCaseFloatMaxValueToDouble", new JsonDouble(3.4028234663852886E38d)),
				JsonProperty.create("testParseJsonGreaterFloatMax", new JsonDouble(3.4028236E38d)),
				JsonProperty.create("testParseJsonLessFloatMin", new JsonDouble(-3.4028236E38d)),
				JsonProperty.create("testParseJsonMantissaPartOf24bit", new JsonBigDecimal("170141178389866830818769697729071284224.0")),
				JsonProperty.create("testParseJsonMantissaPartOf24bitCaseMin", new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38")),
				JsonProperty.create("testParseJsonGreaterMantissaPart", new JsonDouble(3.40282351E37d)),
				JsonProperty.create("testParseJsonFloatMax", new JsonFloat(3.4028235E38f)),
				JsonProperty.create("testParseJsonFloatMin", new JsonFloat(-3.4028235E38f))
		})).toJson(new JsonOptions(new JsonOption[] {
				JsonOption.HEX_TAG,
                JsonOption.HEX_AMP,
                JsonOption.HEX_APO,
                JsonOption.HEX_QUOT,
                JsonOption.NUMBER_TO_STRING,
                JsonOption.BIGINT_AS_STRING,
                JsonOption.BIGLONG_AS_STRING,
                JsonOption.BIGFLOAT_AS_STRING,
                JsonOption.BIGDOUBLE_AS_STRING,
                JsonOption.UNESCAPED_SLASHES,
                JsonOption.ESCAPED_UNICODE
		})), is(json));

	}


	@Test
	public void testJsonSourceFullOptionAndMixedElementsPrettyJson() {
		String json = jsons[13];

		assertThat((new JsonObject(new JsonProperty[] {
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
				JsonProperty.create("testParseJsonDoubleMin", new JsonDouble(-1.7976931348623157E308)),
				JsonProperty.create("testJsonDoubleCaseFloatMaxValueToDouble", new JsonDouble(3.4028234663852886E38d)),
				JsonProperty.create("testParseJsonGreaterFloatMax", new JsonDouble(3.4028236E38d)),
				JsonProperty.create("testParseJsonLessFloatMin", new JsonDouble(-3.4028236E38d)),
				JsonProperty.create("testParseJsonMantissaPartOf24bit", new JsonBigDecimal("170141178389866830818769697729071284224.0")),
				JsonProperty.create("testParseJsonMantissaPartOf24bitCaseMin", new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38")),
				JsonProperty.create("testParseJsonGreaterMantissaPart", new JsonDouble(3.40282351E37d)),
				JsonProperty.create("testParseJsonFloatMax", new JsonFloat(3.4028235E38f)),
				JsonProperty.create("testParseJsonFloatMin", new JsonFloat(-3.4028235E38f))
		})).toPrettyJson(new JsonOptions(new JsonOption[] {
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
		})), is(json));

	}
}
