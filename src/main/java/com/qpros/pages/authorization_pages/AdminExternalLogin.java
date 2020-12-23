package com.qpros.pages.authorization_pages;

import com.qpros.common.Base;
import com.qpros.helpers.ActionsHelper;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class AdminExternalLogin extends Base {
    public AdminExternalLogin(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "Input_Email")
    private WebElement email;
    @FindBy(id = "Input_Password")
    private WebElement password;
    @FindBy(className = "btn-danger")//TODO MY: find better locator
    private WebElement LoginButton;

    public void signInAsExternalUser(String email, String password){
        ActionsHelper.waitVisibility( getEmail(), 5 );
        getEmail().sendKeys(email);
        getPassword().sendKeys(password);
        ActionsHelper.waitVisibility( getLoginButton(), 5 );
        getLoginButton().click();

    }
}
