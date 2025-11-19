package Examples;

import se.umu.cs.unittest.TestClass;

/**
 * An example test class that validates
 * that the unit tester catches failing
 * tests as expected.
 *
 * @author c24nen
 * @version 25.11.19
 */
public class FailingTest implements TestClass {
    // Should fail
    public boolean testFails() {
        return false;
    }
}
