package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionsDemo {

    public static void main(String[] args) {
        List<Integer> integerList = new ArrayList<>();

        integerList.add(1);
        integerList.add(7);
        integerList.add(10);
        integerList.add(10);


        System.out.println(Collections.frequency(integerList,10));

    }
}
