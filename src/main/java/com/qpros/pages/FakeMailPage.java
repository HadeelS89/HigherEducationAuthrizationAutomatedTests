package com.qpros.pages;

import com.qpros.helpers.ActionsHelper;
//import com.qpros.pages.scholarship_admin_pages.ScholarshipsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.HashMap;

public class FakeMailPage {
    public FakeMailPage(WebDriver driver) {
        PageFactory.initElements( driver, this );
    }


    @FindBy(id = "schranka")
    private WebElement mailTable;


//    public void checkReceivedEmail(WebDriver driver) throws InterruptedException {
//        driver.navigate().to("https://www.fakemail.net/");
//        String email = "";
//
//        ActionsHelper.waitForExistance(mailTable);
//        HashMap map = ActionsHelper.getWebColumnIndex("schranka", 1);
//
//        for (int i = 0; i<map.size(); i++){
//            if (map.get(i).equals(ScholarshipsPage.title)){
//                email = (String) map.get(i);
//                break;
//            }
//        }
//
//        Assert.assertEquals(email, ScholarshipsPage.title);
//    }
//
//
//


}
