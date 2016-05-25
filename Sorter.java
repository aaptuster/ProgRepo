import java.util.Random;

public class Sorter<T extends Comparable<? super T>> {
	
	private T[] m_sortArray = null;
	
	Sorter(T array[])
	{
		m_sortArray = array;
	}
	
	public T[] insertionSort()
	{
		if (m_sortArray == null || m_sortArray.length <=1)
			return m_sortArray;
		internalInsertionSort();
		return m_sortArray;
	}
	
	private void internalInsertionSort()
	{
		int j;
		for (int i = 1; i< m_sortArray.length ; i++)
		{
			j = i;
			while (j>= 1 && m_sortArray[j].compareTo(m_sortArray[j-1]) == -1)
			{
				swap(j, j-1);
				j--;
			}
		}
		
	}
	
	/*private int compareTo(T i, T j)
	{
		return i.compareTo(j);
		/*if (i instanceof String)
		{
			return ((String) i).compareTo((String)j); 
			
		}
		else if (i instanceof Integer)
		{
			return ((Integer) i).compareTo((Integer)j);
			
		}
		else 
		{
			System.out.printf("Beyond scope");
			return -1;
		
	}*/
	
	public T[] selectionSort()
	{
		if (m_sortArray == null || m_sortArray.length <=1)
			return m_sortArray;
		internalSelectionSort();
		return m_sortArray;
	}
	
	private void internalSelectionSort()
	{
		for (int i=0; i < m_sortArray.length; i++)
		{
			int pos = i;
			for (int j = i; j < m_sortArray.length; j++)
			{
				if (m_sortArray[pos].compareTo(m_sortArray[j]) == 1)
					pos = j;
			}
			swap(i,pos);
		}
	}
	
	public T[] threeNumSort()
	{
		if (m_sortArray == null || m_sortArray.length <=1)
			return m_sortArray;
			
		internalThreeNumSort();
		return m_sortArray;
	}
	
	private void internalThreeNumSort()
	{
		int low = 0;
		int mid = 0;
		int high = m_sortArray.length-1;
		
		while (mid <= high)
		{
			switch ((int)m_sortArray[mid])
			{
			case 0: 
				swap(low, mid);
				low++;
				mid++;
				break;
			case 1:
				mid++;
				break;
			case 2: 
				swap(high, mid);
				high--;
			
			}
		}
		
	}
	
	public T[] randomizedQuickSort()
	{
		if (m_sortArray == null || m_sortArray.length <=1)
			return m_sortArray;
			
		internalRandomizedQuickSort(0, m_sortArray.length-1);
		return m_sortArray;
	}
	
	private void internalRandomizedQuickSort(int low, int high)
	{
		if(low < high)
		{
		   int partitionIndex = randomPartition(low, high);
		
		   internalRandomizedQuickSort(low, partitionIndex-1);
		   internalRandomizedQuickSort(partitionIndex+1, high);
		}
	}
	
	private int randomPartition(int low, int high)
	{
		int index = -1;
		Random rIndex = new Random();
		//do
		//{
			index = rIndex.nextInt(high-low+1)+low;
		//}while(index >= low && index <= high);
		
		swap(index, high);
		
		T pivot = m_sortArray[high];
		
		int i = low-1;
		int j = low;
		
		for (;j < high; j++)
		{
			if (m_sortArray[j].compareTo(pivot) != 1)
			{
				i++;
				swap(i,j);
			}
		}
		i++;
		swap(i, high);
		return i;
	}
	
	public T[] threeWayPartitionSort()
	{
		if (m_sortArray == null || m_sortArray.length <=1)
			return m_sortArray;
			
		internalThreeWayPartitionSort(0, m_sortArray.length-1);
		return m_sortArray;
	}
	
	private void internalThreeWayPartitionSort(int low, int high)
	{
		int[] boundsArray = new int[2];
		if(low < high)
		{
		   threeWayPartition(low, high, boundsArray);
		
		   internalThreeWayPartitionSort(low, boundsArray[0]-1);
		   internalThreeWayPartitionSort(boundsArray[0]+1, boundsArray[1]);
		   internalThreeWayPartitionSort(boundsArray[1]+1, high);
		   
		}
	}
	
	private void threeWayPartition(int low, int high, int[] partPoints)
	{
		// we need two pivots
		if (m_sortArray[low].compareTo(m_sortArray[high]) == 1)
			swap(low,high);
		
		T pivotLeft = m_sortArray[low];
		T pivotRight = m_sortArray[high];
		
		// three pointers
		int i = low;
		int j = low+1;
		int k = high;
		
		while(j < k)
		{
			// case 1: m_sortArray[j] < pivotLeft
			if (m_sortArray[j].compareTo(pivotLeft) != 1)
			{
				i++;
				swap(j,i);
				j++;
				
			}
			// case 2: m_sortArray[j] > pivotLeft && m_sortArray[j] < pivotRight
			else if (m_sortArray[j].compareTo(pivotLeft) == 1 && 
					m_sortArray[j].compareTo(pivotRight) != 1)
			{
				j++;
			}
			// case3: m_sortArray[j] > pivotRight
			else
			{
				k--;
				swap(j,k);
			}
		}
		swap(high,k);
		swap(low, i);
		
		partPoints[0] = i;
		partPoints[1] = k;
		
		
	}
	
	
	public T[] quickSort()
	{
		if (m_sortArray == null || m_sortArray.length <=1)
			return m_sortArray;
			
		internalQuickSort(0, m_sortArray.length-1);
		return m_sortArray;
	}
	
