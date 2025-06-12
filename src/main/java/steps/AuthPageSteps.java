package steps;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import pages.AuthPage;

public class AuthPageSteps extends AuthPage{
    private SoftAssert softAssert;

    public AuthPageSteps(WebDriver driver, WebDriverWait wait, SoftAssert softAssert) {
        super(driver, wait);
        this.softAssert = softAssert;
    }

    @Step("Fill user name input with: {userName}")
    public AuthPageSteps fillUserName(String userName) {
        getUserNameInput().sendKeys(userName);

        return this;
    }

    @Step("Fill password input") // სატესტო პაროლია და რა მნიშვნელობა აქვს, მაგრამ რაკი პაროლია რეპორტში არ გავაყოლებ:)
    public AuthPageSteps fillPassword(String password) {
        getPasswordInput().sendKeys(password);

        return this;
    }

    @Step("Click on the submit button")
    public AuthPageSteps clickOnSubmitButton() {
        getSubmitButton().click();

        return this;
    }

    @Step("Verify the submit button is disabled")
    public AuthPageSteps verifySubmitButtonIsDisabled() {
        boolean isButtonDisabled = !getSubmitButton().isEnabled();

        softAssert.assertTrue(isButtonDisabled,"Submit button should be disabled when username or password is empty");

        return this;
    }

    @Step("Verify user name input is empty")
    public AuthPageSteps verifyUserNameInputIsEmpty() {
        boolean isEmpty = getUserNameInput().getAttribute("value").isEmpty();

        softAssert.assertTrue(isEmpty,"User name input should be empty");

        return this;
    }

    @Step("Verify the incorrect data error is displayed with text: '{expectedError}'")
    public AuthPageSteps verifyIncorrectDataError(String expectedError) {
        WebElement errorElement = getIncorrectDataErrorElement();

        softAssert.assertTrue(errorElement.isDisplayed(), "Error message is not displayed");

        String actualError = errorElement.getText();
        softAssert.assertEquals(actualError, expectedError, "Expected error text: '" + expectedError + "' but got: '" + actualError + "'");

        return this;
    }

    @Step("Click on the language switcher button")
    public AuthPageSteps clickOnLanguageSwitcherButton() {
        getLanguageSwitcherButton().click();

        return this;
    }

    @Step("Verify the login form header is: {expectedLoginFormHeader}")
    public AuthPageSteps verifyLanguageSwitchedCorrectly(String expectedLoginFormHeader) {
        String actualLoginFormHeader = getLoginFormHeader().getText().trim();

        softAssert.assertEquals(
                actualLoginFormHeader,
                expectedLoginFormHeader,
                String.format("Login form header mismatch. Expected: '%s', but was: '%s'",
                        expectedLoginFormHeader, actualLoginFormHeader));

        return this;
    }

}
