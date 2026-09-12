package com.company.api.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("custom_reports/ExtentReport.html");
            spark.config().setReportName("RestAssured API Automation Report");
            spark.config().setDocumentTitle("API Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Framework", "RestAssured + TestNG");
            extent.setSystemInfo("Environment", "ReqRes");
            extent.setSystemInfo("QA", "Ankit Kumar");
        }
        return extent;
    }
}