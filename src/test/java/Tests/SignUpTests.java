package Tests;

import Base.OpenDriver;
import org.example.Pages.HomePage;
import org.example.Pages.SignUpPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTests extends OpenDriver {

    @Test(priority = 1)
    public void TC1_SignUpFormOpens() {
        HomePage home = new HomePage(driver);
        home.openSignUpForm();
        SignUpPage signUp = new SignUpPage(driver);
        Assert.assertTrue(signUp.isFormVisible(), "Sign-up form did not appear.");
    }

    @Test(priority = 2)
    public void TC2_ValidSignup() {
        HomePage home = new HomePage(driver);
        home.openSignUpForm();
        SignUpPage signUp = new SignUpPage(driver);
        signUp.enterUsername("f8egfgewfgwagq9trgwuh7ylesflg7o8");
        signUp.enterPassword("Password123!");
        signUp.clickSignUp();
        String alertText = signUp.getAlertText();
        Assert.assertEquals(alertText, "Sign up successful.");
    }

    @Test(priority = 3)
    public void TC3_EmptyFieldsSignup() {
        HomePage home = new HomePage(driver);
        home.openSignUpForm();
        SignUpPage signUp = new SignUpPage(driver);
        signUp.enterUsername("");
        signUp.enterPassword("");
        signUp.clickSignUp();
        String alertText = signUp.getAlertText();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
    }

    @Test(priority = 4)
    public void TC4_ValidatePasswordLength() {
        HomePage home = new HomePage(driver);
        home.openSignUpForm();
        SignUpPage signUp = new SignUpPage(driver);
        signUp.enterUsername("f8egfgewfgwagq9trwewegwuh7ylesflg7o8");
        signUp.enterPassword("1");
        signUp.clickSignUp();
        String alertText = signUp.getAlertText();
        Assert.assertEquals(alertText, "Short Password length.");
    }

    @Test(priority = 5)
    public void TC5_ExistingUsername() {
        HomePage home = new HomePage(driver);
        home.openSignUpForm();
        SignUpPage signUp = new SignUpPage(driver);
        signUp.enterUsername("f8egfgewfgwagq9trgwuh7ylesflg7o8");
        signUp.enterPassword("Password123!");
        signUp.clickSignUp();
        String alertText = signUp.getAlertText();
        Assert.assertEquals(alertText, "This user already exist.");
    }

    @Test(priority = 6)
    public void TC6_SignupWithoutUsername() {
        HomePage home = new HomePage(driver);
        home.openSignUpForm();
        SignUpPage signUp = new SignUpPage(driver);
        signUp.enterUsername("");
        signUp.enterPassword("Password123!");
        signUp.clickSignUp();
        String alertText = signUp.getAlertText();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
    }

    @Test(priority = 7)
    public void TC7_SignupWithoutPassword() {
        HomePage home = new HomePage(driver);
        home.openSignUpForm();
        SignUpPage signUp = new SignUpPage(driver);
        signUp.enterUsername("newuser");
        signUp.enterPassword("");
        signUp.clickSignUp();
        String alertText = signUp.getAlertText();
        Assert.assertEquals(alertText, "Please fill out Username and Password.");
    }

    @Test(priority = 8)
    public void TC8_FormRetainsInputAfterError() {
        HomePage home = new HomePage(driver);
        home.openSignUpForm();
        SignUpPage signUp = new SignUpPage(driver);
        signUp.enterUsername("existinguser");
        signUp.enterPassword("Password123!");
        signUp.clickSignUp();
        signUp.getAlertText();
        Assert.assertEquals(signUp.getUsernameFieldValue(), "existinguser");
    }

    @Test(priority = 10)
    public void TC10_SignupFormClosesProperly() throws InterruptedException {
        HomePage home = new HomePage(driver);
        home.openSignUpForm();
        SignUpPage signUp = new SignUpPage(driver);
        signUp.closeSignUpForm();
        Thread.sleep(1000); // to give modal time to close
        Assert.assertFalse(signUp.isFormVisible(), "Sign-up form did not close properly.");
    }

    @Test(priority = 11)
    public void TC11_VerifyPasswordMasked() {
        HomePage home = new HomePage(driver);
        home.openSignUpForm();
        SignUpPage signUp = new SignUpPage(driver);
        Assert.assertEquals(signUp.getPasswordFieldType(), "password", "Password is not masked");
    }
}
