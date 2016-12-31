package net.will_co21.format.json;

import java.util.ArrayList;
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

	private ArrayList<ObjectReference> referenceList;

	private boolean generateException;

	public CircularReferenceDetector()
	{
		this(true);
	}

	public CircularReferenceDetector(boolean generateException)
	{
		this.generateException = generateException;
		this.referenceList = new ArrayList<ObjectReference>();
	}

	public CircularReferenceDetector push(Object obj)
	{
		ObjectReference r = new ObjectReference(obj);

		referenceList.add(r);

		return this;
	}

	public boolean detect(Object obj)
	{
		if(referenceList.contains(new ObjectReference(obj)))
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
		Object raw = referenceList.get(referenceList.size() - 1).getRawObject();
		referenceList.remove(referenceList.size() - 1);

		return raw;
	}
}
