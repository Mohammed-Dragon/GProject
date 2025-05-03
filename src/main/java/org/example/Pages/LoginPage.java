package org.example.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginLink = By.linkText("Log in");
    private final By loginModal = By.id("logInModalLabel");
    private final By usernameField = By.id("loginusername");
    private final By passwordField = By.id("loginpassword");
    private final By loginButton = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]");
    private final By closeButton = By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[1]");
    private final By logoutButton = By.id("logout2");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean isLoginFormVisible() {
        return driver.findElement(loginModal).isDisplayed();
    }

    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickCloseButton() {
        driver.findElement(closeButton).click();
    }

    public void logoutIfVisible() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton));
            driver.findElement(logoutButton).click();
        } catch (TimeoutException e) {
            // Logout not visible
        }
    }

    public String getAlertTextAndAccept() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    public void clearFields() {
        driver.findElement(usernameField).clear();
        driver.findElement(passwordField).clear();
    }

    public boolean isLoginModalClosed() {
        try {
            return !driver.findElement(By.id("logInModal")).isDisplayed();
        } catch (NoSuchElementException e) {
            return true;
        }
    }
}
