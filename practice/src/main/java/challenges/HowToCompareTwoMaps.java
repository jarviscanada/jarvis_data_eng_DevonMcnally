package challenges;

//https://www.notion.so/jarvisdev/How-to-compare-two-maps-73b4eeb9f6684914a416199197fcd97e


import java.util.HashMap;
import java.util.Map;

public class HowToCompareTwoMaps {


    public static void main(String[] args) {

        Solution solution = new Solution();

        Map<Integer, Integer> m1 = new HashMap<Integer, Integer>();
        Map<Integer, Integer> m2 = new HashMap<Integer, Integer>();

        m1.put(1, 1);
        m1.put(2, 2);

        m2.put(1, 1);
        m2.put(2, 2);

        System.out.println(solution.compareMaps(m1, m2));

        m1.clear();
        m2.clear();

        m1.put(5, 67);
        m1.put(7, 2);

        m2.put(11, 7);
        m2.put(9, 5);

        System.out.println(solution.compareMaps(m1, m2));

    }

}

class Solution{
    public <K,V> boolean compareMaps(Map<K,V> m1, Map<K,V> m2){

        /**
         * Big-O: O(N)
         * The equals() method iterates through the array once while performing this operation
         */

        if(m1.equals(m2)){
            return true;
        }
        return false;
    }
}