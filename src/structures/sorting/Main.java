package structures.sorting;

public class Main 
{
	public static void main(String[] args) 
	{
		Integer[] a = {34,8,64,51,32,21};
		SelectionSort<Integer> s = new SelectionSort<Integer>();
		s.selectionSort(a);
		for(Integer i : a)
		{
			System.out.print(i+" ");
		}
	}
}