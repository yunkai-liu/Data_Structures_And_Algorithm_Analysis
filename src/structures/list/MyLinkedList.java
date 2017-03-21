package structures.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<AnyType> implements Iterable<AnyType> 
{
	private Node<AnyType> beginMarker;
	private Node<AnyType> endMarker;
	private int theSize;
	private int modCount = 0;
	
	private static class Node<AnyType>
	{
		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n)
		{
			this.data = d;
			this.prev = p;
			this.next = n;
		}
		
		public AnyType data;
		public Node<AnyType> prev;
		public Node<AnyType> next;
	}
	
	public MyLinkedList()
	{
		this.clear();
	}
	
	public void clear()
	{
		this.beginMarker = new Node<AnyType>(null, null, null);
		this.endMarker = new Node<AnyType>(null, this.beginMarker, null);
		this.beginMarker.next = this.endMarker;
		
		this.theSize = 0;
		this.modCount ++;
	}
	
	public int size()
	{
		return this.theSize;
	}
	
	public boolean isEmpty()
	{
		return this.size() == 0;
	}
	
	public boolean add(AnyType x)
	{
		this.add(this.size(), x);
		return true;
	}
	
	public void add(int idx, AnyType x)
	{
		this.addBefore(this.getNode(idx), x);
	}
	
	public AnyType get(int idx)
	{
		return this.getNode(idx).data;
	}
	
	public AnyType set(int idx, AnyType newVal)
	{
		Node<AnyType> p = this.getNode(idx);
		AnyType oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}
	
	public AnyType remove(int idx)
	{
		return this.remove(this.getNode(idx));
	}
	
	private void addBefore(Node<AnyType> p, AnyType x)
	{
		Node<AnyType> newNode = new Node<AnyType>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		
		this.theSize ++;
		this.modCount ++;
	}
	
	private AnyType remove(Node<AnyType> p)
	{
		p.next.prev = p.prev;
		p.prev.next = p.next;
		
		this.theSize --;
		this.modCount ++;
		
		return p.data;
	}
	
	private Node<AnyType> getNode(int idx)
	{
		Node<AnyType> p;
		
		if(idx < 0 || idx > this.size())
		{
			throw new IndexOutOfBoundsException();
		}
		
		if(idx < this.size() / 2)
		{
			p = this.beginMarker.next;
			for(int i=0; i<idx; i++)
			{
				p = p.next;
			}
		}
		else
		{
			p = this.endMarker;
			for(int i=this.size(); i>idx; i--)
			{
				p = p.prev;
			}
		}
		
		return p;
	}
	
	@Override
	public Iterator<AnyType> iterator() 
	{
		return new LinkedListIterator();
	}
	
	private class LinkedListIterator implements Iterator<AnyType>
	{
		private Node<AnyType> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;
		
		@Override
		public boolean hasNext() 
		{
			return this.current != endMarker;
		}

		@Override
		public AnyType next() 
		{
			if(modCount != this.expectedModCount)
			{
				throw new ConcurrentModificationException();
			}
			
			if(!this.hasNext())
			{
				throw new NoSuchElementException();
			}
			
			AnyType nextItem = this.current.data;
			this.current = this.current.next;
			this.okToRemove = true;

			return nextItem;
		}
		
		public void remove()
		{
			if(modCount != this.expectedModCount)
			{
				throw new ConcurrentModificationException();
			}
			
			if(!this.okToRemove)
			{
				throw new IllegalStateException();
			}
			
			MyLinkedList.this.remove(this.current.prev);
			this.okToRemove = false;
			this.expectedModCount ++;
		}
	}
}