package Core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * A class containing all relevant information
 * about a test class.
 *
 * @author c24nen
 * @version 25.11.17
 */
public class TestInfo {
    private Class<?> testClass;
    private Constructor<?> constructor;
    private Method setup;
    private Method tearDown;
    private List<Method> testMethods;

    public TestInfo(Class<?> testClass) {
        this.testClass = testClass;

        // Find constructor
        for (Constructor<?> constructor : testClass.getConstructors()) {
            if (constructor.getParameterCount() == 0) {
                this.constructor = constructor;
                break;
            }
        }

        // Categorize methods
        testMethods = new ArrayList<>();

        for (Method method : testClass.getDeclaredMethods()) {
            if (method.getParameterCount() != 0) continue;

            String methodName = method.getName();

            if (methodName.equals("setUp")) {
                setup = method;
            } else if (methodName.equals("tearDown")) {
                tearDown = method;
            } else if (methodName.startsWith("test") && method.getReturnType() == boolean.class) {
                testMethods.add(method);
            }
        }
    }

    // Public methods

    /**
     * Gets the class information.
     *
     * @return Class
     */
    public Class<?> getTestClass() { return testClass; }

    /**
     * Gets the setup method for a test class. Will return
     * null if no setup method is defined for the class.
     *
     * @return Method, null if no setup method is defined
     */
    public Method getSetup() { return setup; }

    /**
     * Gets the teardown method for a test class. Will return
     * null if no teardown method is defined for the class.
     *
     * @return Method, null if no teardown method is defined
     */
    public Method getTearDown() { return tearDown; }

    /**
     * Gets all test methods.
     *
     * @return A list of methods
     */
    public List<Method> getTestMethods() { return testMethods; }

    /**
     * Gets the constructor for the test class.
     *
     * @return A constructor
     */
    public Constructor<?> getConstructor() { return constructor; }
}
