package com.report.core;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentXReporter;
import org.testng.ITestResult;

import java.io.File;
import java.lang.reflect.Method;

//OB: ExtentReports extent instance created here. That instance can be reachable by getReporter() method.

public class ExtentManager {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	private static ThreadLocal<ExtentTest> nodeClass = new ThreadLocal<ExtentTest>();

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

		ExtentTest child =  nodeClass.get().createNode(method.getName()).log(Status.INFO, MarkupHelper.createLabel("===" + method.getName() + "===", ExtentColor.BLUE));

		test.set(child);
	}
	protected static void createSubNodeClass(String className){

		ExtentTest childClass =  parentTest.get().createNode(className);

		nodeClass.set(childClass);
	}
	protected static void createMainNode(String mainNodeName){

		ExtentTest parent = extent.createTest(mainNodeName);

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