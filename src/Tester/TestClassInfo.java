package Tester;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestClassInfo {
    private Class<?> testClass;
    private Constructor<?> constructor;
    private Method setup;
    private Method tearDown;
    private List<Method> testMethods;

    public TestClassInfo(Class<?> testClass) {
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
            } else if (methodName.startsWith("test")) {
                testMethods.add(method);
            }
        }
    }

    // Public methods

    public Class<?> getTestClass() { return testClass; }
    public Method getSetup() { return setup; }
    public Method getTearDown() { return tearDown; }
    public List<Method> getTestMethods() { return testMethods; }
    public Constructor<?> getConstructor() { return constructor; }

    // Private methods

}
