import java.util.Random;

public class SortDriver {

	public static void main(String[] args) {
		
		int num;
		
		Random r = new Random();
		num = Integer.parseInt(args[0]);
		
		Integer[] arrayInt = new Integer[num];
		
		for (int i =0; i< num; i++)
		{
			arrayInt[i] = r.nextInt(10);
			if (arrayInt[i]%2 == 0)
				arrayInt[i] = arrayInt[i]*-1;
			System.out.printf("%d\n", arrayInt[i]);
					
		}
		
		Sorter<Integer> s = new Sorter<Integer>(arrayInt);
		
		//s.insertionSort();
		//s.selectionSort();
		//s.heapSort();
		//s.threeNumSort();
		//s.quickSort();
		//s.randomizedQuickSort();
		//s.threeWayPartitionSort();
		s.longestSequenceArray();
		
		/*System.out.printf("Sorted order is");
		for (int i= 0; i< num ;i++)
		{
			System.out.printf("%d\n", arrayInt[i]);
		}*/
		try
		{
		testSorted(arrayInt);
		}
		catch (Exception e)
		{
			System.out.printf("Exception encountered "+e.getMessage());
		}
		

	}
	
	private static void testSorted(Integer[] arrayInt)
	throws Exception
	{
		for (int i=0; i<arrayInt.length-1; i++)
			if (arrayInt[i] > arrayInt[i+1])
				throw new Exception("Faulty array, not sorted "+arrayInt[i]+" and "+ arrayInt[i+1]);
		
	}

}
