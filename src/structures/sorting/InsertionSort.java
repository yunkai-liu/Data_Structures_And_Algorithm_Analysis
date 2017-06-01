package structures.sorting;

public class InsertionSort<AnyType extends Comparable<? super AnyType>>
{
	public void insertionSort(AnyType[] a)
	{
		int j;
		
		for(int p=1; p<a.length; p++)
		{
			AnyType tmp = a[p];
			
			for(j=p; j>0 && tmp.compareTo(a[j-1]) < 0; j--)
			{
				a[j] = a[j-1];
			}
			a[j] = tmp;
		}
	}
}