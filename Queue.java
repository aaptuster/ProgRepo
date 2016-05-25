
public class Queue<T extends Comparable<? super T>> {

	private final static int MAX_ARR_SIZE = 100;
	private Object[] m_queueArr = null;
	private Stack<T> m_stack1;
	private Stack<T> m_stack2;
	private int m_front = 0;
	private int m_rear = -1;
	public Queue()
	{
		m_queueArr = new Object[MAX_ARR_SIZE];
		
	}
	public Queue(boolean usingStack)
	{
		if (usingStack)
		{
			m_stack1 = new Stack<T>();
		    m_stack2 = new Stack<T>();
		}
		else
			new Queue<T>();
	}
	
	public void enqueue(T elem)
	{
		if (m_queueArr == null)
		{
			m_stack1.push(elem);
		}
		else
		{
			m_queueArr[++m_rear] = elem;
		}
	}
	
	public T dequeue()
	throws Exception
	{
		if (m_queueArr == null)
		{
			if (m_stack2.isStackEmpty())
			{
				while(!m_stack1.isStackEmpty())
				{
					m_stack2.push(m_stack1.pop());
				}
			}
			return m_stack2.pop();
		}
		else
		{
			if (m_front <= m_rear)
				return (T) m_queueArr[m_front++];
		
			throw new Exception("Queue Underflow");
		}
	}
	
	public boolean isSingleElem()
	{
		return (m_front == m_rear);
	}
	
	
}
