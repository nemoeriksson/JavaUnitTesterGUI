package Core;

public class TestResult {
    private Status status;
    private String message;

    // Constructors

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

    public Status getStatus() { return status; }
    public String getMessage() { return message; }

    // Private methods
}
