package base;

public interface ILogger {
    interface Severity {
        int EXCEPTION = 0;

        int WARNING = 2;

        int INFO = 3;

        int PERFORMANCE = 4;

        int DEBUG = 5;

        int ALL = Integer.MAX_VALUE;
    }

    interface IEntry {
        int getSeverity();

        String getMessage();

        Throwable getException();

        String toString();
    }

    void setSeverity(int severity);

    void log(IEntry entry);
}