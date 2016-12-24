package net.will_co21.format.json;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

public class JsonNumberParserTest {

	@Test
	public void testParseJsonInvalidStart() {
		String json = "a100000";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonMinusSignOnly() {
		String json = "-";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json number format."));
		}
	}

	@Test
	public void testParseJsonInvalidNegativeStart() {
		String json = "-a";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonDecimalsTooShort() {
		String json = "0.";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json number format."));
		}
	}

	@Test
	public void testParseJsonNoDotDecimal() {
		String json = "01111";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"1\" was found."));
		}
	}

	@Test
	public void testParseJsonNextCharacterOfDotIsNotNumberCaseLessThan1() {
		String json = "0.a";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonEndOfDotCase1orMore() {
		String json = "1.";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json number format."));
		}
	}

	@Test
	public void testParseJsonNextCharacterOfDotIsNotNumberCase1orMore() {
		String json = "1.a";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonHasExponentPartExponentCaseEndsWithLowerExponentMark() {
		String json = "1234e";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json number format."));
		}
	}

	@Test
	public void testParseJsonHasExponentPartExponentCaseEndsWithUpperExponentMark() {
		String json = "1234E";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json number format."));
		}
	}

	@Test
	public void testParseJsonInvalidCharacterFollowingExponentMark() {
		String json = "1234Ea";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonExponentialNotationEndsWithSignCharacterCasePlus() {
		String json = "1234E+";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json number format."));
		}
	}

	@Test
	public void testParseJsonExponentialNotationEndsWithSignCharacterCaseMinus() {
		String json = "1234E-";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("The format of this json string is not an json number format."));
		}
	}

	@Test
	public void testParseJsonNextCharacterToTheExponentialNotationIsNotNumberCasePlus() {
		String json = "1234E+a";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonNextCharacterToTheExponentialNotationIsNotNumberCaseMinus() {
		String json = "1234E-a";

		try {
			(new JsonNumberParser()).parseJson(json, 0);
			fail();
		} catch (JsonFormatErrorException e) {
			assertThat(e.getMessage(), is("unexpected character \"a\" was found."));
		}
	}

	@Test
	public void testParseJsonPlusDecimalCaseLessThan1() {
		String json = "0.111";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(0.111), 5)));
	}

	@Test
	public void testParseJsonMinusDecimalCaseLessThan1() {
		String json = "-0.111";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(-0.111), 6)));
	}

	@Test
	public void testParseJsonPlusDecimalCase1orMore() {
		String json = "1.111";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(1.111), 5)));
	}

	@Test
	public void testParseJsonMinusDecimalCase1orMore() {
		String json = "-1.111";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(-1.111), 6)));
	}

	@Test
	public void testParseJsonExponentialNotationUnsignedCaseLowerExponentMark() {
		String json = "1e10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonFloat(1e10f), 4)));
	}

	@Test
	public void testParseJsonExponentialNotationUnsignedCaseUpperExponentMark() {
		String json = "1E10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonFloat(1e10f), 4)));
	}

	@Test
	public void testParseJsonExponentialNotationPlusCaseLowerExponentMark() {
		String json = "1e+10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonFloat(1e10f), 5)));
	}

	@Test
	public void testParseJsonExponentialNotationPlusCaseUpperExponentMark() {
		String json = "1E+10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonFloat(1e10f), 5)));
	}


	@Test
	public void testParseJsonExponentialNotationMinusCaseLowerExponentMark() {
		String json = "1e-10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(1e-10), 5)));
	}

	@Test
	public void testParseJsonExponentialNotationMinusCaseUpperExponentMark() {
		String json = "1E-10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(1e-10), 5)));
	}

	@Test
	public void testParseJsonDecimalAndExponentialNotationUnsignedCaseLowerExponentMark() {
		String json = "1.1e10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(1.1e10), json.length())));
	}

	@Test
	public void testParseJsonDecimalAndExponentialNotationUnsignedCaseUpperExponentMark() {
		String json = "1.1E10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(1.1e10), json.length())));
	}

	@Test
	public void testParseJsonDecimalAndExponentialNotationPlusCaseLowerExponentMark() {
		String json = "1.1e+10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(1.1e10), json.length())));
	}

	@Test
	public void testParseJsonDecimalAndExponentialNotationPlusCaseUpperExponentMark() {
		String json = "1.1E+10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(1.1e10), json.length())));
	}


	@Test
	public void testParseJsonDecimalAndExponentialNotationMinusCaseLowerExponentMark() {
		String json = "1.1e-10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(1.1e-10), json.length())));
	}

	@Test
	public void testParseJsonDecimalAndExponentialNotationMinusCaseUpperExponentMark() {
		String json = "1.1E-10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(1.1e-10), json.length())));
	}


	@Test
	public void testParseJsonMinusDecimalAndExponentialNotationUnsignedCaseLowerExponentMark() {
		String json = "-1.1e10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(-1.1e10), json.length())));
	}

	@Test
	public void testParseJsonMinusDecimalAndExponentialNotationUnsignedCaseUpperExponentMark() {
		String json = "-1.1E10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(-1.1e10), json.length())));
	}

	@Test
	public void testParseJsonMinusDecimalAndExponentialNotationPlusCaseLowerExponentMark() {
		String json = "-1.1e+10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(-1.1e10), json.length())));
	}

	@Test
	public void testParseJsonMinusDecimalAndExponentialNotationPlusCaseUpperExponentMark() {
		String json = "-1.1E+10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(-1.1e10), json.length())));
	}


	@Test
	public void testParseJsonMinusDecimalAndExponentialNotationMinusCaseLowerExponentMark() {
		String json = "-1.1e-10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(-1.1e-10), json.length())));
	}

	@Test
	public void testParseJsonMinusDecimalAndExponentialNotationMinusCaseUpperExponentMark() {
		String json = "-1.1E-10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(-1.1e-10), json.length())));
	}

	@Test
	public void testParseJsonGreaterLongMax() {
		String json = "9223372036854775808";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigInteger("9223372036854775808"), json.length())));
	}

	@Test
	public void testParseJsonLessLongMin() {
		String json = "-9223372036854775809";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigInteger("-9223372036854775809"), json.length())));
	}

	@Test
	public void testParseJsonLongMax() {
		String json = "9223372036854775807";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonLong(9223372036854775807L), json.length())));
	}

	@Test
	public void testParseJsonLongMin() {
		String json = "-9223372036854775808";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonLong(-9223372036854775808L), json.length())));
	}

	@Test
	public void testParseJsonGreaterIntMax() {
		String json = "2147483648";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonLong(2147483648L), json.length())));
	}

	@Test
	public void testParseJsonLessIntMin() {
		String json = "-2147483649";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonLong(-2147483649L), json.length())));
	}

	@Test
	public void testParseJsonIntMax() {
		String json = "2147483647";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonInt(2147483647), json.length())));
	}

	@Test
	public void testParseJsonIntMin() {
		String json = "-2147483648";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonInt(-2147483648), json.length())));
	}

	@Test
	public void testParseJsonGreaterDoubleMax() {
		String json = "1.7976931348623158E308";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigDecimal("1.7976931348623158E308"), json.length())));
	}

	@Test
	public void testParseJsonLessDoubleMin() {
		String json = "-1.7976931348623158E308";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigDecimal("-1.7976931348623158E308"), json.length())));
	}

	@Test
	public void testParseJsonMantissaPartOf53bit() {
		String json = "44942328371557895198432242851325853769983533177509456734043444745111242073191187736662254494396801774082571604173221977736638886962985100657664372167876455170477225500177095919068463711454427882941118932567517463892841739905710728704965208734118823179768542111091377676085677889924877023260720044476043624448.0";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigDecimal("44942328371557895198432242851325853769983533177509456734043444745111242073191187736662254494396801774082571604173221977736638886962985100657664372167876455170477225500177095919068463711454427882941118932567517463892841739905710728704965208734118823179768542111091377676085677889924877023260720044476043624448.0"), json.length())));
	}

	@Test
	public void testParseJsonMantissaPartOf53bitCaseMin() {
		String json = "2.225073858507201259573821257020768020077017763406988739288376763306013328417497570685406341460323054239108249322037716056011260300124027377191834796392769721437078990836532798904431849864732504110467273084696977812028716236556967935895657351868202788722494811530151317616366333296945953431369222190308053787694940411743707809822580740988880551617907119002148759401915892151482081924890263312702257321184750771861452224096212631698623638776860141838061165702263776640907648194435536054336373727978014593100678660492117516784908521511159767373323339191983221326853519128338784891913380715532840971003878993627240686726663397609149834349831344879676653469091559130189899114521124782380547341009775590676096291585949697743018930811385869272811532937339507043361663818359375E-308";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigDecimal("2.225073858507201259573821257020768020077017763406988739288376763306013328417497570685406341460323054239108249322037716056011260300124027377191834796392769721437078990836532798904431849864732504110467273084696977812028716236556967935895657351868202788722494811530151317616366333296945953431369222190308053787694940411743707809822580740988880551617907119002148759401915892151482081924890263312702257321184750771861452224096212631698623638776860141838061165702263776640907648194435536054336373727978014593100678660492117516784908521511159767373323339191983221326853519128338784891913380715532840971003878993627240686726663397609149834349831344879676653469091559130189899114521124782380547341009775590676096291585949697743018930811385869272811532937339507043361663818359375E-308"), json.length())));
	}

	@Test
	public void testParseJsonDoubleMax() {
		String json = "1.7976931348623157E308";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(1.7976931348623157E308d), json.length())));
	}

	@Test
	public void testParseJsonDoubleMin() {
		String json = "-1.7976931348623157E308";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(-1.7976931348623157E308d), json.length())));
	}

	@Test
	public void testParseJsonGreaterFloatMax() {
		String json = "3.4028236E38";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(3.4028236E38), json.length())));
	}

	@Test
	public void testParseJsonLessFloatMin() {
		String json = "-3.4028236E38";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(-3.4028236E38), json.length())));
	}

	@Test
	public void testParseJsonMantissaPartOf24bit() {
		String json = "340282356779733661637539395458142568448.0";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigDecimal("340282356779733661637539395458142568448.0"), json.length())));
	}

	@Test
	public void testParseJsonMantissaPartOf24bitCaseMin() {
		String json = "1.17549431578982589984830976412900609557076227476553897459585741235171016220995010570504746283404529094696044921875E-38";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigDecimal("1.17549431578982589984830976412900609557076227476553897459585741235171016220995010570504746283404529094696044921875E-38"), json.length())));
	}

	@Test
	public void testParseJsonFloatMax() {
		String json = "3.4028235E38";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(3.4028235E38), json.length())));
	}

	@Test
	public void testParseJsonFloatMin() {
		String json = "-3.4028235E38";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(-3.4028235E38), json.length())));
	}
}
