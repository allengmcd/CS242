/**
 * This class just takes in inputs and
 * calls methods from the graph class
 * this method also uses the DiGraph interface
 * @author Allen McDermott
 * @since 12/13/2014
**/





public class runGraph implements DiGraph {
	
	//global variable g of type graph
	graph g;


	// constructor for rungraph creates graph
	public runGraph() {
		g = new graph();
	}


	// Reinitializes the graph
	// worst case time complexity O(1)
	public void clear() {
		g = new graph();
	}

	// Adds a vertex with the specified id and returns true. Returns false if the id is already
	// in the graph.
	// worst case time complexity O(n) n being the amount of items in the graph
	public boolean addVertex(String id) {
		return g.addVertex(id);
	}
	

	// Removes the vertex with the specified id. Returns false if the id is not in the graph
	// time complexity
	// worst case time complexity O(|n|+|e|) n being the amount of vertexes in the graph and e being
	// the amount of neighbors of id
	public boolean removeVertex(String id) {
		return g.removeVertex(id);
	}


	// Adds an edge from the vertex id1 to the vertex id2. Has no effect if the edge already
	// exists.
	// worst case time complexity O(n) n being the amount of vertexes in the graph
	public void addEdge(String id1, String id2) {
		g.addEdge(id1,id2);
	}


	// Removes the edge from vertex id1 to vertex id2, if
	// such an edge exists. Otherwise has no effect.
	// worst case time complexity O(n) n being the amount of vertexes in the graph
	public void removeEdge(String id1, String id2) {
		g.removeEdge(id1,id2);
	}


	// Returns a list of the ids of all the vertices in the graph.
	// worst case time complexity O(n) n being the amount of vertexes in the graph
	public StringList getVertexList() {
		return g.getVertexList();
	}


	// Returns a list of the ids of vertices that vertex id1 has an edge to.
	// Returns null if the specified id1 is not in the graph.
	// worst case time complexity O(|n|+|e|) n being the amount of vertexes in the
	// graph and being the amount of neighbors of id
	public StringList getNeighbors(String id) {
		return g.getNeighbors(id);
	}


	// Returns the number of edges that start at vertex id1, or -1 if
	// the specified id1 is not in the graph
	// worst case time complexity O(|n|+|e|) n being the amount of vertexes in the
	// graph and being the amount of outs of id
	public int getOutDegree(String id1) {
		return g.getOutDegree(id1);
	}


	// Returns the number of edges that end at vertex id1, or -1 if
	// the specified id1 is not in the graph.
	// worst case time complexity O(|n|+|e|) n being the amount of vertexes in the
	// graph and being the amount of ins of id
	public int getInDegree(String id1) {
		return g.getInDegree(id1);
	}
	

	// Returns the number of vertices in the entire graph
	// worst case time complexity O(1)
	public int getVertexCount() {
		return g.getVertexCount();
	}


	// Returns the number of edges in the entire graph
	// worst case time complexity O(1)
	public int getEdgeCount() {
		return g.getEdgeCount();
	}



	// Returns true if there is an edge from vertex id1 to vertex id2
	// worst case time complexity O(|n|+|e|) n being the amount of vertexes in the
	// graph and being the amount of outs of id1
	public boolean hasEdge(String id1, String id2) {
		return g.hasEdge(id1,id2);
	}


	// Returns true if there is a path from vertex id1 to vertex id2
	// worst case time complexity O(|n|+|e|) n being the amount of vertexes in the
	// graph and being the amount of edges of id
	public boolean hasPath(String id1, String id2) {
		return g.hasPath(id1,id2);
	}


	// Returns a list of vertices that represents a shortest path from
	// start vertex to end vertex, or null if there is no path
	// worst case time complexity O(|n|+|e|) n being the amount of vertexes in the
	// graph and being the amount of edges of id
	public StringList getShortestPath(String start,String end) {
		return g.getShortestPath(start,end);
	}


	// Returns a list of vertices that represents a shortest path from start vertex to end
	// vertex that passes through the mid vertex, or null if there is no such pat
	// worst case time complexity O(|n|+|e|) n being the amount of vertexes in the
	// graph and being the amount of edges of id
	public StringList getPathThrough(String start, String mid, String end) {
		return g.getPathThrough(start,mid,end);
	}


	// Returns a parenthesized string representing a breadth-first spanning tree of the
	// graph in breadth-first order, starting with the specified root. Returns null if the
	// specified root is not in the graph
	// worst case time complexity O(|n|+|e|) n being the amount of vertexes in the
	// graph and being the amount of edges of id
	public String getBFTree(String root) {
		return g.getBFTree(root);
	}
}
