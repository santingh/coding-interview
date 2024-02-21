// Solve using BFS

// Solve using BFS

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
          // found a new island - increament count and trace every connected land
          ++islandCount;
          Queue<Integer> neighbours = new LinkedList<>();
          neighbours.add(row * colCount + col);
          grid[row][col] = '0'; // mark visited
          while(!neighbours.isEmpty()){
            int pos = neighbours.remove();
            int landRow = pos / colCount; 
            int landCol = pos % colCount; 
            // check neighbours if within grid boundaries and value 1
            // check right
            if (landCol < (colCount - 1) && grid[landRow][landCol + 1] == '1'){
              neighbours.offer(landRow * colCount + (landCol + 1));
              grid[landRow][landCol + 1] = '0'; // mark visited
            }

            // check left - grid[landRow][landCol - 1]
            if (landCol > 0 && grid[landRow][landCol - 1] == '1'){
              neighbours.offer(landRow * colCount + (landCol - 1));
              grid[landRow][landCol - 1] = '0';
            }

            // check up - grid[landRow - 1][landCol]
            if (landRow > 0 && grid[landRow - 1][landCol] == '1'){
              neighbours.add((landRow - 1) * colCount + landCol);
              grid[landRow - 1][landCol] = '0';
            }

            // check down - grid[landRow + 1][landCol]
            if (landRow < (rowCount - 1) && grid[landRow + 1][landCol] == '1'){
              neighbours.add((landRow + 1) * colCount + landCol);
              grid[landRow + 1][landCol] = '0';
            }
            
          }
        }
      }
    }
    return islandCount;
    
  }
  
}
