package net.will_co21.format.json;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonBooleanTest {

	@Test
	public void testGetBoolean() {
		assertThat((new JsonBoolean(true)).getBoolean(), is(true));
	}

	@Test
	public void testGetOptionalBoolean() {
		assertThat((new JsonBoolean(true)).getOptionalBoolean().get(), is(true));
	}

	@Test
	public void testToJsonSourceCaseTrue() {
		assertThat((new JsonBoolean(true))
				.toJsonSource(new JsonOptions(new JsonOption[] {}), new CircularReferenceDetector()).toJson(),
				is("true"));

	}

	@Test
	public void testToJsonSourceCaseFalse() {
		assertThat((new JsonBoolean(false))
				.toJsonSource(new JsonOptions(new JsonOption[] {}), new CircularReferenceDetector()).toJson(),
				is("false"));

	}
}
