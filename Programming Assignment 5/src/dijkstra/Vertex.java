package dijkstra;

import java.util.HashMap;
import java.util.Map;

/**
 * Class representing a vertex in a graph. 
 */
public class Vertex {
	// A list of all vertices adjacent to this one
	private Map<Vertex, Integer> adjacentVertices;
	
	// Flag denoting whether a vertex has already been visited
	private boolean visited;
	
	// Number value associated with each vertex
	private int value;
	
	/**
	 * Creates a new vertex with the specified value
	 * @param value - integer value for the vertex
	 */
	public Vertex(int value) {
		this.adjacentVertices = new HashMap<Vertex, Integer>();
		this.visited = false;
		this.value = value;
	}
	
	/**
	 * Returns the integer value for the vertex
	 * @return integer value for the vertex
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * Sets the integer value for the vertex
	 * @param value - integer value to be associated with the vertex
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * Returns whether or not the vertex has been visited
	 * @return boolean flag representing whether or not the vertex has been visited
	 */
	public boolean isVisited() {
		return visited;
	}
	
	/**
	 * Sets the visited state of the vertex
	 * @param visited - boolean: true if visited, false if not visited
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	/**
	 * Returns Map of all adjacent vertices and respective path lengths
	 * @return Returns a map of vertices (keys) and path lengths (weights) to all vertices ajacent to vertex
	 */
	public Map<Vertex, Integer> getAdjacentVertices() {
		return adjacentVertices;
	}
	
	/**
	 * Adds a new vertex to the adjacency list with the specified weight
	 * @param newVertex - vertex to be added to the list of adjacent nodes
	 * @param weight - path length to from this to the vertex to be added
	 */
	public void addAdjacentNode(Vertex newVertex, int weight) {
		this.adjacentVertices.put(newVertex, weight);
	}

	/**
	 * Removes the specified node from the list of adjacent nodes for this vertex
	 * @param v - vertex to be removed
	 */
	public void removeAdjacentNode(Vertex v) {
		this.adjacentVertices.remove(v);
	}
	
	
	
	
}
