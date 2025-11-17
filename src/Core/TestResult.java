package Core;

/**
 * Utility class containing information about the
 * result of a test method being run.
 *
 * @author c24nen
 * @version 25.11.17
 */
public class TestResult {
    private Status status;
    private String message;

    /**
     * Enum representing the different states
     * of a test when completed/terminated.
     */
    public enum Status {
        SUCCEEDED,
        FAILED,
        ERRORED,
        INTERNAL_ERROR;
    }

    public TestResult (Status status, String message){
        this.status = status;
        this.message = message;
    }

    // Public methods

    /**
     * Gets the result's status
     *
     * @return A status
     */
    public Status getStatus() { return status; }

    /**
     * Gets a string with details about
     * the result of the test.
     *
     * @return A result message
     */
    public String getMessage() { return message; }
}
