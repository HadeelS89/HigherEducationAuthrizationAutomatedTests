package com.qpros.higher.education.admin.provider;

import com.qpros.common.Base;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.pages.authorization_pages.LoginPage;
import com.qpros.pages.higher.education.admin.Providers;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.qpros.reporting.Listeners.class)
public class ProviderTest extends Base {
    LoginPage loginPage;
    Providers provider;

    @Test(description = "Create new Provider",
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void createProvider() throws Exception {
        //Login as Program Manager
        loginPage = new LoginPage(driver);
        loginPage.signInAsADEKEmployee(ReadWriteHelper.readCredentialsXMLFile("ProviderManger1",
                "username"),
                ReadWriteHelper.readCredentialsXMLFile("ProviderManger1", "password"),"HigherAdminURL");

        //Create program and set configurations and team
        provider = new Providers(driver);
        provider.createFullProvider();

    }
}

