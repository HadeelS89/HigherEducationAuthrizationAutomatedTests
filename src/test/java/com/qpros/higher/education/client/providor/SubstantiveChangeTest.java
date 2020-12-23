package com.qpros.higher.education.client.providor;

import com.qpros.common.Base;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.pages.authorization_pages.LoginPage;
import com.qpros.pages.higher.education.client.HEProgram;
import com.qpros.pages.higher.education.client.SubstantiveChangePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.qpros.reporting.Listeners.class)
public class SubstantiveChangeTest extends Base {
    LoginPage loginPage;
    SubstantiveChangePage substantiveChangePage;

    @Test(description = "Submit new program for existing provider ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void submitProgramExistingProv() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signIn(ReadWriteHelper.readCredentialsXMLFile("HEProvider2",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("HEProvider2", "password"),"HigherApplicantURL");

        //Create program and set configurations and team
        substantiveChangePage = new SubstantiveChangePage (driver);
        substantiveChangePage.changeProviderName();
        //Assert.assertTrue( myProgram.getThankYou().isDisplayed());


    }

    @Test(description = "Submit chane mission  for existing provider ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void changeMission() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signIn(ReadWriteHelper.readCredentialsXMLFile("HEProvider2",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("HEProvider2", "password"),"HigherApplicantURL");

        //Create program and set configurations and team
        substantiveChangePage = new SubstantiveChangePage (driver);
        substantiveChangePage.changeMission();
        // Assert.assertTrue( myProgram.getThankYou().isDisplayed());


    }

    @Test(description = "Submit change mission  for existing provider ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void operateWithDifferentPurpose() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signIn(ReadWriteHelper.readCredentialsXMLFile("HEProvider2",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("HEProvider2", "password"),"HigherApplicantURL");

        //Create program and set configurations and team
        substantiveChangePage = new SubstantiveChangePage (driver);
        substantiveChangePage.operateWithDifferentPurpose();
        // Assert.assertTrue( myProgram.getThankYou().isDisplayed());


    }

    @Test(description = "Submit change location  for existing provider ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void changeLocation() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signIn(ReadWriteHelper.readCredentialsXMLFile("HEProvider2",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("HEProvider2", "password"),"HigherApplicantURL");

        //Create program and set configurations and team
        substantiveChangePage = new SubstantiveChangePage (driver);
        substantiveChangePage.changeLocation();
        // Assert.assertTrue( myProgram.getThankYou().isDisplayed());


    }
    @Test(description = "Submit Change of Partnerships / New Partnerships  for existing provider ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void changePartnerShip() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signIn(ReadWriteHelper.readCredentialsXMLFile("HEProvider2",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("HEProvider2", "password"),"HigherApplicantURL");

        //Create program and set configurations and team
        substantiveChangePage = new SubstantiveChangePage (driver);
        substantiveChangePage.changePartnerShip();
        // Assert.assertTrue( myProgram.getThankYou().isDisplayed());


    }
}

