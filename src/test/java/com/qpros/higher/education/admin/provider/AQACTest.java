package com.qpros.higher.education.admin.provider;

import com.qpros.common.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.helpers.RetryAnalyzer;
import com.qpros.pages.authorization_pages.LoginPage;
import com.qpros.pages.higher.education.admin.AQACSecretary;
import com.qpros.pages.higher.education.admin.DDActions;
//import com.qpros.scholarship.admin.applicationsScenarios.AcceptAcknowledgeApplsTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.qpros.reporting.Listeners.class)
public class AQACTest extends Base {
    LoginPage loginPage;
    DDActions ddActions;
    AQACSecretary aQACSecretary;

    // read fields from excel file
    final String ER_numbers = ReadWriteHelper.readFromExcel
            ("ERHireEducation", "ER", "Number of ER");

    @Test(description = "Schedule Meeting by AQAC Secretary",
            retryAnalyzer = RetryAnalyzer.class)
    public void scheduleMeeting() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("AQACSecretary",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("AQACSecretary", "password"),"HigherAdminURL");

        //Create program and set configurations and team
        aQACSecretary = new AQACSecretary(driver);

        aQACSecretary.scheduleMeeting(ReadWriteHelper.getHEApplication());
        ActionsHelper.waitForListExistance(aQACSecretary.getScheduleMeetingBtn(),80);
       Assert.assertTrue( aQACSecretary.getScheduleMeetingBtn().get(1).isDisplayed());


    }
    @Test(description = "Issue NOL by AQAC secretary ",
            retryAnalyzer = RetryAnalyzer.class)
    public void issueNOL() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("AQACSecretary",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("AQACSecretary", "password"),"HigherAdminURL");

        //Create program and set configurations and team
        aQACSecretary = new AQACSecretary(driver);

        aQACSecretary.findProgram(ReadWriteHelper.getHEApplication());
        aQACSecretary.issueNOL();
       // ActionsHelper.waitForListExistance(aQACSecretary.getScheduleMeetingBtn(),80);
        //Assert.assertTrue( aQACSecretary.getScheduleMeetingBtn().get(1).isDisplayed());



    }
    @Test(description = "Issue NAL by AQAC secretary ",
            retryAnalyzer = RetryAnalyzer.class)
    public void issueNAL() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("AQACSecretary",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("AQACSecretary", "password"),"HigherAdminURL");

        //Create program and set configurations and team
        aQACSecretary = new AQACSecretary(driver);

        aQACSecretary.findProgram(ReadWriteHelper.getHEApplication());
        aQACSecretary.issueNAL();
        // ActionsHelper.waitForListExistance(aQACSecretary.getScheduleMeetingBtn(),80);
        //Assert.assertTrue( aQACSecretary.getScheduleMeetingBtn().get(1).isDisplayed());



    }
}

