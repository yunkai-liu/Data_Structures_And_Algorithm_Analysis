package structures.sorting;

public class Main 
{
	public static void main(String[] args) 
	{
		Integer[] a = {34,8,64,51,32,21};
		MergeSort<Integer> s = new MergeSort<Integer>();
		s.mergeSort(a);
		for(Integer i : a)
		{
			System.out.print(i+" ");
		}
	}
}