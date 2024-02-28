class MovingAverage {
    
    int windowSize;
    List<Integer> window;
    int tail = -1; 
    int latestSum = 0;

    public MovingAverage(int size) {
        this.windowSize = size;
        this.window = new LinkedList<>();
    }
    
    public double next(int val) {
        
        if (tail == -1){
            // this is the first element
            tail = 0;
            window.add(0,val);
            latestSum = val;
            return Double.valueOf(val);  
        }
        
        int insertPos = (tail + 1) % windowSize;
        tail = insertPos;
        if (window.size() < windowSize){
            // window is still not full
            window.add(insertPos,val); 
            latestSum = (latestSum + val);
            return (latestSum * 1.0) /window.size();
            
        } else {
            // window is full, slide it to move first element in 
            int old = window.get(insertPos);
            window.set(insertPos,val);
            latestSum = (latestSum - old + val);
            return (latestSum * 1.0)/windowSize;
        }
        
        
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
