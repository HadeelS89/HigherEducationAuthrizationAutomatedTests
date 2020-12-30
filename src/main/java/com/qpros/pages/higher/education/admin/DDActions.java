package com.qpros.pages.higher.education.admin;

import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
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
public class DDActions {

    public DDActions(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


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
    @FindBy(xpath = "//button[@class='swal2-confirm swal2-styled' and contains(text(), 'OK')]")
    private List<WebElement> okButton;
    @FindBy(css = "input[class='rdorequestochangeClass']")
    private List<WebElement> requestForChangeBtn;
    @FindBy(id = "Comment")
    private WebElement commentChange;
    @FindBy(id = "btnRequestForChange")
    private WebElement requestMainChangeBtn;
    @FindBy(css = ".adek-upload-div")
    private WebElement uploadInternalMemo;
    @FindBy(css = ".row > .form-control")
    private WebElement uploadDescription; //Test123
    @FindBy(xpath = "//input[starts-with(@class,'btn btn-sm')]")
    private List<WebElement> aQACWFButtons;


    public void findProgram(String programName) throws InterruptedException {

        Thread.sleep(3000);
        ActionsHelper.waitForExistance(getApplicationTab1(), 30);
        getApplicationTab1().click();

        ActionsHelper.waitForExistance(getSearchField(), 100);
        sleep(3000);
        getSearchField().sendKeys(programName);
        getSearchField().sendKeys(Keys.ENTER);
        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();


    }


    public void assignApplication(String PCName) throws InterruptedException {

        ActionsHelper.waitForListExistance(getAssignBtn(), 30);
        getAssignBtn().get(0).click();
        Thread.sleep(2000);
        ActionsHelper.waitForExistance(getAssignToField(), 40);
        getAssignToField().sendKeys(PCName);
        Thread.sleep(3000);
        ActionsHelper.waitForListExistance(getSelectAssignee(), 40);
        getSelectAssignee().get(0).click();
        getSubmitBtn().click();
        ActionsHelper.waitForExistance(getYetBtnYesNew(), 30);
        getYetBtnYesNew().click();
        //  getYesBtn().get(0).click();
    }

    public void proceedWithoutER() {

        ActionsHelper.waitForListExistance(getAssignBtn(), 30);
        getAssignBtn().get(1).click();
        ActionsHelper.waitForExistance(getYetBtnYesNew(), 40);
        getYetBtnYesNew().click();

    }

    public void proceedWithER(String er_numbers) {

        ActionsHelper.waitForListExistance(getAssignBtn(), 30);
        //with ER button same locator as assign btn

        getAssignBtn().get(0).click();
        ActionsHelper.waitForExistance(getInputERField(), 30);
        getInputERField().clear();
        getInputERField().sendKeys(er_numbers);
        getSubmitERBtn().click();
        ActionsHelper.waitForExistance(getYetBtnYesNew(), 40);
        getYetBtnYesNew().click();
    }

    public void acceptAllReviewers() {

        ActionsHelper.waitForExistance(getAcceptAllReviewersBtn(), 50);
        getAcceptAllReviewersBtn().click();
        getYetBtnYesNew().click();

    }

    public void requestToChangeER() throws InterruptedException {
        Thread.sleep(5000);
        String System_ER_List = "";


        // read fields from excel file
        final String ER_numbers = ReadWriteHelper.readFromExcel
                ("ERHireEducation", "ER", "Number of ER");
        int numberOfER = Integer.parseInt(ER_numbers);


        HashMap table = ActionsHelper.getWebColumnIndex("selectedExternalReviewersforSubmit", 0);


        // read fields from excel file
        final String ER_List_names = ReadWriteHelper.readFromExcel
                ("ERHireEducation", "RequestToChangeER", "ER names");
        System.out.println("ER names from excel " + ER_List_names);

        for (int i = 2; i <= table.size() + 1; i++) {
            System.out.println("Table size = " + table.size());


            System_ER_List = (String) table.get(i);

            System.out.println("Check system Record " + System_ER_List);
            System.out.println("excel inside for " + ER_List_names);
            if (System_ER_List.equalsIgnoreCase(ER_List_names)) {

                System.out.println(" inside if " + System_ER_List + ER_List_names);
                ActionsHelper.waitForListExistance(getRequestForChangeBtn(), 40);
                getRequestForChangeBtn().get(i - 2).click();
                System.out.println("i = " + i + "new i = " + (i - 2));

                break;
            }


        }

        getCommentChange().sendKeys("Automation Test");
        ActionsHelper.waitForExistance(getRequestMainChangeBtn(), 50);
        getRequestMainChangeBtn().click();
        getYetBtnYesNew().click();

    }

    public void proceedToAQAC() throws InterruptedException {
ActionsHelper.waitForExistance(getUploadInternalMemo(),40);
        getUploadInternalMemo().click();
        getUploadPdf().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        Thread.sleep(2000);
        getUploadDescription().sendKeys("MyDescription");
        Thread.sleep(1000);
        getUploadBtn().click();
        ActionsHelper.waitForListExistance(getAQACWFButtons(),40);
        getAQACWFButtons().get(0).click();
        getYetBtnYesNew().click();


    }
}
