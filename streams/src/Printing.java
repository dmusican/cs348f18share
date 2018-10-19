import java.util.ArrayList;
import java.util.stream.IntStream;

public class Printing {
    public static void main(String[] args) {
        ArrayList<Integer> myList = new ArrayList<>();
        int size = 1000;
        for (int i=0; i < size; i++) {
            myList.add(i);
        }

//        myList.parallelStream().forEach(item -> System.out.print(item + " "));

        // Go do an old assignment. Oh so short.
        IntStream.range(0, 10)
                 .parallel()
                 .mapToDouble(item -> item*Math.PI)
                 .forEach(item -> System.out.println(item));

    }
}
