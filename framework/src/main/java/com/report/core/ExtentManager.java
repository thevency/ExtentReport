package com.report.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.ITestResult;

import java.io.File;
import java.lang.reflect.Method;

//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.

public class ExtentManager {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance("extent.html");
		return extent;
	}

	private static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
		htmlReporter.loadXMLConfig(new File("C:\\Users\\trinhh\\Documents\\OnIntelliJ\\ExtendReport\\framework\\src\\main\\resources\\extent-config.xml"));
//		htmlReporter.setAppendExisting(true);

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
//		extent.setAnalysisStrategy(AnalysisStrategy.TEST);

		return extent;
	}

	protected static void createSubNode(Method method){

		ExtentTest child =  parentTest.get().createNode(method.getName());

		test.set(child);
	}
	protected static void createMainNode(String classname){

		ExtentTest parent = extent.createTest(classname);

		parentTest.set(parent);
	}

	protected static void generateReport(ITestResult result, Method method){

		if(result.getStatus() == ITestResult.FAILURE){
			test.get().fail(result.getThrowable());
		}else if(result.getStatus() ==ITestResult.SKIP){
			test.get().skip(result.getThrowable());
		}else {
			test.get().pass("Test Methods " + method.getName() + " Passed" );
		}

		extent.flush();
	}
}