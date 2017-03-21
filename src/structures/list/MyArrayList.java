package structures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<AnyType> implements Iterable<AnyType>
{
	private static final int DEFAULT_CAPACITY = 10;
	
	private int theSize;
	private AnyType[] theItems;
	
	public MyArrayList()
	{
		this.clear();
	}
	
	public void clear()
	{
		this.theSize = 0;
		this.ensureCapacity(DEFAULT_CAPACITY);
	}
	
	public int size()
	{
		return this.theSize;
	}
	
	public boolean isEmpty()
	{
		return this.theSize == 0;
	}
	
	public void trimToSize()
	{
		this.ensureCapacity(this.size());
	}
	
	public AnyType get(int idx)
	{
		if(idx < 0 || idx >= this.size())
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		
		return this.theItems[idx];
	}
	
	public AnyType set(int idx, AnyType newVal)
	{
		if(idx < 0 || idx >= this.size())
		{
			throw new ArrayIndexOutOfBoundsException();
		}
		
		AnyType old = this.theItems[idx];
		this.theItems[idx] = newVal;
		return old;
	}
	
	public void ensureCapacity(int newCapacity)
	{
		if(newCapacity < this.theSize)
		{
			return ;
		}
		
		AnyType[] old = this.theItems;
		this.theItems = (AnyType[]) new Object[newCapacity];
		
		for(int i=0; i<this.size(); i++)
		{
			this.theItems[i] = old[i];
		}
	}
	
	public boolean add(AnyType x)
	{
		this.add(this.size(), x);
		return true;
	}
	
	public void add(int idx, AnyType x)
	{
		if(this.theItems.length == this.size())
		{
			this.ensureCapacity(this.size() * 2 + 1);
		}
		
		for(int i=this.theSize; i>idx; i--)
		{
			this.theItems[i] = this.theItems[i - 1];
		}
		this.theItems[idx] = x;
		
		this.theSize ++;
	}
	
	public AnyType remove(int idx)
	{
		AnyType removedItem = this.theItems[idx];
		
		for(int i=idx; i<this.size() - 1; i++)
		{
			this.theItems[i] = this.theItems[i + 1];
		}
		
		this.theSize --;
		return removedItem;
	}
	
	@Override
	public Iterator<AnyType> iterator() 
	{
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements Iterator<AnyType>
	{
		private int current = 0;
		
		@Override
		public boolean hasNext() 
		{
			return this.current < size();
		}

		@Override
		public AnyType next() 
		{
			if(!this.hasNext())
			{
				throw new NoSuchElementException();
			}
			
			return theItems[this.current ++];
		}	
		
		public void remove()
		{
			MyArrayList.this.remove(-- this.current);
		}
	}
}