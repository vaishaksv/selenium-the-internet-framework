# 🚀 Selenium The Internet Framework

[![Java](https://img.shields.io/badge/Java-11+-blue.svg)](https://www.java.com)
[![Selenium](https://img.shields.io/badge/Selenium-4.15-green.svg)](https://www.selenium.dev)
[![TestNG](https://img.shields.io/badge/TestNG-7.8-orange.svg)](https://testng.org)
[![Maven](https://img.shields.io/badge/Maven-3.6+-red.svg)](https://maven.apache.org)
[![Allure](https://img.shields.io/badge/Allure-2.24-yellow.svg)](https://docs.qameta.io/allure/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

> **A production-ready Selenium automation framework** for [the-internet.herokuapp.com](https://the-internet.herokuapp.com) with enterprise-grade features.

---

## ✨ Key Features

| Feature | Description |
|---------|-------------|
| 🔄 **Auto-Retry** | Failed tests automatically retry (2x) |
| 📊 **Data-Driven** | TestNG DataProviders with multiple data sources |
| ✔️ **Soft Assertions** | Multiple validations per test |
| 📈 **Allure Reports** | Beautiful HTML reports with screenshots |
| ⚡ **Parallel Execution** | Thread-safe with ThreadLocal |
| 🌍 **Multi-Environment** | Dev/Test/Staging/Prod support |
| 🌐 **Cross-Browser** | Chrome, Firefox, Edge, Safari |
| 🤖 **Headless Mode** | CI/CD ready |

---

## 🎥 Demo

![Test Execution](https://img.shields.io/badge/Test%20Execution-Success-brightgreen)
![Coverage](https://img.shields.io/badge/Coverage-15%20Pages-blue)
![Tests](https://img.shields.io/badge/Tests-50+-brightgreen)

---

## 🛠️ Tech Stack

```
Java 11+ | Selenium 4.15 | TestNG 7.8 | Maven | Allure | Log4j2
```

---

## 📋 Prerequisites

- ☕ Java 11 or higher
- 📦 Maven 3.6+
- 🌐 Chrome/Firefox/Edge browser

---

## 🚀 Quick Start

### 1. Clone the Repository

```bash
git clone https://github.com/vaishaksv/selenium-the-internet-framework.git
cd selenium-the-internet-framework
```

### 2. Install Dependencies

```bash
mvn clean install
```

### 3. Run Tests

```bash
# Run all tests
mvn test

# Run with Firefox
mvn test -Dbrowser=firefox

# Run in headless mode
mvn test -Dheadless=true

# Run specific test
mvn test -Dtest=FormAuthenticationTests
```

### 4. Generate Allure Report

```bash
mvn test
mvn allure:serve
```

---

## 📁 Project Structure

```
selenium-the-internet-framework/
├── 📂 src/test/java/
│   ├── 📂 pages/              # Page Object Models (15 pages)
│   │   ├── FormAuthenticationPage.java
│   │   ├── JavaScriptAlertsPage.java
│   │   └── ...
│   ├── 📂 tests/              # Test classes (19 tests)
│   │   ├── FormAuthenticationTests.java
│   │   └── ...
│   └── 📂 utils/              # Framework utilities (16 utilities)
│       ├── BaseActions.java           # Enhanced element actions
│       ├── RetryAnalyzer.java         # Auto-retry failed tests
│       ├── DataProviderFactory.java   # Test data management
│       ├── AssertionHelper.java       # Soft assertions
│       ├── AllureHelper.java          # Reporting
│       └── ...
├── 📂 src/test/resources/
│   ├── testng.xml             # Test suite configuration
│   ├── allure.properties      # Allure config
│   └── config.properties      # App config
├── 📄 pom.xml                 # Maven dependencies
└── 📄 README.md               # This file
```

---

## 🎯 Test Coverage

| Page | Status | Description |
|------|--------|-------------|
| ✅ Add/Remove Elements | Complete | Dynamic element handling |
| ✅ Checkboxes | Complete | Checkbox interactions |
| ✅ Dropdown | Complete | Select operations |
| ✅ JavaScript Alerts | Complete | Alert/Confirm/Prompt |
| ✅ Drag and Drop | Complete | Mouse actions |
| ✅ Hovers | Complete | Mouse hover actions |
| ✅ Frames (iFrame/Nested) | Complete | Frame switching |
| ✅ Multiple Windows | Complete | Window handling |
| ✅ Form Authentication | Complete | Login/Logout flow |
| ✅ Dynamic Loading | Complete | Waits and loading |
| ✅ Data Tables | Complete | Table interactions |
| ✅ File Upload | Complete | File operations |
| ✅ Key Presses | Complete | Keyboard actions |
| ✅ Infinite Scroll | Complete | Scrolling |
| ✅ Challenging DOM | Complete | Dynamic elements |

**Total: 15 Pages | 50+ Test Cases | 100% Coverage**

---

## 📝 Example Test

```java
@Test(description = "Verify successful login")
public void testValidLogin() {
    // Using PageFactory
    var loginPage = new PageFactory(driver).getFormAuthenticationPage();
    
    // Using TestDataBuilder
    var user = TestDataBuilder.validUser();
    
    // Execute login
    loginPage.login(user.username, user.password);
    
    // Verify with Allure reporting
    AllureHelper.logStep("Verify secure area displayed");
    Assert.assertTrue(loginPage.isSecureAreaDisplayed());
    Assert.assertTrue(loginPage.isFlashMessageContains(
        "You logged into a secure area!"
    ));
    
    // Attach screenshot
    AllureHelper.attachScreenshot(driver);
}
```

---

## 🔧 Configuration

### System Properties

| Property | Values | Default | Description |
|----------|--------|---------|-------------|
| `browser` | chrome, firefox, edge, safari | chrome | Browser to use |
| `headless` | true, false | false | Run in headless mode |
| `env` | dev, test, staging, prod | test | Environment |

### Examples

```bash
# Run with Firefox in headless mode
mvn test -Dbrowser=firefox -Dheadless=true

# Run against staging environment
mvn test -Denv=staging

# Run smoke tests only
mvn test -Dgroups=smoke
```

---

## 📊 Reports

### Allure Report

Beautiful HTML reports with:
- Test execution timeline
- Screenshots on failure
- Step-by-step execution
- Environment information

```bash
mvn allure:serve
```

### TestNG Report

```bash
mvn test
# View: target/surefire-reports/index.html
```

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────┐
│                     Test Layer                          │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────────┐  │
│  │   Tests     │ │  Listeners  │ │  Retry Analyzer │  │
│  └─────────────┘ └─────────────┘ └─────────────────┘  │
├─────────────────────────────────────────────────────────┤
│                   Page Object Layer                     │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────────┐  │
│  │   Pages     │ │   Factory   │ │  Page Actions   │  │
│  └─────────────┘ └─────────────┘ └─────────────────┘  │
├─────────────────────────────────────────────────────────┤
│                    Utility Layer                        │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────────┐  │
│  │ BaseActions │ │   Waits     │ │  Screenshot     │  │
│  └─────────────┘ └─────────────┘ └─────────────────┘  │
├─────────────────────────────────────────────────────────┤
│                   Driver Layer                          │
│  ┌─────────────┐ ┌─────────────┐ ┌─────────────────┐  │
│  │   Driver    │ │   Manager   │ │   Factory       │  │
│  └─────────────┘ └─────────────┘ └─────────────────┘  │
└─────────────────────────────────────────────────────────┘
```

---

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Vaishak** - [@vaishaksv](https://github.com/vaishaksv)

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=flat&logo=linkedin&logoColor=white)](https://linkedin.com/in/vaishaksv)
[![Email](https://img.shields.io/badge/Email-D14836?style=flat&logo=gmail&logoColor=white)](mailto:svvaishak@gmail.com)

---

## 🙏 Acknowledgments

- [the-internet.herokuapp.com](https://the-internet.herokuapp.com) - Practice website
- [Selenium](https://www.selenium.dev) - Web automation
- [TestNG](https://testng.org) - Testing framework
- [Allure](https://docs.qameta.io/allure/) - Reporting framework

---

## ⭐ Star this Repository

If you found this project helpful, please give it a ⭐! It helps others discover the project.

---

<p align="center">
  <img src="https://img.shields.io/badge/Made%20with%20%E2%9D%A4%EF%B8%8F%20by-Vaishak-blue" alt="Made with love by Vaishak">
</p>
