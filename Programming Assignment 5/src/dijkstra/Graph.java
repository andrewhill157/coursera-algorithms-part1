package dijkstra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

import dijkstra.Vertex;

/**
 * A Graph representation that provides the ability to find shortest paths using dikjstra's algorithm. 
 * This is a O(mn) version because it does not maintain a priority queue to find the minimum path for each 
 * iteration. This means that O(n) is required to find Vertex with min dijkstra score, rather than O(logn).
 * Note that to allow for use of a priority queue, the paths/vertices would have to implement tha comparable
 * interface in java, as this is how priorities are calculated. 
 * 
 * The implementation here may also have other inefficiencies. For example, there it may make more sense to keep
 * a list of all Vertices on the Frontier (Vertices that have been visited, but have unvisited adjacent nodes). 
 * This would make finding the shortest paths easier, because rather than scanning through all explored nodes, you
 * could simply scan through those on the frontier. However, this is a good starting point for future. 
 * 
 * There also is no formal test suite, although I did test functions for converting file to graph and the shortest
 * path algorithms on a number of small inputs manually. A future improvement would be adding automated tests.
 *
 */
public class Graph {
	// Collection of vertices in the graph
	private Map<Integer, Vertex> vertices;
	
	// Heap for dijkstraScores
	private PriorityQueue<Vertex> dijkstraScore;
	
	// List of vertices in the order they complete in a DFS pass on the reversed graph G
	Stack<Vertex> finishingTimes;
	
	/**
	 * Contructs Graph from a Map of integers to vertex objects
	 * @param vertices - a map of values for vertices to vertex objects
	 */
	public Graph(Map<Integer,Vertex> vertices) {
		this.vertices = vertices;
		finishingTimes = new Stack<Vertex>();
		dijkstraScore = new PriorityQueue<Vertex>();
		
	}
	
	/**
	 *  Constructs a graph directly from a text file (format specified in assignment)
	 *  
	 * @param filePath a string with the full filePath with graph representation (see assignment for spec)
	 * @throws IOException
	 */
	public Graph(String filePath) throws IOException {
		// Both normal and reversed graphs will be constructed
		Map<Integer, Vertex> vertices = new HashMap<Integer, Vertex>();
		
		// Open necessary objects to read file contents
		FileReader fileReader = new FileReader(filePath);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String line = bufferedReader.readLine();
		
		while(line != null) {
			// Split each line into head and tail
			String[] edge = line.split("\t");
			
			int vertex = Integer.parseInt(edge[0]);
			if(!vertices.containsKey(vertex)) {
				Vertex v = new Vertex(vertex);
				vertices.put(vertex, v);
			}
			
			for(int i = 1; i < edge.length; i++) {
				String[] adjacentNode = edge[i].split(",");
				
				int value = Integer.parseInt(adjacentNode[0]);
				int weight  = Integer.parseInt(adjacentNode[1]);
				
				if(!vertices.containsKey(value)) {
					Vertex v = new Vertex(value);
					vertices.put(value, v);
				}
				
				vertices.get(vertex).addAdjacentNode(vertices.get(value), weight);
				
			}
			
			
			line = bufferedReader.readLine(); // get the next line
		} 
		bufferedReader.close(); // clean up
		
		// Finally, set the graph
		this.vertices = vertices;
	}
	
	/**
	 * Returns all vertices in the graph
	 * @return a map of integer values (keys) and Vertex objects (values) composing the graph
	 */
	public Map<Integer, Vertex> getVertices() {
		return vertices;
	}
	
	/**
	 * Sets all vertices in the graph to the be either visited or not
	 * @param visited - true for visited, false for unvisited
	 */
	public void setAllVisited(boolean visited) {
		for(Vertex n : this.getVertices().values()) {
			n.setVisited(false);
		}
	}
	
	/**
	 * Returns whether or not there are any unvisited vertices remaining in the graph
	 * @return true if there are unvisited vertices in the graph, false otherwise
	 */
	public boolean unexploredVerticesRemaining() {
		for(Vertex v : vertices.values()) {
			if(!v.isVisited()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns a list of shortest paths to all vertices in the graph when starting at sourceVertex
	 * @param sourceVertex - starting vertex for shortest paths algorithm
	 * @return an array of the shortest path lengths to all vertices in the graph. The index i in the array is the
	 * 			path length from the source vertex to vertex i. The path length of the source vertex to itself is
	 * 			always 0. Path length to unreachable vertices in the graph is set to infinity (1000000).
	 */
	public int[] shortestPaths(Vertex sourceVertex) {
		// Set the source vertex as visited
		sourceVertex.setVisited(true);
		
		// Create an array to store the shortest paths
		int shortestPaths[] = new int[vertices.size() + 1];
		
		// Shortest path from the source vertex to itself is always zero
		shortestPaths[sourceVertex.getValue()] = 0;
		
		// Continue computing shortest paths until all vertices are explored, or there are no more reachable vertices
		while(unexploredVerticesRemaining()) {
			// Initialize minnimum score to "infinity"
			int minnimumScore = 1000000;
			
			// Keep track of the head and tail for shortest paths across the frontier
			Vertex minEnd = sourceVertex;
			Vertex minStart = sourceVertex;
			
			// For all visited vertices in the graph
			for(Vertex v : vertices.values()) {
				if(v.isVisited()) {
					
					// For all unvisited adjacent vertices (paths that cross the frontier)
					for(Vertex x : v.getAdjacentVertices().keySet()) {
						if(!x.isVisited()) {
							// Calculate the dijkstra score (shortest distance to start point + length of new path)
							int temp = shortestPaths[v.getValue()] + v.getAdjacentVertices().get(x);
							
							// If it is shortest seen so far, remember it
							if(temp < minnimumScore) {
								minnimumScore = temp;
								minEnd = x;
								minStart = v;
							}
						}
					}	
				}
			}
			// Absorb the new Vertex (end of shortest path) the explored region and set its shortest path value
			minEnd.setVisited(true);
			shortestPaths[minEnd.getValue()] = minnimumScore;
			
			// If the minEnd did not change, there are no more reachable nodes, so return
			if(minEnd == sourceVertex) {
				return shortestPaths;
			}
		}
		return shortestPaths;
	}
}
