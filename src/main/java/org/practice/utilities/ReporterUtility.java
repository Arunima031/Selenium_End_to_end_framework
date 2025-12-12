package org.practice.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

public class ReporterUtility {
    private static ExtentReports extent;
    private ReporterUtility() {}
    public static synchronized ExtentReports generateReport() {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/reports";
            File reportDir = new File(reportPath);
            if (!reportDir.exists()) {
                reportDir.mkdirs();
            }
            String filePath = reportDir + "/automation.html";
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(filePath);
            sparkReporter.config().setReportName("Automation Report");
            sparkReporter.config().setDocumentTitle("Automation Report for application");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setKeepLastRetryOnly(true);
            extent.setSystemInfo("Automated By", "Arunima");
        }
            return extent;
    }
}





