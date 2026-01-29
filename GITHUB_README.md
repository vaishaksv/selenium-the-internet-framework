# The Internet - Selenium Automation Framework

[![Java](https://img.shields.io/badge/Java-11+-blue.svg)](https://www.java.com)
[![Selenium](https://img.shields.io/badge/Selenium-4.15-green.svg)](https://www.selenium.dev)
[![TestNG](https://img.shields.io/badge/TestNG-7.8-orange.svg)](https://testng.org)
[![Maven](https://img.shields.io/badge/Maven-3.6+-red.svg)](https://maven.apache.org)

A production-ready Selenium automation framework for [the-internet.herokuapp.com](https://the-internet.herokuapp.com) with enterprise-grade features.

## 🚀 Features

- ✅ **Page Object Model** - Clean, maintainable page classes
- ✅ **Auto-Retry** - Failed tests retry automatically (2x)
- ✅ **Data-Driven Testing** - TestNG DataProviders with multiple data sources
- ✅ **Soft Assertions** - Multiple validations per test
- ✅ **Allure Reporting** - Beautiful HTML reports with screenshots
- ✅ **Parallel Execution** - Thread-safe with ThreadLocal
- ✅ **Multi-Environment** - Dev/Test/Staging/Prod support
- ✅ **Cross-Browser** - Chrome, Firefox, Edge, Safari
- ✅ **Headless Mode** - CI/CD ready
- ✅ **WebDriver Events** - Automatic action logging

## 📋 Prerequisites

- Java 11 or higher
- Maven 3.6+
- Chrome/Firefox/Edge browser

## 🛠️ Installation

```bash
# Clone the repository
git clone <your-repo-url>
cd the-internet-tests

# Install dependencies
mvn clean install
```

## 🧪 Running Tests

### Basic Run
```bash
mvn test
```

### With Options
```bash
# Different browser
mvn test -Dbrowser=firefox

# Headless mode
mvn test -Dheadless=true

# Different environment
mvn test -Denv=staging

# All options combined
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

### Generate Allure Report
```bash
mvn test
mvn allure:serve
```

## 📁 Project Structure

```
the-internet-tests/
├── src/test/java/
│   ├── pages/              # Page Object Models (15 pages)
│   ├── tests/              # Test classes
│   └── utils/              # Framework utilities
│       ├── BaseActions.java           # Enhanced element actions
│       ├── RetryAnalyzer.java         # Auto-retry failed tests
│       ├── DataProviderFactory.java   # Test data management
│       ├── AssertionHelper.java       # Soft assertions
│       ├── AllureHelper.java          # Reporting
│       ├── EnvironmentManager.java    # Multi-env support
│       ├── TestDataBuilder.java       # Data builder pattern
│       └── ...
├── src/test/resources/
│   ├── testng.xml          # Test suite configuration
│   ├── allure.properties   # Allure config
│   └── config.properties   # App config
├── pom.xml
└── README.md
```

## 🌐 Test Coverage

| Page | Status |
|------|--------|
| Add/Remove Elements | ✅ |
| Checkboxes | ✅ |
| Dropdown | ✅ |
| JavaScript Alerts | ✅ |
| Drag and Drop | ✅ |
| Hovers | ✅ |
| Frames (iFrame/Nested) | ✅ |
| Multiple Windows | ✅ |
| Form Authentication | ✅ |
| Dynamic Loading | ✅ |
| Data Tables | ✅ |
| File Upload | ✅ |
| Key Presses | ✅ |
| Infinite Scroll | ✅ |
| Challenging DOM | ✅ |

## 📝 Example Test

```java
@Test(description = "Verify successful login")
public void testValidLogin() {
    // Using PageFactory
    var loginPage = new PageFactory(driver).getFormAuthenticationPage();
    
    // Using TestDataBuilder
    var user = TestDataBuilder.validUser();
    
    loginPage.login(user.username, user.password);
    
    // Assertions
    Assert.assertTrue(loginPage.isSecureAreaDisplayed());
    Assert.assertTrue(loginPage.isFlashMessageContains("You logged into a secure area!"));
}
```

## 🔧 Configuration

### System Properties
| Property | Values | Default |
|----------|--------|---------|
| `browser` | chrome, firefox, edge, safari | chrome |
| `headless` | true, false | false |
| `env` | dev, test, staging, prod | test |

### Environment Variables
```bash
export BROWSER=chrome
export HEADLESS=true
export ENV=staging
```

## 🐛 Debugging

- **Screenshots**: Automatically captured on failure (`screenshots/`)
- **Logs**: Detailed logs in `logs/test-execution.log`
- **Allure**: Timeline view of test execution
- **Retry**: Failed tests retry with logging

## 📊 Reports

### Allure Report
```bash
mvn allure:serve
```

### TestNG Report
```bash
mvn test
# View: target/surefire-reports/index.html
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License.

## 🙏 Acknowledgments

- [the-internet.herokuapp.com](https://the-internet.herokuapp.com) - Practice website
- [Selenium](https://www.selenium.dev) - Web automation
- [TestNG](https://testng.org) - Testing framework
- [Allure](https://docs.qameta.io/allure/) - Reporting
