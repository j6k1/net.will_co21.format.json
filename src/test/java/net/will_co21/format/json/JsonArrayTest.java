package net.will_co21.format.json;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonArrayTest {
	private static String[] jsons;

	@BeforeClass
	public static void loadJsons() throws UnsupportedCharsetException, UnsupportedEncodingException, FileNotFoundException, IOException
	{
		String currentDir = System.getProperty("user.dir");

		String path = String.join(File.separator, new String[] { currentDir, "testdata", "test_jsonarrayserializable_jsons.txt" });

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
	public void testGetIntIndexOfOutBound() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		try {
			IJsonValue value = jarr.get(10);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("This index cannot be referenced. Invalid reference to object."));
		}
	}

	@Test
	public void testGetInt() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		assertThat(jarr.get(4), is(new JsonInt(5)));
	}

	@Test
	public void testGetOptionalIntIndexOfOutBound() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		assertThat(jarr.getOptional(10).isPresent(), is(false));
	}

	@Test
	public void testGetOptionalInt() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		assertThat(jarr.getOptional(4).get(), is(new JsonInt(5)));
	}

	@Test
	public void testJsonArrayIJsonValueArrayIsNull() {
		IJsonValue[] values = null;

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonArray type constructor."));
		}
	}

	@Test
	public void testJsonArrayIJsonValueArrayElementIsNull() {
		IJsonValue[] values = new IJsonValue[10];

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("Items in the initialization list contain null values."));
		}
	}

	@Test
	public void testJsonArrayArrayListOfIJsonValueIsNull() {
		ArrayList<IJsonValue> values = null;

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonArray type constructor."));
		}
	}

	@Test
	public void testJsonArrayArrayListOfIJsonValueElementIsNull() {
		ArrayList<IJsonValue> values = new ArrayList<IJsonValue>();

		values.add(null);

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("Items in the initialization list contain null values."));
		}
	}

	@Test
	public void testJsonArrayIntArrayIsNull() {
		int[] values = null;

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonArray type constructor."));
		}
	}

	@Test
	public void testJsonArrayLongArrayIsNull() {
		long[] values = null;

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonArray type constructor."));
		}
	}

	@Test
	public void testJsonArrayFloatArrayIsNull() {
		float[] values = null;

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonArray type constructor."));
		}
	}

	@Test
	public void testJsonArrayDoubleArrayIsNull() {
		double[] values = null;

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonArray type constructor."));
		}
	}

	@Test
	public void testJsonArrayBigIntegerArrayIsNull() {
		BigInteger[] values = null;

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonArray type constructor."));
		}
	}

	@Test
	public void testJsonArrayBigIntegerArrayElementIsNull() {
		BigInteger[] values = new BigInteger[10];

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("Items in the initialization list contain null values."));
		}
	}

	@Test
	public void testJsonArrayBigDecimalArrayIsNull() {
		BigDecimal[] values = null;

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonArray type constructor."));
		}
	}

	@Test
	public void testJsonArrayBigDecimalArrayElementIsNull() {
		BigDecimal[] values = new BigDecimal[10];

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("Items in the initialization list contain null values."));
		}
	}

	@Test
	public void testJsonArrayStringArrayIsNull() {
		String[] values = null;

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonArray type constructor."));
		}
	}

	@Test
	public void testJsonArrayStringArrayElementIsNull() {
		String[] values = new String[10];

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("Items in the initialization list contain null values."));
		}
	}

	@Test
	public void testJsonArrayBooleanArrayIsNull() {
		boolean[] values = null;

		try {
			new JsonArray(values);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonArray type constructor."));
		}
	}

	@Test
	public void testJsonArrayCircularReference() {
		JsonArray arr = new JsonArray();
		arr.add(arr);

		try {
			arr.toJson();
			fail();
		} catch (CircularReferenceException e) {
			assertThat(e.getMessage(), is("Circular reference detected."));
		}
	}

	@Test
	public void testJsonArrayCircularReferenceNested() {
		JsonArray arr = new JsonArray();
		arr.add(new JsonArray(new IJsonValue[] { arr }));

		try {
			arr.toJson();
			fail();
		} catch (CircularReferenceException e) {
			assertThat(e.getMessage(), is("Circular reference detected."));
		}
	}

	@Test
	public void testSize() {
		assertThat((new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 })).size(), is(10));
	}

	@Test
	public void testSetValueIsNull() {
		JsonArray jarr = new JsonArray(new int[] { 0 });

		try {
			jarr.set(0, null);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null reference was passed as the value of the element."));
		}
	}

	@Test
	public void testSet() {
		JsonArray jarr = new JsonArray(new int[] { -1 });

		jarr.set(0, new JsonInt(10));

		assertThat(jarr, is(new JsonArray(new int[] { 10 })));
	}

	@Test
	public void testAddIsNull() {
		JsonArray jarr = new JsonArray();

		try {
			jarr.add(null);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null reference was passed as the value of the element."));
		}
	}

	@Test
	public void testAdd() {
		JsonArray jarr = new JsonArray();

		jarr.add(new JsonInt(10));

		assertThat(jarr, is(new JsonArray(new int[] { 10 })));
	}

	@Test
	public void testInsertIsNull() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		try {
			jarr.insert(5, null);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null reference was passed as the value of the element."));
		}
	}

	@Test
	public void testInsertIndexOfOutBound() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		try {
			jarr.insert(11, null);
			fail();
		} catch (ArrayIndexOutOfBoundsException e) {
			assertThat(e.getMessage(), is("Index out of range was specified."));
		}
	}

	@Test
	public void testInsert() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		jarr.insert(5, new JsonInt(99));

		assertThat(jarr, is(new JsonArray(new int[] { 1,2,3,4,5,99,6,7,8,9,10 })));
	}

	@Test
	public void testInsertTail() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		jarr.insert(10, new JsonInt(11));

		assertThat(jarr, is(new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10,11 })));
	}

	@Test
	public void testRemoveAtIndexOfOutBound() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		try {
			jarr.removeAt(10);
			fail();
		} catch (ArrayIndexOutOfBoundsException e) {
			assertThat(e.getMessage(), is("Index out of range was specified."));
		}
	}

	@Test
	public void testRemoveAt() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		assertThat(jarr.removeAt(0), is(new JsonInt(1)));
		assertThat(jarr, is(new JsonArray(new int[] { 2,3,4,5,6,7,8,9,10 })));
	}

	@Test
	public void testRemoveValueIsNull() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		assertThat(jarr.remove(null), is(false));
		assertThat(jarr, is(new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 })));
	}

	@Test
	public void testRemoveFoundElement() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		assertThat(jarr.remove(new JsonInt(9)), is(true));
		assertThat(jarr, is(new JsonArray(new int[] { 1,2,3,4,5,6,7,8,10 })));
	}

	@Test
	public void testRemoveNotFoundElement() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		assertThat(jarr.remove(new JsonInt(11)), is(false));
		assertThat(jarr, is(new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 })));
	}

	@Test
	public void testToArrayList() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		assertThat(jarr.toArrayList(), is(Arrays.asList(new JsonInt[] {
			new JsonInt(1),
			new JsonInt(2),
			new JsonInt(3),
			new JsonInt(4),
			new JsonInt(5),
			new JsonInt(6),
			new JsonInt(7),
			new JsonInt(8),
			new JsonInt(9),
			new JsonInt(10)
		})));
	}

	@Test
	public void testEachNullCallback() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		try {
			jarr.each(null);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The callback function is a null reference."));
		}
	}

	@Test
	public void testEach() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		final List<Integer> arr = new ArrayList<Integer>();

		jarr.each((v) -> arr.add(v.value.getInt()));

		assertThat(arr, is(Arrays.asList(new Integer[] { 1,2,3,4,5,6,7,8,9,10 })));
	}

	@Test
	public void testMapNullCallback() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		try {
			jarr.map(null);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The callback function is a null reference."));
		}
	}

	@Test
	public void testMap() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		assertThat(jarr.map((v) -> v.value.getInt() * 10), is(Arrays.asList(new Integer[] { 10,20,30,40,50,60,70,80,90,100 })));
	}

	@Test
	public void testFoldNullCallback() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		try {
			jarr.fold(null, null);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("The callback function is a null reference."));
		}
	}

	@Test
	public void testFold() {
		JsonArray jarr = new JsonArray(new int[] { 1,2,3,4,5,6,7,8,9,10 });

		int acc = 0;

		acc = jarr.fold((argAcc, v) -> argAcc + v.value.getInt(), acc);

		assertThat(acc, is(Arrays.asList(new Integer[] { 1,2,3,4,5,6,7,8,9,10 }).stream().reduce(0, (v1, v2) -> v1 + v2)));
	}


	@Test
	public void testJsonSourceNotPrettyJson() {
		String json = jsons[0];

		assertThat((new JsonArray(new IJsonValue[] {
				new JsonInt(12345),
				new JsonString("aaaa"),
				new JsonBoolean(true),
				new JsonBoolean(false),
				new JsonNull() })).toJson(), is(json));
	}

	@Test
	public void testJsonSourceNestedInArrayNotPrettyJson() {
		String json = jsons[1];

		assertThat((new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonArray(new IJsonValue[] {
							new JsonString("aaaa"),
							new JsonBoolean(true),
							new JsonBoolean(false),
							new JsonNull()
						}),
						new JsonInt(123)
				})).toJson(), is(json));
	}

	@Test
	public void testJsonSourceDoubleNestedInArrayNotPrettyJson() {
		String json = jsons[2];

		assertThat((
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
				})).toJson(), is(json));
	}

	@Test
	public void testJsonSourcePrettyPrintOption() {
		String json = jsons[3];

		assertThat((new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonString("aaaa"),
						new JsonBoolean(true),
						new JsonBoolean(false),
						new JsonNull() })).toJson(new JsonOptions(new JsonOption[] {
							JsonOption.PRETTY_PRINT
						})), is(json));
	}

	@Test
	public void testJsonSourceNestedInArrayPrintOption() {
		String json = jsons[4];

		assertThat((new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonArray(new IJsonValue[] {
							new JsonString("aaaa"),
							new JsonBoolean(true),
							new JsonBoolean(false),
							new JsonNull()
						}),
						new JsonInt(123)
				})).toJson(new JsonOptions (new JsonOption[] {
					JsonOption.PRETTY_PRINT
				})), is(json));
	}


	@Test
	public void testJsonSourceDoubleNestedInArrayPrintOption() {
		String json = jsons[5];

		assertThat((new JsonArray(new IJsonValue[] {
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
				})).toJson(new JsonOptions (new JsonOption[] {
					JsonOption.PRETTY_PRINT
				})), is(json));
	}


	@Test
	public void testJsonSourcePrettyJson() {
		String json = jsons[3];

		assertThat((new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonString("aaaa"),
						new JsonBoolean(true),
						new JsonBoolean(false),
						new JsonNull() })).toPrettyJson(), is(json));
	}

	@Test
	public void testJsonSourceNestedInArrayPrettyJson() {
		String json = jsons[4];

		assertThat((new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonArray(new IJsonValue[] {
							new JsonString("aaaa"),
							new JsonBoolean(true),
							new JsonBoolean(false),
							new JsonNull()
						}),
						new JsonInt(123)
				})).toPrettyJson(), is(json));
	}

	@Test
	public void testJsonSourceDoubleNestedInArrayPrettyJson() {
		String json = jsons[5];

		assertThat((new JsonArray(new IJsonValue[] {
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
				})).toPrettyJson(), is(json));
	}

	@Test
	public void testJsonSourceSameInstanceOfSiblingElementNotPrettyJson() {
		String json = jsons[6];

		JsonArray arr = new JsonArray(new int[] {1,2,3});

		assertThat((new JsonArray(new IJsonValue[] {
						arr,
						arr
				})).toJson(), is(json));
	}

	@Test
	public void testJsonSourceSameInstanceOfSiblingElementPrettyJson() {
		String json = jsons[7];

		JsonArray arr = new JsonArray(new int[] {1,2,3});

		assertThat((new JsonArray(new IJsonValue[] {
						arr,
						arr
				})).toPrettyJson(), is(json));
	}

	@Test
	public void testJsonSourceFullStringOptionAndMixedElementsNotPrettyJson() {
		String json = jsons[8];

		assertThat((new JsonArray(new IJsonValue[] {
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
			new JsonDouble(-1.7976931348623157E308),
			new JsonDouble(3.4028234663852886E38d),
			new JsonDouble(3.4028236E38d),
			new JsonDouble(-3.4028236E38d),
			new JsonBigDecimal("170141178389866830818769697729071284224.0"),
			new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38"),
			new JsonDouble(3.40282351E37d),
			new JsonFloat(3.4028235E38f),
			new JsonFloat(-3.4028235E38f)
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
	public void testJsonSourceFullStringOptionAndMixedElementsPrettyJson() {
		String json = jsons[9];

		assertThat((new JsonArray(new IJsonValue[] {
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
			new JsonDouble(-1.7976931348623157E308),
			new JsonDouble(3.4028234663852886E38d),
			new JsonDouble(3.4028236E38d),
			new JsonDouble(-3.4028236E38d),
			new JsonBigDecimal("170141178389866830818769697729071284224.0"),
			new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38"),
			new JsonDouble(3.40282351E37d),
			new JsonFloat(3.4028235E38f),
			new JsonFloat(-3.4028235E38f)
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

		assertThat((new JsonArray(new IJsonValue[] {
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
			new JsonDouble(-1.7976931348623157E308),
			new JsonDouble(3.4028234663852886E38d),
			new JsonDouble(3.4028236E38d),
			new JsonDouble(-3.4028236E38d),
			new JsonBigDecimal("170141178389866830818769697729071284224.0"),
			new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38"),
			new JsonDouble(3.40282351E37d),
			new JsonFloat(3.4028235E38f),
			new JsonFloat(-3.4028235E38f)
		})).toJson(new JsonOptions(new JsonOption[] {
		})), is(json));

	}


	@Test
	public void testJsonSourceNonOptionAndMixedElementsPrettyJson() {
		String json = jsons[11];

		assertThat((new JsonArray(new IJsonValue[] {
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
			new JsonDouble(-1.7976931348623157E308),
			new JsonDouble(3.4028234663852886E38d),
			new JsonDouble(3.4028236E38d),
			new JsonDouble(-3.4028236E38d),
			new JsonBigDecimal("170141178389866830818769697729071284224.0"),
			new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38"),
			new JsonDouble(3.40282351E37d),
			new JsonFloat(3.4028235E38f),
			new JsonFloat(-3.4028235E38f)
		})).toPrettyJson(new JsonOptions(new JsonOption[] {
		})), is(json));

	}


	@Test
	public void testJsonSourceOptionAndMixedElementsNotPrettyJson() {
		String json = jsons[12];

		assertThat((new JsonArray(new IJsonValue[] {
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
			new JsonDouble(-1.7976931348623157E308),
			new JsonDouble(3.4028234663852886E38d),
			new JsonDouble(3.4028236E38d),
			new JsonDouble(-3.4028236E38d),
			new JsonBigDecimal("170141178389866830818769697729071284224.0"),
			new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38"),
			new JsonDouble(3.40282351E37d),
			new JsonFloat(3.4028235E38f),
			new JsonFloat(-3.4028235E38f)
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

		assertThat((new JsonArray(new IJsonValue[] {
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
			new JsonDouble(-1.7976931348623157E308),
			new JsonDouble(3.4028234663852886E38d),
			new JsonDouble(3.4028236E38d),
			new JsonDouble(-3.4028236E38d),
			new JsonBigDecimal("170141178389866830818769697729071284224.0"),
			new JsonBigDecimal("2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38"),
			new JsonDouble(3.40282351E37d),
			new JsonFloat(3.4028235E38f),
			new JsonFloat(-3.4028235E38f)
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
