package steps.componentsSteps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;
import pages.components.LanguageSwitcherPopUp;

public class LanguageSwitcherPopUpSteps extends LanguageSwitcherPopUp {
    private SoftAssert softAssert;

    public LanguageSwitcherPopUpSteps(WebDriver driver, WebDriverWait wait, SoftAssert softAssert) {
        super(wait);
        this.softAssert = softAssert;
    }

    @Step("Switch language to: {language}")
    public LanguageSwitcherPopUpSteps switchLanguage(String language) {
        getLanguageButton(language).click();

        return this;
    }

}
