import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModificationDemo {

    public static void main(String[] args) {
       
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        
       
        try {
          
            for (int number : numbers) {
                System.out.println(number);
                if (number == 2) {
                    numbers.remove(Integer.valueOf(2)); 
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Caught ConcurrentModificationException! Reason: " + e.getMessage());
        }
        
      
        List<Integer> numbers2 = new ArrayList<>();
        numbers2.add(1);
        numbers2.add(2);
        numbers2.add(3);
        numbers2.add(4);
        
       
        Iterator<Integer> iterator = numbers2.iterator();
        while (iterator.hasNext()) {
            int number = iterator.next();
            System.out.println(number);
            if (number == 2) {
                iterator.remove(); 
            }
        }
        
        System.out.println("After safe removal using Iterator: " + numbers2);
    }
}
