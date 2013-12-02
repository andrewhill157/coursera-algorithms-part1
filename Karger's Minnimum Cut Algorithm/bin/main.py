from minCuts import *
# MinCuts is a module with all the functions defined for this assignment.

""" Loads graph from assignment file and then calculates the min cut of the graph. As this process is randomized, many iterations are run in order to ensure that the global minnimum cut is found.
"""
# Load graph from file
graph = fileToGraph('graph.txt')

# Make a copy to perform algorithm on, so don't have to reload from file
temp = graph.copy()

# Calculate the mincut of the graph many times, remembering the minnimum value seen
min = findMinCuts(temp)
for i in range(200):
    # make a copy of the graph to pass each time to pass on to the algorithm
    temp = graph.copy()
    
    # find min cuts in graph
    x = findMinCuts(temp)
    
    if(x < min):
        min = x

print min