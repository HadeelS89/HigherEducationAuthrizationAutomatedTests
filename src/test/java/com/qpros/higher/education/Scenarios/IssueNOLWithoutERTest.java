package com.qpros.higher.education.Scenarios;

import com.qpros.common.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.helpers.RetryAnalyzer;
import com.qpros.pages.authorization_pages.LoginPage;
import com.qpros.pages.higher.education.admin.AQACSecretary;
import com.qpros.pages.higher.education.admin.DDActions;
import com.qpros.pages.higher.education.admin.PCActions;
import com.qpros.pages.higher.education.client.HEProgram;
import com.qpros.pages.higher.education.client.SubstantiveChangePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.qpros.reporting.Listeners.class)
public class IssueNOLWithoutERTest extends Base {
    LoginPage loginPage;
    HEProgram myProgram;
    DDActions ddActions;
    PCActions pcActions;
    AQACSecretary aQACSecretary;
    SubstantiveChangePage substantiveChangePage;


    @Test(description = "Submit Change of Partnerships / New Partnerships  for existing provider ",
            retryAnalyzer = RetryAnalyzer.class, priority = 0)
    public void changePartnerShip() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signIn(ReadWriteHelper.readCredentialsXMLFile("HEProvider2",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("HEProvider2", "password"), "HigherApplicantURL");

        //Create program and set configurations and team
        substantiveChangePage = new SubstantiveChangePage(driver);
        substantiveChangePage.changePartnerShip();
        // Assert.assertTrue( myProgram.getThankYou().isDisplayed());
        myProgram = new HEProgram(driver);
        myProgram.addProgramToFile();

    }
    //@Test(description = "Submit new program for existing provider ",
      //      retryAnalyzer = RetryAnalyzer.class,priority = 0)
    public void submitProgramExistingProv() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signIn(ReadWriteHelper.readCredentialsXMLFile("HEProvider2",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("HEProvider2", "password"),"HigherApplicantURL");

        //Create program and set configurations and team
        myProgram = new HEProgram(driver);
        myProgram.applyForProgram();
        //ActionsHelper.waitForExistance(myProgram.getThankYouTag(),30);
        //Assert.assertTrue( myProgram.getThankYouTag().isDisplayed());
        myProgram.addProgramToFile();


    }


    @Test(description = "Assign to PC ",
            retryAnalyzer = RetryAnalyzer.class,priority = 1)
    public void assignProgramToProvisionManager() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("DivisionDirector",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("DivisionDirector", "password"),"HigherAdminURL");

        //Create program and set configurations and team
        ddActions = new DDActions(driver);
        ddActions.findProgram(ReadWriteHelper.getHEApplication());
        ddActions.assignApplication("Hadeel Salameh");

        Assert.assertTrue( ddActions.getSuccessLabel().isDisplayed());


    }
    @Test(description = "Start application and send technical report  by PC ",
            retryAnalyzer = RetryAnalyzer.class,priority = 2)
    public void startReviewSendTechReportPC() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        pcActions = new PCActions(driver);
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.startReviewApplication();

        ActionsHelper.waitForExistance(pcActions.getSuccessStartReviewText(), 30);
        Assert.assertTrue(pcActions.getSuccessStartReviewText().isDisplayed());
        pcActions.getOkButton().get(0).click();

        // pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.sendTechnicalReport();
        ActionsHelper.waitForExistance(pcActions.getSuccessStartReviewText(), 40);
        Assert.assertTrue(pcActions.getSuccessStartReviewText().isDisplayed());

    }

    @Test(description = "Proceed without ER ",
            retryAnalyzer = RetryAnalyzer.class,priority = 4)
    public void proceedWithoutER() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("DivisionDirector",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("DivisionDirector", "password"),"HigherAdminURL");

        //Create program and set configurations and team
        ddActions = new DDActions(driver);
        ddActions.findProgram(ReadWriteHelper.getHEApplication());
        ddActions.proceedWithoutER();

        Assert.assertTrue( ddActions.getSuccessLabel().isDisplayed());
        Thread.sleep(3000);
        ddActions.getOkButton().get(0).click();
        Thread.sleep(3000);
        ActionsHelper.waitForListExistance(ddActions.getOkButton(),40);

        ddActions.getOkButton().get(0).click();
        ddActions.findProgram(ReadWriteHelper.getHEApplication());
        Thread.sleep(2000);
        ddActions.proceedToAQAC();
    }



    @Test(description = "Schedule Meeting by AQAC Secretary",
            retryAnalyzer = RetryAnalyzer.class,priority = 5)
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
        Thread.sleep(3000);
        Assert.assertTrue( aQACSecretary.getScheduleMeetingBtn().get(1).isDisplayed());


    }
    @Test(description = "Issue NOL by AQAC secretary ",
            retryAnalyzer = RetryAnalyzer.class,priority = 6)
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


    @Test(description = "Send NOL without payment by PC",
            retryAnalyzer = RetryAnalyzer.class,priority = 7)
    public void sendNOLWithoutPayment() throws Exception {

        loginPage = new LoginPage(driver);

        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        pcActions = new PCActions(driver);
//        pcActions.findProgram(ReadWriteHelper.getHEApplication());
//        pcActions.requestForPayment();
//        Thread.sleep(3000);
//        pcActions.getOkButton().get(0).click();
        Thread.sleep(2000);
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.sendNOLWithoutPayment();


    }

    @Test(description = "Check program Status",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class,priority = 8)
    public void getProgramStatus() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signIn(ReadWriteHelper.readCredentialsXMLFile("HEProvider2",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("HEProvider2", "password"), "HigherApplicantURL");

        //Create program and set configurations and team
        myProgram = new HEProgram(driver);
        myProgram.getProgramStatus();
        System.out.println("Check Status " + myProgram.
                getProgramStatusField().getText());
        Assert.assertTrue(myProgram.
                getProgramStatusField().getText().equalsIgnoreCase("Completed"));


    }
}

