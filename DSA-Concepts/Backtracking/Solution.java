class Solution {
    public int totalNQueens(int n) {
        
        int[][] board = new int[n][n];
        if (n == 0){
            return 0;
        }
        
        return countOptions(board, 0);
        
    }
    
    public int countOptions(int[][] board, int row){
        int count = 0;
        for(int col = 0; col < board[0].length; col++){
            if (!isUnderAttack(board, row, col)){
                placeQueen(board, row, col);
                
                if (row+1 == board.length){
                    // last row queen placed - Found a solution
                    removeQueen(board, row, col);
                    return 1;
                } else{
                    count = count + countOptions(board, row+1);
                }
                
                removeQueen(board, row, col);
            }
        }
        
        return count;
        
    }
    
    public void placeQueen(int[][] board, int row, int col){
        for (int i = 0; i < board[0].length; i++ ){
            board[row][i] = board[row][i] + 1;
        }
        
        for (int i = 0; i < board.length; i++ ){
            board[i][col] = board[i][col] + 1;
        }

        board[row][col] = board[row][col] - 1;
        
        int i = row+1; int j = col+1;
        while(i < board.length && j < board[0].length){
            board[i][j] = board[i][j] + 1;
            i++; j++;
        }
        
        i = row-1; j = col-1;
        while(i >= 0 && j >= 0){
            board[i][j] = board[i][j] + 1;
            i--; j--;
        }

        i = row-1; j = col+1;
        while(i >= 0 && j < board[0].length){
            board[i][j] = board[i][j] + 1;
            i--; j++;
        }

        i = row+1; j = col-1;
        while(i < board.length && j >= 0){
            board[i][j] = board[i][j] + 1;
            i++; j--;
        }
        
    }
    
    public void removeQueen(int[][] board, int row, int col){
        for (int i = 0; i < board[0].length; i++ ){
            board[row][i] = board[row][i] - 1;
        }
        
        for (int i = 0; i < board.length; i++ ){
            board[i][col] = board[i][col] - 1;
        }

        board[row][col] = board[row][col] + 1;
        
        int i = row+1; int j = col+1;
        while(i < board.length && j < board[0].length){
            board[i][j] = board[i][j] - 1;
            i++; j++;
        }
        
        i = row-1; j = col-1;
        while(i >= 0 && j >= 0){
            board[i][j] = board[i][j] - 1;
            i--; j--;
        }

        i = row-1; j = col+1;
        while(i >= 0 && j < board[0].length){
            board[i][j] = board[i][j] - 1;
            i--; j++;
        }

        i = row+1; j = col-1;
        while(i < board.length && j >= 0){
            board[i][j] = board[i][j] - 1;
            i++; j--;
        }
    }
    
    public boolean isUnderAttack(int[][] board, int row, int col){
         // 1 or more - under attack, else 0
        
        if (board[row][col] <= 0){
            return false;
        } 
        
        return true;
    }
}
