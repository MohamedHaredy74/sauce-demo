# Sauce Demo UI Automation Framework

A Java-based UI test automation framework built with **Selenium WebDriver, TestNG, Maven, Allure, Log4j2, and Jackson**.  
The framework uses the **Page Object Model (POM)** with a **fluent interface style**, reusable action classes, external JSON test data, and a browser factory.

> **Note:** The current project structure and implementation are documented as they exist in the framework. Some components are intentionally kept simple as the framework evolves.

---

## Tech Stack

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Selenium WebDriver | UI/browser automation |
| TestNG | Test execution |
| Maven | Build and dependency management |
| Allure | Test reporting |
| Log4j2 | Logging |
| Jackson | JSON test-data deserialization |
| IntelliJ IDEA | Development environment |

---

## Framework Features

- Selenium WebDriver automation
- TestNG-based test execution
- Maven project management
- Page Object Model
- Fluent Page Object methods
- Reusable browser actions
- Reusable element actions
- Reusable assertion actions
- `ActionsBot` facade for accessing framework actions
- `BrowserFactory` for WebDriver creation
- `FluentWait` synchronization
- JSON-based test data
- Java Records for test-data models
- Log4j2 logging
- Allure reporting
- Allure `@Step` annotations
- TestNG execution listener
- Browser selection through TestNG parameters

---

# Project Structure

```text
sauce-demo/
│
├── .idea/
├── .mvn/
├── allure-report/
├── logs/
│
├── src/
│   │
│   ├── main/
│   │   │
│   │   ├── java/
│   │   │   │
│   │   │   ├── dataModels/
│   │   │   │   ├── AccountData.java
│   │   │   │   ├── AddressData.java
│   │   │   │   ├── LoginData.java
│   │   │   │   ├── PreSignUpData.java
│   │   │   │   └── RegisterData.java
│   │   │   │
│   │   │   ├── engin/
│   │   │   │   ├── ActionsBot.java
│   │   │   │   ├── AssertionActions.java
│   │   │   │   ├── BrowserActions.java
│   │   │   │   ├── BrowserFactory.java
│   │   │   │   └── ElementActions.java
│   │   │   │
│   │   │   ├── pages/
│   │   │   │   ├── AccountCreatedPage.java
│   │   │   │   ├── HomePage.java
│   │   │   │   ├── LoginPage.java
│   │   │   │   └── SignUpPage.java
│   │   │   │
│   │   │   └── utils/
│   │   │       └── LogUtils.java
│   │   │
│   │   └── resources/
│   │       └── log4j2.properties
│   │
│   └── test/
│       │
│       ├── java/
│       │   ├── tests/
│       │   │   ├── TestCase.java
│       │   │   └── LoginTests.java
│       │   │
│       │   └── utils/
│       │
│       └── resources/
│           ├── testData/
│           └── allure.properties
│
├── target/
├── .gitignore
├── pom.xml
└── README.md
```

---

# Architecture

The framework is divided into several responsibilities:

```text
                         TestNG Test
                              │
                              ▼
                         TestCase
                              │
                    ┌─────────┴─────────┐
                    │                   │
                WebDriver             Wait
                    │                   │
                    └─────────┬─────────┘
                              ▼
                        ActionsBot
                              │
             ┌────────────────┼────────────────┐
             │                │                │
             ▼                ▼                ▼
       ElementActions    BrowserActions   AssertionActions
             │                │                │
             └────────────────┼────────────────┘
                              ▼
                        Page Objects
                              │
             ┌────────────────┼────────────────┐
             ▼                ▼                ▼
         HomePage         LoginPage       SignUpPage
                              │
                              ▼
                         Data Models
                              │
                              ▼
                         JSON Test Data
```

---

# Engine Layer

The `engin` package contains the reusable automation infrastructure.

## BrowserFactory

`BrowserFactory` is responsible for creating the WebDriver instance.

The test environment requests a browser through a TestNG parameter:

```java
@Parameters({"browserType"})
public void setUp(@Optional("chrome") String browserType)
```

The browser is then created through:

```java
browserFactory = new BrowserFactory();
driver = browserFactory.createDriver(browserType);
```

This keeps browser creation separate from the test scenarios.

---

## BrowserActions

`BrowserActions` contains browser-level operations.

Examples include:

```java
browserAction.navigateTo(url);
browserAction.getTitle();
browserAction.refresh();
browserAction.back();
browserAction.forward();
```

The purpose is to keep browser operations separate from element-level interactions.

