package com.qpros.pages.higher.education.admin;

import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
import com.qpros.utils.RandomString;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;
import java.util.List;

import static java.lang.Thread.sleep;

@Getter
public class AQACSecretary {

    public AQACSecretary(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static String randomName = "";

    @FindBy(css = ".air__menuLeft__item:nth-child(1) span")
    private WebElement applicationTab1;
    @FindBy(css = "a[class='air__menuLeft__link']")
    private List<WebElement> applicationTab;
    @FindBy(id = "searchbox")
    private WebElement searchField;
    @FindBy(css = ".text-muted > .list-inline-item:nth-child(1)")
    private WebElement selectFirstRecord;
    @FindBy(xpath = "//input[starts-with(@class,'btn btn-sm')]")
    private List<WebElement> acceptBtn;
    @FindBy(id = "typeahead_AssignedTo")
    private WebElement assignToField;
    @FindBy(css = "li[class='typeahead__item typeahead__group-group']")
    private List<WebElement> selectAssignee;
    @FindBy(id = "adekFlowCommandParamSubmit")
    private WebElement submitBtn;
    @FindBy(xpath = "//button[@class='swal2-confirm swal2-styled' and contains(text(), 'Yes')]")
    private List<WebElement> yesBtn;
    @FindBy(id = "swal2-title")
    private WebElement successLabel;
    @FindBy(id = "ExternalReviewerRequired")
    private WebElement inputERField;
    @FindBy(id = "btnsubmit")
    private WebElement submitERBtn;
    @FindBy(id = "btnAcceptAllReviewers")
    private WebElement acceptAllReviewersBtn;
    @FindBy(css = "input[type='file']")
    private List<WebElement> uploadPdf;
    @FindBy(css = "#docUp785 .form-control")
    private WebElement uploadPdfConflict;
    @FindBy(css = ".adek-upload-popup-btn")
    private WebElement uploadBtn;
    @FindBy(css = "#docUp784 .form-control")
    private WebElement uploadPdfCommitment;
    @FindBy(css = "#docUp783 .form-control")
    private WebElement uploadPdfInvoice;
    @FindBy(css = ".dropdown > .text-nowrap")
    private WebElement logoutDDL;
    @FindBy(css = "#div_menu > .dropdown-item")
    private WebElement logoutBtn;
    @FindBy(css = ".adek-upload-div")
    private WebElement uploadIndvReport;
    @FindBy(css = ".form-group > .btn-primary")
    private WebElement submitButton;
    @FindBy(xpath = "//button[contains(.,'Yes')]")
    private WebElement yetBtnYesNew;
    @FindBy(xpath = "//button[starts-with(@class,'btn btn-primary')]")
    private List<WebElement> assignBtn;
    @FindBy(css = "input[class='rdorequestochangeClass']")
    private List<WebElement> requestForChangeBtn;
    @FindBy(id = "Comment")
    private WebElement commentChange;
    @FindBy(id = "btnRequestForChange")
    private WebElement requestMainChangeBtn;
    @FindBy(xpath = "//button[@class='btn btn-primary']")
    private List<WebElement> scheduleMeetingBtn;
    @FindBy(xpath = "//input[starts-with(@id,'selectAQAC')]")
    private List<WebElement> selectProgramCheckBox;
    @FindBy(id = "MeetingTitle")
    private WebElement meetingTitleArea;
    @FindBy(id = "Comment")
    private WebElement commentArea;
    @FindBy(css = ".adek-upload-div")
    private WebElement uploadInternalMemo;
    @FindBy(css = ".row > .form-control")
    private WebElement uploadDescription; //Test123
    @FindBy(xpath = "//input[@value='Schedule AQAC Meeting']")
    private WebElement scheduleAQACMeetingBtn;
    @FindBy(xpath = "//input[starts-with(@class,'btn btn-primary')]")
    private List<WebElement> aQACWFButtons;


    public void findProgram(String programName) throws InterruptedException {

        Thread.sleep(3000);
//        ActionsHelper.waitForExistance(getApplicationTab1(), 30);
//        getApplicationTab1().click();
        ActionsHelper.waitForListExistance(getApplicationTab(), 50);
        ActionsHelper.selectElementFromList(getApplicationTab(), "Applications");
        ActionsHelper.waitForExistance(getSearchField(), 100);
        sleep(3000);
        getSearchField().sendKeys(programName);
        getSearchField().sendKeys(Keys.ENTER);
        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();


    }

    public void scheduleMeeting(String programName) throws InterruptedException {

        ActionsHelper.waitForListExistance(getApplicationTab(), 70);
        ActionsHelper.selectElementFromList(getApplicationTab(), "AQAC Meetings");
        getScheduleMeetingBtn().get(1).click();

        HashMap table = ActionsHelper.getWebColumnIndex("tblAQACApplicationList",
                2);
        String programId = "";
        for (int i = 1; i <= table.size() + 1; i++) {
            programId = (String) table.get(i);
            System.out.println(programId);
            System.out.println("i = " + i);
            if (programName.equalsIgnoreCase(programId)) {

                getSelectProgramCheckBox().get(i - 2).click();
                break;
            }// end if
        }//end for
        randomName = "" + System.currentTimeMillis() % 100000;
        getMeetingTitleArea().sendKeys(randomName);
        randomName = "Automation " + System.currentTimeMillis() % 100000;
        getCommentArea().sendKeys(randomName);
        ActionsHelper.waitForExistance(getUploadInternalMemo(), 100);
        getUploadInternalMemo().click();
        getUploadPdf().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        Thread.sleep(2000);
        getUploadDescription().sendKeys("MyDescription");
        Thread.sleep(1000);
        getUploadBtn().click();
        getScheduleAQACMeetingBtn().click();
        getYetBtnYesNew().click();
        Thread.sleep(2000);

    }


    public void issueNOL() {

        ActionsHelper.waitForExistance(getCommentArea(), 60);
        getCommentArea().sendKeys("Issue NOL by Automation");
        getAQACWFButtons().get(0).click();

        getYetBtnYesNew().click();


    }

    public void issueLOR() {

        ActionsHelper.waitForExistance(getCommentArea(), 60);
        getCommentArea().sendKeys("Issue NOL by Automation");
        getAQACWFButtons().get(1).click();

        getYetBtnYesNew().click();


    }

    public void issueNAL() {

        ActionsHelper.waitForExistance(getCommentArea(), 60);
        getCommentArea().sendKeys("Issue NOL by Automation");
        getAQACWFButtons().get(2).click();

        getYetBtnYesNew().click();


    }


}
