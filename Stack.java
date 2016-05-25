
public class Stack<T extends Comparable<? super T>> {
	
	private final static int STACK_SIZE = 100;
	private Queue<T> m_queue1 = null;
	private Queue<T> m_queue2 = null;
	private Object[] m_stackArr = null;
	private int m_top = -1;
	
	Stack()
	{
		m_stackArr= new Object[STACK_SIZE];
	}
	
	public Stack(boolean useQueue)
	{
		if (useQueue)
		{
			m_queue1 = new Queue<T>();
			m_queue2 = new Queue<T>();
		}
		else
		{
			new Stack<T>();
		}
	}
	
	public void push(T elem)
	{
		if (m_stackArr == null)
		{
			m_queue1.enqueue(elem);
		}
		else
		{
			if (m_top + 1 < STACK_SIZE)
				m_stackArr[++m_top] = elem;
		}
		
	}
	
	public boolean isStackEmpty()
	{
		return (m_top == -1);
	}
	
	public T pop()
	throws Exception
	{
		if (m_stackArr == null)
		{
			while (!m_queue1.isSingleElem())
			{
				m_queue2.enqueue(m_queue1.dequeue());
			}
			
			Queue<T> temp = m_queue2;
			m_queue2 = m_queue1;
			m_queue1 = temp;
			return (T) m_queue2.dequeue();
		}
		else
		{
			if (m_top >= 0)
				return (T) m_stackArr[m_top--];
		
			throw new Exception("Stack Underflow");
		}
	}

}
