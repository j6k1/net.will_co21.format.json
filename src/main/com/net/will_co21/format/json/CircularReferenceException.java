package net.will_co21.format.json;

public class CircularReferenceException extends RuntimeException {
	public CircularReferenceException(String message)
	{
		super(message);
	}
}
