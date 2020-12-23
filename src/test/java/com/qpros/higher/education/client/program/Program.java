package com.qpros.higher.education.client.program;

import com.qpros.common.Base;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.pages.authorization_pages.LoginPage;
import com.qpros.pages.higher.education.client.HEProgram;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.qpros.reporting.Listeners.class)
public class Program extends Base {
    LoginPage loginPage;
    HEProgram myProgram;

    @Test(description = "Create new Provider",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void applyProgram() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signIn(ReadWriteHelper.readCredentialsXMLFile("HEProvider1",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("HEProvider1", "password"), "HigherApplicantURL");

        //Create program and set configurations and team
        myProgram = new HEProgram(driver);
        myProgram.applyForProgram();
        myProgram.addProgramToFile();
        //provider = new Providers(driver);
        //provider.createFullProvider();

    }


    @Test(description = "Check program Status",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
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