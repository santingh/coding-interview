class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        
        Deque<Integer> stack = new ArrayDeque<>();
        int[] output = new int[temperatures.length];
        
        for (int i = 1; i < temperatures.length; i++){
            
            if (temperatures[i] > temperatures[i-1]){
                // curr temp is greater than last day
                output[i-1] = 1; 
                
                // Check if curr temp is greater than days on stack 
                // if yes keep updating the warmer days untill stack is empty
                
                while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                    output[stack.peek()] = i - stack.remove();
                }
                
            } else {
                // push i-1
                stack.push(i-1); // wait for warmer day
            }
        }
        output[temperatures.length - 1] = 0;
        return output;
        
    }
}
