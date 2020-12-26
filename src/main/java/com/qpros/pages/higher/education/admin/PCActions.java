package com.qpros.pages.higher.education.admin;

import com.qpros.helpers.ActionsHelper;
import com.qpros.helpers.ReadWriteHelper;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static java.lang.Thread.sleep;

@Getter
public class PCActions {

    public PCActions(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }


    @FindBy(css = "a[class='air__menuLeft__link']")
    private List<WebElement> applicationTab;
    @FindBy(id = "searchbox")
    private WebElement searchField;
    @FindBy(css = ".text-muted > .list-inline-item:nth-child(1)")
    private WebElement selectFirstRecord;
    @FindBy(xpath = "//button[starts-with(@class,'btn btn-primary')]")
    private List<WebElement> assignBtn;
    @FindBy(id = "typeahead_AssignedTo")
    private WebElement assignToField;
    @FindBy(css = "li[class='typeahead__item typeahead__group-group']")
    private List<WebElement> selectAssignee;
    @FindBy(id = "adekFlowCommandParamSubmit")
    private WebElement submitBtn;
    @FindBy(xpath = "//button[@class='swal2-confirm btn-primary swal2-styled' and contains(text(), 'Yes')]")
    private List<WebElement> yesBtn;
    @FindBy(xpath = "//p[starts-with(@class,'lead ')]")
    private List<WebElement> sucessLabel;
    @FindBy(id = "swal2-content")
    private WebElement successStartReviewText;
    @FindBy(css = ".note-editable")
    private WebElement techReportText;
    @FindBy(xpath = "//input[@value='Send Technical Report for Approval']")
    private WebElement sendTechReportBtn;
    @FindBy(xpath = "//button[contains(.,'Yes')]")
    private WebElement yetBtnYesNew;
    @FindBy(xpath = "//button[starts-with(@class,'btn btn-outline-primary')]")
    private List<WebElement> selectBtn;
    @FindBy(xpath = "//input[@value='Submit ER List']")
    private WebElement submitERList;

    @FindBy(css = "input[value='Give Full Access']")
    private List<WebElement> fullAccessBtn;//this locator been used
    @FindBy(xpath = "//input[@value='Give Full Access']")
    private WebElement fullAccessBtn2;
    @FindBy(xpath = "//td[@class='sorting_1']")
    private List<WebElement> expandButton;
    @FindBy(css = "css=.close > span")
    private WebElement closeBtn;
    @FindBy(css = ".even > .sorting_1")
    private WebElement expandButton2;
    @FindBy(xpath = "//input[@value='Accept all Individual Report']")
    private WebElement acceptIndividualBtn;
    @FindBy(xpath = "//input[@value='Review and Send Invoice']")
    private WebElement reviewSendInvoiceBtn;
    @FindBy(xpath = "//input[@value='Create Invoice']")
    private WebElement createInvoiceBtn;
    @FindBy(css = ".adek-upload-div")
    private WebElement uploadInternalMemo;
    @FindBy(css = ".adek-upload-div")
    private List<WebElement> uploadLetter;
    @FindBy(css = ".adek-upload-popup-btn")
    private WebElement uploadBtn;
    @FindBy(css = "input[type='file']")
    private List<WebElement> uploadPdf;
    @FindBy(id = "ExternalReviewerId")
    private WebElement chairpersonField;
    @FindBy(xpath = "//input[@value='Request for Joint Report']")
    private WebElement requestForJointBtn;
    @FindBy(css = ".air__menuLeft__toggleButton")
    private WebElement leftMenuBtn;
    @FindBy(xpath = "//input[@value='Approve']")
    private WebElement approveBtn;
    @FindBy(xpath = "//button[@class='swal2-confirm swal2-styled' and contains(text(), 'OK')]")
    private List<WebElement> okButton;
    @FindBy(xpath = "//input[starts-with(@class,'btn btn-sm')]")
    private List<WebElement> requestForPaymentBtn;
    @FindBy(id = "Comment")
    private WebElement commentArea;
    @FindBy(css = ".row > .form-control")
    private WebElement uploadDescription; //Test123
    @FindBy(xpath = "//input[starts-with(@class,'btn btn-sm')]")
    private List<WebElement> sendNOLWithoutPayBtn;
    @FindBy(css = "#externalReviewersTable_length .custom-select")
  //  @FindBy(css="#externalReviewersTable_paginate .paginate_button:nth-child(3) > .page-link")
    private WebElement maximizeListDDl;
    @FindBy(css="input[class='btn btn-primary']")
    private List<WebElement> pcReviseButtons;