---

## ElementActions

`ElementActions` contains reusable Selenium element interactions.

Examples:

```java
elementAction.click(locator);
elementAction.type(locator, text);
elementAction.typeAndEnter(locator, text);
elementAction.selectByVisibleText(locator, text);
```

This prevents repetitive Selenium code from being duplicated throughout Page Objects.

---

## AssertionActions

`AssertionActions` provides reusable assertion and validation functionality.

Examples include:

```java
assertionAction.assertEqual(actual, expected);
assertionAction.assertTrue(condition, message);
assertionAction.validateElementIsDisplayed(locator);
assertionAction.validateTheTextOfElement(locator, expectedText);
```

Separating assertions from element and browser actions keeps the framework responsibilities clear.

---

# ActionsBot

`ActionsBot` currently acts as a **facade** over the individual action classes.

Instead of injecting three different action classes into every Page Object:

```java
ElementActions
BrowserActions
AssertionActions
```

Page Objects receive one object:

```java
public LoginPage(ActionsBot actionsBot) {
    this.actionsBot = actionsBot;
}
```

The individual action classes are accessed through getters:

```java
actionsBot.getElementAction();
actionsBot.getBrowserAction();
actionsBot.getAssertionAction();
```

Example:

```java
actionsBot
        .getElementAction()
        .click(loginButton);
```

This approach reduces constructor complexity when additional action classes are introduced.

### Current responsibility

```text
ActionsBot
    │
    ├── ElementActions
    ├── BrowserActions
    └── AssertionActions
```

If the framework grows, `ActionsBot` can continue acting as the single entry point for common automation services.

---

# Page Object Model

The framework uses the **Page Object Model**.

Each application page is represented by a dedicated Java class.

Current Page Objects include:

- `HomePage`
- `LoginPage`
- `SignUpPage`
- `AccountCreatedPage`

A Page Object contains:

- Page-specific locators
- Page-specific actions
- Navigation to other pages
- Fluent method chaining

Example:

```java
public LoginPage navigate() {
    actionsBot.getBrowserAction().navigateTo(URL);
    return this;
}
```

---

# Fluent Interface

Page Objects use a fluent style to make test scenarios readable.

Methods that keep the user on the same page return:

```java
return this;
```

Methods that navigate to another page return the next Page Object:

```java
return new SignUpPage(actionsBot);
```

This allows application workflows to be written as chains.

Example:

```java
new LoginPage(actionsBot)
        .navigate()
        .preSignUp(data);
```

For a login workflow:

```java
new LoginPage(actionsBot)
        .navigate()
        .login(loginData);
```

---

# Test Base Class

`TestCase` is the base class for TestNG tests.

It is responsible for:

1. Creating the browser
2. Creating the Selenium wait
3. Initializing `ActionsBot`
4. Providing setup and teardown hooks
5. Closing the browser after the test

Example setup:

```java
@BeforeMethod
@Parameters({"browserType"})
public void setUp(@Optional("chrome") String browserType) {

    browserFactory = new BrowserFactory();

    driver = browserFactory.createDriver(browserType);

    wait = new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(5))
            .pollingEvery(Duration.ofMillis(250))
            .ignoring(NotFoundException.class)
            .ignoring(ElementNotInteractableException.class)
            .ignoring(StaleElementReferenceException.class);

    actionsBot = new ActionsBot(wait);
}
```

The teardown method closes the browser:

```java
@AfterMethod
public void tearDown() {
    browserFactory.quitDriver();
}
```

---

# Synchronization

The framework uses Selenium's `FluentWait`:

```java
new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(5))
        .pollingEvery(Duration.ofMillis(250));
```

The current configuration ignores selected transient Selenium exceptions:

```java
NotFoundException
ElementNotInteractableException
StaleElementReferenceException
```

The goal is to synchronize browser interactions with the application state without relying on fixed delays such as:

```java
Thread.sleep(...)
```

Where possible, action implementations should wait for the specific condition required by the operation, such as element visibility or clickability.

---

# Test Data Management

Test data is externalized under:

```text
src/test/resources/testData/
```

The framework uses data model classes under:

```text
src/main/java/dataModels/
```

For example:

```java
public record LoginData(
        String email,
        String password
) {
}
```

The JSON data is loaded into the model using `JsonReader`:

```java
LoginData loginData =
        JsonReader.read(
                "validLogin.json",
                LoginData.class
        );
```

This keeps test data separate from the test implementation.

### Benefits

