import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
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
//        long start = System.currentTimeMillis();
//        double answer = IntStream.range(0, 10_000_000)
////                                 .parallel()
//                                 .mapToDouble(item -> Math.sin(item))
//                                 .reduce(0, (a,b) -> a + b);
//        System.out.println(answer);
//        System.out.println(System.currentTimeMillis() - start);

        // Rewrite instead with RxJava
//        Observable.range(0, 10_000_000)
//                  .map(e -> Math.sin(e))
//                  .reduce(0.0, (a,b) -> a+b)
//                  .subscribe(answer -> System.out.println(answer));

        Observable.interval(1, TimeUnit.SECONDS)
                  .subscribe(item -> System.out.println(item));

        while(true);

    }
}
