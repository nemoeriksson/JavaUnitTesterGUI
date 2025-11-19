package Examples;

import se.umu.cs.unittest.TestClass;

/**
 * An example test class that validates
 * that the unit tester handles successful
 * tests as expected.
 *
 * @author c24nen
 * @version 25.11.19
 */
public class PassingTest implements TestClass {
    private TestedClass instance;
    private final int initialValue = 10;

    public void setUp() {
        this.instance = new TestedClass(initialValue);
    }

    // Should pass
    public boolean testInit() {
        return instance != null && instance.getValue() == initialValue;
    }

    // Should pass
    public boolean testIncrease() {
        instance.increase();
        return instance.getValue() == initialValue+1;
    }

    // Should pass
    public boolean testDecrease() {
        instance.decrease();
        return instance.getValue() == initialValue-1;
    }
}
