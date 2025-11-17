package Core;

import java.util.List;

/**
 * A class containing information about
 * multiple test classes.
 *
 * @author c24nen
 * @version 17.11.25
 */
public class TestInfoCollection {
    private List<TestInfo> testInfoList;

    public TestInfoCollection(List<TestInfo> testInfoList) {
        this.testInfoList = testInfoList;
    }

    // Public methods

    /**
     * Checks if a specific class is a valid test class.
     *
     * @param className A string with a class' name
     * @return true if it is a valid test class, otherwise false
     */
    public boolean isValidTest(String className) {
        return getTestInfo(className) != null;
    }

    /**
     * Returns all information about a specific test class.
     * Will return null if the provided string does not
     * correlate to a valid test class.
     *
     * @param className A string with a class' name
     * @return TestInfo instance, or null if not a test class
     */
    public TestInfo getTestInfo(String className) {
        for (TestInfo testInfo : testInfoList) {
            if (testInfo.getTestClass().getName().equals(className))
                return testInfo;
        }

        return null;
    }
}
