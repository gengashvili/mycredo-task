package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.config.BasePage;

public class AuthPage extends BasePage{
    
    protected By
            userNameInputLocator = By.xpath("//input[@id = 'userName']"),
            passwordInputLocator = By.xpath("//input[@id = 'newPass']"),
            submitButtonLocator = By.xpath("//button[@id = 'submitAuth']"),
            incorrectDataErrorLocator = By.xpath("//p[@id = 'growlText']"),
            languageSwitcherButtonLocator = By.xpath("//div[@id = 'languageSwitcherBtn']"),
            loginFormHeaderLocator = By.xpath("//section[@id = 'authloading']//p[contains(@class, 'header')]");

    public AuthPage(WebDriver driver, WebDriverWait wait) {
        super(wait);
    }

    protected WebElement getUserNameInput() {
        return findElement(userNameInputLocator);
    }

    protected WebElement getPasswordInput() {
        return findElement(passwordInputLocator);
    }

    protected WebElement getSubmitButton() {
        return findElement(submitButtonLocator);
    }

    protected WebElement getIncorrectDataErrorElement() {
        return findElement(incorrectDataErrorLocator);
    }

    protected WebElement getLanguageSwitcherButton() {
        return findElement(languageSwitcherButtonLocator);
    }

    protected WebElement getLoginFormHeader() {
        return findElement(loginFormHeaderLocator);
    }

}
