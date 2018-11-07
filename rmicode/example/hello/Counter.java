package example.hello;

import java.io.Serializable;


public class Counter implements Serializable {
    private int value = 0;
    public void add() {
        value++;
    }
    public int getValue() {
        return value;
    }
}
