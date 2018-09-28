// If f and and g

public class C {
    private int x = 0;
    private int y = 0;

    public void f() {
        x = 1;    // line A
        y = 1;    // line B
    }

    public void g() {
        int a = y;   // line C
        int b = x;   // line D
        if (b < a)
            System.out.println("ERROR");
    }
}
