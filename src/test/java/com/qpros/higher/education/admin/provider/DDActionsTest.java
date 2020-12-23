package com.qpros.higher.education.admin.provider;

import com.qpros.common.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.helpers.RetryAnalyzer;
import com.qpros.pages.authorization_pages.LoginPage;
import com.qpros.pages.higher.education.admin.DDActions;
import com.qpros.pages.higher.education.admin.Providers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.qpros.reporting.Listeners.class)
public class DDActionsTest extends Base {
    LoginPage loginPage;
    DDActions ddActions;

    // read fields from excel file
    final String ER_numbers = ReadWriteHelper.readFromExcel
            ("ERHireEducation", "ER", "Number of ER");

    @Test(description = "Assign to PC ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
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
    @Test(description = "Proceed with selected ER Numbers ",
            retryAnalyzer = RetryAnalyzer.class)
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


    }
    @Test(description = "Proceed with selected ER Numbers ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void proceedWithER() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("DivisionDirector",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("DivisionDirector", "password"),"HigherAdminURL");

        //Create program and set configurations and team
        ddActions = new DDActions(driver);
        ddActions.findProgram(ReadWriteHelper.getHEApplication());
        ddActions.proceedWithER(ER_numbers);

        Assert.assertTrue( ddActions.getSuccessLabel().isDisplayed());


    }



    @Test(description = "DD accepts all the ERs ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void acceptAllReviewersByDD() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("DivisionDirector",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("DivisionDirector", "password"),"HigherAdminURL");

        //Create program and set configurations and team
        ddActions = new DDActions(driver);
        ddActions.findProgram(ReadWriteHelper.getHEApplication());
        ddActions.acceptAllReviewers();

        Assert.assertTrue( ddActions.getSuccessLabel().isDisplayed());


    }

    @Test(description = "DD request to change one  ER ",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void requestToChangeER() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("DivisionDirector",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("DivisionDirector", "password"),"HigherAdminURL");

        //Create program and set configurations and team
        ddActions = new DDActions(driver);
        ddActions.findProgram(ReadWriteHelper.getHEApplication());
        ddActions.requestToChangeER();

       // Assert.assertTrue( ddActions.getSuccessLabel().isDisplayed());


    }
}

