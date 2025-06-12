package myCredo;

import data.LoginData;
import data.Routes;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.AuthPageSteps;
import steps.componentsSteps.LanguageSwitcherPopUpSteps;
import utils.config.BaseTest;

public class MyCredoTest extends BaseTest {
    AuthPageSteps authPageSteps;
    LanguageSwitcherPopUpSteps languageSwitcherPopUpSteps;

    @BeforeMethod
    public void beforeMethod() {
        authPageSteps = new AuthPageSteps(driver, wait, softAssert);
        languageSwitcherPopUpSteps = new LanguageSwitcherPopUpSteps(driver, wait, softAssert);

        driver.get(Routes.AUTH_URL);
    }

    @Test
    public void testSteps() {
        authPageSteps.clickOnLanguageSwitcherButton();
        languageSwitcherPopUpSteps.switchLanguage(LoginData.LANGUAGE_GE);

        authPageSteps
                .verifyLanguageSwitchedCorrectly(LoginData.LOGIN_FORM_HEADER_GE)
                .fillUserName("username")
                .fillPassword("password")
                .clickOnSubmitButton()
                .verifyIncorrectDataError(LoginData.INCORRECT_DATA_ERROR_MESSAGE_GE);

        softAssert.assertAll();
    }

}
