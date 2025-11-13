package Core;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestExecutor extends SwingWorker<List<TestResult>, Object> {
    private TestInfo testInfo;

    public TestExecutor(TestInfo testInfo) {
        this.testInfo = testInfo;
    }

    // Public methods

    @Override
    public List<TestResult> doInBackground() {
        List<TestResult> testResults = new ArrayList<>();

        try {
            Method setup = testInfo.getSetup();
            Method tearDown = testInfo.getTearDown();

            for (Method testMethod : testInfo.getTestMethods()) {
                Object instance = testInfo.getConstructor().newInstance();

                // Run setup
                if (setup != null) {
                    setup.invoke(instance);
                }

                TestResult result = runTest(instance, testMethod);
                testResults.add(result);

                // Run teardown
                if (tearDown != null)
                    tearDown.invoke(instance);
            }

            return testResults;
        } catch (InvocationTargetException e) {
            return addInternalErrorResult(testResults, String.format(
                    "Error when attempting to call setUp() or tearDown() methods for %s",
                    testInfo.getTestClass().getName()
            ));
        } catch (IllegalAccessException e) {
            return addInternalErrorResult(testResults, String.format(
                    "Invalid access permissions to instantiate or invoke methods for %s",
                    testInfo.getTestClass().getName()
            ));
        } catch (InstantiationException e) {
             return addInternalErrorResult(testResults, String.format(
                    "Unable to create instance of class %s",
                    testInfo.getTestClass().getName()
            ));
        }
    }

    // Private methods

    private TestResult runTest(Object instance, Method testMethod) {
        try {
            boolean succeeded = (boolean)testMethod.invoke(instance);

            return new TestResult(
                    succeeded ? TestResult.Status.SUCCEEDED : TestResult.Status.FAILED,
                    String.format("Test %s - %s",
                            testMethod.getName(),
                            succeeded ? "PASSED" : "FAILED"
                    )
            );
        }
        // Test method threw error
        catch (InvocationTargetException e) {
            return new TestResult(TestResult.Status.ERRORED, String.format(
                    "Test %s - FAILED %s", testMethod.getName(), e.getCause().getMessage()
            ));
        }
        // Critical error
        catch (IllegalAccessException e) {
            return new TestResult(TestResult.Status.INTERNAL_ERROR, String.format(
                    "Invalid access permissions to instantiate or invoke methods for %s",
                    testInfo.getTestClass().getName()
            ));
        }
    }

    private List<TestResult> addInternalErrorResult(List<TestResult> testResults, String message) {
        testResults.add(new TestResult(
                TestResult.Status.INTERNAL_ERROR,
                message
        ));

        return testResults;
    }
}
