class Solution {
    public void wallsAndGates(int[][] rooms) {
        // idea is to start BFS search from all the gates by putting them in queue. 
        // BFS search always goes level by level, so for each gate the most immediate cells will be marked with 
        // lebel. Any cell already marked means its all ready visited. 

        // check for empty rooms
        if (rooms == null || rooms.length == 0 || rooms[0].length == 0){
            return;
        }
        
        // scan the rooms to identify gates (O(mn))
        int numRow = rooms.length;
        int numCol = rooms[0].length;
        Queue<Integer> q = new LinkedList<>();
        for (int r = 0; r < numRow; r++){
            for (int c = 0; c < numCol; c++){
                if (rooms[r][c] == 0){
                    // found a gate - add it to queue for BFS
                    q.offer(r * numCol + c); 
                }
            }
        }

        // start BFS use some marker to identify level increment. 
        q.offer(Integer.MIN_VALUE); // Marker for level change
        int level = 1;
        while (! q.isEmpty()){
            // check if its marker for level change
            int room = q.remove();
            if (room == Integer.MIN_VALUE){
                if (q.peek() == null || q.peek() == Integer.MIN_VALUE){
                    // two consequetive markers denote end of BFS
                    break;
                } else {
                    // single marker is indication for level increment
                    level++;
                    q.offer(Integer.MIN_VALUE);
                    continue; 
                } 
            }

            int r = room/numCol;
            int c = room%numCol; 

            // walls and unvisited rooms are never added to queue. 

            List<Integer> neighbours = getValidNeighbours(rooms, r, c,numRow,numCol);
            for(Integer neighbour : neighbours){
                // update distance n add to queue
                int nR = neighbour/numCol;
                int nC = neighbour%numCol;
                rooms[nR][nC] = level;
                q.offer(neighbour); 
            }    
    }
}

public List<Integer> getValidNeighbours(int[][] rooms, int r, int c, int nRow, int nCol){
    List<Integer> validNeighbours = new LinkedList<>();
    if ( (c+1) < nCol && rooms[r][c+1] != -1 && rooms[r][c+1] != 0){ // left
        if (rooms[r][c+1] == Integer.MAX_VALUE){
            // its unvisited
            validNeighbours.add(r * nCol + (c+1));
        }
    }

    if ( (c-1) >= 0 && rooms[r][c-1] != -1 && rooms[r][c-1] != 0){ // right
        if (rooms[r][c-1] == Integer.MAX_VALUE){
            // its unvisited
            validNeighbours.add(r * nCol + (c-1));
        }
    }

    if ( (r-1) >= 0 && rooms[r-1][c] != -1 && rooms[r-1][c] != 0){ // up
        if (rooms[r-1][c] == Integer.MAX_VALUE){
            // its unvisited
            validNeighbours.add((r-1) * nCol + (c));
        }
    }

    if ( (r+1) < nRow && rooms[r+1][c] != -1 && rooms[r+1][c] != 0){ // down
        if (rooms[r+1][c] == Integer.MAX_VALUE){
            // its unvisited
            validNeighbours.add((r+1) * nCol + (c));
        }
    }
    return validNeighbours;
}

}
