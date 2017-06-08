package structures.sorting;

public class QuickSort<AnyType extends Comparable<? super AnyType>>
{
	public void quickSort(AnyType[] a)
	{
		quickSort(a, 0, a.length-1);
	}
	
	private int partition(AnyType[] a, int left, int right)
	{
		AnyType pivot = a[(left + right) / 2];
		while(left <= right)
		{
			while(a[left].compareTo(pivot) < 0)
			{
				left++;
			}
			while(a[right].compareTo(pivot) > 0)
			{
				right--;
			}
			
			if(left <= right)
			{
				swap(a, left, right);
				left++;
				right--;
			}
		}
		
		return left;
	}
	
	private void quickSort(AnyType[] a, int left, int right)
	{
		int index = partition(a, left, right);
		if(left < index-1)
		{
			quickSort(a, left, index-1);
		}
		if(index < right)
		{
			quickSort(a, index, right);
		}
	}
	
	private void swap(AnyType[] a, int left, int right)
	{
		AnyType tmp = a[right];
		a[right] = a[left];
		a[left] = tmp;
	}
}