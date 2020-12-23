package com.qpros.pages.higher.education.admin;

import com.github.javafaker.Faker;
import com.qpros.helpers.ActionsHelper;
import com.qpros.pages.Data;
import com.qpros.pages.Locators;
import com.qpros.utils.Month;
import lombok.Getter;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Getter
public class Providers {

    public static String randomName = "";
    public static String createdProvider = "";
    WebElement createdProviderName = null;

    public Providers(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = Locators.PROVIDER_TAB)
    private List<WebElement> providerTab;
    @FindBy(className = Locators.PROVIDER_DIV)
    private List<WebElement> providerDiv;
    @FindBy(css = Locators.ADD_PROVIDER_BUTTON)
    private List<WebElement> addProviderButton;
    @FindBy(id = Locators.NAME_ENGLISH_FIELD)
    private WebElement nameEnglishField;
    @FindBy(id = Locators.NAME_ARABIC_FIELD)
    private WebElement nameArabicField;
    @FindBy(id = Locators.REFERENCE_NUMBER_FIELD)
    private WebElement referenceNumberField;
    @FindBy(css = Locators.PROVIDER_TYPE_ID_LIST)
    private List<WebElement> providerTypeIdList;
    @FindBy(id = Locators.PROVIDER_TYPE_ID)
    private WebElement providerTypeId;
    @FindBy(id = Locators.PROVIDER_CATEGORY_ID_LIST)
    private List<WebElement> providerCategoryIdList;
    @FindBy(id = Locators.PROVIDER_UNIT_ID_LIST)
    private List<WebElement> providerUnitIdList;
    @FindBy(id = Locators.PROVIDER_ZONE_ID_LIST)
    private List<WebElement> ProviderZoneIdList;
    @FindBy(id = Locators.WEBSITE)
    private WebElement website;
    @FindBy(id = Locators.PROVIDER_STATUS_ID)
    private List<WebElement> ProviderStatusId;
    @FindBy(id = Locators.AUTHORIZATION_REFERENCE)
    private WebElement authorizationReference;
    @FindBy(id = Locators.ISSUANCE_ON)
    private WebElement issuanceOn;
    @FindBy(id = Locators.EXPIRY_ON)
    private WebElement expiryOn;
    @FindBy(id = Locators.PROVIDER_ZONE_ID)
    private WebElement providerZoneId;
    @FindBy(id = Locators.ADDRESS_ENGLISH)
    private WebElement addressEnglish;
    @FindBy(id = Locators.ADDRESS_ARABIC)
    private WebElement addressArabic;
    @FindBy(id = Locators.LONGITUDE)
    private WebElement longitude;
    @FindBy(id = Locators.LATITUDE)
    private WebElement latitude;
    @FindBy(xpath = Locators.PROVIDER_SAVE_BUTTON)
    private WebElement providerSaveButton;
    @FindBy(xpath = Locators.PROVIDER_NEXT_BUTTON)
    private WebElement providerNextButton;
    @FindBy(xpath = Locators.PROVIDER_YES_BUTTON)
    private WebElement providerYesButton;
    @FindBy(xpath = Locators.PROVIDER_CANCEL_BUTTON)
    private WebElement providerCancelButton;
    @FindBy(xpath = Locators.PROVIDER_OK_BUTTON)
    private WebElement providerOKButton;
    @FindBy(xpath = Locators.PROVIDER_TOKEN_SEARCH)
    private WebElement providerTokenSearch;
    @FindBy(xpath = Locators.PROVIDER_TOKEN_SEND)
    private WebElement providerTokenSend;
    @FindBy(xpath = Locators.PROVIDER_TOKEN_SEND_TOKEN_EXISTING_BUTTON)
    private WebElement providerTokenSendTokenExistingButton;
    @FindBy(id = Locators.PROVIDER_TOKEN_REG_NUMBER)
    private List<WebElement> providerTokenRegNumber;
    @FindBy(id = Locators.PROVIDER_TOKEN_REG_NUMBER_CONTAINER)
    private List<WebElement> providerTokenRegNumberContainer;
    @FindBy(id = Locators.PROVIDER_TOKEN_EMAIL)
    private WebElement providerTokenEmail;
    @FindBy(css = Locators.PROVIDER_TOKEN_SELECT_FROM_LIST)
    private WebElement providerTokenSelectFromList;
    @FindBy(css = Locators.PROVIDER_TOKEN_SELECT_FROM_LIST_SEARCH_BOX)
    private WebElement providerTokenSelectFromListSearchBox;
    @FindBy(id = Locators.SEARCH_BOX)
    private WebElement searchBox;
    @FindBy(id = Locators.ddl_Page_Size)
    private WebElement ddlPageSize;
    @FindBy(css = Locators.EMAIL_URL_BODY)
    private WebElement emailUrlBody;

