package net.will_co21.format.json;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonPropertyTest {

	@Test
	public void testCreateStringIJsonValueCaseNullKey() {
		try {
			JsonProperty.create(null, new JsonInt(0));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed as the value of key to the JsonProperty constructor."));
		}
	}

	@Test
	public void testCreateStringIJsonValueCaseNullValue() {
		try {
			JsonInt value = null;
			JsonProperty.create("aa", value);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonProperty type constructor."));
		}
	}

	@Test
	public void testCreateStringTIJsonSourceDelegateOfTCaseNullKey() {
		try {
			JsonProperty.create(null, new Pair<String, Integer>("key", 1), (options, detector, obj) -> {
				return (new JsonStringSerializable(obj.fst + obj.toString(), options));
			});
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed as the value of key to the JsonProperty constructor."));
		}
	}

	@Test
	public void testCreateStringTIJsonSourceDelegateOfTNullObj() {
		try {
			Pair<String, Integer> value = null;
			JsonProperty.create("aa", value,  (options, detector, obj) -> {
				return (new JsonStringSerializable(obj.fst + obj.toString(), options));
			});
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonProperty type constructor."));
		}
	}

	@Test
	public void testCreateStringTIJsonSourceDelegateOfTCaseNullSourceCreator() {
		try {
			JsonProperty.create("aa", new Pair<String, Integer>("key", 1), null);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("IJsonSourceDelegate type object is null."));
		}
	}

	@Test
	public void testCreateStringIntCaseNullKey() {
		try {
			JsonProperty.create(null, 1);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed as the value of key to the JsonProperty constructor."));
		}
	}

	@Test
	public void testCreateStringLongCaseNullKey() {
		try {
			JsonProperty.create(null, 1L);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed as the value of key to the JsonProperty constructor."));
		}
	}

	@Test
	public void testCreateStringFloatCaseNullKey() {
		try {
			JsonProperty.create(null, 1.0f);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed as the value of key to the JsonProperty constructor."));
		}
	}

	@Test
	public void testCreateStringDoubleCaseNullKey() {
		try {
			JsonProperty.create(null, 1.0d);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed as the value of key to the JsonProperty constructor."));
		}
	}

	@Test
	public void testCreateStringBigIntegerCaseNullKey() {
		try {
			JsonProperty.create(null, BigInteger.valueOf(1));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed as the value of key to the JsonProperty constructor."));
		}
	}

	@Test
	public void testCreateStringBigIntegerCaseNullValue() {
		try {
			BigInteger value = null;
			JsonProperty.create("aa", value);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonProperty type constructor."));
		}
	}

	@Test
	public void testCreateStringBigDecimalCaseNullKey() {
		try {
			JsonProperty.create(null, BigDecimal.valueOf(1));
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed as the value of key to the JsonProperty constructor."));
		}
	}

	@Test
	public void testCreateStringBigDecimalCaseNullValue() {
		try {
			BigDecimal value = null;
			JsonProperty.create("aa", value);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonProperty type constructor."));
		}
	}

	@Test
	public void testCreateStringStringCaseNullKey() {
		try {
			JsonProperty.create(null, "aaa");
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed as the value of key to the JsonProperty constructor."));
		}
	}

	public void testCreateStringStringCaseNullValue() {
		try {
			String value = null;
			JsonProperty.create("aa", value);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed in to the JsonProperty type constructor."));
		}
	}

	@Test
	public void testCreateStringBooleanCaseNullKey() {
		try {
			JsonProperty.create(null, true);
			fail();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("null value was passed as the value of key to the JsonProperty constructor."));
		}
	}

}
