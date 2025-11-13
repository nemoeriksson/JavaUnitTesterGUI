package Core;

import java.util.List;

public class TestInfoCollection {
    private List<TestInfo> testInfoList;

    public TestInfoCollection(List<TestInfo> testInfoList) {
        this.testInfoList = testInfoList;
    }

    public boolean isValidTest(String className) {
        return getTestInfo(className) != null;
    }

    public TestInfo getTestInfo(String className) {
        for (TestInfo testInfo : testInfoList) {
            if (testInfo.getTestClass().getName().equals(className))
                return testInfo;
        }

        return null;
    }
}
