package scc;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	// A list of all vertices adjacent to this one
	private List<Vertex> adjacentVertices;
	
	// Flag denoting whether a vertex has already been visited
	private boolean visited;
	
	// Number value associated with each vertex
	private int value;
	
	/**
	 * 
	 */
	public Vertex(int value) {
		this.adjacentVertices = new ArrayList<Vertex>();
		this.visited = false;
		this.value = value;
	}
	
	public int getValue() {
		return this.value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isVisited() {
		return visited;
	}
	
	/**
	 * 
	 * @param visited
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Vertex> getAdjacentVertices() {
		return adjacentVertices;
	}
	
	/**
	 * 
	 * @param newVertex
	 */
	public void addAdjacentNode(Vertex newVertex) {
		this.adjacentVertices.add(newVertex);
	}

	/**
	 * 
	 * @param v
	 */
	public void removeAdjacentNode(Vertex v) {
		this.adjacentVertices.remove(v);
	}
	
	
	
	
}
