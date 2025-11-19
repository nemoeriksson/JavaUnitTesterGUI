package Examples;

import se.umu.cs.unittest.TestClass;

/**
 * An example test class that validates that
 * the unit tester catches different types
 * of errors.
 *
 * @author c24nen
 * @version 25.11.19
 */
public class ErroringTest implements TestClass {
    // Should fail
    public boolean test() {
        throw new NullPointerException("Expected NullPointerException");
    }

    // Should fail
    public boolean testRuntimeException() {
        throw new RuntimeException("Expected RuntimeException");
    }

    // Should fail
    public boolean testIndexOutOfBoundsException() {
        throw new IndexOutOfBoundsException("Expected IndexOutOfBoundsException");
    }
}
