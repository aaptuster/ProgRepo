
public class Misc {
	
	private static final int MIN_SEGMENT_LENGTH = 2;

	private static int numberOfLineSegments(Point[] pointArr)
	{
		if (pointArr.length < MIN_SEGMENT_LENGTH + 1)
			return 0;
		
		int lineCounter = 0;
		Sorter a = new Sorter<Point>(pointArr);
		a.quickSort();
		
		// now pointArr is sorted from x and then y
		// now start with the first point and create a slope with all other points except itself
		// sort this slope array, that holds the information of its point too
		for (int i = 0; i < pointArr.length-MIN_SEGMENT_LENGTH ; i++)
		{
			Slope[] slopeArr = new Slope[pointArr.length-1];
			for (int j = 0, k=0; j < pointArr.length; j++)
			{
				if ( i == j)
					continue;
				slopeArr[k++] =  new Slope(pointArr[i], pointArr[j],pointArr[i].slopeTo(pointArr[j]));
			}
			
			// now sort the slopeArr
			Sorter slopeSort = new Sorter<Misc.Slope>(slopeArr);
			slopeSort.quickSort();
			
			// now all the slopes are sorted.
			// Traverse  the slope object to see if adjacent sort arrays are equal and there are at least more than three. 
			// print the slopeArray that was found.
			int lineLength = 0;
			for (int x = 0; x < slopeArr.length-1; x++)
			{
				if (slopeArr[x].slopeVal == slopeArr[x+1].slopeVal)
					lineLength++;
				
				if (x == slopeArr.length-2 || 
						slopeArr[x].slopeVal != slopeArr[x+1].slopeVal)
				{
					if (x == slopeArr.length-2 && slopeArr[x].slopeVal == slopeArr[x+1].slopeVal)
						x++;
					
					if (lineLength >= MIN_SEGMENT_LENGTH-1 && slopeArr[x-lineLength].p2.compareTo(pointArr[i]) == 1 )
					{
						System.out.printf("\n line found with length %d and point %s with slope %f\n", lineLength+2, pointArr[i].toString(),slopeArr[x-lineLength].slopeVal);
						System.out.printf("\n %s",slopeArr[x-lineLength].p1.toString());
						for (int l = x-lineLength; l <= x ; l++)
							System.out.printf(" ,%s",slopeArr[l].p2.toString());
							
						lineCounter++;
					}
					lineLength = 0;
				}
			}
			
		}
		return lineCounter;
	}
	
	private static class Slope implements Comparable<Slope>
	{
		Point p1, p2;
		double slopeVal;
		Slope(Point p1, Point p2, double slope)
		{
			this.p1 = p1;
			this.p2 = p2;
			slopeVal = slope;
		}
		
		public int compareTo(Slope s)
		{
			if (slopeVal > s.slopeVal)
				return 1;
			if (slopeVal < s.slopeVal)
				return -1;
			return 
					p2.compareTo(s.p2);
		}
		
	}
	
	public static void main(String[] args)
	{
		Point[] pointArr = new Point[args.length/2];
		for (int i=0, j=0; i < args.length ;j++,i++,i++)
		{
			pointArr[j] = new Point(Integer.parseInt(args[i]), Integer.parseInt(args[i+1]));
		}
		
		int numLineSegment = numberOfLineSegments(pointArr);
		System.out.printf("\nNumber of line segments is %d", numLineSegment);
	}
}
