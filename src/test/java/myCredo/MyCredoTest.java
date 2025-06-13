package myCredo;

import data.LoginDataProvider;
import data.Routes;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import steps.AuthPageSteps;
import steps.componentsSteps.LanguageSwitcherPopUpSteps;
import utils.CustomRandomStringUtils;
import utils.RetryAnalyzer;
import utils.config.BaseTest;
import utils.listeners.ScreenshotListener;

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

    @Test(priority = 1, retryAnalyzer = RetryAnalyzer.class)
    public void loginWithEmptyPassword() {
        String userName = RandomStringUtils.randomAlphabetic(10); // შემთხვევითი ასოები

        authPageSteps
                .fillUserName(userName)
                .fillPassword("")
                .verifySubmitButtonIsDisabled();
        Assert.fail();
        softAssert.assertAll();
    }

    @Test(priority = 2, retryAnalyzer = RetryAnalyzer.class)
    public void loginWithEmptyUserName() {
        String password = RandomStringUtils.randomAlphabetic(10); // შემთხვევით ასოები + ციფრები

        authPageSteps
                .fillUserName("")
                .fillPassword(password)
                .verifySubmitButtonIsDisabled();

        softAssert.assertAll();
    }

    @Test(priority = 3, retryAnalyzer = RetryAnalyzer.class)
    public void loginWithGeorgianLettersInUsername() {
        String georgianUserName = CustomRandomStringUtils.randomGeorgianAlphabetic(10); // შემთხვევით ქართული ასოები

        authPageSteps
                .fillUserName(georgianUserName)
                .verifyUserNameInputIsEmpty();

        softAssert.assertAll();
    }

    @Test(priority = 4, retryAnalyzer = RetryAnalyzer.class)
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
            dataProvider = "invalidCredentialsDataProvider",
            dataProviderClass = LoginDataProvider.class
            , retryAnalyzer = RetryAnalyzer.class
    )
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
