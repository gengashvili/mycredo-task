package myCredo;

import data.Routes;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.AuthPageSteps;
import steps.componentsSteps.LanguageSwitcherPopUpSteps;
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

}
