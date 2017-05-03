package structures.tree;

import java.util.LinkedList;
import java.util.Queue;

public class AvlTree<AnyType extends Comparable<? super AnyType>>
{
	private AvlNode<AnyType> root;
	
	public AvlTree()
	{
		this.root = null;
	}
	
	private static class AvlNode<AnyType> 
	{
		AnyType element;
		AvlNode<AnyType> left;
		AvlNode<AnyType> right;
		int height;
		
		AvlNode(AnyType theElement)
		{
			this(theElement, null, null);
		}
		
		AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt)
		{
			this.element = theElement;
			this.left = lt;
			this.right = rt;
			this.height = 0;
		}
	}
	
	public boolean isEmpty()
	{
		return this.root == null;
	}
	
	private int height(AvlNode<AnyType> t)
	{
		return t == null ? -1 : t.height;
	}
	
	public void insert(AnyType x)
	{
		this.root = this.insert(x, this.root);
	}
	
	private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t)
	{
		if(t == null)
		{
			return new AvlNode<AnyType>(x);
		}
		
		int compareResult = x.compareTo(t.element);
		
		if(compareResult < 0)
		{
			t.left = this.insert(x, t.left);
			
			if(this.height(t.left) - this.height(t.right) == 2)
			{
				if(x.compareTo(t.left.element) < 0)
				{
					t = this.rotateWithLeftChild(t);
				}
				else
				{
					t = this.doubleWithLeftChild(t);
				}
			}
		}
		else if(compareResult > 0)
		{
			t.right = this.insert(x, t.right);
			
			if(this.height(t.right) - this.height(t.left) == 2)
			{
				if(x.compareTo(t.right.element) > 0)
				{
					t = this.rotateWithRightChild(t);
				}
				else
				{
					t = this.doubleWithRightChild(t);
				}
			}
		}
		else
		{
			//Duplicate; do nothing
		}
		
		t.height = Math.max(this.height(t.left), this.height(t.right)) + 1;
		
		return t;
	}
	
	private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2)
	{
		AvlNode<AnyType> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		
		k2.height = Math.max(this.height(k2.left), this.height(k2.right)) + 1;
		k1.height = Math.max(this.height(k1.left), k2.height) + 1;
		
		return k1;
	}
	
	private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k1)
	{
		AvlNode<AnyType> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		
		k1.height = Math.max(this.height(k1.left), this.height(k1.right)) + 1;
		k2.height = Math.max(k1.height, this.height(k2.right)) + 1;
		
		return k2;
	}
	
	private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3)
	{
		k3.left = this.rotateWithRightChild(k3.left);
		
		return this.rotateWithLeftChild(k3);
		
	}
	
	private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k1)
	{
		k1.right = this.rotateWithLeftChild(k1.right);
		
		return this.rotateWithRightChild(k1);
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
	
	private void printTreePreOrder(AvlNode<AnyType> t)
	{
		if(t != null)
		{
			System.out.print(t.element+" ");
			this.printTreePreOrder(t.left);
			this.printTreePreOrder(t.right);
		}
	}
	
	private void printTreeInOrder(AvlNode<AnyType> t)
	{
		if(t != null)
		{
			this.printTreeInOrder(t.left);
			System.out.print(t.element+" ");
			this.printTreeInOrder(t.right);
		}
	}
	
	private void printTreePostOrder(AvlNode<AnyType> t)
	{
		if(t != null)
		{
			this.printTreePostOrder(t.left);
			this.printTreePostOrder(t.right);
			System.out.print(t.element+" ");
		}
	}
	
	private void printTreeLayer(AvlNode<AnyType> t)
	{
		Queue<AvlNode<AnyType>> queue = new LinkedList<AvlNode<AnyType>>();
		
		queue.offer(t);
		while(!queue.isEmpty())
		{
			AvlNode<AnyType> r = queue.poll();
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