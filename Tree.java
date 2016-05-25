public class Tree {
	private Node m_root;
	private static final int MAX = 9999999;
	private static final int MIN = -1;
	
	Tree()
	{
		m_root = null;
	}
	
	public void add(int data)
	{
		if (find(data) != null)
		{
			System.out.println("Ignored addition of node "+data+" as it is duplicate");
			return;
		}
		Node pos = findInsertPosition(m_root, data);
		
		Node elem = new Node();
		elem.llink = null;
		elem.rlink = null;
		elem.data = data;
		
		if (m_root == null)
			m_root = elem;
		
		else if (data < pos.data)
			pos.llink = elem;
		else
			pos.rlink = elem;
	}
	
	private Node findInsertPosition(Node trnode,int data)
	{
		if (trnode == null)
			return null;
		else if (data > trnode.data)
		{
			if (trnode.rlink == null)
				return trnode;
			else 
				return findInsertPosition(trnode.rlink,data);
		}
		else
		{
			if (trnode.llink == null)
				return trnode;
			else 
				return findInsertPosition(trnode.llink,data);
		}
	}
	
	public void messWithTree()
	{
		messWithTreeInternal(m_root);
	}
	
	private void messWithTreeInternal(Node trnode)
	{
		// Fault injection in to the left subtree of the root
		trnode = trnode.rlink;
		int tmp = trnode.llink.data;
		trnode.llink.data = trnode.rlink.data;
		trnode.rlink.data = tmp;
	}
	
	public int findMAXBST()
	{
		NodeInfo info = new NodeInfo();
		return findMAXBSTInternal(m_root,info);
	}
	
	private int findMAXBSTInternal(Node trnode, NodeInfo info)
	{
		if (trnode == null)
			return 0;
		
		// create two information holders
		NodeInfo leftChildInfo = new NodeInfo();
		NodeInfo rightChildInfo = new NodeInfo();
		
		// travel left and right
		findMAXBSTInternal(trnode.llink, leftChildInfo);
		findMAXBSTInternal(trnode.rlink, rightChildInfo);
		
		//visit the node
		// process the information received from nodeInfo holders
		if (!leftChildInfo.isBST || !rightChildInfo.isBST
			|| (leftChildInfo.max != MAX && trnode.data < leftChildInfo.max)  
			|| (rightChildInfo.min != MIN && trnode.data > rightChildInfo.min))
		{
			info.isBST = false;
			info.size = 0;
			if (leftChildInfo.size > rightChildInfo.size)
				info.size = leftChildInfo.size;
			else
				info.size = rightChildInfo.size;
			return info.size;
		}
		else // current node is a valid BST. Pass on the information to the higher nodes. 
		{
			info.min = leftChildInfo.min == MIN ? trnode.data : leftChildInfo.min;
			info.max = rightChildInfo.max == MAX ? trnode.data : rightChildInfo.max;
			info.size = leftChildInfo.size + rightChildInfo.size +1;
			return info.size;
		}
	}
	
	public int leastCommonAncestor(int num1, int num2)
	{
		if (find(num1) == null || find(num2) == null)
		{
			//System.out.println("Either both or one of the numbers input is not present in the tree ");
			return -1;
		}
		
		Node lcaNode = leastCommonAncestorInternal(m_root,num1,num2);
		return lcaNode.data;
	}
	
	private Node leastCommonAncestorInternal(Node trnode, int num1, int num2)
	{
		if (trnode == null)
			return null;
		if (trnode.data == num1 || trnode.data == num2)
			return trnode;
		
		Node rootL = leastCommonAncestorInternal(trnode.llink, num1, num2);
		Node rootR = leastCommonAncestorInternal(trnode.rlink, num1, num2);
		
		if (rootL == null && rootR != null)
			return rootR;
		else if (rootR == null && rootL != null)
			return rootL;
		else if (rootL != null && rootR != null)
			return trnode;
		else
			return null;
	}
	
	public void inorder()
	{
		inorderInternal(m_root);
	}
	
	private void inorderInternal(Node trnode)
	{
		if (trnode== null)
			return;
		
		inorderInternal(trnode.llink);
		System.out.println(" "+trnode.data);
		inorderInternal(trnode.rlink);
	}
	
	public Node find(int data)
	{
		return findElem(m_root, data);
	}
	
	private Node findElem(Node trnode, int data)
	{
		if (trnode == null)
			return null;
		else if (trnode.data == data)
			return trnode;
		else if (trnode.data> data)
			return findElem(trnode.llink,data);
		else 
			return findElem(trnode.rlink,data);	
	}
	
	public void convertTreeToLinkedList()
	{
		if (m_root == null)
			return;
		
		System.out.printf("Converting BST to list with llink as the useful link");
		
		Node[] startNode = new Node[1];
		convertTreeToLinkedListInternal(m_root, startNode);
		printConvertedList(startNode[0]);
	}
	
	private void printConvertedList(Node trnode)
	{
		while (trnode != null)
		{
			System.out.printf(" %d",trnode.data);
			trnode = trnode.rlink;
		}
	}
	
	private Node convertTreeToLinkedListInternal(Node trnode, Node[] startNode)
	{
		if (trnode == null)
			return null;
		
		Node llink = convertTreeToLinkedListInternal(trnode.llink, startNode);
		Node rlink = convertTreeToLinkedListInternal(trnode.rlink, startNode);
		
		if (startNode[0] == null)
			startNode[0] = trnode;
		
		trnode.llink = rlink;
		
		if (llink != null)
		{
			Node start = llink;
			while(start.llink != null)
				start = llink.llink;
			start.llink = trnode;
			
			return llink;
		}
		else
			return trnode;
	}
	
	public void convertTreeToDoublyLinkedList()
	{
		if (m_root == null)
			return;
		
		System.out.printf("Converting BST to list with llink as the useful link");
		
		Node[] startNode = new Node[1];
		convertTreeToDoublyLinkedListInternal(m_root, startNode);
		printConvertedList(startNode[0]);
	}
	
	private Node convertTreeToDoublyLinkedListInternal(Node trnode, Node[] startNode)
	{
		if (trnode == null)
			return null;
		
		Node llink = convertTreeToDoublyLinkedListInternal(trnode.llink, startNode);
		Node rlink = convertTreeToDoublyLinkedListInternal(trnode.rlink, startNode);
		
		if (startNode[0] == null)
			startNode[0] = trnode;
		
		while (rlink != null && rlink.llink != null)
			rlink = rlink.llink;
		trnode.rlink = rlink;
		
		if (llink != null)
		{
			while (llink != null && llink.rlink != null)
				llink = llink.rlink;
			llink.rlink = trnode;
		}
		
		return trnode;
	}
	
	
	private class NodeInfo
	{
		boolean isBST;
		int min;
		int max;
		int size;
		
		NodeInfo()
		{
			isBST = true;
			min = MIN;
			max = MAX;
			size = 0;
		}
	}
	
	private class Node 
	{
		Node llink;
		Node rlink;
		int data;
	}
	
	private class LinkedList
	{
		LinkedList link;
		int data;
	}
	
	private class DoublyLinkedList
	{
		DoublyLinkedList llink;
		DoublyLinkedList rlink;
		int data;
	}
}