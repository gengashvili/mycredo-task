package data;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "invalidCredentialsDataProvider")
    public static Object[][] getInvalidCredentials() {
        return new Object[][]{
                {
                        LoginData.LANGUAGE_GE,
                        LoginData.LOGIN_FORM_HEADER_GE,
                        RandomStringUtils.randomAlphabetic(10),
                        RandomStringUtils.randomAlphabetic(10),
                        LoginData.INCORRECT_DATA_ERROR_MESSAGE_GE
                },
                {
                        LoginData.LANGUAGE_SVAN,
                        LoginData.LOGIN_FORM_HEADER_SVAN,
                        RandomStringUtils.randomAlphabetic(10),
                        RandomStringUtils.randomAlphabetic(10),
                        LoginData.INCORRECT_DATA_ERROR_MESSAGE_SVAN
                },
                {
                        LoginData.LANGUAGE_ENG,
                        LoginData.LOGIN_FORM_HEADER_ENG,
                        RandomStringUtils.randomAlphabetic(10),
                        RandomStringUtils.randomAlphabetic(10),
                        LoginData.INCORRECT_DATA_ERROR_MESSAGE_ENG
                }
        };
    }

}
