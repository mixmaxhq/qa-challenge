package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.io.File;

public class ExtentManager {

    public static final ExtentReports extent = new ExtentReports();
    private static String reportFileName = "MixMaxTestAutomationResultReport.html";
    private static String macPath = System.getProperty("user.dir") + "/reports";
    private static String windowsPath = System.getProperty("user.dir") + "\\reports";
    private static String macReportFileLoc = macPath + "/" + reportFileName;
    private static String winReportFileLoc = windowsPath + "\\" + reportFileName;

    public synchronized static ExtentReports createExtentReports()
    {
        ExtentHtmlReporter reporter;
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win")){
            File directory = new File(windowsPath);
            if(!directory.exists())
                directory.mkdir();
            reporter = new ExtentHtmlReporter(winReportFileLoc);
        }
        else {
            File directory = new File(macPath);
            if(!directory.exists())
                directory.mkdir();
            reporter = new ExtentHtmlReporter(macReportFileLoc);
        }

        reporter.config().setReportName("MixMax Test Automation Report");
        extent.attachReporter(reporter);
        extent.setSystemInfo("App Name", "SauceDemo & Reqres");
        extent.setSystemInfo("Author", "Amil Uslu");
        return extent;

    }
}