	private void internalQuickSort(int low, int high)
	{
		if(low < high)
		{
		   int partitionIndex = partition(low, high);
		
		   internalQuickSort(low, partitionIndex-1);
		   internalQuickSort(partitionIndex+1, high);
		}
	}
	
	private int partition(int low, int high)
	{
		T pivot = m_sortArray[low];
		int j = high;
		int i = low;
		
		while(i < j)
		{
			while(i <= high && m_sortArray[i].compareTo(pivot) != 1)
				i++;
			while(j>= low && m_sortArray[j].compareTo(pivot) == 1)
				j--;
			
			if (i < j)
				swap(i,j);
		}
		swap(j, low);
		return j;
	}
	
	public T[] heapSort()
	{
		if (m_sortArray == null || m_sortArray.length <=1)
			return m_sortArray;
			
		internalHeapSort();
		return m_sortArray;
	}
	
	private void internalHeapSort()
	{
		heapify();
		for (int i =0; i<m_sortArray.length; i++)
		{
			swap(0,m_sortArray.length-1-i);
			bubbledown(0,m_sortArray.length-1-i);
		}
	}

	//bottom up approach
	private void heapify()
	{
		for (int i=m_sortArray.length-1 ; i >=0 ; i--)
			bubbledown(i, m_sortArray.length);
	}
	
		
	private void bubbledown(int n, int arrayLength)
	{
		T min;
		int childIndexMin = childIndex(n);
		if (childIndexMin < arrayLength)
		{
			if (childIndexMin+1 < arrayLength &&
					m_sortArray[childIndexMin+1].compareTo(m_sortArray[childIndexMin]) == 1)
				childIndexMin++;
			if (m_sortArray[childIndexMin].compareTo(m_sortArray[n]) == 1)
				swap(n,childIndexMin);
				
			bubbledown(childIndexMin, arrayLength);
		}
		
	}
	
	private int parentIndex(int i)
	{
		return (i+1)/2-1;
	}
	
	private int processIndex(int i)
	{
		return i+1;
		
	}
	private int childIndex(int i)
	{
		return (i+1)*2-1;
	}
	
	private void swap(int i, int j)
	{
		T tmpVal = m_sortArray[i];
		m_sortArray[i] = m_sortArray[j];
		m_sortArray[j] = tmpVal;
	}
	
	public void longestSequenceArray()
	{
		if (m_sortArray == null || 
				m_sortArray.length <2)
		{
			System.out.printf("Not a valid use case");
		    return;
	    }
		
		int[] resultArr = new int[3];
		internalMaxSubArray(0, m_sortArray.length-1, resultArr);
		
		System.out.printf("LeftIndex = %d, rightIndex = %d and sum = %d \n Result is: \n ", resultArr[0], resultArr[1], resultArr[2]);
		
		for (int i = resultArr[0]; i <= resultArr[1]; i++ )
			System.out.printf(" %d",  (int)m_sortArray[i]);
	}
	
	private void internalMaxSubArray(int low, int high, int[] resultArr)
	{
		// Base Case
		if (low == high)
		{
			resultArr[0] = low;
			resultArr[1] = high;
			resultArr[2] = (int)m_sortArray[low];
			return;
		}
		
		int mid = (low + high)/2;
		//Else get inot the left, right, and middle to get the max subarray
		int[] resArrLeft = new int[3];
		internalMaxSubArray(low, mid, resArrLeft);
		
		int[] resArrRight = new int[3];
		internalMaxSubArray(mid+1, high, resArrRight);
		
		int[] resArrCross = new int[3];
		internalMaxCrossSubArray(low, high, resArrCross);
		
		if (resArrLeft[2] > resArrRight[2] && resArrLeft[2] > resArrCross[2])
		{
			resultArr[0] = resArrLeft[0];
			resultArr[1] = resArrLeft[1];
			resultArr[2] = resArrLeft[2];
			return;
		}
		else if (resArrRight[2] > resArrLeft[2] && resArrRight[2] > resArrCross[2])
		{
			resultArr[0] = resArrRight[0];
			resultArr[1] = resArrRight[1];
			resultArr[2] = resArrRight[2];
			return;
		}
		else
		{
			resultArr[0] = resArrCross[0];
			resultArr[1] = resArrCross[1];
			resultArr[2] = resArrCross[2];
			return;
		}
				
	}
	
	private void internalMaxCrossSubArray(int low, int high, int[] resArr)
	{
		int leftSum = -99999;
		int leftIndex;
		int rightIndex;
		int rightSum = -99999;
		int sum = 0;
		
		int mid = (low+high)/2;
		leftIndex = mid;
		while (mid >= low)
		{
			sum += (int)m_sortArray[mid];
			if (sum > leftSum )
			{
				leftSum = sum;
				leftIndex = mid;
			}
			mid--;
		}
				
		mid = ((low+high)/2)+1;
		sum = 0;
		rightIndex = mid;
		while (mid <= high)
		{
			sum += (int)m_sortArray[mid];
			if (sum > rightSum)
			{
				rightSum = sum;
				rightIndex = mid;
			}
			mid++;
		}
		
		
		resArr[0] = leftIndex;
		resArr[1] = rightIndex;
		resArr[2] = leftSum + rightSum;
		return;
	}
}
