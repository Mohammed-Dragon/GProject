package Tests;

import Base.OpenDriver;
import org.example.Pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeTests extends OpenDriver {
    @Test(priority = 1)
    public void check_name_of_The_Website(){
        HomePage homepage = new HomePage(driver);
        String ActualName = homepage.getCompanyName();
        String ExpectedName = "PRODUCT STORE";
        Assert.assertEquals(ActualName, ExpectedName,"The Company Name in the website is wrong");
    }
    @Test(priority = 2)
    public void check_HomePage_Title(){
        String ActualName = driver.getTitle();
        String ExpectedName = "PRODUCT STORE";
        Assert.assertEquals(ActualName, ExpectedName,"The home page title does not contain the company name");
    }
    @Test(priority = 3)
    public void check_If_name_of_The_Company_Is_clickable(){
        HomePage homepage = new HomePage(driver);
        homepage.ClickOnCompanyName();
        boolean FluidImg = homepage.Verify_fluiding_appearance();
        Assert.assertTrue(FluidImg,"The Home Page is not visible");
    }
    @Test(priority = 4)
    public void check_Home_Button(){
        HomePage homepage = new HomePage(driver);
        homepage.ClickOnHomeButton();
        boolean FluidImg = homepage.Verify_fluiding_appearance();
        Assert.assertTrue(FluidImg,"The Home Page is not visible");
    }
    @Test(priority = 5)
    public void check_Contact_Button() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        homepage.ClickOnContactButton();
        Thread.sleep(1000);
        String ActualText = driver.findElement(By.xpath("//div[@aria-labelledby='exampleModalLabel']//div[@class='modal-header']//h5[@id='exampleModalLabel']")).getText();
        String ExpectedText = "New message";
        Assert.assertEquals(ActualText,ExpectedText,"The Contact window is not visible");
        String ActualText2 = driver.findElement(By.xpath("//div[@aria-labelledby='exampleModalLabel']//div[@class='modal-body']//div[@class='form-group']//label[@for='recipient-name']")).getText();
        String ExpectedText2 = "Contact Email:";
        Assert.assertEquals(ActualText2,ExpectedText2,"The content of the window is not visible");
    }
    @Test(priority = 6)
    public void check_AboutUs_Button() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        homepage.ClickOnAboutUsButton();
        Thread.sleep(1000);
        String ActualText = driver.findElement(By.xpath("//*[@id=\"videoModalLabel\"]")).getText();
        String ExpectedText = "About us";
        Assert.assertEquals(ActualText,ExpectedText,"The Content of the window is not visible");
        String ActualText2 = driver.findElement(By.xpath("//*[@id=\"example-video\"]/div[4]/button[1]/span[2]")).getText();
        String ExpectedText2 = "Play";
        Assert.assertEquals(ActualText2,ExpectedText2,"THe AboutUs window is not visible");
    }
    @Test(priority = 7)
    public void check_Cart_Button() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        homepage.ClickOnCartButton();
        String ActualText = driver.findElement(By.xpath("//*[@id=\"page-wrapper\"]/div/div[1]/h2")).getText();
        String ExpectedText = "Products";
        Assert.assertEquals(ActualText,ExpectedText,"THe Cart page does not open");
    }
    @Test(priority = 8)
    public void check_Page_Title_After_Navigating_To_Cart() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        homepage.ClickOnCartButton();
        String ActualTitle = driver.getTitle();
        boolean isChanged = ActualTitle.contains("Cart");
        Assert.assertTrue(isChanged,"The Page Title does not changed after navigating");
    }
    @Test(priority = 9)
    public void check_Login_Button() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        homepage.openLoginForm();
        Thread.sleep(1000);
        String ActualText = driver.findElement(By.xpath("//*[@id=\"logInModalLabel\"]")).getText();
        String ExpectedText = "Log in";
        Assert.assertEquals(ActualText,ExpectedText,"The Login window is not visible");
        String ActualText2 = driver.findElement(By.xpath("//*[@id=\"logInModal\"]/div/div/div[2]/form/div[1]/label")).getText();
        String ExpectedText2 = "Username:";
        Assert.assertEquals(ActualText2,ExpectedText2,"The content of the window is not visible");
    }
    @Test(priority = 10)
    public void check_Signup_Button() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        homepage.openSignUpForm();
        Thread.sleep(1000);
        String ActualText = driver.findElement(By.xpath("//*[@id=\"signInModalLabel\"]")).getText();
        String ExpectedText = "Sign up";
        Assert.assertEquals(ActualText,ExpectedText,"The Signup window is not visible");
        String ActualText2 = driver.findElement(By.xpath("//*[@id=\"signInModal\"]/div/div/div[2]/form/div[1]/label")).getText();
        String ExpectedText2 = "Username:";
        Assert.assertEquals(ActualText2,ExpectedText2,"The content of the window is not visible");
    }
    @Test(priority = 11)
    public void check_Phones_Filter() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        homepage.Filters_Phones();
        Thread.sleep(1000);
        List<WebElement> Products = homepage.FilteredProducts();

        for(WebElement Product : Products){
            String ProductName = Product.getText().toLowerCase();
            boolean isPhone = ProductName.contains("samsung") ||
                    ProductName.contains("iphone")  ||
                    ProductName.contains("nokia")   ||
                    ProductName.contains("xperia")  ||
                    ProductName.contains("nexus")   ||
                    ProductName.contains("htc");
            Assert.assertTrue(isPhone,"The PHones Filter is not working correctly");
        }
    }
    @Test(priority = 12)
    public void check_Laptops_Filter() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        homepage.Filters_Laptops();
        Thread.sleep(1000);
        List<WebElement> Products = homepage.FilteredProducts();

        for(WebElement Product : Products){
            String ProductName = Product.getText().toLowerCase();
            boolean isLaptop = ProductName.contains("vaio") ||
                    ProductName.contains("macbook")  ||
                    ProductName.contains("dell");
            Assert.assertTrue(isLaptop,"The PHones Filter is not working correctly");
        }
    }
    @Test(priority = 13)
    public void check_Monitors_Filter() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        homepage.Filters_Monitors();
        Thread.sleep(1000);
        List<WebElement> Products = homepage.FilteredProducts();

        for(WebElement Product : Products){
            String ProductName = Product.getText().toLowerCase();
            boolean isMonitor = ProductName.contains("monitor") ||
                    ProductName.contains("asus");

            Assert.assertTrue(isMonitor,"The PHones Filter is not working correctly");
        }
    }
    @Test(priority = 14)
    public void check_product_details_page() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        Thread.sleep(1000);
        homepage.Choosing_Nexus_6();
        Thread.sleep(1000);
        String ActualText = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/h2")).getText();
        String ExpectedText = "Nexus 6";
        Assert.assertEquals(ActualText, ExpectedText, "The Nexus 6 details page is not visible");
    }
    @Test(priority = 15)
    public void check_product_price_in_HomePage_DetailsPage() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        Thread.sleep(1000);
        String HomePage_Price = homepage.Nokia_lumia_1520_Price() + " *includes tax";
        homepage.Choosing_lumia_1520();
        Thread.sleep(1000);
        String DetailsPage_Price = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/h3")).getText();
        Assert.assertEquals(HomePage_Price,DetailsPage_Price,"The HomePage price of the product is different of the Details page price");
    }
    @Test(priority = 16)
    public void check_product() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        Thread.sleep(1000);
        homepage.Choosing_Samsung_s7();
        Thread.sleep(1000);
        String ActualProductName = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/h2")).getText();
        String ExpectedProductName = "Samsung galaxy s7";
        Assert.assertEquals(ActualProductName,ExpectedProductName,"The Product name is not visible");
        String ActualProductPrice = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/h3")).getText();
        String ExpectedProductPrice = "$800 *includes tax";
        Assert.assertEquals(ActualProductPrice,ExpectedProductPrice,"The product price is not visible");
        String ActualProductPrice2 = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/h3")).getText();
        String ExpectedProductPrice2 = "$800 *includes tax";
        Assert.assertEquals(ActualProductPrice2,ExpectedProductPrice2,"The product price is not visible");
        String ActualProductDescription = driver.findElement(By.xpath("//*[@id=\"more-information\"]/p")).getText();
        String ExpectedProductDescription = "The Samsung Galaxy S7 is powered by 1.6GHz octa-core it comes with 4GB of RAM. The phone packs 32GB of internal storage that can be expanded up to 200GB via a microSD card.";
        Assert.assertEquals(ActualProductDescription,ExpectedProductDescription,"The product description is not visible");
        boolean check1 = driver.findElement(By.xpath("//*[@id=\"imgp\"]/div/img")).isDisplayed();
        Assert.assertTrue(check1,"The main photo is not visible");
        boolean check2 = driver.findElement(By.xpath("//*[@id=\"myCarousel-2\"]/a[1]")).isDisplayed();
        Assert.assertTrue(check2,"The photo on the left of the main photo is not visible");
        boolean check3 = driver.findElement(By.xpath("//*[@id=\"myCarousel-2\"]/a[2]")).isDisplayed();
        Assert.assertTrue(check3,"(The photo on the right of the main photo) is not visible");
    }
    @Test(priority = 17)
    public void check_The_Previous_button_in_the_FirstPage() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        Thread.sleep(1000);
        boolean check = (driver.findElement(homepage.PreviousButton).isDisplayed() &&
                driver.findElement(homepage.PreviousButton).isEnabled() );
        Assert.assertFalse(check,"The Previous button is clickable in the first products page");
    }
    @Test(priority = 18)
    public void check_The_Next_button_in_the_FirstPage() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        Thread.sleep(1000);
        boolean check = (driver.findElement(homepage.NextButton).isDisplayed() &&
                driver.findElement(homepage.NextButton).isEnabled() );
        Assert.assertTrue(check,"The Next button is not clickable in the first products page");
    }
    @Test(priority = 19)
    public void check_The_Previous_button_in_the_LastPage() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        Thread.sleep(1000);
        homepage.ClickNextButton();
        Thread.sleep(1000);
        boolean check = (driver.findElement(homepage.PreviousButton).isDisplayed() &&
                driver.findElement(homepage.PreviousButton).isEnabled() );
        Assert.assertTrue(check,"The Previous button is clickable in the first products page");
    }
    @Test(priority = 20)
    public void check_The_Next_button_in_the_LastPage() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        Thread.sleep(1000);
        homepage.ClickNextButton();
        Thread.sleep(1000);
        boolean check = (driver.findElement(homepage.NextButton).isDisplayed() &&
                driver.findElement(homepage.NextButton).isEnabled() );
        Assert.assertFalse(check,"The Next button is clickable in the last products page");
    }
    @Test(priority = 21)
    public void validate_that_the_user_can_add() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        Thread.sleep(1000);
        homepage.Choosing_Sony_Vaio_i5();
        // Add to cart button
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a")).click();
        // Navigate to Cart page
        homepage.ClickOnCartButton();
        String ActualText = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/tr/td[2]")).getText();
        String ExpectedText = "Sony vaio i5";
        Assert.assertEquals(ActualText,ExpectedText,"Sony Vaio i5 did not added to the cart");
    }
    @Test(priority = 22)
    public void validate_the_navigation() throws InterruptedException {
        HomePage homepage = new HomePage(driver);
        Thread.sleep(1000);
        homepage.ClickNextButton();
        Thread.sleep(1000);
        homepage.Choosing_MacBook_Pro();
        Thread.sleep(1000);
        String ActualTitle = driver.getTitle().toLowerCase();
        boolean check = ActualTitle.contains("macbook");
        Assert.assertTrue(check,"The title is not contain the product name");
    }

}
