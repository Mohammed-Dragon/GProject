package org.example.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SignUpPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }


    private final By usernameField = By.id("sign-username");
    private final By passwordField = By.id("sign-password");
    private final By signUpSubmitButton = By.xpath("//button[text()='Sign up']");
    private final By closeButton = By.xpath("//div[@id='signInModal']//button[@class='btn btn-secondary' and text()='Close']");
    private final By signUpModalLabel = By.id("signInModalLabel");



    public void enterUsername(String username) {
        driver.findElement(usernameField).clear();
        driver.findElement(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickSignUp() {
        driver.findElement(signUpSubmitButton).click();
    }

    public String getAlertText() {
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }

    public boolean isFormVisible() {
        return driver.findElement(signUpModalLabel).isDisplayed();
    }

    public void closeSignUpForm() {
        driver.findElement(closeButton).click();
    }

    public String getPasswordFieldType() {
        return driver.findElement(passwordField).getAttribute("type");
    }

    public String getUsernameFieldValue() {
        return driver.findElement(usernameField).getAttribute("value");
    }
}
