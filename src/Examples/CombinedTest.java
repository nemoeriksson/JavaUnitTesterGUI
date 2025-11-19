package Examples;

import se.umu.cs.unittest.TestClass;

/**
 * An example test class that validates
 * that the unit tester can handle different
 * types of results as expected.
 *
 * @author c24nen
 * @version 25.11.19
 */
public class CombinedTest implements TestClass {
    private TestedClass instance;
    private final int initialValue = 10;

    public void setUp() {
        instance = new TestedClass(initialValue);
    }

    // Should pass
    public boolean testPassing() {
        return true;
    }

    // Should pass
    public boolean testSetup() {
        return instance != null && instance.getValue() == initialValue;
    }

    // Should fail
    public boolean testFailing() {
        return false;
    }

    // Should fail
    public boolean testErroring() {
        throw new RuntimeException("Expected RuntimeException");
    }
}
