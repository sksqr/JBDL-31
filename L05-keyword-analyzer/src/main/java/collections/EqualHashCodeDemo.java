package collections;

import java.util.HashSet;
import java.util.Set;

public class EqualHashCodeDemo {

    public static void main(String[] args) {
        Employee e1 = new Employee("Shashi",6);

        Employee e2 = new Employee("Shashi",6);

        System.out.println(e1.equals(e2));


        System.out.println(e1==e2);

        Set<Employee> set = new HashSet<>();

        set.add(e1);
        set.add(e2);

        System.out.println("Size:"+set.size());

    }
}