    Faker faker = new Faker();

    public void addProvider()  {
        ActionsHelper.waitForListExistance(getProviderTab(), 50);
        ActionsHelper.selectElementFromList(getProviderTab(), Data.PROVIDERS);
        ActionsHelper.waitForListExistance(getAddProviderButton(), 50);
        getAddProviderButton().get(0).click();
        randomName = Data.RANDOM_NAME;
        ActionsHelper.waitForExistance(getProviderTypeId(), 30);
        getProviderTypeId().sendKeys("Federal");
        getProviderNextButton().click();
        //ActionsHelper.selectElementFromList(getProviderTypeIdList(),"Federal");
        ActionsHelper.waitForExistance(getNameEnglishField(), 30);
        getNameEnglishField().sendKeys(randomName);
        getNameArabicField().sendKeys(randomName);
        getReferenceNumberField().sendKeys(randomName);
        getProviderCategoryIdList().get(0).sendKeys("University");
        getProviderUnitIdList().get(0).sendKeys("College");
        getWebsite().sendKeys(faker.company().url());
        getProviderStatusId().get(0).sendKeys("Active");
        getAuthorizationReference().sendKeys(randomName);
        Calendar cal= ActionsHelper.getTodayDateFromCalender();
        getIssuanceOn().click();
        try {
            ActionsHelper.HandleKendoDateTimePicker(
                    String.valueOf(cal.get(Calendar.DAY_OF_MONTH)),
                    Month.get(cal.get(Calendar.MONTH)).name(),
                    String.valueOf(cal.get(Calendar.YEAR)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getExpiryOn().click();
        try {
            ActionsHelper.HandleKendoDateTimePicker(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)),
                    Month.get(cal.get(Calendar.MONTH)).name(),
                    String.valueOf(cal.get(Calendar.YEAR)+1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getProviderZoneId().sendKeys("Abu Dhabi");
        getAddressEnglish().sendKeys(randomName);
        getAddressArabic().sendKeys(randomName);
        getLatitude().sendKeys(faker.address().latitude());
        getLongitude().sendKeys(faker.address().longitude());
        getProviderSaveButton().click();
        getProviderYesButton().click();
        ActionsHelper.waitForExistance(getProviderOKButton(), 30);
        getProviderOKButton().click();


    }

    public void sendProviderToken() throws Exception {
        ActionsHelper.waitForListExistance(getProviderTab(), 50);
        ActionsHelper.selectElementFromList(getProviderTab(), "Providers Token");
        ActionsHelper.waitForExistance(getProviderTokenSendTokenExistingButton(), 30);
        getProviderTokenSendTokenExistingButton().click();
        ActionsHelper.waitForListExistance(getProviderTokenRegNumberContainer(), 30);
        getProviderTokenRegNumberContainer().get(0).click();
        ActionsHelper.waitForExistance(getProviderTokenSearch(), 30);
        getProviderTokenSearch().sendKeys(randomName + Keys.ENTER);
        getProviderTokenEmail().sendKeys(String.format("qprosautomation+%s@gmail.com", randomName.substring(16)));
        Thread.sleep(3000);
        ActionsHelper.waitForExistance(getProviderTokenSend(), 30);
        getProviderTokenSend().click();
        ActionsHelper.waitForExistance(getProviderOKButton(), 30);
        getProviderOKButton().click();


    }

    public void createProviderAccountVerification() throws Exception {

        Thread.sleep(5000);
        ActionsHelper.navigateTo("http://autoserver:8080/externalhelper-email/api/v1/email");
        ActionsHelper.waitForExistance(getEmailUrlBody(), 2);
        ActionsHelper.navigateTo(removeLastCharacter(getEmailUrlBody().getText()));
    }


    public void createFullProvider() throws Exception {
        //Create new provider without partners
        addProvider();
        sendProviderToken();
        //createProviderAccountVerification();
    }

    public void createFullProviderWithPartners() throws Exception {
        //Create new provider
        addProvider();
        sendProviderToken();
        //createProviderAccountVerification();
    }

    public static String removeLastCharacter(String str) {
        return Optional.ofNullable(str)
                .filter(sStr -> sStr.length() != 0)
                .map(sStr -> sStr.substring(0, sStr.length() - 1))
                .orElse(str);
    }
}
