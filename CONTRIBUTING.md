# Contributing to Selenium The Internet Framework

First off, thank you for considering contributing to this project! 🎉

## 📋 How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check the existing issues to avoid duplicates.

When creating a bug report, include:
- **Clear title** and description
- **Steps to reproduce**
- **Expected behavior**
- **Actual behavior**
- **Screenshots** (if applicable)
- **Environment details** (OS, Browser, Java version)

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. Include:
- **Use case** - Why is this enhancement needed?
- **Detailed description**
- **Possible implementation** (optional)

### Pull Requests

1. **Fork** the repository
2. **Create a branch** from `main`
   ```bash
   git checkout -b feature/your-feature-name
   ```
3. **Make your changes**
4. **Run tests** locally
   ```bash
   mvn clean test
   ```
5. **Commit** with a clear message
   ```bash
   git commit -m "feat: Add new feature X"
   ```
6. **Push** to your fork
   ```bash
   git push origin feature/your-feature-name
   ```
7. **Open a Pull Request**

### Commit Message Guidelines

We follow [Conventional Commits](https://www.conventionalcommits.org/):

- `feat:` - New feature
- `fix:` - Bug fix
- `docs:` - Documentation changes
- `style:` - Code style changes (formatting, semi-colons, etc)
- `refactor:` - Code refactoring
- `test:` - Adding or updating tests
- `chore:` - Build process or auxiliary tool changes

Example:
```
feat: Add drag and drop test cases

- Added DragAndDropTests class
- Implemented dragAtoBUsingJS method
- Added verification for element swap

Closes #123
```

## 🧪 Testing Guidelines

### Writing Tests

1. **Use Page Object Model** - All interactions should go through Page classes
2. **Add meaningful descriptions** - Use `@Test(description = "...")`
3. **Group tests appropriately** - Use `@Test(groups = {...})`
4. **Use soft assertions** for multiple validations
5. **Add Allure annotations** for reporting

Example:
```java
@Test(description = "Verify login with valid credentials",
      groups = {"smoke", "regression"})
@Severity(SeverityLevel.CRITICAL)
public void testValidLogin() {
    AllureHelper.logStep("Navigate to login page");
    var page = new FormAuthenticationPage(driver);
    page.navigateTo().loginWithValidCredentials();
    
    AllureHelper.logStep("Verify successful login");
    Assert.assertTrue(page.isSecureAreaDisplayed());
}
```

## 📁 Project Structure

When adding new features:
- **Pages** go in `src/test/java/pages/`
- **Tests** go in `src/test/java/tests/`
- **Utilities** go in `src/test/java/utils/`
- **Resources** go in `src/test/resources/`

## 🎯 Code Style

- Follow **Java naming conventions**
- Use **meaningful variable names**
- Add **JavaDoc comments** for public methods
- Keep methods **focused and small**
- Maximum line length: **120 characters**

## 🙏 Thank You!

Your contributions help make this project better for everyone!
