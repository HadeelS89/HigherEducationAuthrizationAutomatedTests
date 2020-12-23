package com.qpros.higher.education.admin.provider;

import com.qpros.common.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.helpers.RetryAnalyzer;
import com.qpros.pages.authorization_pages.LoginPage;
import com.qpros.pages.higher.education.admin.DDActions;
import com.qpros.pages.higher.education.admin.PCActions;
import com.qpros.pages.higher.education.admin.Providers;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.qpros.reporting.Listeners.class)
public class PCActionsTest extends Base {
    LoginPage loginPage;
    PCActions pcActions;

    @Test(description = "Start application by PC ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void startReviewByPC() throws Exception {
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


    }


    @Test(description = "Send Technical Report by PC ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void sendTechnicalReport() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        pcActions = new PCActions(driver);
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.sendTechnicalReport();
        ActionsHelper.waitForExistance(pcActions.getSuccessStartReviewText(), 40);
        Assert.assertTrue(pcActions.getSuccessStartReviewText().isDisplayed());


    }

    @Test(description = "Select ER list by PC ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
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


    @Test(description = "Invite ER list by PC ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
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

    @Test(description = " PC give full access to ER  ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
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


    @Test(description = " Accept all Individual reports by PC ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
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

    }

    @Test(description = " Send invoice by PC ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void sendInvoice() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        pcActions = new PCActions(driver);
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.reviewAndSendInvoice();

    }

    @Test(description = " Request For Joint Report by PC ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void requestForJoint() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("ProvisionCoord1", "password"), "HigherAdminURL");

        //Create program and set configurations and team
        pcActions = new PCActions(driver);
        pcActions.findProgram(ReadWriteHelper.getHEApplication());
        pcActions.requestForJointReport();


    }

    @Test(description = "approve joint report By ER ",
            retryAnalyzer = RetryAnalyzer.class)
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

    @Test(description = "Send NOL without payment by PC",
            retryAnalyzer = RetryAnalyzer.class)
    public void sendNOLWithoutPayment() throws Exception {

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

        // Assert.assertTrue(erActions.getSuccessLabel().isDisplayed());

    }

}