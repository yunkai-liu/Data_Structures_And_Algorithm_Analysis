package structures.sorting;

public class SelectionSort<AnyType extends Comparable<? super AnyType>>
{
	public void selectionSort(AnyType[] a)
	{
		for(int i=0; i<a.length-1; i++)
		{
			int minIndex = i;
			for(int j=i+1; j<a.length; j++)
			{
				if(a[j].compareTo(a[minIndex]) < 0)
				{
					minIndex = j;
				}
			}
			
			if(minIndex != i)
			{
				AnyType tmp = a[i];
				a[i] = a[minIndex];
				a[minIndex] = tmp;
			}
		}
	}
}