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
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[0];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat((new JsonArray(new IJsonValue[] {
				new JsonInt(12345),
				new JsonString("aaaa"),
				new JsonBoolean(true),
				new JsonBoolean(false),
				new JsonNull() })).toJson(), is(json));
	}

	@Test
	public void testJsonSourceNestedInArrayNotPrettyJson() {
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[1];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

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
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[2];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

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
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[3];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

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
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[4];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

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
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[5];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

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
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[3];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

		assertThat((new JsonArray(new IJsonValue[] {
						new JsonInt(12345),
						new JsonString("aaaa"),
						new JsonBoolean(true),
						new JsonBoolean(false),
						new JsonNull() })).toPrettyJson(), is(json));
	}

	@Test
	public void testJsonSourceNestedInArrayPrettyJson() {
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[4];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

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
		JsonArrayParser parser = new JsonArrayParser();
		String json = jsons[5];

		Pair<IJsonValue, Integer> result = parser.parseJson(json, 0);

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
}
