import java.util.*;

interface DiGraph
{
    public void clear();
    // Reinitializes the graph

    public boolean addVertex(String id);
    // Adds a vertex with the specified id and returns true.
    // Returns false if the id is already in the graph.

    public void addEdge(String id1, String id2);
    // Adds an edge from the vertex id1 to the vertex id2.

    public boolean removeVertex(String id);
    // Removes and returns the vertex with the specified id.
    // Returns null if the id is not in the graph.

    public void removeEdge(String id1, String id2);
    // Removes the edge between the vertices with the specified ids, if
    // such an edge exists.

    public StringList getVertexList();
    // Returns a list of the ids of all the vertices in the graph.

    public StringList getNeighbors(String id);
    // Returns a list of the ids of vertices that are neighbors to the specified
    // vertex.  Returns null if the specified id is not in the graph.

    public int getOutDegree(String id);
    // Returns the number of vertices adjacent to the specified vertiex, or -1 if
    // the specified id is not in the graph.

    public int getInDegree(String id);
    // Returns the number of vertices adjacent to the specified vertiex, or -1 if
    // the specified id is not in the graph.

    public int getVertexCount();
    // Returns the number of vertices in the graph.

    public int getEdgeCount();
    // Returns the number of edges in the graph.

    public boolean hasEdge(String id1, String id2);
    // Returns true if the specified vertices are joined by an edge.

    public boolean hasPath(String id1, String id2);
    // Returns true if there is a path between the specified vertices.

    public StringList getShortestPath(String start, String end);
    // Returns a list of vertices that represents a shortest path from
    // start vertex to end vertex, or null if there is no path
    
    public StringList getPathThrough(String start, String mid, String end);
    // Returns a list of vertices that represents a shortest path from
    // start vertex to end vertex that passes through the mid vertex,
    // or null if there is no path
    
    public String getBFTree(String root);
    // Returns a parenthesized string representing a breadth-first spanning tree of the
    // graph starting with the specified root.  Returns null if the specified root is 
    // not in the graph.
}

