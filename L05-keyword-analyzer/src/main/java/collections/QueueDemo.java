package collections;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//FIFO
public class QueueDemo {

    public static void main(String[] args) {
        Queue<String> queue = new PriorityQueue<>(Comparator.reverseOrder());
//        Queue<String> queue = new LinkedList<>();

        queue.add("B");
        queue.add("Z");
        queue.add("C");


        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());


    }
}
