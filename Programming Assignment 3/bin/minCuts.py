import random
"""
 Karger's Algorithm for finding the minnimum cut of a graph.
    
 As of the completion of this assignment, this code does not run particularly fast.The data structure used (adjacency list) makes every contraction scan through the entire graph. This takes lots of time. In addition, the data structure does not make each node equally likely to be chosen when picking random nodes. This can impact the results you get, as the algorithm is randomized. There are better ways to implement a graph, but this is the one discussed in lecture.
    
    Due to time constraints, I did not write a test suite for this program. I made sure to make the program modular and test components one at a time quickly. 
        If I have time, I will develop a formal test suite.
"""

def fileToGraph(filePath):
	""" Converts a text file with an adjacency list representation of a graph
	to a graph data structure.
	@param filePath a file that contains on each line: an integer value corresponding to 
	a node in a graph followed by a tab-separated list of all nodes in the graph connected
	to that node. 1 \t 2 \t 3 \t would indicate that node  is connected to 2 and 3. Each line
	specifies connections for each node in the graph. 
	@rtype returns a dictionary representation of a graph with node values as the keys, 
	and a list of all nodes connected to that node as values. 
	"""
	graph = dict()
	
	# Open/Close the file for reading 
	with open(filePath) as f:
	    # Read all lines in the file
	    data = f.readlines()
	    
	    # Store each line of the list as a node and list of edges
	    for lines in data:
	    	# Split the text into a list, splitting by tab locations
		lines = lines.strip().split('\t')
		
		# Convert all elements to integers
		lines[:] = [int(n) for n in lines]
		
		# Store the first element as the key and the list of adjacent nodes as 
		# the values.
		graph[lines[0]] = lines[1:]
	return graph

def contractNodes(graph, node1, node2):
    """ Merges two chosen nodes in a graph
    @param graph a dictionary representation of a graph with node values as the keys
    and a list of all nodes connected to that node as values. Assumed to have at least two nodes. Graph is modified by this function.
    @param node1 the key for the first node to be merged. Node1 remains in the graph on completion.
    @param node2 the key for the second node to be merged. Node2 is no longer in the graph on completion.
    @rtype none. graph is modified such that Node1 and Node2 are merged. Generated self loops (caused by paths from Node1 to Node2 in the original graph) are discarded.
    """
    
    # Merge node1 and node2, eliminating self-loops
    node2List = [n for n in graph[node2] if n != node1]
    
    for n in node2List:
    	graph[node1].append(n)
    
    # Remove node2 from the graph
    graph.pop(node2, None)
    
    # Remove node2 from all adjacent nodes of all other nodes 
    for n in graph:
        if n == node1:
            graph[n] = [x for x in graph[n] if x != node2]
        else:
            # Node1 does not contain node2 in list, no need to process
            graph[n] = [node1 if x == node2 else x for x in graph[n]]
           
    return graph
    
def pickConnectedNodes(graph):
    """ Returns two connected nodes at random from the graph.
    @param graph a dictionary representation of a graph with node values as the keys, 
    and a list of all nodes connected to that node as values. Assumed to have at least two nodes.
    @rtype a tuple of two values, representing the keys of the two randomly chosen nodes from the graph
    """
    node1 = random.choice(graph.keys())
    node2 = random.choice(graph[node1])
    
    return (node1, node2)

def findMinCuts(graph):
    """ Returns the number of minnimum cut of a graph. Does not guarantee global minnimum cut.
    @param graph a dictionary representation of a graph with node values as the keys, 
    and a list of all nodes connected to that node as values. Assumed to have at least two nodes.
    @rtype the number of cuts in a minnimum cut of a graph. Does not guarantee global minnimum cut. 
    """
    
    # Contract random connected nodes of the graph until only two nodes are present
    while(len(graph) > 2):
        randomNodes = pickConnectedNodes(graph)
        graph = contractNodes(graph, randomNodes[0], randomNodes[1])
    return len(graph[graph.keys()[0]])
