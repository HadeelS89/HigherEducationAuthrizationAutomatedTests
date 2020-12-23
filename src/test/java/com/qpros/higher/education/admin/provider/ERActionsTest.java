package com.qpros.higher.education.admin.provider;

import com.qpros.common.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.helpers.RetryAnalyzer;
import com.qpros.pages.authorization_pages.LoginPage;
import com.qpros.pages.higher.education.admin.DDActions;
import com.qpros.pages.higher.education.admin.ERActions;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;

@Listeners(com.qpros.reporting.Listeners.class)
public class ERActionsTest extends Base {
    LoginPage loginPage;
    ERActions erActions;



    @Test(description = "Accept invitation By ER ",
            retryAnalyzer = RetryAnalyzer.class)
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
           erActions.getLogoutDDL().click();
           erActions.getLogoutBtn().click();

        }// end for j

    }



    @Test(description = "Submit individual report By ER ",
            retryAnalyzer = RetryAnalyzer.class)
    public void submitIndividualReport() throws Exception {


        // read fields from excel file to determine how many login there are by readin the number or ERs from excel
        final String ER_numbers = ReadWriteHelper.readFromExcel
                ("ERHireEducation", "ER", "Number of ER");
        int numberOfER = Integer.parseInt(ER_numbers);
        System.out.println(numberOfER);

        for (int j = 2; j <= numberOfER; j++) {
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
            ActionsHelper.waitForExistance(erActions.getLogoutDDL(),50);
            erActions.getLogoutDDL().click();
            erActions.getLogoutBtn().click();

        }// end for j

    }


    @Test(description = "Submit joint report By ER ",
            retryAnalyzer = RetryAnalyzer.class)
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

}

