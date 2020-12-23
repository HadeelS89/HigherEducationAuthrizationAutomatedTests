package com.qpros.pages;

import com.qpros.helpers.ActionsHelper;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Getter
public class SecurePage {
    public SecurePage(WebDriver driver) {
        PageFactory.initElements( driver, this );
    }


    @FindBy(id = "details-button")
    private WebElement advancedButton;
    @FindBy(id = "proceed-link")
    private WebElement proceedLink;




    public void redirectToURL(){
        ActionsHelper.waitForExistance( getAdvancedButton(), 10 );
        getAdvancedButton().click();
        ActionsHelper.waitForExistance( getProceedLink(), 10 );
        getProceedLink().click();
    }





}
