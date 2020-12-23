package com.qpros.authorization;

import com.qpros.common.Base;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.pages.authorization_pages.AdminExternalLoginPage;
import org.testng.annotations.Test;


public class AdminExternalLoginTest extends Base {

    private AdminExternalLoginPage adminExternalLogin;

    @Test(description = "Try login using external recruiter", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void externalAdminLogin(){

        adminExternalLogin = new AdminExternalLoginPage(driver);
        adminExternalLogin.signInAsExternalUser( ReadWriteHelper.
                        readCredentialsXMLFile( "recruiterCredentials1", "username" ),
                ReadWriteHelper.
                        readCredentialsXMLFile( "recruiterCredentials1", "password" )  );
        //Assert.assertEquals(loginPage.getAlertMessage().getText(),
        //      "The email you’ve entered doesn’t match any account.");
    }
}
