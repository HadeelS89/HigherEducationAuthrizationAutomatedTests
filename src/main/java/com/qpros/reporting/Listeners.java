package com.qpros.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qpros.helpers.EmailHelper;
import lombok.SneakyThrows;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import javax.mail.MessagingException;
import java.security.GeneralSecurityException;

public class Listeners extends TestListenerAdapter{

    private static ExtentReports extent;

    static {
        try {
            extent = ExtentManager.createInstance();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
    ExceptionListner exceptionListener = new ExceptionListner();
    public static boolean CONSOLE;


    @Override
    public synchronized void onStart(ITestContext context) {
        if (CONSOLE) {

            System.out.println("Test Suite started!");
        }

    }

    @SneakyThrows
    @Override
    public synchronized void onFinish(ITestContext context) {

        if (CONSOLE) {
            System.out.println(("Test Suite is ending!"));

        }
        extent.flush();

        String reportPath = "src/main/resources/Reports/" + ExtentManager.reportFileName;
        EmailHelper.sendEmail("mohammedm@q-pros.net, mohammadY@q-pros.net, hadeelS@q-pros.net," +
                " khaledM@q-pros.net, rawaQ@q-pros.net, HamzahA@q-pros.net", reportPath);

//        EmailHelper sender = new EmailHelper();
//        String emails = ReadWriteHelper.ReadData( "EmailsTo" );
//        String toSendEmails = ReadWriteHelper.ReadData( "SendMail" );
//        System.out.println(emails);
//        if (!emails.equals("") && toSendEmails.equals("true")) {
//            String[] emailArray = emails.split(",");
//            for(String email: emailArray) {
//                sender.sendMail(email , ExtentManager.path + ExtentManager.reportFileName);
//            }
//        }

    }

    @Override
    public synchronized void onTestStart(ITestResult result) {
        result.getTestClass().getXmlClass().getAllParameters();
        //result.getTestClass().getXmlClass().m_xmlTest.m_suite.m_fileNam;
        if (CONSOLE) {
            System.out.println((result.getMethod().getMethodName() + " started!"));

        }
        ExtentTest extentTest = extent.createTest(result.getMethod().getDescription(),
                result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        if (CONSOLE) {
            System.out.println((result.getMethod().getMethodName() + " passed!"));
        }

        test.get().pass("Test passed");
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {
        if (CONSOLE) {
            System.out.println((result.getMethod().getMethodName() + " failed!"));
        }

        test.get().fail(exceptionListener.checkException(result.getThrowable().toString()));

    }

    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        if (CONSOLE) {
            System.out.println((result.getMethod().getMethodName() + " skipped!"));
        }
        test.get().skip(exceptionListener.checkException(result.getThrowable().toString()));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        if (CONSOLE) {
            System.out.println(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));

        }

    }
}
