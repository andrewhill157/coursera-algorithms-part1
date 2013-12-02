import unittest
import minCuts
"""
    This is the test suite for TestMinCuts. Due to time constraints, it is not at all complete. This is more to demonstrate how a test suite is set up in Python as compared to Java.
    
    Note that all tests must start with test to be found when running the test suite
    
    Also note that you must import the module you are testing, as done with minCuts in this test suite.
"""

class TestMinCuts(unittest.TestCase):

    def testFileToGraph(self):
        graph = minCuts.fileToGraph('test.txt')
        self.assertTrue(graph[1] == [2, 3])
        self.assertTrue(graph[2] == [1, 3])
        self.assertTrue(graph[3] == [1, 2])

    def testContractNodes(self):
        graph = minCuts.fileToGraph('test.txt')
        minCuts.contractNodes(graph, 1, 2)
        self.assertTrue(2 not in graph.keys())
        self.assertTrue(graph[1] == [3, 3])
        
    def testPickConnectedNodes(self):
        graph = minCuts.fileToGraph('test.txt')
        result = minCuts.pickConnectedNodes(graph)
        self.assertTrue(result[1] in graph[result[0]])
    
    def testFindMinCuts(self):
        graph = minCuts.fileToGraph('test.txt')
        result = minCuts.findMinCuts(graph)
        self.assertTrue(result == 2)

if __name__ == '__main__':
    unittest.main()