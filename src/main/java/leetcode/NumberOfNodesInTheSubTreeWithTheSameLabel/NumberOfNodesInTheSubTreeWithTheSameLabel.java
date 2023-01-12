package leetcode.NumberOfNodesInTheSubTreeWithTheSameLabel;

import java.util.ArrayList;
import java.util.List;

/**
 * Issue 1519 medium "Number of Nodes in the Sub-Tree With the Same Label"
 * Skills: Tree, Depth-First Search
 * 
 * https://leetcode.com/problems/number-of-nodes-in-the-sub-tree-with-the-same-label/description/
 * 
 * You are given a tree (i.e. a connected, undirected graph that has no cycles) 
 * consisting of n nodes numbered from 0 to n - 1 and exactly n - 1 edges. 
 * The root of the tree is the node 0, and each node of the tree has a label which is a 
 * lower-case character given in the string labels (i.e. The node with the number i 
 * has the label labels[i]).
 * The edges array is given on the form edges[i] = [ai, bi], 
 * which means there is an edge between nodes ai and bi in the tree.
 * Return an array of size n where ans[i] is the number of nodes in the subtree 
 * of the ith node which have the same label as node i.
 * A subtree of a tree T is the tree consisting of a node in T and all of its descendant nodes.
 * 
 * Example 1:
 * Input: n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]], labels = "abaedcd"
 * Output: [2,1,1,1,1,1,1]
 * Explanation: Node 0 has label 'a' and its sub-tree has node 2 with label 'a' as well, 
 * thus the answer is 2. Notice that any node is part of its sub-tree.
 * Node 1 has a label 'b'. The sub-tree of node 1 contains nodes 1,4 and 5, 
 * as nodes 4 and 5 have different labels than node 1, the answer is just 1 (the node itself).
 * 
 * Example 2:
 * Input: n = 4, edges = [[0,1],[1,2],[0,3]], labels = "bbbb"
 * Output: [4,2,1,1]
 * Explanation: The sub-tree of node 2 contains only node 2, so the answer is 1.
 * The sub-tree of node 3 contains only node 3, so the answer is 1.
 * The sub-tree of node 1 contains nodes 1 and 2, both have label 'b', thus the answer is 2.
 * The sub-tree of node 0 contains nodes 0, 1, 2 and 3, all with label 'b', thus the answer is 4.
 * 
 */

public class NumberOfNodesInTheSubTreeWithTheSameLabel {
	// declare globals
	private int[] ans;
	private List<Integer>[] adj;

	public int[] countSubTrees(int n, int[][] edges, String labels) {
		adj = new List[n]; // initialising the adjacency list
		for (int i = 0; i < n; i++) {
			adj[i] = new ArrayList<>();
		}
		for (int[] e : edges) { // populating the adjacency list
			adj[e[0]].add(e[1]);
			adj[e[1]].add(e[0]);
		}

		ans = new int[n]; // initialising the answer array
		dfs(0, -1, labels); // calling the dfs
		return ans;
	}

	private int[] dfs(int currNode, int parent, String labels) {
		// Our DFS will go to the depth and bring us frequencies of
		// characters present in each subtree, go to bottom and
		// come back to top with frequencies
		int[] currSubtreeFreq = new int[26]; // records the frequency of all characters in the current node's subtree
		for (int child : adj[currNode]) { // we look at all the nodes connected to our current node
			// we don't want to go back to parent node where we came from
			if (child == parent) { // so keep iterating if child is equal to parent
				continue;
			}
			// we now call dfs to record the frequencies of all characters present in the
			// subtree of the child
			int[] childSubtreeFreq = dfs(child, currNode, labels); // child becomes new the currentNode, currentNode
																	// becomes the new parent
			// we update our currSubtreeFreq array after we get childSubtreeFreq array
			// populated
			// because child's subtree is a part of our current node's subtree
			for (int i = 0; i < 26; i++) {
				currSubtreeFreq[i] += childSubtreeFreq[i];
			}
		}
		// the current node is also associated to a character so we update
		// currSubtreeFreq
		currSubtreeFreq[labels.charAt(currNode) - 'a']++;
		// now, currSubtreeFreq contains the frequency of all characters present in
		// subtree.
		// but now we just need the frequency of that particular character which is
		// associated to our current node
		// say current node value is 1 and the letter associated is 'a'.
		// so we need the frequency of 'a's that are in the subtree
		ans[currNode] = currSubtreeFreq[labels.charAt(currNode) - 'a'];
		return currSubtreeFreq; // we return the current subtree frequency array which will be utilised upon
								// backtracking
	}
}
