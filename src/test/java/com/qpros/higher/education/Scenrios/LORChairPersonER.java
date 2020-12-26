package com.qpros.higher.education.Scenrios;

import com.qpros.common.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.helpers.RetryAnalyzer;
import com.qpros.pages.authorization_pages.LoginPage;
import com.qpros.pages.higher.education.admin.AQACSecretary;
import com.qpros.pages.higher.education.admin.DDActions;
import com.qpros.pages.higher.education.admin.ERActions;
import com.qpros.pages.higher.education.admin.PCActions;
import com.qpros.pages.higher.education.client.HEProgram;
import com.qpros.pages.higher.education.client.SubstantiveChangePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.qpros.reporting.Listeners.class)
public class LORChairPersonER extends Base {
    LoginPage loginPage;
    HEProgram myProgram;
    DDActions ddActions;
    PCActions pcActions;
    ERActions erActions;
    AQACSecretary aQACSecretary;
    SubstantiveChangePage substantiveChangePage;


    // read fields from excel file
    final String ER_numbers = ReadWriteHelper.readFromExcel
            ("ERHireEducation", "ER", "Number of ER");


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

//     @Test(description = "Submit new program for existing provider ",
//           retryAnalyzer = RetryAnalyzer.class,priority = 0)
    public void submitProgramExistingProv() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signIn(ReadWriteHelper.readCredentialsXMLFile("HEProvider2",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("HEProvider2", "password"), "HigherApplicantURL");
        Thread.sleep(5000);
        //Create program and set configurations and team
        myProgram = new HEProgram(driver);
        myProgram.applyForProgram();
        //ActionsHelper.waitForExistance(myProgram.getThankYouTag(),30);
        //Assert.assertTrue( myProgram.getThankYouTag().isDisplayed());
        myProgram.addProgramToFile();


    }


    @Test(description = "Assign to PC ",
            retryAnalyzer = RetryAnalyzer.class, priority = 1)
    public void assignProgramToProvisionManager() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("DivisionDirector",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("DivisionDirector", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        ddActions = new DDActions(driver);
        ddActions.findProgram(ReadWriteHelper.getHEApplication());
        ddActions.assignApplication("Hadeel Salameh");

        Assert.assertTrue(ddActions.getSuccessLabel().isDisplayed());


    }

    @Test(description = "Start application and send technical report  by PC ",
            retryAnalyzer = RetryAnalyzer.class, priority = 2)
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

    @Test(description = "Proceed with selected ER Numbers ",
            retryAnalyzer = RetryAnalyzer.class, priority = 3)
    public void proceedWithER() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("DivisionDirector",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("DivisionDirector", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        ddActions = new DDActions(driver);
        ddActions.findProgram(ReadWriteHelper.getHEApplication());
        ddActions.proceedWithER(ER_numbers);

        Assert.assertTrue(ddActions.getSuccessLabel().isDisplayed());


    }


    @Test(description = "Select ER list by PC ",
            retryAnalyzer = RetryAnalyzer.class, priority = 4)
    public void selectERFromList() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        pcActions = new PCActions(driver);
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.selectERList("ERList", "ER names");


    }

    @Test(description = "DD accepts all the ERs ",
            retryAnalyzer = RetryAnalyzer.class, priority = 5)
    public void acceptAllReviewersByDD() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("DivisionDirector",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("DivisionDirector", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        ddActions = new DDActions(driver);
        ddActions.findProgram(ReadWriteHelper.getHEApplication());
        ddActions.acceptAllReviewers();

        Assert.assertTrue(ddActions.getSuccessLabel().isDisplayed());


    }


    @Test(description = "Invite ER list by PC ",
            retryAnalyzer = RetryAnalyzer.class, priority = 6)
    public void inviteERsByPC() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        pcActions = new PCActions(driver);
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.inviteERs();

    }


    @Test(description = "Accept invitation By ER ",
            retryAnalyzer = RetryAnalyzer.class, priority = 7)
    public void acceptInvitationByER() throws Exception {


        // read fields from excel file to determine how many login there are by readin the number or ERs from excel
        final String ER_numbers = ReadWriteHelper.readFromExcel
                ("ERHireEducation", "ER", "Number of ER");
        int numberOfER = Integer.parseInt(ER_numbers);
        System.out.println(numberOfER);

        for (int j = 1; j <= numberOfER; j++) {
            //read from excel the ER credentials

            loginPage = new LoginPage(driver);

            loginPage.signIn(ReadWriteHelper.readFromExcel
                            ("ERHireEducation", "ERList", "ERLoginEmails", j),
                    ReadWriteHelper.readFromExcel
                            ("ERHireEducation", "ERList", "Password", j), "HigherAdminURL");

            //Create program and set configurations and team
            erActions = new ERActions(driver);
            erActions.findProgram(ReadWriteHelper.getHEApplication());
            erActions.acceptInvitationByER();

            Assert.assertTrue(erActions.getSuccessLabel().isDisplayed());
            Thread.sleep(4000);
            ActionsHelper.waitForExistance(erActions.getLogoutDDL(), 50);
            erActions.getLogoutDDL().click();
            erActions.getLogoutBtn().click();
        }// end for j

    }

    @Test(description = " PC give full access to ER  ",
            retryAnalyzer = RetryAnalyzer.class, priority = 8)
    public void giveREFullAccess() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        pcActions = new PCActions(driver);
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.fullAccessGivenToPC();

    }

