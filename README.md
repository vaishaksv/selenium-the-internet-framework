# The Internet - Production Ready Selenium Framework

## 🚀 Major Improvements Added

### 1. **Retry Mechanism** (`RetryAnalyzer.java`)
- Automatically retries failed tests (up to 2 times)
- Reduces flaky test failures
- No annotation needed - applied globally via `AnnotationTransformer`

### 2. **Data Provider Factory** (`DataProviderFactory.java`)
- Centralized test data management
- Multiple data sources (inline, external, dynamic)
- Supports parallel data-driven tests

### 3. **Soft Assertions** (`AssertionHelper.java`)
- Run multiple assertions in one test
- Collect all failures before failing
- Thread-safe implementation

```java
AssertionHelper.initSoftAssert();
AssertionHelper.softAssertTrue(condition1, "Message 1");
AssertionHelper.softAssertEquals(actual, expected, "Message 2");
AssertionHelper.assertAll(); // Reports all failures
```

### 4. **Custom Exceptions** (`FrameworkException.java`)
- Better error categorization
- Descriptive error messages
- Types: `ElementNotFoundException`, `TimeoutException`, `InvalidTestDataException`

### 5. **Allure Reporting** (`AllureHelper.java`)
- Rich HTML reports with screenshots
- Test steps and attachments
- Severity levels and categorization
- Run: `mvn allure:serve`

### 6. **WebDriver Event Listener** (`DriverEventListener.java`)
- Logs all driver actions automatically
- Detailed element descriptions
- Helps debug test failures

### 7. **Page Factory** (`PageFactory.java`)
- Centralized page creation
- Ensures pages are properly initialized
- Cleaner test code

```java
PageFactory factory = new PageFactory(driver);
var loginPage = factory.getFormAuthenticationPage();
```

### 8. **Environment Manager** (`EnvironmentManager.java`)
- Multi-environment support (dev, test, staging, prod)
- Environment-specific configurations
- Run: `mvn test -Denv=staging`

### 9. **Test Data Builder** (`TestDataBuilder.java`)
- Fluent builder pattern for test data
- Predefined test data sets
- Readable and maintainable

```java
var user = new TestDataBuilder()
    .withUsername("test")
    .withPassword("pass")
    .withEmail("test@example.com")
    .build();
```

### 10. **Annotation Transformer** (`AnnotationTransformer.java`)
- Auto-applies retry analyzer to all tests
- Sets default timeouts
- Adds default descriptions

---

## 📊 Running Tests

### Basic Run
```bash
mvn test
```

### With Parameters
```bash
# Different browser
mvn test -Dbrowser=firefox

# Headless mode
mvn test -Dheadless=true

# Different environment
mvn test -Denv=staging

# All combined
mvn test -Dbrowser=chrome -Dheadless=true -Denv=test
```

### Run Specific Tests
```bash
# By class
mvn test -Dtest=FormAuthenticationTests

# By method
mvn test -Dtest=FormAuthenticationTests#testValidLogin

# By group
mvn test -Dgroups=smoke
mvn test -Dgroups=regression
```

### Allure Reports
```bash
# Generate and serve
mvn allure:serve

# Generate only
mvn allure:report
```

---

## 📁 Updated Project Structure

```
the-internet-tests/
├── src/test/java/
│   ├── pages/
│   │   └── [All Page Objects]
│   ├── tests/
│   │   ├── BaseTest.java
│   │   ├── DataDrivenTests.java      # NEW: Data provider examples
│   │   ├── FrameworkFeatureTests.java # NEW: Feature demonstrations
│   │   ├── HomePageTests.java         # NEW: With Allure
│   │   └── [Other test classes]
│   └── utils/
│       ├── AllureHelper.java          # NEW: Allure reporting
│       ├── AnnotationTransformer.java # NEW: Auto retry
│       ├── AssertionHelper.java       # NEW: Soft assertions
│       ├── BaseActions.java           # Enhanced with retry
│       ├── Constants.java             # Centralized constants
│       ├── DataProviderFactory.java   # NEW: Test data
│       ├── DriverEventListener.java   # NEW: Action logging
│       ├── DriverManager.java         # Enhanced
│       ├── EnvironmentManager.java    # NEW: Multi-env support
│       ├── FrameworkException.java    # NEW: Custom exceptions
│       ├── LoggerUtil.java
│       ├── PageFactory.java           # NEW: Page creation
│       ├── RetryAnalyzer.java         # NEW: Test retry
│       ├── ScreenshotHelper.java
│       ├── TestDataBuilder.java       # NEW: Data builder
│       └── TestListener.java          # Enhanced
└── src/test/resources/
    ├── testng.xml                     # Updated with listeners
    ├── allure.properties              # NEW: Allure config
    ├── log4j2.xml
    └── config.properties
```

---

## 🎯 Best Practices Implemented

| Practice | Implementation |
|----------|----------------|
| **DRY Principle** | Constants, Page Factory, BaseActions |
| **Single Responsibility** | Each class has one purpose |
| **Open/Closed** | Easy to add new pages/tests |
| **Retry Flaky Tests** | Automatic retry with RetryAnalyzer |
| **Parallel Execution** | ThreadLocal for driver, parallel data providers |
| **Reporting** | Allure + custom listeners + screenshots |
| **Environment Config** | EnvironmentManager for multi-env support |
| **Test Data** | Builder pattern + DataProviderFactory |
| **Logging** | Event listener + Log4j2 |
| **Soft Assertions** | Multiple validations per test |

---

## 🔧 Configuration Options

### System Properties
```bash
-Dbrowser=chrome|firefox|edge|safari
-Dheadless=true|false
-Denv=dev|test|staging|prod
-Dgrid.url=http://localhost:4444/wd/hub
```

### Environment Variables
```bash
export BROWSER=chrome
export HEADLESS=true
export ENV=staging
```

---

## 📈 Test Groups

- `smoke` - Critical path tests (fast)
- `regression` - Full test suite
- `data-driven` - Tests with data providers

---

## 🐛 Debugging Features

1. **Auto Screenshots** - On every failure
2. **Retry Logs** - Shows retry attempts
3. **Event Logging** - Every click, type, navigation
4. **Allure Timeline** - Visual test execution
5. **Thread Dumps** - If tests hang

---

## 🎓 Example Usage

### Simple Test
```java
@Test(description = "Verify login")
public void testLogin() {
    var loginPage = new PageFactory(driver).getFormAuthenticationPage();
    loginPage.loginWithValidCredentials();
    Assert.assertTrue(loginPage.isSecureAreaDisplayed());
}
```

### Data-Driven Test
```java
@Test(dataProvider = "loginDataProvider", 
      dataProviderClass = DataProviderFactory.class)
public void testLogin(String user, String pass, boolean shouldPass) {
    // Runs once per data row
}
```

### With Soft Assertions
```java
@Test
public void testMultipleThings() {
    AssertionHelper.initSoftAssert();
    AssertionHelper.softAssertTrue(check1, "Check 1");
    AssertionHelper.softAssertTrue(check2, "Check 2");
    AssertionHelper.assertAll();
}
```

### With Allure
```java
@Test
public void testWithReporting() {
    AllureHelper.setFeature("Authentication");
    AllureHelper.logStep("Step 1: Navigate");
    // ... test code
    AllureHelper.attachScreenshot(driver);
}
```
