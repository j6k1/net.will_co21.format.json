package net.will_co21.format.json;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonNullTest {

	@Test
	public void testToJsonSource() {
		assertThat((new JsonNull())
				.toJsonSource(new JsonOptions(new JsonOption[] {}), new CircularReferenceDetector()).toJson(),
				is("null"));
	}

	@Test
	public void testGetInt() {
		try {
			(new JsonNull()).getInt();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("This value cannot be referenced. Invalid reference to object."));
		}
	}

	@Test
	public void testGetOptionalInt() {
		assertThat((new JsonNull()).getOptionalInt().isPresent(), is(false));
	}

	@Test
	public void testGetLong() {
		try {
			(new JsonNull()).getLong();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("This value cannot be referenced. Invalid reference to object."));
		}
	}

	@Test
	public void testGetOptionalLong() {
		assertThat((new JsonNull()).getOptionalLong().isPresent(), is(false));
	}

	@Test
	public void testGetBigInteger() {
		try {
			(new JsonNull()).getBigInteger();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("This value cannot be referenced. Invalid reference to object."));
		}
	}

	@Test
	public void testGetOptionalBigInteger() {
		assertThat((new JsonNull()).getOptionalBigInteger().isPresent(), is(false));
	}

	@Test
	public void testGetFloat() {
		try {
			(new JsonNull()).getFloat();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("This value cannot be referenced. Invalid reference to object."));
		}
	}

	@Test
	public void testGetOptionalFloat() {
		assertThat((new JsonNull()).getOptionalFloat().isPresent(), is(false));
	}

	@Test
	public void testGetDouble() {
		try {
			(new JsonNull()).getDouble();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("This value cannot be referenced. Invalid reference to object."));
		}
	}

	@Test
	public void testGetOptionalDouble() {
		assertThat((new JsonNull()).getOptionalDouble().isPresent(), is(false));
	}

	@Test
	public void testGetBigDecimal() {
		try {
			(new JsonNull()).getBigDecimal();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("This value cannot be referenced. Invalid reference to object."));
		}
	}

	@Test
	public void testGetOptionalBigDecimal() {
		assertThat((new JsonNull()).getOptionalBigDecimal().isPresent(), is(false));
	}

	@Test
	public void testGetString() {
		try {
			(new JsonNull()).getString();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("This value cannot be referenced. Invalid reference to object."));
		}
	}

	@Test
	public void testGetOptionalString() {
		assertThat((new JsonNull()).getOptionalString().isPresent(), is(false));
	}

	@Test
	public void testGetBoolean() {
		try {
			(new JsonNull()).getBoolean();
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("This value cannot be referenced. Invalid reference to object."));
		}
	}

	@Test
	public void testGetOptionalBoolean() {
		assertThat((new JsonNull()).getOptionalBoolean().isPresent(), is(false));
	}

	@Test
	public void testGetInt1() {
		try {
			(new JsonNull()).get(1);
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("Members can not be referenced. Invalid reference to container object."));
		}
	}

	@Test
	public void testGetString1() {
		try {
			(new JsonNull()).get("key");
		} catch (TypeOfNullableNotAllowedException e) {
			assertThat(e.getMessage(), is("Members can not be referenced. Invalid reference to container object."));
		}
	}

	@Test
	public void testGetOptionalInt1() {
		assertThat((new JsonNull()).getOptional(1).isPresent(), is(false));
	}

	@Test
	public void testGetOptionalString1() {
		assertThat((new JsonNull()).getOptional("key").isPresent(), is(false));
	}

}
