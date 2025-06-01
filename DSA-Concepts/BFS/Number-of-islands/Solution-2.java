// Solve using DFS

class Solution {

  public int numIslands(char[][] grid){

    if (grid == null || grid.length == 0 || grid[0].length == 0){
      return 0;
    }

    int rowCount = grid.length;
    int colCount = grid[0].length;
    int islandCount = 0;

    for(int row = 0; row < rowCount; row++){
      for(int col = 0; col < colCount; col++){
        if (grid[row][col] == '1'){
          ++islandCount;
          dfs(row, col, grid);
        }
      }
    }
    return islandCount;
    
  }

  public void dfs(int row, int col, char[][] grid){
    if (row >= grid.length || row < 0 || col >= grid[0].length || col < 0 || grid[row][col] == '0'){
      return; 
    }
    grid[row][col] = '0';
    dfs(row, col + 1, grid);
    dfs(row, col - 1, grid);
    dfs(row + 1, col, grid);
    dfs(row - 1, col, grid);
  }
  
}
