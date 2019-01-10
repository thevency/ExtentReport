package com.report.core;

import org.testng.ITestContext;
import org.testng.ITestResult;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.stream.Collectors;

import static com.report.core.ConfigReport.getNodeName;

public class Listen {
    List<String> nodeName;

	@BeforeSuite
    public synchronized void getInstanceForReport(){
        ExtentManager.getInstance();
//        System.out.println("I'm creating the SUITE ");
    }

    @BeforeTest
    public synchronized void createMainNodeForReport(ITestContext iTestContext){
        ExtentManager.createMainNode(iTestContext.getName());
//        System.out.println("I'm creating the NODE:  " + getClass().getName());
    }
    @BeforeClass
    public synchronized void createSubNodeClass(){
	    ExtentManager.createSubNodeClass(getClass().getName());
        nodeName = getNodeName(getClass().getName());


    }
    @BeforeMethod
    public synchronized void createSubTestForReport(Method method){
        ExtentManager.createSubNode(method);
//        System.out.println("I'm creating: " + method.getName());
    }

    @AfterMethod
    public synchronized void createReport(ITestResult result, Method method){
        ExtentManager.generateReport(result,method);
    }

//    @AfterSuite
//    public void configureReport() throws IOException {
//        System.out.println("name name name: " + nodeName.toString());
//        String ctn = ConfigReport.configureReport("extent.html", nodeName).stream().map(n->String.valueOf(n))
//                .collect(Collectors.joining("\r\n"));
////        System.out.println(ctn);
//        ConfigReport.writeFile(ctn,"extent.html");
//    }

}