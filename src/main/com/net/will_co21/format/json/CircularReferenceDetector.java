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

		@SuppressWarnings("unused")
		public boolean equals(ObjectReference other)
		{
			return this.obj == other.obj;
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
	}

	public CircularReferenceDetector push(Object obj)
	{
		ObjectReference r = new ObjectReference(obj);

		referenceSet.add(r);
		referenceStack.offerLast(r);

		return this;
	}

	public boolean detect()
	{
		if(referenceSet.contains(referenceStack.getLast()))
		{
			if(generateException) throw new CircularReferenceException("Circular reference detected.");
			else return true;
		}
		else
		{
			return false;
		}
	}

	public void pop()
	{
		referenceSet.remove(referenceStack.getLast());
		referenceStack.pollLast();
	}
}