    @Test(description = "Submit individual report By ER ",
            retryAnalyzer = RetryAnalyzer.class, priority = 9)
    public void submitIndividualReport() throws Exception {


        // read fields from excel file to determine how many login there are by readin the number or ERs from excel
        final String ER_numbers = ReadWriteHelper.readFromExcel
                ("ERHireEducation", "ER", "Number of ER");
        int numberOfER = Integer.parseInt(ER_numbers);
        System.out.println(numberOfER);

        for (int j = 1; j <= numberOfER; j++) {
            //read from excel the ER credentials

            loginPage = new LoginPage(driver);

            loginPage.signIn(ReadWriteHelper.readFromExcel
                            ("ERHireEducation", "ERList", "ERLoginEmails", j),
                    ReadWriteHelper.readFromExcel
                            ("ERHireEducation", "ERList", "Password", j), "HigherAdminURL");

            //Create program and set configurations and team
            erActions = new ERActions(driver);


            erActions.submitIndvReport(ReadWriteHelper.getHEApplication());

            Assert.assertTrue(erActions.getSuccessLabel().isDisplayed());
            Thread.sleep(4000);
            ActionsHelper.waitForExistance(erActions.getLogoutDDL(), 50);
            erActions.getLogoutDDL().click();
            erActions.getLogoutBtn().click();

        }// end for j

    }

    @Test(description = " Accept individual report , send invoice and request for joint report by PC  ",
            retryAnalyzer = RetryAnalyzer.class, priority = 10)
    public void acceptIndividualReport() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        pcActions = new PCActions(driver);
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.acceptAllIndividualReports();
        Thread.sleep(2000);
        pcActions.getSearchField().clear();
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.reviewAndSendInvoice();
        ActionsHelper.waitForListExistance(pcActions.getOkButton(), 50);
        pcActions.getOkButton().get(0).click();
        Thread.sleep(2000);
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.requestForJointReport();
    }

    @Test(description = "Submit joint report By ER ",
            retryAnalyzer = RetryAnalyzer.class, priority = 11)
    public void submitJointReport() throws Exception {

        loginPage = new LoginPage(driver);

        loginPage.signIn(ReadWriteHelper.readFromExcel
                        ("ERHireEducation", "ERList", "ERLoginEmails", 1),
                ReadWriteHelper.readFromExcel
                        ("ERHireEducation", "ERList", "Password", 1), "HigherAdminURL");

        //Create program and set configurations and team
        erActions = new ERActions(driver);
        erActions.jointReport(ReadWriteHelper.getHEApplication());

        Assert.assertTrue(erActions.getSuccessLabel().isDisplayed());

    }

    @Test(description = "approve joint report By ER ",
            retryAnalyzer = RetryAnalyzer.class, priority = 12)
    public void approveJointReport() throws Exception {

        loginPage = new LoginPage(driver);

        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        pcActions = new PCActions(driver);
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.approveJointReport();

        // Assert.assertTrue(erActions.getSuccessLabel().isDisplayed());

    }

    @Test(description = "Schedule Meeting by AQAC Secretary",
            retryAnalyzer = RetryAnalyzer.class, priority = 13)
    public void scheduleMeeting() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("AQACSecretary",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("AQACSecretary", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        aQACSecretary = new AQACSecretary(driver);

        aQACSecretary.scheduleMeeting(ReadWriteHelper.getHEApplication());
        ActionsHelper.waitForListExistance(aQACSecretary.getScheduleMeetingBtn(), 80);
        Thread.sleep(3000);
        Assert.assertTrue(aQACSecretary.getScheduleMeetingBtn().get(1).isDisplayed());


    }

    @Test(description = "Issue LOR by AQAC secretary ",
            retryAnalyzer = RetryAnalyzer.class, priority = 14)
    public void issueLOR() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("AQACSecretary",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("AQACSecretary", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        aQACSecretary = new AQACSecretary(driver);

        aQACSecretary.findProgram(ReadWriteHelper.getHEApplication());
        aQACSecretary.issueLOR();



    }


    @Test(description = "Send LOR without payment by PC",
            retryAnalyzer = RetryAnalyzer.class, priority = 15)
    public void sendLORWithoutPayment() throws Exception {

        loginPage = new LoginPage(driver);

        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        pcActions = new PCActions(driver);
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.requestForPayment();
        Thread.sleep(3000);
        pcActions.getOkButton().get(0).click();
        Thread.sleep(2000);
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.sendNOLWithoutPayment();


    }

    @Test(description = "Revise and Submit ",
            retryAnalyzer = RetryAnalyzer.class, priority = 16)
    public void getProgramStatus() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signIn(ReadWriteHelper.readCredentialsXMLFile("HEProvider2",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("HEProvider2", "password"), "HigherApplicantURL");

        //Create program and set configurations and team
        myProgram = new HEProgram(driver);
        myProgram.reviseAndSubmit();

    }

    @Test(description = "PC send the revise applocation to AQAC again",
            retryAnalyzer = RetryAnalyzer.class, priority = 17)
    public void sendRevisedAppToAQAC() throws Exception {

        loginPage = new LoginPage(driver);

        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        pcActions = new PCActions(driver);
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.sendToERChairPerson();

    }
    @Test(description = "ER submission ",
            retryAnalyzer = RetryAnalyzer.class, priority = 18)
    public void submissionByER() throws Exception {

        loginPage = new LoginPage(driver);

        loginPage.signIn(ReadWriteHelper.readFromExcel
                        ("ERHireEducation", "ERList", "ERLoginEmails", 1),
                ReadWriteHelper.readFromExcel
                        ("ERHireEducation", "ERList", "Password", 1), "HigherAdminURL");

        //Create program and set configurations and team
        erActions = new ERActions(driver);

        erActions.sendSubmission(ReadWriteHelper.getHEApplication());

        //Assert.assertTrue(erActions.getSuccessLabel().isDisplayed());

    }
}

