package pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.config.BasePage;

public class LanguageSwitcherPopUp extends BasePage {
    public LanguageSwitcherPopUp(WebDriverWait wait) {
        super(wait);
    }

    protected WebElement getLanguageButton(String language) {
        By languageLocator = By.xpath("//ul[contains(@class, 'language-list')]/li//p[text() = '" + language+ "']");

        return findElement(languageLocator);
    }

}
