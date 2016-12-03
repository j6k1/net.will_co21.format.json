package net.will_co21.format.json;

public class JsonOption {
	public final int value;

	public static final JsonOption HEX_TAG;
	public static final JsonOption HEX_AMP;
	public static final JsonOption HEX_APO;
	public static final JsonOption HEX_QUOT;
	public static final JsonOption NUMBERTOSTRING;
	public static final JsonOption BIGINTASSTRING;
	public static final JsonOption BIGLONGASSTRING;
	public static final JsonOption BIGFLOATASSTRING;
	public static final JsonOption BIGDOUBLEASSTRING;
	public static final JsonOption PRETTY_PRINT;
	public static final JsonOption UNESCAPED_SLASHES;
	public static final JsonOption ESCAPED_UNICODE;

	static {
		HEX_TAG = new JsonOption(1);
		HEX_AMP = new JsonOption(1 << 1);
		HEX_APO = new JsonOption(1 << 2);
		HEX_QUOT = new JsonOption(1 << 3);
		NUMBERTOSTRING = new JsonOption(1 << 4);
		BIGINTASSTRING = new JsonOption(1 << 5);
		BIGLONGASSTRING = new JsonOption(1 << 6);
		BIGFLOATASSTRING = new JsonOption(1 << 7);
		BIGDOUBLEASSTRING = new JsonOption(1 << 8);
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
