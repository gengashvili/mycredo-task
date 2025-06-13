## mycredo-task
Automated negative login test cases for MyCredo Business Internet Banking in three languages (GEO, SVAN, ENG).

## Technologies & Tools

- Java 21

- Selenium WebDriver 4.33.0

- TestNG 7.11.0

- WebDriverManager 6.1.0 (io.github.bonigarcia)

- Allure TestNG & Allure Java Commons 2.29.1

- Apache Commons Lang3 (RandomStringUtils) 3.17.0

- Maven (Compiler Plugin, Surefire Plugin) 3.2.5

## Test Cases

The project includes the following test cases:

**Login with Empty Password**

- Priority: 1

- Severity: Critical

- Description: Verifies that the login button is disabled when the password field is empty.


**Login with Empty Username**

- Priority: 2

- Severity: Critical

- Description: Verifies that the login button is disabled when the username field is empty.


**Login with Invalid Characters in Username - Georgian**

- Priority: 3

- Severity: Critical

- Description: Verifies that Georgian letters in the username field are rejected and the field remains empty.


**Login with Invalid Characters in Username - Special Characters**

- Priority: 4

- Severity: Critical

- Description: Verifies that special characters in the username field are rejected and the field remains empty.


**Login with Invalid Credentials and Localized Error Messages**

- Priority: 5

- Severity: Critical

- Description: Verifies that incorrect username/password combinations display the appropriate localized error message in Georgian, Megrelian, or Svan.

- Data provider: invalidCredentialsDataProvider (language, expected header, username, password, expected error message)
- 
 ##  Design Pattern
The framework follows the Page Object Model (POM) design pattern:

- Separation of concerns: Page classes encapsulate web element locators, while step classes define actions with @Step annotations for readability and reporting.


- Maintainability: Changes to UI locators or flows are localized within page or step classes without affecting test logic.

## Parallel Execution

**Tests are executed in parallel to reduce total runtime:**

- Configured in testng.xml  to run multiple TestNG tests concurrently.

- RetryAnalyzer re-runs flaky tests up to MAX_RETRY_COUNT times to increase stability in parallel environments.


## Wait Strategies

**Synchronized interactions use explicit waits or fluent waits instead of implicit waits:**

- Explicit Wait: WebDriverWait with ExpectedConditions in BasePage.findElement() ensures elements are visible before interacting.

- Avoid Implicit Wait to prevent unpredictable wait behavior when used with explicit waits.
