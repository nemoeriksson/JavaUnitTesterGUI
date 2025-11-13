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

            int testNum = 1;
            for (Method testMethod : testInfo.getTestMethods()) {
                Object instance = testInfo.getConstructor().newInstance();

                // Run setup
                if (setup != null) {
                    setup.invoke(instance);
                }

                TestResult result = runTestMethod(instance, testMethod, testNum);
                testResults.add(result);

                // Run teardown
                if (tearDown != null)
                    tearDown.invoke(instance);

                testNum++;
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

    private TestResult runTestMethod(Object instance, Method testMethod, int testNum) {
        try {
            boolean succeeded = (boolean)testMethod.invoke(instance);

            return new TestResult(
                    succeeded ? TestResult.Status.SUCCEEDED : TestResult.Status.FAILED,
                    String.format("Test %d: %s - %s",
                            testNum,
                            testMethod.getName(),
                            succeeded ? "PASSED" : "FAILED"
                    )
            );
        }
        // Test method threw error
        catch (InvocationTargetException e) {
            return new TestResult(TestResult.Status.ERRORED, String.format(
                    "Test %d: %s - ERROR \n%s", testNum, testMethod.getName(), e.getCause().getMessage()
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