    public void findProgram(String programName) throws InterruptedException {


        ActionsHelper.waitForListExistance(getApplicationTab(), 100);
        ActionsHelper.selectElementFromList(getApplicationTab(), "Applications");

        ActionsHelper.waitForExistance(getSearchField(), 100);
        sleep(3000);

        getSearchField().sendKeys(programName);
        getSearchField().sendKeys(Keys.ENTER);

    }

    public void startReviewApplication() throws InterruptedException {

        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();

        ActionsHelper.waitForListExistance(getAssignBtn(), 30);
        getAssignBtn().get(0).click();
        Thread.sleep(3000);


        getYesBtn().get(0).click();

//        ActionsHelper.waitForListExistance(getOkButton(), 30);
//       getOkButton().get(0).click();
    }

    public void sendTechnicalReport() throws InterruptedException {

        //ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        //getSelectFirstRecord().click();


        Thread.sleep(5000);
        getTechReportText().sendKeys("hadeel test 1233 automation");
        getSendTechReportBtn().click();
        ActionsHelper.waitForExistance(getYetBtnYesNew(), 30);


        getYetBtnYesNew().click();


    }


    public void selectERList(String sheetName, String columnName) throws InterruptedException {

        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();


        Thread.sleep(5000);
        String System_ER_List = "";
        // read fields from excel file
        final String ER_numbers = ReadWriteHelper.readFromExcel
                ("ERHireEducation", "ER", "Number of ER");
        int numberOfER = Integer.parseInt(ER_numbers);
        System.out.println(numberOfER);

        getMaximizeListDDl().sendKeys("25");
        HashMap table = ActionsHelper.getWebColumnIndex("externalReviewersTable", 0);


        Thread.sleep(3000);

        for (int j = 1; j <= numberOfER; j++) {
            //read from excel the ER list
            final String ER_List_names = ReadWriteHelper.readFromExcel
                    ("ERHireEducation", sheetName, columnName, j);

            System.out.println("ER names from excel " + ER_List_names);

            for (int i = 2; i <= table.size(); i++) {

                System_ER_List = (String) table.get(i);

                System.out.println("check Record " + System_ER_List);

                if (ER_List_names.equalsIgnoreCase(System_ER_List)) {

                    System.out.println(" inside if " + System_ER_List + ER_List_names);
                    ActionsHelper.waitForListExistance(getSelectBtn(), 40);
                    getSelectBtn().get(i - 2).click();
                    System.out.println("i = " + i + "new i = " + (i - 2));
                    getMaximizeListDDl().sendKeys("25");
                    break;
                }


            }
        }// end for j

        ActionsHelper.waitForExistance(getSubmitERList(), 50);
        getSubmitERList().click();
        ActionsHelper.waitForExistance(getYetBtnYesNew(), 30);
        getYetBtnYesNew().click();


    }


    public void inviteERs() throws InterruptedException {

        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();

        ActionsHelper.waitForListExistance(getAssignBtn(), 30);
        //click on accepts ER button
        getAssignBtn().get(0).click();
        Thread.sleep(3000);
        getYesBtn().get(0).click();


    }

    public void fullAccessGivenToPC() throws Exception {


        final String ER_numbers = ReadWriteHelper.readFromExcel
                ("ERHireEducation", "ER", "Number of ER");
        int numberOfER = Integer.parseInt(ER_numbers);
        System.out.println(numberOfER);

        //  HashMap table = ActionsHelper.getWebColumnIndex("externalReviewersTable", 0);
        for (int j = 1; j <= numberOfER; j++) {
            ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
            getSelectFirstRecord().click();
            Thread.sleep(3000);
            if (!ReadWriteHelper.ReadData("headless").equalsIgnoreCase("true")) {
                getExpandButton().get(j - 1).click();
                Thread.sleep(3000);
                ActionsHelper.waitForListExistance(getFullAccessBtn(), 50);
                getFullAccessBtn().get(1).click();
            } else {

                getFullAccessBtn2().click();
            }

            Thread.sleep(3000);
            getYetBtnYesNew().click();
            Thread.sleep(3000);
            ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
            //getSelectFirstRecord().click();
            Thread.sleep(3000);
        }


    }


