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

public class JsonCompositTest {
	private static String[] jsons;

	@BeforeClass
	public static void loadJsons() throws UnsupportedCharsetException, UnsupportedEncodingException, FileNotFoundException, IOException
	{
		String currentDir = System.getProperty("user.dir");

		String path = String.join(File.separator, new String[] { currentDir, "testdata", "test_jsoncomposit_jsons.txt" });

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
	public void testGet() {
		PairComposit obj = new PairComposit(new Pair<String, String>("aaaa", "uuuu"));
		
		assertThat(obj.get(), is(new Pair<String, String>("aaaa", "uuuu")));
	}
	
	@Test
	public void testToJsonSourceNotPrettyJson()
	{
		PairComposit obj = new PairComposit(new Pair<String, String>("aaaa", "uuuu"));
		
		assertThat(obj.toJson(), is(jsons[0]));
	}
	
	@Test
	public void testToJsonSourcePrettyJson()
	{
		PairComposit obj = new PairComposit(new Pair<String, String>("aaaa", "uuuu"));
		
		assertThat(obj.toPrettyJson(), is(jsons[1]));
	}
}
class PairComposit extends JsonComposit<Pair<String, String>> {
	public PairComposit(Pair<String, String> value)
	{
		this.value = value;
	}
	
	public IPrettyJsonSerializable toJsonSource(JsonOptions options, CircularReferenceDetector detector)
	{
		return (new JsonObject(new JsonProperty[] {
			JsonProperty.create("fst", value.fst),
			JsonProperty.create("snd", value.snd)
		})).toJsonSource(options, detector);
	}
}
