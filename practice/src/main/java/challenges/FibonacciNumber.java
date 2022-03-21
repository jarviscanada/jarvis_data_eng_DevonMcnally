package challenges;

public class FibonacciNumber {


    /**
     * Time Complexity: O(2^n);
     *
     */

        public int fib(int n) {

            if (n == 0) return 0;
            if (n == 1) return 1;

            int a =0;
            int b =1;


            return fib(n - 1) + fib(n -2);


    }

}
