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

public class JsonSourceTest {
	private static String[] jsons;

	@BeforeClass
	public static void loadJsons() throws UnsupportedCharsetException, UnsupportedEncodingException, FileNotFoundException, IOException
	{
		String currentDir = System.getProperty("user.dir");

		String path = String.join(File.separator, new String[] { currentDir, "testdata", "test_jsonsource_jsons.txt" });

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
		} catch (OutOfIndexRangeException e) {
			assertThat(e.getMessage(), is("This index cannot be referenced. Invalid reference to object."));
		}
	}

	@Test
	public void testNotPrettyJson() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
			JsonProperty.create("value", new Pair<String, String>("aaaa", "bbbb"), (options, detector, obj) -> {
				return (new JsonObject(new JsonProperty[] {
					JsonProperty.create("fst", obj.fst),
					JsonProperty.create("snd", obj.snd)
				})).toJsonSource(options, detector);
			})
		});
		
		assertThat(jobj.toJson(), is(jsons[0]));
	}

	@Test
	public void testPrettyJson() {
		JsonObject jobj = new JsonObject(new JsonProperty[] {
			JsonProperty.create("value", new Pair<String, String>("aaaa", "bbbb"), (options, detector, obj) -> {
				return (new JsonObject(new JsonProperty[] {
					JsonProperty.create("fst", obj.fst),
					JsonProperty.create("snd", obj.snd)
				})).toJsonSource(options, detector);
			})
		});
		
		assertThat(jobj.toPrettyJson(), is(jsons[1]));
	}
}
