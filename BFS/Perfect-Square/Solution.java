class Solution {
    public int numSquares(int n) {
        
        // idea is to start with breaking it down in sum of two numbers, one of which is PS.
        // then move on to finding min sum of perfect square for other number which is not PS. 
        
        // int[] PS = {1, 4, 9, 16,..}
        
        // for each PS in list identify two number such that one number is from PS list and other number is  n - PS item. 
        // Eg: 12 - (1,11), (4,8), (3,9) ... stop when other number <= 0; 
        // or when the other number too is a perfect square. (This is expected result)
        
        // Push them all to Queue and start BFS. make sure not pushing duplicates. 
        
        // poll and check, last number -> generate pair of numbers for it such that one is PS
        // for each of this list push these List to Queue. 
        
        // precreated PS list to be used. The operations on it are iterate and lookup. We will use
        // LinkedHashSet, as it perfoms contains operation in constant time, and also maintains order 
        // while iterating. 
        
        if (n < 1){
            return 0;
        }
        
        HashSet<Integer> psSet = new LinkedHashSet<>();
        int ps = 1;
        int i = 1;
        while(ps <= n){
            psSet.add(ps);
            i++;
            ps = i * i; 
        }
        
        // prepare for BFS
        Queue<Integer> q = new LinkedList<>();
        q.offer(n); q.offer(-1);
        int level = 1; 
        
        while(! q.isEmpty()){
            n = q.poll(); 
            
            // check if item is PS
            if (psSet.contains(n)){
                return level;
            }
            
            if (n == -1){
                if (q.peek() == null || q.peek() == -1){
                    return 0;
                } else {
                    q.offer(-1);
                    level = level + 1;
                    continue;
                }
            }
            
            
            // if item is not ps - need to find next set of options and add to queue
            Iterator<Integer> it = psSet.iterator();
            
            while(it.hasNext()){
                ps = it.next();
                int rem = n - ps;
                
                if (rem < 0){
                    break;
                } else {
                    if (psSet.contains(rem)){
                        return level + 1;
                    }
                    
                    q.offer(rem); 
                }
            }
            
        }
        
        return 0;
        
    }
}
