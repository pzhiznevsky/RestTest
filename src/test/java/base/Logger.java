package base;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;

import org.testng.Assert;
import org.testng.Reporter;

public class Logger implements ILogger {

    private final int defaultLogLevel = Severity.INFO;

    protected static java.util.logging.Logger perfLogger;

    protected static Logger defaultLogger = new Logger();

    protected int severity;

    public class Entry implements IEntry {

        protected int severity;

        protected String message;

        protected Throwable exception;

        public Entry(int severity, String message, Throwable exception) {
            this.severity = severity;
            this.message = message;
            this.exception = exception;
        }

        @Override
        public int getSeverity() {
            return severity;
        }

        @Override
        public String getMessage() {
            return message;
        }

        @Override
        public Throwable getException() {
            return exception;
        }

        @Override
        public String toString() {
            return toString(true);
        }

        public String toString(boolean doSnapshot) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");
            Date time = Calendar.getInstance().getTime();

            String currentDate = dateFormat.format(time);

            StringBuffer buffer = new StringBuffer(currentDate);

            buffer.append(severityToString(severity)).append(": ");

            if (message != null) {
                buffer.append(message);
            }

            if (exception != null) {
                if (message != null) {
                    buffer.append(", ");
                }
                buffer.append(exception);
            }

            return buffer.toString();
        }
    }


    public class PerfFormatter extends java.util.logging.Formatter {
        private final String lineSeparator = System.getProperty("line.separator");
        @Override
        public String format(java.util.logging.LogRecord record) {
            String message = formatMessage(record);
            SimpleDateFormat dateFormat = new SimpleDateFormat("[yyyy-MM-dd HH:mm:ss]");
            Date time = Calendar.getInstance().getTime();
            StringBuffer sb = new StringBuffer(dateFormat.format(time));
            sb.append(record.getLevel().getName());
            sb.append(": ");
            sb.append(message);
            sb.append(lineSeparator);
            return sb.toString();
        }
    }

    /*
     *
     */
    public static String severityToString(int severity) {
        switch (severity) {
            case Severity.WARNING:
                return "WARNING";
            case Severity.INFO:
                return "INFO";
            case Severity.PERFORMANCE:
                return "PERFORMANCE";
            case Severity.DEBUG:
                return "DEBUG";
            case Severity.ALL:
                return "ALL";
        }

        return null;
    }

    public static final String stackTrace(Throwable ex) {
        ByteArrayOutputStream ostr = new ByteArrayOutputStream();
        ex.printStackTrace(new PrintWriter(ostr, true));
        String text = ostr.toString();

        return text;
    }

    public static Logger getDefault() {
        return Logger.defaultLogger;
    }

    private Logger() {
        this.severity = defaultLogLevel;
        try {
            perfLogger = java.util.logging.Logger.getLogger("Performance");
            java.util.logging.FileHandler perfHandler = new java.util.logging.FileHandler("performance%u.output", true);
            perfHandler.setFormatter(new PerfFormatter());
            perfLogger.addHandler(perfHandler);

            perfLogger.setUseParentHandlers(false);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void log(IEntry entry) {
        Reporter.log(entry.toString());
        System.out.println(entry.toString());
    }

    public void log(int severity, String message, Throwable exception) {
        if (this.severity < severity)
            return;

        if (severity == Severity.EXCEPTION){
            Assert.fail(message, exception);
        } else {
            log(new Entry(severity, message, exception));
        }
    }

    public void log(int severity, String message) {
        log(severity, message, null);
    }

    public void log(int severity, Throwable exception) {
        log(severity, null, exception);
    }

    public void warning(String message, Throwable exception) {
        log(Severity.WARNING, message, exception);
    }

    public void info(String message, Throwable exception) {
        log(Severity.INFO, message, exception);
    }

    public void all(String message, Throwable exception) {
        log(Severity.ALL, message, exception);
    }

    private class PerformanceLevel extends Level{
        private static final long serialVersionUID = 1L;

        protected PerformanceLevel() {
            super("PERFORMANCE",1488);
        }
    }
    private final PerformanceLevel oLevel = new PerformanceLevel();

    public void performance(String message, long startTime, Throwable exception) {
        double time = System.currentTimeMillis() - startTime;
        System.out.println(new Entry(Severity.PERFORMANCE, time/1000 + ", Sec takes to " + message, exception).toString());
        perfLogger.log(oLevel, time/1000 + ", Sec takes to " + message);
    }

    public void performance(String message, Throwable exception) {
        System.out.println(new Entry(Severity.PERFORMANCE, message, exception).toString());
        perfLogger.log(oLevel, message);
    }

    public void debug(String message, Throwable exception) {
        log(Severity.DEBUG, message, exception);
    }

    public void warning(String message) {
        warning(message, null);
    }

    public void info(String message) {
        info(message, null);
    }

    public void performance(String message, long startTime) {
        performance(message, startTime, null);
    }

    public void performance(String message) {
        performance(message, null);
    }

    public void debug(String message) {
        debug(message, null);
    }

    @Override
    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public void exception(String message, Throwable exception){
        log(Severity.EXCEPTION, message, exception);
    }

    public void exception(String message){
        log(Severity.EXCEPTION, message, null);
    }

}
