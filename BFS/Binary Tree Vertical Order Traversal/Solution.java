/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class Solution{
  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> result = new LinkedList<>();
    if (root == null){ 
      return result;
    }
    
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    int minCol = 0;
    
    Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
    queue.offer(new Pair(root, 0));
    
    while(!queue.isEmpty()){
      Pair<TreeNode, Integer> pair = queue.poll();
      TreeNode node = pair.getKey();
      int col = pair.getValue();
      if (col < minCol){
        minCol = col;
      }

      if (node != null){
        addToMap(col, node.val, map);
      }

      TreeNode lChild = node.left;
      if (lChild != null){
        queue.offer(new Pair(lChild, col - 1));
      }
      
      TreeNode rChild = node.right;
      if (rChild != null){
        queue.offer(new Pair(rChild, col + 1));
      }
    }

    while(map.get(minCol) != null){
      result.add(map.remove(minCol));
      ++minCol;
    }
    
    return result;
    }

  public void addToMap(int col, int val, HashMap<Integer, List<Integer>> map){
    if (map.get(col) == null){
      List<Integer> list = new LinkedList<>();
      list.add(val);
      map.put(col, list);
    } else {
      map.get(col).add(val);
    }
  }
  /**
  TC#1 - Null Root - Return Empty List<List<Integer>>
  TC#2 - []

  */
}
