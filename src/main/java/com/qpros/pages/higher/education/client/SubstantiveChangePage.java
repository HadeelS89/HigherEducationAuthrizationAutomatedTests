package com.qpros.pages.higher.education.client;

import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sun.awt.windows.ThemeReader;

import java.util.List;

@Getter
public class SubstantiveChangePage {
    public SubstantiveChangePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "New applications")
    private WebElement expandNewApplications;
    @FindBy(linkText = "Programs")
    private WebElement programSection;
    @FindBy(xpath = "//li[2]/a")
    private WebElement applicationsSection;
    @FindBy(xpath = "(//input[@type='text'])")
    private List<WebElement> programNameEnglish; //Test123
    @FindBy(css = ".col:nth-child(2) adek-input .form-control")
    private WebElement programNameArabic; //فثسف123
    @FindBy(css = ".col:nth-child(1) adek-text-area .form-control")
    private WebElement descriptionEnglish; //Test123
    @FindBy(css = ".col:nth-child(2) adek-text-area .form-control")
    private WebElement descriptionArabic; //فثسف123
    @FindBy(css = ".ng-arrow-wrapper")
    private WebElement facultyArrow; //Test123
    @FindBy(css = ".ng-arrow-wrapper:nth-of-type(1)")
    private WebElement providerContactArrow; //Test123
    @FindBy(css = ".ng-arrow-wrapper:nth-of-type(1)")
    private WebElement degreeLevelArrow; //Test123
    @FindBy(css = ".btn-primary")
    private WebElement nextButton; //Test123

    @FindBy(css = ".adek-upload-div")
    private WebElement uploadDiv; //Test123
    @FindBy(css = "input[type='file']")
    private List<WebElement> uploadFilePath; //Add filepath to this by sendkeys
    @FindBy(css = ".row > .form-control")
    private WebElement uploadDescription; //Test123
    @FindBy(css = ".adek-upload-popup-btn")
    //Maintenance reached here
    private WebElement uploadButton; //Test123
    @FindBy(xpath = "//button[contains(.,'Yes')]")
    private WebElement yetBtnYesNew;
    @FindBy(xpath = "//button[@class='swal2-confirm swal2-styled' and contains(text(), 'OK')]")
    private List<WebElement> okButton;
    @FindBy(css = ".ml-1")
    private WebElement submitButton; //Test123
    @FindBy(css = ".swal2-confirm")
    private WebElement confirmButton; //Test123
    @FindBy(css = ".h5")
    private WebElement feedbackText; //Should contain: SHARE YOUR FEEDBACK
    @FindBy(css = ".ng-star-inserted:nth-child(1) > td:nth-child(2)")
    private WebElement currentProgram; //GetText
    @FindBy(css = ".ng-star-inserted > adek-label .mb-2")
    private WebElement expiryDate; //GetText
    @FindBy(css = ".fa-remove")
    private WebElement closeFeedback; //GetText
    @FindBy(css = "a[class='air__menuLeft__link']")
    private List<WebElement> newApplicationTab;
    @FindBy(xpath = "//div[@id='toast-container']/div/div[2]")
    private WebElement successMsg;
    @FindBy(css = "css=.fa-remove")
    private WebElement xBtn;
    @FindBy(xpath = "/html/body/app-root/layout-app/block-ui/div/nz-layout/nz-layout/nz-content/div/div/app-provider-applications/div/div/table/tbody/tr[1]/td[2]")
    private WebElement programId;
    @FindBy(id = "swal2-title")
    private WebElement thankYou;
    @FindBy(xpath = "//button[contains(.,'OK')]")
    private WebElement okBtn;
    @FindBy(xpath = "//input[starts-with(@class,'ant-checkbox-input')]")
    private List<WebElement> checkBoxSubChange;
    @FindBy(xpath = "//textarea[@class='form-control ng-untouched ng-pristine ng-invalid']")
    private WebElement programNameEnglishNew; //Test123


    //TODO: Name arabic and English must be unique

    public void changeProviderName() throws Exception {
        //getXBtn().click();
        Thread.sleep(10000);
        ActionsHelper.selectElementFromList(getNewApplicationTab(), "New Applications");
        ActionsHelper.waitForListExistance(getNewApplicationTab(), 100);
        ActionsHelper.selectElementFromList(getNewApplicationTab(), "Substantive Changes");

        Thread.sleep(4000);

        getCheckBoxSubChange().get(0).click();

        ActionsHelper.waitForListExistance(getProgramNameEnglish(), 60);
        getProgramNameEnglish().get(0).sendKeys("Testapp d" + System.currentTimeMillis() % 100000);
        getProgramNameEnglish().get(1).sendKeys("عربي اسم " + System.currentTimeMillis() % 100000);
        getNextButton().click();
        Thread.sleep(8000);
        ActionsHelper.safeJavaScriptClick(getUploadDiv());
//        ActionsHelper.waitForExistance(getUploadDiv(), 100);
//        getUploadDiv().click();
        Thread.sleep(3000);
        getUploadFilePath().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        ActionsHelper.waitForExistance(getUploadDescription(),50);
        getUploadDescription().sendKeys("My Automation Test ");

        ActionsHelper.waitForExistance(getUploadButton(),40);
        getUploadButton().click();
        Thread.sleep(3000);
      ActionsHelper.safeJavaScriptClick( getSubmitButton());
        getYetBtnYesNew().click();
    }
    public void changeMission() throws Exception {
        //getXBtn().click();
        Thread.sleep(10000);
        ActionsHelper.selectElementFromList(getNewApplicationTab(), "New Applications");
        ActionsHelper.waitForListExistance(getNewApplicationTab(), 100);
        ActionsHelper.selectElementFromList(getNewApplicationTab(), "Substantive Changes");

        Thread.sleep(4000);
        getCheckBoxSubChange().get(1).click();
        Thread.sleep(5000);
        ActionsHelper.waitForExistance(getProgramNameEnglishNew(), 60);
        getProgramNameEnglishNew().sendKeys("Automation Test app d" + System.currentTimeMillis() % 100000);
        getProgramNameEnglishNew().sendKeys("عربي اسمAutomation  " + System.currentTimeMillis() % 100000);
        getProgramNameEnglishNew().sendKeys("عربي اسم Automation " + System.currentTimeMillis() % 100000);
        getProgramNameEnglishNew().sendKeys("عربي اسمAutomation  " + System.currentTimeMillis() % 100000);
        ActionsHelper.safeJavaScriptClick(getNextButton());
        Thread.sleep(8000);
        ActionsHelper.safeJavaScriptClick(getUploadDiv());


        Thread.sleep(3000);
        getUploadFilePath().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        ActionsHelper.waitForExistance(getUploadDescription(), 50);
        getUploadDescription().sendKeys("My Automation Test ");

        ActionsHelper.waitForExistance(getUploadButton(), 40);
        getUploadButton().click();
        Thread.sleep(3000);
        ActionsHelper.safeJavaScriptClick(getSubmitButton());
        getYetBtnYesNew().click();
    }
