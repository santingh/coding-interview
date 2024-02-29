class Solution {
    public int openLock(String[] deadends, String target) {

        Set<String> visited = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        int level = 0;

        for (int i = 0; i < deadends.length; i++) {
            visited.add(deadends[i]);
        }
        if (visited.contains("0000")) {
            return -1;
        }

        q.offer("0000");
        q.offer("-1");
        visited.add("0000");

        while (!q.isEmpty()) {
            String cur = q.poll();

            if (cur.equals(target)) {
                return level; // found the target;
            }

            if (cur.equals("-1")) {
                if (q.peek() == null || q.peek().equals("-1")) {
                    // end of traversal
                    return -1;
                } else {
                    level++; // prepare for next set of rotations
                    q.offer("-1");
                    continue;
                }
            }

            // get options avoiding deadends and visited options
            List<String> options = getValidOptions(cur, visited);
            for (String option : options) {
                if (option.equals(target)) {
                    return level + 1;
                }
                visited.add(option);
                q.offer(option);
            }
        }

        return -1;

    }

    public List<String> getValidOptions(String cur, Set<String> visited) {
        List<String> options = new LinkedList<>();
        char[] cArray = cur.toCharArray();

        // generate one forward and one backward option for each lock.
        for (int i = 0; i < 4; i++) {
            int n = cArray[i] - '0';
            int n1 = ((n + 1) == 10) ? 0 : n + 1; // forward rotation
            int n2 = ((n - 1) == -1) ? 9 : n - 1; // backward rotation
            cArray[i] = (char) (n1 + '0');
            String forwardOption = String.valueOf(cArray);
            if (!visited.contains(forwardOption)) {
                options.add(forwardOption);
            }

            cArray[i] = (char) (n2 + '0');
            String backwardOption = String.valueOf(cArray);
            if (!visited.contains(backwardOption)) {
                options.add(backwardOption);
            }
            cArray[i] = (char) (n + '0'); // restore the value
        }

        return options;

    }

}