- Reusable test data
- Cleaner test methods
- Less hard-coded data
- Type-safe data models
- Easy addition of new test scenarios

---

# Example Test

A login test currently follows this structure:

```java
public class LoginTests extends TestCase {

    @Description("Test to validate successful login with valid credentials")
    @Test
    void loginWithValidData() {

        LoginData loginData =
                JsonReader.read(
                        "validLogin.json",
                        LoginData.class
                );

        new LoginPage(actionsBot)
                .navigate()
                .login(loginData);
    }
}
```

The test itself remains focused on the scenario, while:

- Browser creation is handled by `BrowserFactory`
- Synchronization is handled by `FluentWait`
- Browser interaction is handled by `BrowserActions`
- Element interaction is handled by `ElementActions`
- Page behavior is handled by Page Objects
- Test data is handled by JSON + data models

---

# Logging

The framework uses **Log4j2** for logging.

Configuration:

```text
src/main/resources/log4j2.properties
```

Logs are stored under:

```text
logs/
```

A reusable `LogUtils` class is available to keep logging consistent across the framework.

Example:

```java
LogUtils.info("Setting up the test environment");
```

---

# Allure Reporting

The framework uses **Allure** for test reporting.

Allure configuration:

```text
src/test/resources/allure.properties
```

Important test and framework actions are documented using Allure's `@Step` annotation.

Example:

```java
@Step("Navigate to login page")
public LoginPage navigate() {
    actionsBot.getBrowserAction().navigateTo(URL);
    return this;
}
```

This produces readable steps in the Allure report.

---

# TestNG Listener

The framework registers a TestNG execution listener:

```java
@Listeners({ExcecutionListener.class})
```

The listener can be used to centralize execution-related behavior such as:

- Test lifecycle handling
- Failure handling
- Screenshots
- Logging
- Reporting attachments

---

# Running the Project

## Prerequisites

Install:

- JDK
- Maven
- Chrome or another supported browser
- IntelliJ IDEA (optional)

Verify Java:

```bash
java -version
```

Verify Maven:

```bash
mvn -version
```

---

## Run Tests

From the project root:

```bash
mvn test
```

## Clean and Run

```bash
mvn clean test
```

---

# Browser Configuration

The default browser is Chrome:

```java
@Optional("chrome")
```

The browser can be supplied through the TestNG parameter:

```xml
<parameter name="browserType" value="chrome"/>
```

The `BrowserFactory` is responsible for translating the browser type into the appropriate WebDriver implementation.

---

# Recommended Framework Practices

### Keep locators inside Page Objects

Prefer:

```java
private final By loginButton =
        By.xpath("//form[@action='/login']/button");
```

over placing locators directly inside test classes.

### Keep Selenium implementation inside action classes

Prefer:

```java
elementAction.click(loginButton);
```

instead of:

```java
driver.findElement(loginButton).click();
```

inside every Page Object.

### Keep test data external

Prefer:

```java
LoginData loginData =
        JsonReader.read(
                "validLogin.json",
                LoginData.class
        );
```

instead of hard-coding credentials throughout tests.

### Keep tests focused on behavior

Tests should describe the scenario rather than Selenium implementation details.

---

# Current Design Considerations

The framework currently uses `ActionsBot` as a facade over the action classes:

```text
TestCase
   │
   ▼
ActionsBot
   ├── ElementActions
   ├── BrowserActions
   └── AssertionActions
```

This is useful for the current fluent Page Object implementation because a new Page Object only needs:

```java
new LoginPage(actionsBot);
```

rather than:

```java
new LoginPage(
        elementActions,
        browserActions,
        assertionActions
);
```

If the number of framework services grows significantly, a dedicated `PageContext` or dependency-injection approach can be evaluated to prevent `ActionsBot` from becoming a large service container.

---

# Future Improvements

Potential improvements as the framework grows:

- Introduce a `BasePage` for shared Page Object behavior
- Improve condition-specific waiting inside action classes
- Add automatic screenshots on test failure
- Add TestNG groups such as Smoke and Regression
- Add parallel execution
- Add environment-specific configuration
- Add configurable test-data environments
- Add CI/CD integration
- Add retry handling for appropriate transient failures
- Improve Allure attachments
- Add API testing integration
- Add database validation where required
- Consider dependency injection if the framework's service dependencies become large

---

# Author

**MHAMED HARIDI**

QA Engineer | Software Tester

---

## License

This project is intended for learning, demonstration, and portfolio purposes.
