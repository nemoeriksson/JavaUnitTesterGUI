package Examples;

public class TestedClass {
    private int value;

    public TestedClass(int value) {
        this.value = value;
    }

    public void increase() { this.value++; }

    public void decrease() { this.value--; }

    public int getValue() { return value; }
}
