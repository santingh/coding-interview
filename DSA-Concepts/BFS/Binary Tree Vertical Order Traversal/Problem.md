Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

 

Example 1:
![vtree1](https://github.com/santingh/coding-interview/assets/16878844/9c666731-045f-464b-9476-05ddf29e90d1)



Input: root = [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]

Example 2:
![vtree2-1](https://github.com/santingh/coding-interview/assets/16878844/33fdd42b-f386-421b-931f-a073d63fb1f2)



Input: root = [3,9,8,4,0,1,7]
Output: [[4],[9],[3,0,1],[8],[7]]

Example 3:
![vtree2](https://github.com/santingh/coding-interview/assets/16878844/2d6e3cf9-6786-4283-989f-dc69970cefe7)



Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
Output: [[4],[9,5],[3,0,1],[8,2],[7]]
 

Constraints:

The number of nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
