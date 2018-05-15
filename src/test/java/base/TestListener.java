package base;

import org.testng.*;

public class TestListener extends TestListenerAdapter implements ISuiteListener {
    public static Logger logger = Logger.getDefault();
    private long counter = 1;
    private long countFailed = 0;
    private long countSkipped = 0;
    private final String lineSeparator = System.getProperty("line.separator");

    @Override
    public void onConfigurationFailure(ITestResult tr) {
        if (tr.getThrowable().getClass() == AssertionError.class) {
            Reporter.setCurrentTestResult(tr);
        }

        log(lineSeparator);
        log("FAILED CONFIGURATION: " + tr.getName());
        log(tr.getThrowable().toString());
        log("================================================================================" + lineSeparator);
    }
    @Override
    public void onConfigurationSkip(ITestResult tr) {
        log("SKIPPED CONFIGURATION: " + tr.getName());
        log("================================================================================" + lineSeparator);
    }
    @Override
    public void onConfigurationSuccess(ITestResult tr) {
        log("PASSED CONFIGURATION: " + tr.getName());
        log("================================================================================" + lineSeparator);
    }
    @Override
    public void onStart(ITestContext tc) {
        log(lineSeparator);
        log("================================================================================");
        log("STARTED TEST: " + tc.getName());
        log("================================================================================");

        logger.info("================================================================================");
        logger.info("STARTED TEST: " + tc.getName());
        logger.info("================================================================================" + lineSeparator);
    }
    @Override
    public void onFinish(ITestContext tc) {
        log("TEST FINISHED: " + tc.getName());
        log("Tests run: " + tc.getPassedTests().size() +
                ", Failures: " + tc.getFailedTests().size() +
                ", Skips: " + tc.getSkippedTests().size());
        if(tc.getFailedConfigurations().size() > 0) {
            log("Configurations Failures: " + tc.getFailedConfigurations().size() +
                    ", Skips: " + tc.getSkippedConfigurations().size());
        }
        log("================================================================================");

        logger.info("");
        logger.info("================================================================================");
        logger.info("================================================================================");
        logger.info(lineSeparator);
    }
    @Override
    public void onTestStart(ITestResult tr) {
        log("STARTED: " + tr.getName());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onTestFailure(ITestResult tr) {
        if (tr.getThrowable().getClass() == AssertionError.class) {
            Reporter.setCurrentTestResult(tr);
        }

        log("FAILED: " + tr.getName());
        log(tr.getThrowable().toString());
        log("================================================================================");

        logger.info("================================================================================");
        logger.info("FAILED: " + tr.getName());
        logger.info(tr.getThrowable().toString());

        countFailed++;

        logger.info("========= Current statistics:  Run: '"+(counter++)+"', Failures: '"+countFailed+"', Skips: '"+countSkipped+"'");
        logger.info("================================================================================" + lineSeparator);
    }
    @Override
    public void onTestSkipped(ITestResult tr) {
        log("SKIPPED: " + tr.getName());
        log("================================================================================" + lineSeparator);

        countSkipped++;

        logger.info("================================================================================");
        logger.info("SKIPPED: " + tr.getName());
        logger.info("========= Current statistics:  Run: '"+(counter++)+"', Failures: '"+countFailed+"', Skips: '"+countSkipped+"'");
        logger.info("================================================================================" + lineSeparator);
    }
    @Override
    public void onTestSuccess(ITestResult tr) {
        log("PASSED: " + tr.getName());
        log("================================================================================");

        logger.info("================================================================================");
        logger.info("PASSED: " + tr.getName());
        logger.info("========= Current statistics:  Run: '"+(counter++)+"', Failures: '"+countFailed+"', Skips: '"+countSkipped+"'");
        logger.info("================================================================================" + lineSeparator);
    }

    private void log(String string) {
        Reporter.log(string);
    }

    @Override
    public void onFinish(ISuite suite) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStart(ISuite suite) {
        // TODO Auto-generated method stub
    }
} 