//ToDo
    public void operateWithDifferentPurpose () throws Exception {
        Thread.sleep(10000);
        ActionsHelper.selectElementFromList(getNewApplicationTab(), "New Applications");
        ActionsHelper.waitForListExistance(getNewApplicationTab(), 100);
        ActionsHelper.selectElementFromList(getNewApplicationTab(), "Substantive Changes");

        Thread.sleep(4000);
        getCheckBoxSubChange().get(2).click();
        Thread.sleep(5000);
        ActionsHelper.waitForListExistance(getProgramNameEnglish(), 60);
        getProgramNameEnglish().get(0).sendKeys("Automation Test app d" + System.currentTimeMillis() % 100000);
        getProgramNameEnglish().get(1).sendKeys("عربي اسمAutomation  " + System.currentTimeMillis() % 100000);
        getProgramNameEnglish().get(2).sendKeys("عربي اسم Automation " + System.currentTimeMillis() % 100000);
        getProgramNameEnglish().get(3).sendKeys("عربي اسمAutomation  " + System.currentTimeMillis() % 100000);
        ActionsHelper.safeJavaScriptClick(getNextButton());
        Thread.sleep(8000);
        ActionsHelper.safeJavaScriptClick(getUploadDiv());


        Thread.sleep(3000);
        getUploadFilePath().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        ActionsHelper.waitForExistance(getUploadDescription(), 50);
        getUploadDescription().sendKeys("My Automation Test ");

        ActionsHelper.waitForExistance(getUploadButton(), 40);
        getUploadButton().click();
        Thread.sleep(3000);
        ActionsHelper.safeJavaScriptClick(getSubmitButton());
        getYetBtnYesNew().click();

    }



    public void changeLocation() throws Exception {
        //getXBtn().click();
        Thread.sleep(10000);
        ActionsHelper.selectElementFromList(getNewApplicationTab(), "New Applications");
        ActionsHelper.waitForListExistance(getNewApplicationTab(), 100);
        ActionsHelper.selectElementFromList(getNewApplicationTab(), "Substantive Changes");

        Thread.sleep(4000);
        getCheckBoxSubChange().get(3).click();

        ActionsHelper.waitForListExistance(getProgramNameEnglish(), 60);
        getProgramNameEnglish().get(0).sendKeys("Testapp d" + System.currentTimeMillis() % 100000);
        getProgramNameEnglish().get(1).sendKeys("عربي اسم " + System.currentTimeMillis() % 100000);
        getNextButton().click();
        Thread.sleep(8000);
        ActionsHelper.safeJavaScriptClick(getUploadDiv());
//        ActionsHelper.waitForExistance(getUploadDiv(), 100);
//        getUploadDiv().click();
        Thread.sleep(3000);
        getUploadFilePath().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        ActionsHelper.waitForExistance(getUploadDescription(),50);
        getUploadDescription().sendKeys("My Automation Test ");

        ActionsHelper.waitForExistance(getUploadButton(),40);
        getUploadButton().click();
        Thread.sleep(3000);
        ActionsHelper.safeJavaScriptClick( getSubmitButton());
        getYetBtnYesNew().click();
    }



    public void changePartnerShip () throws Exception {
        Thread.sleep(10000);
        ActionsHelper.selectElementFromList(getNewApplicationTab(), "New Applications");
        ActionsHelper.waitForListExistance(getNewApplicationTab(), 100);
        ActionsHelper.selectElementFromList(getNewApplicationTab(), "Substantive Changes");

        Thread.sleep(4000);
        getCheckBoxSubChange().get(4).click();
        Thread.sleep(5000);
        ActionsHelper.waitForListExistance(getProgramNameEnglish(), 60);
        getProgramNameEnglish().get(0).sendKeys("Automation Test app d" + System.currentTimeMillis() % 100000);
        getProgramNameEnglish().get(1).sendKeys("عربي اسمAutomation  " + System.currentTimeMillis() % 100000);
        getProgramNameEnglish().get(2).sendKeys("عربي اسم Automation " + System.currentTimeMillis() % 100000);
        getProgramNameEnglish().get(3).sendKeys("عربي اسمAutomation  " + System.currentTimeMillis() % 100000);
        ActionsHelper.safeJavaScriptClick(getNextButton());
        Thread.sleep(5000);
        ActionsHelper.scrollTo(getUploadDiv());
        ActionsHelper.safeJavaScriptClick(getUploadDiv());


        Thread.sleep(3000);
        getUploadFilePath().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        ActionsHelper.waitForExistance(getUploadDescription(), 50);
        getUploadDescription().sendKeys("My Automation Test ");

        ActionsHelper.waitForExistance(getUploadButton(), 40);
        getUploadButton().click();
        Thread.sleep(3000);
        ActionsHelper.safeJavaScriptClick(getSubmitButton());
        getYetBtnYesNew().click();
        ActionsHelper.waitForListExistance(getOkButton(),40);
        getOkButton().get(0).click();
    }


}