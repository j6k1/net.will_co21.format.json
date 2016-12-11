package net.will_co21.format.json;

public class CircularReferenceException extends RuntimeException {
	/**
	 *
	 */
	private static final long serialVersionUID = 4798916039698607926L;

	public CircularReferenceException(String message)
	{
		super(message);
	}
}
