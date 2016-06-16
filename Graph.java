import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Graph {

	private final static int MAXV = 1000;
	//components of graph
	   
	EdgeNode[] m_edges;
	int[] m_degree;
	int m_numVertices;
	int m_numOfEdges;
	boolean m_isDirected;
	NodeState[] m_nodeStateArr = null;
	Queue<Integer> m_vertexQueue = null;
	int[] m_parent = null;
	int[] m_connectedComponentArr = null;
	int m_componentNumber = 0;
	Colour[] m_color = null;
	private boolean m_bipartite = false;
	private int m_time;
	private int[] m_entryTime = null;
	private int[] m_exitTime = null;
	
	Graph(String inputFile, boolean isDirected)
	{
		initGraph(isDirected);
		readGraph(inputFile);
		initBFS();
	}
	
	public void printGraph()
	{
		for (int i=0;i < m_numVertices; i++)
		{
			EdgeNode e = m_edges[i];
			while(e != null)
			{
				System.out.printf("Edge %d -> %d\n", i, e.m_adjNode);
				e = e.m_next;
			}
		}
	}
	
	private void readGraph(String inputFileName)
	{
		try
		{
			File inputFile = new File(inputFileName);
			Scanner scanner = new Scanner(inputFile);
			System.out.printf("Reading input file %s\n", inputFileName);
			m_numVertices = scanner.nextInt();	
			//System.out.printf("Input data is %d\n",scanner.nextInt());
			while(scanner.hasNextInt())
			{
				int vertex = scanner.nextInt();
				EdgeNode e = new EdgeNode();
				e.m_adjNode = scanner.nextInt();
				e.m_weight = 0;
				e.m_next = m_edges[vertex];
				m_degree[vertex]++;
				m_edges[vertex] = e;
				m_numOfEdges++;
				//System.out.printf("Input data is %d\n",scanner.nextInt());
			}
			
		}
		catch (FileNotFoundException e)
		{
			System.out.printf("Exception is %s", e.getMessage());
		}
		
	}
	
	private void initGraph(boolean isDirected)
	{
		m_edges = new EdgeNode[MAXV+1];
		m_degree = new int[MAXV+1];
		m_isDirected = isDirected;
		m_numOfEdges = 0;
		m_numVertices = 0;
		m_time = 0;
	}
	
	public class EdgeNode
	{
		int m_adjNode;
		int m_weight;
		EdgeNode m_next;
	}
	
	public enum NodeState 
	{
		UNDISCOVERED,
		DISCOVERED,
		PROCESSED
	}

	public enum Colour
	{
		UNCOLORED,
		BLUE,
		RED
	}
	
	private void initBFS()
	{
		m_nodeStateArr = new NodeState[m_numVertices];
		m_vertexQueue = new Queue<Integer>();
		m_parent = new int[m_numVertices];
		m_color = new Colour[m_numVertices];
		m_entryTime = new int[m_numVertices];
		m_exitTime = new int[m_numVertices];
		
		m_connectedComponentArr = new int[m_numVertices];
	
		for (int i =0; i < m_numVertices; i++)
		{
			m_nodeStateArr[i] = NodeState.UNDISCOVERED;
			m_color[i] = Colour.UNCOLORED;
			m_parent[i] = -1;
		}
	}
	
	public boolean twoColor()
	{
		m_bipartite = true;
		for (int i = 0; i < m_numVertices; i++)
		{
			if (m_nodeStateArr[i] == NodeState.UNDISCOVERED)
			{
				m_color[i] = Colour.BLUE;
				BFS(i);
			}
		}
		
		for (int i = 0; i < m_numVertices; i++)
		{
			System.out.printf("\n %d -> %s", i, m_color[i].toString());
		}
		return m_bipartite;
		
	}
	
	// Breadth first search.
	// Start from a node and look at all its children
	public void BFS(int vertex)
	{
		// Enqueue Starting vertex
		m_vertexQueue.enqueue(vertex);
		m_nodeStateArr[vertex] = NodeState.DISCOVERED;
		
		try
		{
			internalBFS();
		}
		catch (Exception e)
		{
			System.out.printf("\nFound exception %s\n", e.getMessage());
		}
	}
	
	
	private void internalBFS()
	throws Exception
	{
		// for each vertex undiscovered
		while (!m_vertexQueue.isEmpty())
		{
			// Dequeue the first element
			int vertex = (int) m_vertexQueue.dequeue();
			
			// process vertex  as desired
			processVertexEarly(vertex);
						
			//list out each edge out from this vertex and process as desired.
			EdgeNode node = m_edges[vertex];
			while (node != null)
			{
				// process edge
				int edgeNode = node.m_adjNode;
				
				// depending on the required condition
				if (m_nodeStateArr[edgeNode] != NodeState.PROCESSED || m_isDirected)
					processEdge(vertex, edgeNode);
				
				//Enqueue all the children of the current node
				
				if (m_nodeStateArr[edgeNode] == NodeState.UNDISCOVERED)
				{
					m_nodeStateArr[edgeNode] = NodeState.DISCOVERED;
					m_parent[edgeNode] = vertex;
					m_vertexQueue.enqueue(edgeNode);
				}
				node = node.m_next;
			}
			processVertexLate(vertex);
		}
		
	}
	
	public void DFS(int vertex)
	{
		internalDFS(vertex);
		
	}
	
	private void internalDFS(int vertex)
	{
		m_nodeStateArr[vertex] = NodeState.DISCOVERED;
		
		// process Vertex early
		processVertexEarly(vertex);
		
		m_time++;
		m_entryTime[vertex] = m_time;
		
		EdgeNode node = m_edges[vertex];

		while (node != null)
		{
			// process edge
			int edgeNode = node.m_adjNode;
			
			// depending on the required condition
			//if (m_nodeStateArr[edgeNode] != NodeState.PROCESSED)
			//	processEdge(vertex, edgeNode);
			
			if (m_nodeStateArr[edgeNode] == NodeState.UNDISCOVERED)
			{
				m_parent[edgeNode] = vertex;
				processEdge(vertex, edgeNode);
				internalDFS(edgeNode);
			}
			else if (m_nodeStateArr[edgeNode] != NodeState.PROCESSED || m_isDirected)
				processEdge(vertex, edgeNode);
			
			node = node.m_next;
		}
		
		m_time++;
		m_exitTime[vertex] = m_time;
		processVertexLate(vertex);
				
	}
	
	public void findPath(int x, int y)
	{
		BFS(x);

		internalFindPath(x,y);
	}
	
	private void internalFindPath(int x, int y)
	{
		if (x == y)
			System.out.printf("\n%d",x);
		if (y == -1)
			System.out.printf("\n No Path");
		else
		{
			internalFindPath(x, m_parent[y]);
			System.out.printf(" %d",y);
		}
	}
	
	private void processVertexEarly(int vertex)
	{
		System.out.printf(" %d ", vertex);
		m_connectedComponentArr[vertex] = m_componentNumber;
	}
	
	private void processEdge(int x, int y)
	{
		if (m_parent[y] != x)
		{
			System.out.printf("\n Loop detected\n");
			internalFindPath(y,x);
		}
		/*
		if (m_color[x] == m_color[y])
		{
			m_bipartite = false;
			System.out.printf("\nNot bipartite due to %d -> %d\n",x,y);
			return;
		}
		m_color[y] = complement(m_color[x]);
		*/
		return;
	}
	
	private void processVertexLate(int vertex)
	{
		m_nodeStateArr[vertex] = NodeState.PROCESSED;
		return;
	}
	
	private Colour complement(Colour x)
	{
		if (x == Colour.BLUE)
			return Colour.RED;
		if (x == Colour.RED)
			return Colour.BLUE;
		
		return Colour.UNCOLORED;
	}
	
	public void connectedComponent(int x, int y)
	{
		int i;
				
		for (i = 0 ; i < m_numVertices; i++)
		{
			if (m_nodeStateArr[i] == NodeState.UNDISCOVERED)
			{
				m_componentNumber++;
				BFS(i);
			}
		}
		
		if (m_connectedComponentArr[x] == m_connectedComponentArr[y])
			System.out.printf("\nConnected component %d and %d component number %d\n",  x, y, m_connectedComponentArr[x]);
		else
			System.out.printf("\nDisconnected component %d and %d component number %d and %d\n",  x, y, m_connectedComponentArr[x],
					m_connectedComponentArr[y]);
	}
	
	public static void main (String[] args)
	{
		Graph g = new Graph(args[0], false);
		//g.printGraph();
		//g.BFS(0);
		
		//g.findPath(1, 201);
		//g.connectedComponent(0, 201);
		//g.twoColor();
		//System.out.printf("\n Number of components %d",g.m_componentNumber);
		g.DFS(0);
	}
}
