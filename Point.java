
public class Point implements Comparable<Point> {

	private static final int INFINITY = 9999999;
	private int x, y;
	public Point(int x, int y)
	{
     	this.x = x;
		this.y = y;
	}
	
	public double slopeTo(Point p)
	{
		if (p.x == x)
			return INFINITY;
		return ((double)(p.y-y))/(p.x-x);
	}
	
	public int compareTo(Point p)
	{
		if (x < p.x ||
				(x == p.x && y < p.y)) 
			return -1;
		
		if (x > p.x ||
				(x == p.x && y > p.y))
				return 1;
		
		return 0;
	}
	
	public String toString()
	{
		return String.format(" (%d,%d) ",x,y);
	}
}
