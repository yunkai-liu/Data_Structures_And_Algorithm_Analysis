package structures.sorting;

public class BubbleSort<AnyType extends Comparable<? super AnyType>>
{
	public void bubbleSort(AnyType[] a)
	{
		for(int i=0; i<a.length-1; i++)
		{
			for(int j=0; j<a.length-1-i; j++)
			{
				if(a[j].compareTo(a[j+1]) > 0)
				{
					AnyType tmp = a[j+1];
					a[j+1] = a[j];
					a[j] = tmp;
				}
			}
		}
	}
}