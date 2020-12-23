package com.qpros.authorization;

import com.qpros.common.Base;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.pages.authorization_pages.AdminExternalLogin;
import org.testng.annotations.Test;


public class ExternalLogin extends Base {

    private AdminExternalLogin adminExternalLogin;

    @Test(description = "Try login using external recruiter", priority = 1,
            retryAnalyzer = com.qpros.helpers.RetryAnalyzer.class)
    public void externalAdminLogin(){

        adminExternalLogin = new AdminExternalLogin(driver);
        adminExternalLogin.signInAsExternalUser( ReadWriteHelper.
                readCredentialsXMLFile( "recruiterCredentials1", "username" ),
                ReadWriteHelper.
                        readCredentialsXMLFile( "recruiterCredentials1", "password" ) );
        //Assert.assertEquals(loginPage.getAlertMessage().getText(),
        //      "The email you’ve entered doesn’t match any account.");
    }
}
