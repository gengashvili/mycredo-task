package myCredo;

import data.LoginDataProvider;
import data.Routes;

import io.qameta.allure.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import steps.AuthPageSteps;
import steps.componentsSteps.LanguageSwitcherPopUpSteps;
import utils.CustomRandomStringUtils;
import utils.RetryAnalyzer;
import utils.config.BaseTest;
import utils.listeners.ScreenshotListener;

@Epic("MyCredo Business Internet Bank - Authorization")
@Feature("Negative Login Scenarios")
@Listeners(ScreenshotListener.class)
public class MyCredoTest extends BaseTest {
    AuthPageSteps authPageSteps;
    LanguageSwitcherPopUpSteps languageSwitcherPopUpSteps;

    @BeforeMethod
    public void setUp() {
        authPageSteps = new AuthPageSteps(driver, wait, softAssert);
        languageSwitcherPopUpSteps = new LanguageSwitcherPopUpSteps(driver, wait, softAssert);

        driver.get(Routes.AUTH_URL);
    }


    @Test(
            priority = 1,
            description = "Login with empty password",
            retryAnalyzer = RetryAnalyzer.class
    )
    @Description("Ensure login button is disabled when password field is empty")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Empty Password Case")
    @Owner("Giorgi Gengashvili")
    public void loginWithEmptyPassword() {
        String userName = RandomStringUtils.randomAlphabetic(10); // შემთხვევითი ასოები

        authPageSteps
                .fillUserName(userName)
                .fillPassword("")
                .verifySubmitButtonIsDisabled();

        softAssert.assertAll();
    }


    @Test(
            priority = 2,
            description = "Login with empty username",
            retryAnalyzer = RetryAnalyzer.class
    )
    @Description("Ensure login button is disabled when username field is empty")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Empty Username Case")
    @Owner("Giorgi Gengashvili")
    public void loginWithEmptyUserName() {
        String password = RandomStringUtils.randomAlphabetic(10); // შემთხვევით ასოები + ციფრები

        authPageSteps
                .fillUserName("")
                .fillPassword(password)
                .verifySubmitButtonIsDisabled();

        softAssert.assertAll();
    }


    @Test(
            priority = 3,
            description = "Login attempt with Georgian letters in username",
            retryAnalyzer = RetryAnalyzer.class
    )
    @Description("Ensure that Georgian characters in the username input are rejected and not accepted by the field")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Invalid Characters in Username - Georgian Letters")
    @Owner("Giorgi Gengashvili")
    public void loginWithGeorgianLettersInUsername() {
        String georgianUserName = CustomRandomStringUtils.randomGeorgianAlphabetic(10); // შემთხვევით ქართული ასოები

        authPageSteps
                .fillUserName(georgianUserName)
                .verifyUserNameInputIsEmpty();

        softAssert.assertAll();
    }


    @Test(
            priority = 4,
            description = "Login attempt with special characters in username",
            retryAnalyzer = RetryAnalyzer.class
    )
    @Description("Ensure that special characters in the username input are rejected and not accepted by the field")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Invalid Characters in Username - Special Characters")
    @Owner("Giorgi Gengashvili")
    public void loginWithSpecialCharactersInUsername() {
        //შემთხვევითი სპეციალური სიმბოლოები, რომელთა ჩაწერაც არის არის ნებადართული მომხმარებლის სახელში
        String specialCharacters = CustomRandomStringUtils.randomSpecialCharacters(10);

        authPageSteps
                .fillUserName(specialCharacters)
                .verifyUserNameInputIsEmpty();

        softAssert.assertAll();
    }


    @Test(
            priority = 5,
            description = "Login with invalid credentials and check error message in different languages",
            dataProvider = "invalidCredentialsDataProvider",
            dataProviderClass = LoginDataProvider.class,
            retryAnalyzer = RetryAnalyzer.class
    )
    @Description("Ensure that invalid credentials show proper localized error message")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Invalid Login Attempt - Localized Error Message")
    @Owner("Giorgi Gengashvili")
    public void loginWithInvalidCredentialsAndCheckErrorMessage(String language, String expectedLoginFormHeader, String username, String password, String expectedErrorMessage) {
        authPageSteps.clickOnLanguageSwitcherButton();
        languageSwitcherPopUpSteps.switchLanguage(language);

        authPageSteps
                .verifyLanguageSwitchedCorrectly(expectedLoginFormHeader)
                .fillUserName(username)
                .fillPassword(password)
                .clickOnSubmitButton()
                .verifyIncorrectDataError(expectedErrorMessage);

        softAssert.assertAll();
    }


}
