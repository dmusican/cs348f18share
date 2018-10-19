import java.util.ArrayList;

public class Printing {
    public static void main(String[] args) {
        ArrayList<Integer> myList = new ArrayList<>();
        int size = 1000;
        for (int i=0; i < size; i++) {
            myList.add(i);
        }

        myList.stream().forEach(item -> System.out.println(item));
        

    }
}
