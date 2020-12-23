package com.qpros.pages.higher.education.admin;

import com.qpros.helpers.ActionsHelper;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.lang.Thread.sleep;

@Getter
public class ERActions {

    public ERActions(WebDriver driver) {
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


    public void findProgram(String programName) throws InterruptedException {

        Thread.sleep(3000);
        ActionsHelper.waitForExistance(getApplicationTab1(), 30);
        getApplicationTab1().click();
//        ActionsHelper.selectElementFromList(getApplicationTab(),"Applications                            ");


        ActionsHelper.waitForExistance(getSearchField(), 100);
        sleep(3000);

        getSearchField().sendKeys(programName);
        getSearchField().sendKeys(Keys.ENTER);
        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();
    }

    public void acceptInvitationByER() throws InterruptedException {
        ActionsHelper.waitForExistance(getUploadPdfConflict(), 40);
        getUploadPdfConflict().click();
        getUploadPdf().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        Thread.sleep(3000);
        getUploadBtn().click();
        getUploadPdfCommitment().click();
        getUploadPdf().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        Thread.sleep(3000);
        getUploadBtn().click();
        getUploadPdfInvoice().click();
        getUploadPdf().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        Thread.sleep(3000);
        getUploadBtn().click();
        ActionsHelper.waitForListExistance(getAcceptBtn(), 40);
        getAcceptBtn().get(0).click();

        getYesBtn().get(0).click();

    }


    public void submitIndvReport(String programName) throws InterruptedException {
        ActionsHelper.waitForExistance(getSearchField(), 100);
        sleep(3000);

        getSearchField().sendKeys(programName);
        getSearchField().sendKeys(Keys.ENTER);
        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();
        ActionsHelper.waitForExistance(getUploadIndvReport(), 40);
        getUploadIndvReport().click();

        getUploadPdf().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        Thread.sleep(3000);
        ActionsHelper.retryClick(getUploadBtn(), 50);
        Thread.sleep(3000);
        getSubmitButton().click();
        Thread.sleep(3000);
        getYetBtnYesNew().click();

    }

    public void jointReport(String programName) throws InterruptedException {
        ActionsHelper.waitForExistance(getSearchField(), 100);
        sleep(3000);

        getSearchField().sendKeys(programName);
        getSearchField().sendKeys(Keys.ENTER);
        ActionsHelper.waitForExistance(getSelectFirstRecord(), 100);
        getSelectFirstRecord().click();
        ActionsHelper.waitForExistance(getUploadIndvReport(), 40);
        getUploadIndvReport().click();

        getUploadPdf().get(0).sendKeys(ActionsHelper.getImagePath("Amending.pdf"));
        Thread.sleep(3000);
        ActionsHelper.retryClick(getUploadBtn(), 50);
        Thread.sleep(3000);
        getSubmitButton().click();
        Thread.sleep(3000);
        getYetBtnYesNew().click();

    }




}
