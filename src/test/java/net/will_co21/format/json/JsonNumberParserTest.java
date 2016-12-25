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
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonFloat(0.111f), 5)));
	}

	@Test
	public void testParseJsonMinusDecimalCaseLessThan1() {
		String json = "-0.111";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonFloat(-0.111f), 6)));
	}

	@Test
	public void testParseJsonPlusDecimalCase1orMore() {
		String json = "1.111";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonFloat(1.111f), 5)));
	}

	@Test
	public void testParseJsonMinusDecimalCase1orMore() {
		String json = "-1.111";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonFloat(-1.111f), 6)));
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
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonFloat(1e-10f), 5)));
	}

	@Test
	public void testParseJsonExponentialNotationMinusCaseUpperExponentMark() {
		String json = "1E-10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonFloat(1e-10f), 5)));
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
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonFloat(1.1e-10f), json.length())));
	}

	@Test
	public void testParseJsonDecimalAndExponentialNotationMinusCaseUpperExponentMark() {
		String json = "1.1E-10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonFloat(1.1e-10f), json.length())));
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
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonFloat(-1.1e-10f), json.length())));
	}

	@Test
	public void testParseJsonMinusDecimalAndExponentialNotationMinusCaseUpperExponentMark() {
		String json = "-1.1E-10";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonFloat(-1.1e-10f), json.length())));
	}

	@Test
	public void testParseJsonGreaterLongMax() {
		String json = "9223372036854775808";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigInteger(json), json.length())));
	}

	@Test
	public void testParseJsonLessLongMin() {
		String json = "-9223372036854775809";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigInteger(json), json.length())));
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
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigDecimal(json), json.length())));
	}

	@Test
	public void testParseJsonLessDoubleMin() {
		String json = "-1.7976931348623158E308";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigDecimal(json), json.length())));
	}

	@Test
	public void testParseJsonMantissaPartOf53bit() {
		String json = "5617791046444734716853691802815937722090286763146500455480614491857219240737852051382275080120153269462546692951354680748778625034831464487353996715491490462840498813487771014877353173681844794310757036798922451959496278143296973382546715803312865050285617898838124936097217840882439081577985847370744594432.0";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigDecimal(json), json.length())));
	}

	@Test
	public void testParseJsonMantissaPartOf53bitCaseMin() {
		String json = "2.78134232313400049369867629354914463885203780502448387554215273529709447955319149312908577324375911245549327057381381426502880408764749781942823959187265752947648246496030759734772970852471822547535903668622287044596387892101148646319768271737773006249977126837713261196192479780180503623092437659102283654799785992192732935312918774674406205924077750900665309356620398772298981866548883116465098928806970619242890901915679607479168095888661241137769374292335967840951341181328060996399584487018032930928136230540638895196312250369893273457077068810260586327404391969549619625755949180894347019520961667511880408542304406548818657119105093552478580014143600649820489991906525938854667995148235531117049069448498525868018930811385869272811532937339507043361663818359375E-309";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigDecimal(json), json.length())));
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
		String json = "170141178389866830818769697729071284224.0";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigDecimal(json), json.length())));
	}

	@Test
	public void testParseJsonMantissaPartOf24bitCaseMin() {
		String json = "2.3509886315796517996966195282580121911415245495310779491917148247034203244199002114100949256680905818939208984375E-38";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonBigDecimal(json), json.length())));
	}

	@Test
	public void testParseJsonGreaterMantissaPart() {
		String json = "3.40282351E37";

		Pair<IJsonValue, Integer> result = (new JsonNumberParser()).parseJson(json, 0);
		assertThat(result, is(new Pair<IJsonValue, Integer>(new JsonDouble(3.40282351E37-38), json.length())));
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
