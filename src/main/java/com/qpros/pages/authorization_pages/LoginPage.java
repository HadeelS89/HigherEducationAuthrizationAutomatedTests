package com.qpros.pages.authorization_pages;


import com.qpros.common.Base;
import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.pages.SecurePage;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


@Getter
public class LoginPage extends Base {

    public LoginPage(WebDriver driver) {
        PageFactory.initElements( driver, this );
    }

    @FindBy(id = "Input_Email")
    private WebElement email;
    @FindBy(id = "Input_Password")
    private WebElement password;
    @FindBy(xpath = "/html/body/div/div/div[1]/div[2]/form/div[5]/button")
    private WebElement LoginButton;
    @FindBy(name = "provider")
    private WebElement LoginAsAdekEmployee;
    @FindBy(id = "i0116")
    private WebElement msEmail;
    @FindBy(id = "i0118")
    private WebElement msPassword;
    @FindBy(id = "idSIButton9")
    private WebElement msNextButton;
    @FindBy(id = "idBtn_Back")
    private WebElement staySignedInNoButton;
    @FindBy(id = "Address")
    private WebElement addressTab;


    SecurePage securePage;

    public void signIn(String email, String password) {
        //Navigate to Application
        driver.navigate().to( ReadWriteHelper.ReadData( "ApplicantURL" ) );
        //securedAccess();

        ActionsHelper.waitVisibility( getEmail(), 60 );
        getEmail().sendKeys( email );
        getPassword().sendKeys( password );
        ActionsHelper.waitVisibility( getLoginButton(), 50 );
        getLoginButton().click();
        ActionsHelper.waitVisibility( getAddressTab(), 50 );

    }

    public void signIn(String email, String password, String url) {
        //Navigate to Application
        driver.navigate().to( ReadWriteHelper.ReadData( url ) );
        //securedAccess();

        ActionsHelper.waitVisibility( getEmail(), 50 );
        getEmail().sendKeys( email );
        getPassword().sendKeys( password );
        ActionsHelper.waitVisibility( getLoginButton(), 10 );
        getLoginButton().click();
        //ActionsHelper.waitVisibility( getAddressTab(), 50 );

    }

    public void signInAsADEKEmployee(String email, String password) {
        //Navigate to Admin panel
        driver.navigate().to( ReadWriteHelper.ReadData( "AdminURL" ) );
        //securedAccess();

        ActionsHelper.waitForExistance( getLoginAsAdekEmployee(), 50 );
        getLoginAsAdekEmployee().click();
        ActionsHelper.waitForExistance( getMsEmail(), 50 );
        getMsEmail().sendKeys( email );
        getMsNextButton().click();
        ActionsHelper.waitForExistance( getMsPassword(), 50 );
        getMsPassword().sendKeys( password );
        getMsNextButton().click();
        if (ActionsHelper.waitVisibility( getStaySignedInNoButton(), 50 )) {
            getStaySignedInNoButton().click();
        }

    }

    public void signInAsADEKEmployee(String email, String password, String adminUrl) {
        //Navigate to Admin panel
        driver.navigate().to(ReadWriteHelper.ReadData(adminUrl));
        //securedAccess();

        ActionsHelper.waitVisibility(getLoginAsAdekEmployee(), 50);
        getLoginAsAdekEmployee().click();
        ActionsHelper.waitVisibility(getMsEmail(), 50);
        getMsEmail().sendKeys(email);
        getMsNextButton().click();
        ActionsHelper.waitVisibility(getMsPassword(), 50);
        getMsPassword().sendKeys(password);
        getMsNextButton().click();
        if (ActionsHelper.waitVisibility(getStaySignedInNoButton(), 50)) {
            getStaySignedInNoButton().click();
        }

    }

    public void securedAccess() {

        try {
            WebElement element = ActionsHelper.getElement( driver, "id", "details-button" );

            if (ActionsHelper.waitForExistance( element, 2 )) {
                securePage = new SecurePage( driver );
                securePage.redirectToURL();
            }
        } catch (Exception e) {

        }

    }


}
