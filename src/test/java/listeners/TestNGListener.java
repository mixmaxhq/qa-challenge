package listeners;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class TestNGListener implements IInvokedMethodListener {
    private static final Logger testngLogger = LogManager.getLogger(TestNGListener.class);

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        try {
            testngLogger.info("TestNG Before Invocation calisti..");
        } catch (Throwable t) {
            testngLogger.error(ExceptionUtils.getMessage(t));
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        try {
            testngLogger.info("TestNG After Invocation calisti..");
        } catch (Throwable t) {
            testngLogger.error(ExceptionUtils.getMessage(t));
        }
    }
}