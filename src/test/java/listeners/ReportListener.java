package listeners;

import base.context.LocalDriverContext;
import com.aventstack.extentreports.MediaEntityBuilder;
import lombok.SneakyThrows;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reporting.ExtentManager;
import reporting.ExtentTestManager;
import utils.ScreenshotTaker;

public class ReportListener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    private static String getTestName(ITestResult iTestResult) {
        return iTestResult.getTestClass().getRealClass().getSimpleName();
    }

    @Override
    public synchronized void onTestStart(ITestResult iTestResult) {
        System.out.println("On Test Start");
    }

    @Override
    public synchronized void onTestSuccess(ITestResult iTestResult) {
        System.out.println("On Test Sucess");
        ExtentTestManager.getTest().pass("Test Passed..");
    }

    @SneakyThrows
    @Override
    public synchronized void onTestFailure(ITestResult iTestResult) {
        System.out.println("On Test Failure");
        if(iTestResult.getTestClass().getName().contains("api")){
            ExtentTestManager.getTest().fail("Test Failed !<br>" + iTestResult.getTestClass().getName()+"<br>"+iTestResult.getThrowable().getMessage());
        }
        else {
            ExtentTestManager.getTest().fail("Test Failed !",MediaEntityBuilder.createScreenCaptureFromBase64String(
                    ScreenshotTaker.takeBase64Screenshot(
                            getTestName(iTestResult),getTestMethodName(iTestResult)
                    )).build());
        }
    }

    @Override
    public synchronized void onTestSkipped(ITestResult iTestResult) {
        System.out.println("On Test Skipped");
        ExtentTestManager.getTest().skip("Test skipped..");
    }

    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println(" Test Percentage");
    }

    @Override
    public synchronized void onStart(ITestContext iTestContext) {
        System.out.println("On Test Start");
        iTestContext.setAttribute("WebDriver", LocalDriverContext.getRemoteWebDriver());
    }

    @Override
    public synchronized void onFinish(ITestContext iTestContext) {
        System.out.println("On Test Finish");
        ExtentManager.extent.flush();
    }
}
