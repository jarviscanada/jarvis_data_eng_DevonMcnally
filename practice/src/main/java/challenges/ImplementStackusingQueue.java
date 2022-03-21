package challenges;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackusingQueue {

    Queue<Integer> stack = new LinkedList<Integer>();
    Queue<Integer> stack2 = new LinkedList<Integer>();

    public static void main(String[] args) {
        ImplementStackusingQueue implementStackusingQueue = new ImplementStackusingQueue();

        implementStackusingQueue.MyStack();


    }



    public void MyStack() {

        stack.add(4);

        System.out.println(stack);

        stack.add(6);
        stack.add(8);

        System.out.println(stack);


        stack2.add(9);

        stack.forEach(i -> stack2.add(i));

        stack = stack2;

        System.out.println(stack);







    }

    public void push(int x) {

    }

    public int pop() {

        return 0;
    }

    public int top() {

        return 0;
    }

    public boolean empty() {

        return false;
    }


}
