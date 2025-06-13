package myCredo;

import data.Routes;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.AuthPageSteps;
import steps.componentsSteps.LanguageSwitcherPopUpSteps;
import utils.CustomRandomStringUtils;
import utils.config.BaseTest;

public class MyCredoTest extends BaseTest {
    AuthPageSteps authPageSteps;
    LanguageSwitcherPopUpSteps languageSwitcherPopUpSteps;

    @BeforeMethod
    public void setUp() {
        authPageSteps = new AuthPageSteps(driver, wait, softAssert);
        languageSwitcherPopUpSteps = new LanguageSwitcherPopUpSteps(driver, wait, softAssert);

        driver.get(Routes.AUTH_URL);
    }

    @Test(priority = 1)
    public void loginWithEmptyPassword() {
        String userName = RandomStringUtils.randomAlphabetic(10); // შემთხვევითი ასოები

        authPageSteps
                .fillUserName(userName)
                .fillPassword("")
                .verifySubmitButtonIsDisabled();

        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void loginWithEmptyUserName() {
        String password = RandomStringUtils.randomAlphabetic(10); // შემთხვევით ასოები + ციფრები

        authPageSteps
                .fillUserName("")
                .fillPassword(password)
                .verifySubmitButtonIsDisabled();

        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void loginWithGeorgianLettersInUsername() {
        String georgianUserName = CustomRandomStringUtils.randomGeorgianAlphabetic(10); // შემთხვევით ქართული ასოები

        authPageSteps
                .fillUserName(georgianUserName)
                .verifyUserNameInputIsEmpty();

        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void loginWithSpecialCharactersInUsername() {
        //შემთხვევითი სპეციალური სიმბოლოები, რომელთა ჩაწერაც არის არის ნებადართული მომხმარებლის სახელში
        String specialCharacters = CustomRandomStringUtils.randomSpecialCharacters(10);

        authPageSteps
                .fillUserName(specialCharacters)
                .verifyUserNameInputIsEmpty();

        softAssert.assertAll();
    }

}
