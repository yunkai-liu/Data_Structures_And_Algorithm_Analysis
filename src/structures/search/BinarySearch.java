package structures.search;

public class BinarySearch<AnyType extends Comparable<? super AnyType>>
{
	public int binarySearch(AnyType[] a, AnyType x)
	{
		int low = 0;
		int high = a.length-1;
		int mid;
		
		while(low <= high)
		{
			mid = (low + high) / 2;
			if(a[mid].compareTo(x) < 0)
			{
				low = mid + 1;
			}
			else if(a[mid].compareTo(x) > 0)
			{
				high = mid - 1;
			}
			else
			{
				return mid;
			}
		}
		
		return -1;
	}
	
	public int binarySearchRecursive(AnyType[] a, AnyType x, int low, int high)
	{
		if(low > high)
		{
			return -1;
		}
		else
		{
			int mid = (low + high) / 2;
			if(a[mid].compareTo(x) < 0)
			{
				return binarySearchRecursive(a, x, mid+1, high);
			}
			else if(a[mid].compareTo(x) > 0)
			{
				return binarySearchRecursive(a, x, low, mid-1);
			}
			else
			{
				return mid;
			}
		}
	}
}