    public void acceptAllIndividualReports() throws InterruptedException {
        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();
        Thread.sleep(3000);
        ActionsHelper.waitForExistance(getAcceptIndividualBtn(), 50);
        getAcceptIndividualBtn().click();
        ActionsHelper.waitForExistance(getYetBtnYesNew(), 40);
        getYetBtnYesNew().click();

    }

    public void reviewAndSendInvoice() throws InterruptedException {

        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();
        ActionsHelper.waitForExistance(getReviewSendInvoiceBtn(), 50);
        getReviewSendInvoiceBtn().click();

        ActionsHelper.waitForExistance(getCreateInvoiceBtn(), 40);
        getCreateInvoiceBtn().click();
        ActionsHelper.waitForExistance(getYetBtnYesNew(), 40);
        getYetBtnYesNew().click();
        Thread.sleep(2000);
    }


    public void requestForJointReport() throws InterruptedException {
        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();
        ActionsHelper.waitForExistance(getUploadInternalMemo(), 100);
        getUploadInternalMemo().click();
        getUploadPdf().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        Thread.sleep(3000);
        getUploadBtn().click();
        getChairpersonField().sendKeys(ReadWriteHelper.readFromExcel("ERHireEducation",
                "ERList", "ER names", 1));

        getRequestForJointBtn().click();

        getYetBtnYesNew().click();

    }


    public void approveJointReport() {


        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();
        ActionsHelper.waitForExistance(getApproveBtn(), 40);
        getApproveBtn().click();
        getYetBtnYesNew().click();


    }

    public void requestForPayment() throws InterruptedException {


        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();
        ActionsHelper.waitForListExistance(getRequestForPaymentBtn(), 40);
        getRequestForPaymentBtn().get(0).click();
        ActionsHelper.waitForExistance(getCreateInvoiceBtn(), 40);
        getCreateInvoiceBtn().click();
        ActionsHelper.waitForExistance(getYetBtnYesNew(), 40);
        getYetBtnYesNew().click();
        Thread.sleep(2000);


    }

    public void sendNOLWithoutPayment() throws InterruptedException {
        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();
        ActionsHelper.waitForExistance(getCommentArea(), 40);
        getCommentArea().sendKeys("Send Nol without payment to PC");
        ActionsHelper.waitForExistance(getUploadInternalMemo(), 100);
        getUploadLetter().get(0).click();
        getUploadPdf().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        Thread.sleep(2000);
        getUploadDescription().sendKeys("MyDescription");
        Thread.sleep(1000);
        getUploadBtn().click();
        ActionsHelper.waitForListExistance(getSendNOLWithoutPayBtn(), 40);
        getSendNOLWithoutPayBtn().get(1).click();

        getYetBtnYesNew().click();
    }

    public void senBackToAQAC() throws InterruptedException {
        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();
        ActionsHelper.waitForExistance(getCommentArea(), 40);
        getCommentArea().sendKeys("Send to AQAC from PC after revise document");

        ActionsHelper.waitForListExistance(getPcReviseButtons(), 40);
        getPcReviseButtons().get(1).click();

        getYetBtnYesNew().click();
    }
    public void sendToERChairPerson() throws InterruptedException {
        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();
        ActionsHelper.waitForExistance(getCommentArea(), 40);
        getCommentArea().sendKeys("Send to AQAC from PC after revise document");

        ActionsHelper.waitForListExistance(getPcReviseButtons(), 40);
        getPcReviseButtons().get(2).click();

        getYetBtnYesNew().click();
    }
    @Test
    public static void main() {

        // read fields from excel file
        final String ER_numbers = ReadWriteHelper.readFromExcel
                ("ERHireEducation", "ER", "Number of ER");
        int numberOfER = Integer.parseInt(ER_numbers);
        System.out.println(numberOfER);


        for (int j = 1; j <= numberOfER; j++) {
            //read from excel the ER list
            final String ER_List_names = ReadWriteHelper.readFromExcel
                    ("ERHireEducation", "ERList", "ER names", j);

            System.out.println("ER names from excel " + ER_List_names);
        }


    }
}

