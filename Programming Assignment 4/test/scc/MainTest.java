package scc;

import static org.junit.Assert.*;

import java.util.Map;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class MainTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFileToGraph() throws IOException {
		String filePath = "/Users/andrewhill/Dropbox/Coursera/Algorithms Design and Analysis, Part 1 (Stanford)/Code/Programming Assignment 4/src/testGraph.txt";
		Graph g = new Graph(filePath);

		Map<Integer, Vertex> nodes = g.getReversedVertices();
		for(int n : nodes.keySet()) {
			Vertex v = nodes.get(n);
			System.out.print(v.getValue() + " ");
			for(Vertex x : v.getAdjacentVertices()) {
				System.out.print(x.getValue() + " ");
			}
			System.out.println();
		}
	}

}
