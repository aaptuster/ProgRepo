
public class DSDriver {

	public static void main(String[] args)
	{
		Stack stack = new Stack(true);
		for (String s: args)
			stack.push(s);
		
		String s;
		try
		{
			while ((s = (String)stack.pop()) != null)
			{
				System.out.printf("\nPopped :%s ",s);
			}
		}
		catch (Exception e)
		{
			System.out.printf("\n%s",e.getMessage());
		}
		
		System.out.printf("\n Queue");
		Queue queue = new Queue(true);
		for (String s1: args)
			queue.enqueue(s1);
		
		
		try
		{
			while ((s = (String)queue.dequeue()) != null)
			{
				System.out.printf("\nExited from queue :%s ",s);
			}
		}
		catch (Exception e)
		{
			System.out.printf("\n%s",e.getMessage());
		}
			
	}
}
