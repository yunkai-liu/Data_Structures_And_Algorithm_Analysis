package structures.tree;

import java.nio.BufferUnderflowException;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
	private BinaryNode<AnyType> root;
	
	public BinarySearchTree()
	{
		this.root = null;
	}
	
	private static class BinaryNode<AnyType>
	{
		AnyType element;
		BinaryNode<AnyType> left;
		BinaryNode<AnyType> right;
		
		BinaryNode(AnyType theElement)
		{
			this(theElement, null, null);
		}
		
		BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt)
		{
			this.element = theElement;
			this.left = lt;
			this.right = rt;
		}
	}
	
	public void makeEmpty()
	{
		this.root = null;
	}
	
	public boolean isEmpty()
	{
		return this.root == null;
	}
	
	public boolean contains(AnyType x)
	{
		return this.contains(x, root);
	}
	
	private boolean contains(AnyType x, BinaryNode<AnyType> t)
	{
		if(t == null)
		{
			return false;
		}
		
		int compareResult =  x.compareTo(t.element);
		
		if(compareResult < 0)
		{
			return this.contains(x, t.left);
		}
		else if(compareResult > 0)
		{
			return this.contains(x, t.right);
		}
		else
		{
			return true;
		}
	}
	
	public AnyType findMin()
	{
		if(this.isEmpty())
		{
			throw new BufferUnderflowException();
		}
		
		return this.findMin(this.root).element;
	}
	
	public AnyType findMax()
	{
		if(this.isEmpty())
		{
			throw new BufferUnderflowException();
		}
		
		return this.findMax(this.root).element;
	}
	
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t)
	{
		if(t == null)
		{
			return null;
		}
		else if(t.left == null)
		{
			return t;
		}
		
		return this.findMin(t.left);
	}
	
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t)
	{
		if(t != null)
		{
			while(t.right != null)
			{
				t = t.right;
			}
			
		}
		
		return t;
	}
	
	public void insert(AnyType x)
	{
		this.root = this.insert(x, this.root);
	}
	
	private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t)
	{
		if(t == null)
		{
			return new BinaryNode<AnyType>(x);
		}
		
		int compareResult = x.compareTo(t.element);
		
		if(compareResult < 0)
		{
			t.left = this.insert(x, t.left);
		}
		else if(compareResult > 0)
		{
			t.right = this.insert(x, t.right);
		}
		else
		{
			//Duplicate; do nothing
		}
		
		return t;
	}
	
	public void remove(AnyType x)
	{
		this.root = this.remove(x, this.root);
	}
	
	private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t)
	{
		if(t == null)
		{
			return t;
		}
		
		int compareResult = x.compareTo(t.element);
		
		if(compareResult < 0)
		{
			t.left = this.remove(x, t.left);
		}
		else if(compareResult > 0)
		{
			t.right = this.remove(x, t.right);
		}
		else if(t.left != null && t.right != null)
		{
			t.element = this.findMin(t.right).element;
			t.right = this.remove(t.element, t.right);
		}
		else
		{
			t = (t.left != null) ? t.left : t.right;
		}
		
		return t;
	}
	
	public int height()
	{
		return this.height(this.root);
	}
	
	private int height(BinaryNode<AnyType> t)
	{
		if(t == null)
		{
			return -1;
		}
		else
		{
			return Math.max(this.height(t.left), this.height(t.right)) + 1;
		}
	}
	
	public void printTree()
	{
		if(this.isEmpty())
		{
			System.out.println("Empty tree");
		}
		else
		{
			System.out.println("pre-order traversal");
			this.printTreePreOrder(this.root);
			
			System.out.println("\nin-order traversal");
			this.printTreeInOrder(this.root);
			
			System.out.println("\npost-order traversal");
			this.printTreePostOrder(this.root);
			
			System.out.println("\nlever-order traversal");
			this.printTreeLayer(this.root);
		}
	}
	
	private void printTreePreOrder(BinaryNode<AnyType> t)
	{
		if(t != null)
		{
			System.out.print(t.element+" ");
			this.printTreePreOrder(t.left);
			this.printTreePreOrder(t.right);
		}
	}
	
	private void printTreeInOrder(BinaryNode<AnyType> t)
	{
		if(t != null)
		{
			this.printTreeInOrder(t.left);
			System.out.print(t.element+" ");
			this.printTreeInOrder(t.right);
		}
	}
	
	private void printTreePostOrder(BinaryNode<AnyType> t)
	{
		if(t != null)
		{
			this.printTreePostOrder(t.left);
			this.printTreePostOrder(t.right);
			System.out.print(t.element+" ");
		}
	}
	
	private void printTreeLayer(BinaryNode<AnyType> t)
	{
		Queue<BinaryNode<AnyType>> queue = new LinkedList<BinaryNode<AnyType>>();
		
		queue.offer(t);
		while(!queue.isEmpty())
		{
			BinaryNode<AnyType> r = queue.poll();
			System.out.print(r.element+" ");
			if(r.left != null)
			{
				queue.offer(r.left);
			}
			if(r.right != null)
			{
				queue.offer(r.right);
			}
		}
	}
}