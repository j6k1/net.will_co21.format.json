package net.will_co21.format.json;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class CircularReferenceDetector {
	private static class ObjectReference {
		private final Object obj;

		public ObjectReference(Object obj)
		{
			this.obj = obj;
		}

		public Object getRawObject()
		{
			return this.obj;
		}

		@Override
		public boolean equals(Object other)
		{
			if(!(other instanceof ObjectReference)) return false;
			return this.obj == ((ObjectReference)other).obj;
		}

		@Override
		public int hashCode()
		{
			return Objects.hashCode(this.obj);
		}
	}

	private HashSet<ObjectReference> referenceSet;
	private LinkedList<ObjectReference> referenceStack;

	private boolean generateException;

	public CircularReferenceDetector()
	{
		this(true);
	}

	public CircularReferenceDetector(boolean generateException)
	{
		this.generateException = generateException;
		this.referenceSet = new HashSet<ObjectReference>();
		this.referenceStack = new LinkedList<ObjectReference>();
	}

	public CircularReferenceDetector push(Object obj)
	{
		ObjectReference r = new ObjectReference(obj);

		referenceSet.add(r);
		referenceStack.offerLast(r);

		return this;
	}

	public boolean detect(Object obj)
	{
		if(referenceSet.contains(new ObjectReference(obj)))
		{
			if(generateException) throw new CircularReferenceException("Circular reference detected.");
			else return true;
		}
		else
		{
			return false;
		}
	}

	public Object pop()
	{
		Object raw = referenceStack.getLast().getRawObject();
		referenceSet.remove(referenceStack.getLast());
		referenceStack.pollLast();

		return raw;
	}
}
