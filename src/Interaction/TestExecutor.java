package Interaction;

import Core.TestInfo;
import Core.TestResult;
import GUI.ContentDisplay;
import GUI.ScrollablePanel;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A utility class to execute test within
 * test classes.
 *
 * @author c24nen
 * @version 25.11.17
 */
public class TestExecutor extends SwingWorker<List<TestResult>, Object> {
    private TestInfo testInfo;
    private ContentDisplay contentDisplay;

    public TestExecutor(TestInfo testInfo, ContentDisplay contentDisplay) {
        this.testInfo = testInfo;
        this.contentDisplay = contentDisplay;
    }

    // Public methods

    /**
     * Attempts to run all test methods for a test class.
     * Will call setUp() before each test method and
     * tearDown() after it has completed.
     *
     * @return A list of test results
     */
    @Override
    public List<TestResult> doInBackground() {
        List<TestResult> testResults = new ArrayList<>();

        try {
            Method setup = testInfo.getSetup();
            Method tearDown = testInfo.getTearDown();

            // Run all test methods
            int testNum = 1;
            for (Method testMethod : testInfo.getTestMethods()) {
                Object instance = testInfo.getConstructor().newInstance();

                // Run setup
                if (setup != null) {
                    setup.invoke(instance);
                }

                // Run test
                TestResult result = runTestMethod(instance, testMethod, testNum);
                testResults.add(result);

                // Run teardown
                if (tearDown != null)
                    tearDown.invoke(instance);

                testNum++;
            }

            return testResults;
        }
        // Critical errors
        catch (InvocationTargetException e) {
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

    /**
     * Updates the GUI with relevant test results
     * after all tests have been executed.
     */
    @Override
    public void done() {
        List<TestResult> testResults;

        // Wait for doInBackground to complete
        try {
            testResults = get();
        } catch (InterruptedException|ExecutionException e) {
            throw new RuntimeException(e);
        }

        // Reset any previous output
        ScrollablePanel scrollablePanel = contentDisplay.getScrollablePanel();
        scrollablePanel.reset();

        int successfull = 0;
        int failed = 0;
        int errored = 0;
        boolean encounteredInternalError = false;

        // Count result types for summary view & display result info
        for (TestResult result : testResults) {
            // Count result status types
            switch (result.getStatus()) {
                case SUCCEEDED -> successfull++;
                case FAILED -> failed++;
                case ERRORED -> errored++;

                // Display error about the internal error
                case INTERNAL_ERROR -> {
                    encounteredInternalError = true;
                    contentDisplay.showPanel(ContentDisplay.DisplayType.MESSAGE);
                    contentDisplay.getMessageBox().setMessage(
                            "An internal error occurred",
                            result.getMessage());
                }
            }

            // Append test result information to ScrollPane
            scrollablePanel.createNewLabel(result.getMessage());
        }

        // Display summary details
        if (!encounteredInternalError) {
            contentDisplay.getSummaryPanel().setLabels(successfull, failed, errored);
            contentDisplay.showPanel(ContentDisplay.DisplayType.RESULT);
        }
    }

    // Private methods

    /**
     * Runs a test method on a test class instance and
     * returns relevant information as a TestResult.
     *
     * @param instance An instance of the test class
     * @param testMethod A test method
     * @param testNum Test index (used in result message)
     * @return A TestResult
     */
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
                    "Test %d: %s - ERROR \n%s", testNum, testMethod.getName(), e.getCause()
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

    /**
     * Creates a new internal error with a specified message
     * and appends it to a list of test results.
     *
     * @param testResults A list of test results to append to
     * @param message Error message
     * @return A list of test results
     */
    private List<TestResult> addInternalErrorResult(List<TestResult> testResults, String message) {
        testResults.add(new TestResult(
                TestResult.Status.INTERNAL_ERROR,
                message
        ));

        return testResults;
    }
}
