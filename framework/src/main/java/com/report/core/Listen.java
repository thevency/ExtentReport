package com.report.core;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;

public class Listen {

	@BeforeSuite
    public synchronized void getInstanceForReport(){
        ExtentManager.getInstance();
//        System.out.println("I'm creating the SUITE ");
    }

    @BeforeClass
    public synchronized void createMainNodeForReport(){
        ExtentManager.createMainNode(getClass().getName());
//        System.out.println("I'm creating the NODE:  " + getClass().getName());
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

}