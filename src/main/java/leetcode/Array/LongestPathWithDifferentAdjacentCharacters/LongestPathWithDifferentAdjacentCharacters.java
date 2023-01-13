package leetcode.Array.LongestPathWithDifferentAdjacentCharacters;

import java.util.ArrayList;

/**
 * 2246 hard "Longest Path With Different Adjacent Characters"
 * https://leetcode.com/problems/longest-path-with-different-adjacent-characters/description/
 * 
 * You are given a tree (i.e. a connected, undirected graph that has no cycles)
 * rooted
 * at node 0 consisting of n nodes numbered from 0 to n - 1.
 * The tree is represented by a 0-indexed array parent of size n,
 * where parent[i] is the parent of node i. Since node 0 is the root, parent[0]
 * == -1.
 * You are also given a string s of length n, where s[i] is the character
 * assigned to node i.
 * Return the length of the longest path in the tree such that no pair of
 * adjacent nodes
 * on the path have the same character assigned to them.
 * 
 * Example 1:
 * Input: parent = [-1,0,0,1,1,2], s = "abacbe"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different
 * characters
 * in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is
 * returned.
 * It can be proven that there is no longer path that satisfies the conditions.
 * 
 * Example 2:
 * Input: parent = [-1,0,0,0], s = "aabc"
 * Output: 3
 * Explanation: The longest path where each two adjacent nodes have different
 * characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is
 * returned.
 * 
 * T.C.:
 */
public class LongestPathWithDifferentAdjacentCharacters {
	
	int maxPath=0;
    public int longestPath(int[] parent, String s){

        //Creating adjacency list

        ArrayList<Integer>[] a = new ArrayList[parent.length];
        
        for(int i=0;i<parent.length;i++)
            a[i] = new ArrayList<Integer>();

        for(int i=1;i<parent.length;i++)
            a[parent[i]].add(i);

        // dfs from root node
        dfs(-1,0,a,s);
        
        return maxPath+1;  //since we have to return no. of vertices in path length, so adding +1
    }

    private int dfs(int prev,int curr,ArrayList<Integer>[] a,String s)
    {
        int maxPath1=0;//first longest Path
        int maxPath2=0;//second longest Path
        for(int x:a[curr]){
            if(x!=prev)
            {
                int pathlength=dfs(curr,x,a,s); //longest path for children node
                pathlength=s.charAt(x)==s.charAt(curr)?0:pathlength+1;
                
                if(pathlength>=maxPath1){
                    maxPath2=maxPath1;
                    maxPath1=pathlength;
                }
                else if(pathlength>=maxPath2)
                    maxPath2=pathlength;
            }
        }
        maxPath = Math.max(maxPath,maxPath1+maxPath2); //updating global maxPath
         
        return maxPath1; //return longest path for children node
    }
}
