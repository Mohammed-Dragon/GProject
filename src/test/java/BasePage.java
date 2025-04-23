import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class BasePage {
    WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\DAR-MG\\Desktop\\DEPI\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.demoblaze.com/");
    }

    @Test(priority = 1)
    public void TC1_SignUpFormOpens() {
        WebElement signupButton = driver.findElement(By.id("signin2"));
        signupButton.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInModalLabel")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"signInModalLabel\"]")).isDisplayed(), "Sign-up form did not appear.");
    }

    @Test(priority = 2)
    public void TC2_ValidSignup() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.findElement(By.xpath("//*[@id=\"sign-username\"]")).sendKeys("okRogkRR3h545tEdfrege8UfYt5U6r7864ok");
        driver.findElement(By.id("sign-password")).sendKeys("Password123!");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Sign up successful.");
        alert.accept();
    }
    /*
    @Test(priority = 3)
    public void TC3_EmptyFieldsSignup() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signin2")));
        driver.findElement(By.id("signin2")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("signInModalLabel")));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"signInModalLabel\"]")).isDisplayed(), "Sign-up form did not appear.");

        driver.findElement(By.id("sign-username")).clear();
        driver.findElement(By.id("sign-password")).clear();
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
        alert.accept();
    }

    @Test(priority = 4)
    public void TC4_ValidatePasswordLength() throws InterruptedException {
        driver.findElement(By.id("sign-username")).sendKeys("userShortPass");
        driver.findElement(By.id("sign-password")).sendKeys("1");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Short Password length.");
        alert.accept();
    }

    @Test(priority = 5)
    public void TC5_ExistingUsername() throws InterruptedException {
        driver.findElement(By.id("sign-username")).clear();
        driver.findElement(By.id("sign-password")).clear();
        driver.findElement(By.id("sign-username")).sendKeys("testuser123");
        driver.findElement(By.id("sign-password")).sendKeys("Password123!");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "This user already exist.");
        alert.accept();
    }

    @Test(priority = 6)
    public void TC6_SignupWithoutUsername() throws InterruptedException {
        driver.findElement(By.id("sign-username")).clear();
        driver.findElement(By.id("sign-password")).sendKeys("Password123!");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
        alert.accept();
    }

    @Test(priority = 7)
    public void TC7_SignupWithoutPassword() throws InterruptedException {
        driver.findElement(By.id("sign-username")).clear();
        driver.findElement(By.id("sign-password")).clear();
        driver.findElement(By.id("sign-username")).sendKeys("newuser");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
        alert.accept();
    }

    @Test(priority = 8)
    public void TC8_FormRetainsInputAfterError() throws InterruptedException {
        driver.findElement(By.id("sign-username")).clear();
        driver.findElement(By.id("sign-password")).clear();
        driver.findElement(By.id("sign-username")).sendKeys("existinguser");
        driver.findElement(By.id("sign-password")).sendKeys("Password123!");
        driver.findElement(By.xpath("//button[text()='Sign up']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
        WebElement usernameField = driver.findElement(By.id("sign-username"));
        Assert.assertEquals(usernameField.getAttribute("value"), "existinguser");
    }

    @Test(priority = 10)
    public void TC10_SignupFormClosesProperly() throws InterruptedException {
        driver.findElement(By.xpath("//div[@id='signInModal']//button[@class='btn btn-secondary' and text()='Close']")).click();
        Thread.sleep(1000);
        Assert.assertFalse(driver.findElement(By.id("signInModal")).isDisplayed(), "Sign-up form did not close properly.");
    }

    @Test(priority = 11)
    public void TC11_VerifyPasswordMasked() throws InterruptedException {
        driver.findElement(By.id("signin2")).click();
        Thread.sleep(1000);
        WebElement passwordField = driver.findElement(By.id("sign-password"));
        Assert.assertEquals(passwordField.getAttribute("type"), "password", "Password is not masked");
    }*/
    @AfterTest
    public void tearDown() {

        driver.close();

    }
}
