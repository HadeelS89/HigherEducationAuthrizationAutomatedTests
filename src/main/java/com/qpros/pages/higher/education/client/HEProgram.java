package com.qpros.pages.higher.education.client;

import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class HEProgram {
    public HEProgram(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(linkText = "New applications")
    private WebElement expandNewApplications;
    @FindBy(linkText = "Programs")
    private WebElement programSection;
    @FindBy(xpath = "//li[2]/a")
    private WebElement applicationsSection;
    @FindBy(css = ".col:nth-child(1) adek-input .form-control")
    private WebElement programNameEnglish; //Test123
    @FindBy(css = ".col:nth-child(2) adek-input .form-control")
    private WebElement programNameArabic; //فثسف123
    @FindBy(css = ".col:nth-child(1) adek-text-area .form-control")
    private WebElement descriptionEnglish; //Test123
    @FindBy(css = ".col:nth-child(2) adek-text-area .form-control")
    private WebElement descriptionArabic; //فثسف123
    //@FindBy(css = ".ng-select-focused .ng-arrow-wrapper")
    //private WebElement focusedArrow; //Test123
    @FindBy(xpath = "//ng-dropdown-panel/div/div[2]/div")
    private WebElement firstOption; //Test123
    @FindBy(css = ".ng-arrow-wrapper")
    private WebElement facultyArrow; //Test123
    @FindBy(css = ".ng-arrow-wrapper:nth-of-type(1)")
    private WebElement providerContactArrow; //Test123
    @FindBy(css = ".ng-arrow-wrapper:nth-of-type(1)")
    private WebElement degreeLevelArrow; //Test123
    @FindBy(css = ".btn:nth-child(2)")
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
    @FindBy(id = "swal2-title")
    private WebElement thankYouTag;
    @FindBy(xpath = "//td[2]")
    private WebElement programId;
    @FindBy(xpath = "//button[@class='swal2-confirm swal2-styled' and contains(text(), 'OK')]")
    private List<WebElement> okButton;
    @FindBy(xpath = "//td[6]")
    private WebElement programStatusField;


//css=.swal2-confirm

    //TODO: Name arabic and English must be unique
    //css=.btn:nth-child(2)
    public void applyForProgram() throws Exception {

        //getXBtn().click();
        Thread.sleep(10000);
        ActionsHelper.selectElementFromList(getNewApplicationTab(), "New Applications");
        ActionsHelper.waitForListExistance(getNewApplicationTab(), 100);
        ActionsHelper.selectElementFromList(getNewApplicationTab(), "Program");
Thread.sleep(5000);

        ActionsHelper.waitVisibility(getProgramNameEnglish(), 60);
        getProgramNameEnglish().sendKeys("Testapp d" + System.currentTimeMillis() % 100000);
        getProgramNameArabic().sendKeys("عربياسم " + System.currentTimeMillis() % 100000);
        getDescriptionEnglish().sendKeys(String.format("Teseawt ", System.currentTimeMillis() % 100000));
        getDescriptionArabic().sendKeys(String.format("عربياسشيسم ", System.currentTimeMillis() % 100000));
        getFacultyArrow().click();
        getFirstOption().click();
        getProviderContactArrow().click();
        getFirstOption().click();
        getDegreeLevelArrow().click();
        getFirstOption().click();
        getNextButton().click();

        ActionsHelper.waitToBeClickable(getUploadDiv(), 60);

        ActionsHelper.retryClick(getUploadDiv(), 30);
        ActionsHelper.waitForListExistance(getUploadFilePath(), 60);
        System.out.println("Upload size: " + getUploadFilePath().size());
        getUploadFilePath().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        getUploadDescription().sendKeys("MyDescription");
        getUploadButton().click();
        ActionsHelper.waitVisibility(getSubmitButton(), 60);
        ActionsHelper.retryClick(getSubmitButton(), 10);
        ActionsHelper.waitVisibility(getConfirmButton(), 60);
        ActionsHelper.retryClick(getConfirmButton(), 10);
        System.out.println("Trying to click application section");
        ActionsHelper.waitForListExistance(getOkButton(),40);
getOkButton().get(0).click();

//        ActionsHelper.retryClick(getCloseFeedback(),10);
//        ActionsHelper.retryClick(getApplicationsSection(),10);
//        System.out.println("Clicked application section successfully");
//        ActionsHelper.waitVisibility(getCurrentProgram(), 60);
//        System.out.println("Found damn program area");
//        ReadWriteHelper.writeCSVFirstCell(getCurrentProgram().getText()); //Write this to a CSV file
    }


    public void addProgramToFile() throws InterruptedException {

        //getXBtn().click();
        Thread.sleep(10000);

        ActionsHelper.waitForListExistance(getNewApplicationTab(), 100);
        ActionsHelper.selectElementFromList(getNewApplicationTab(), "Current and Previous Applications");
        Thread.sleep(5000);
        ReadWriteHelper.writeIntoXMLFileHEApplication(getProgramId().getText());
    }

    public void getProgramStatus() throws InterruptedException {

        //getXBtn().click();
        Thread.sleep(10000);

        ActionsHelper.waitForListExistance(getNewApplicationTab(), 100);
        ActionsHelper.selectElementFromList(getNewApplicationTab(), "Current and Previous Applications");
        Thread.sleep(5000);
    }
}
