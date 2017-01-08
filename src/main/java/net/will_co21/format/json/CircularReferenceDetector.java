package net.will_co21.format.json;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

public class CircularReferenceDetector {
	private static class ObjectReference {
		private final IJsonValue obj;

		public ObjectReference(IJsonValue obj)
		{
			this.obj = obj;
		}

		public IJsonValue getRawObject()
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
			return System.identityHashCode(this.obj);
		}
	}

	private LinkedList<ObjectReference> referenceStack;
	private HashSet<ObjectReference> referenceSet;
	
	private boolean generateException;

	public CircularReferenceDetector()
	{
		this(true);
	}

	public CircularReferenceDetector(boolean generateException)
	{
		this.generateException = generateException;
		this.referenceStack = new LinkedList<ObjectReference>();
		this.referenceSet = new HashSet<ObjectReference>();
	}

	public CircularReferenceDetector push(IJsonValue obj)
	{
		ObjectReference r = new ObjectReference(obj);

		referenceStack.offerLast(r);
		referenceSet.add(r);

		return this;
	}

	public boolean detect(IJsonValue obj)
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

	public IJsonValue pop()
	{
		ObjectReference r = referenceStack.pollLast();
		referenceSet.remove(r);
		IJsonValue raw = r.getRawObject();

		return raw;
	}
}
