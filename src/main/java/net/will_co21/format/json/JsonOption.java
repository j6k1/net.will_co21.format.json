package net.will_co21.format.json;

public class JsonOption {
	public final int value;

	public static final JsonOption HEX_TAG;
	public static final JsonOption HEX_AMP;
	public static final JsonOption HEX_APO;
	public static final JsonOption HEX_QUOT;
	public static final JsonOption NUMBER_TO_STRING;
	public static final JsonOption BIGINT_AS_STRING;
	public static final JsonOption BIGLONG_AS_STRING;
	public static final JsonOption BIGFLOAT_AS_STRING;
	public static final JsonOption BIGDOUBLE_AS_STRING;
	public static final JsonOption PRETTY_PRINT;
	public static final JsonOption UNESCAPED_SLASHES;
	public static final JsonOption ESCAPED_UNICODE;

	static {
		HEX_TAG = new JsonOption(1);
		HEX_AMP = new JsonOption(1 << 1);
		HEX_APO = new JsonOption(1 << 2);
		HEX_QUOT = new JsonOption(1 << 3);
		NUMBER_TO_STRING = new JsonOption(1 << 4);
		BIGINT_AS_STRING = new JsonOption(1 << 5);
		BIGLONG_AS_STRING = new JsonOption(1 << 6);
		BIGFLOAT_AS_STRING = new JsonOption(1 << 7);
		BIGDOUBLE_AS_STRING = new JsonOption(1 << 8);
		PRETTY_PRINT = new JsonOption(1 << 9);
		UNESCAPED_SLASHES = new JsonOption(1 << 10);
		ESCAPED_UNICODE = new JsonOption(1 << 11);
	}

	private JsonOption()
	{
		this.value = 0;
	}

	private JsonOption(int value)
	{
		this.value = value;
	}
}
