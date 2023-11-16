import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    public static final WebDriver driver = new ChromeDriver();
    private static final String username = generateRandomUsername();
    private static final String password = generateRandomPassword();

    private static String generateRandomUsername() {
        return "user_" + UUID.randomUUID().toString().substring(0, 8);
    }

    private static String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 12; i++) {
            int index = (int) (Math.random() * characters.length());
            password.append(characters.charAt(index));
        }
        return password.toString();
    }

    private static Alert switchToAlert(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }

    @BeforeSuite
    public static void main (String[]args){
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Successful sign up")
    public static void successfulSignIn() {
        driver.get(Utils.BASE_URL);
        HomeForm homeForm = new HomeForm(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homeForm.clickSignIn();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homeForm.enterSignInUsername(username);
        homeForm.enterSignInPassword(password);
        homeForm.clickSignUp();
        Assert.assertTrue(switchToAlert().getText().contains("Sign up successful."));
    }

 //   @Test(testName = "Unccessful sign in when entering a username that already exists")
   // public static void unsuccessfulSignIn() {
     //   successfulSignIn();
       // successfulSignIn();
       // switchTo.alert();
    //}

    @Test(testName = "Successful log in")
    public static void successfulLogIn() {
        driver.get(Utils.BASE_URL);
        HomeForm homeForm = new HomeForm(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homeForm.clickLogIn();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homeForm.enterLogInUsername(username);
        homeForm.enterLogInPassword(password);
        homeForm.clickLogInButton();
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Assert.assertEquals(homeForm.getWelcomeContent(), "Welcome "+username);
    }

    @Test(testName = "Unsuccessful log in with inexisting username")
    public static void unsuccessfulLogInInexistingUser() {
        driver.get(Utils.BASE_URL);
        String wrongUser = generateRandomUsername();
        HomeForm homeForm = new HomeForm(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homeForm.clickLogIn();
        homeForm.enterLogInUsername(wrongUser);
        homeForm.enterLogInPassword(password);
        homeForm.clickLogInButton();
        Assert.assertTrue(switchToAlert().getText().contains("User does not exist."));
    }

    @Test(testName = "Unsuccessful log in with incorrect password")
    public static void unsuccessfulLogInWrongPassword() {
        driver.get(Utils.BASE_URL);
        String wrongPassw = generateRandomPassword();
        HomeForm homeForm = new HomeForm(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homeForm.clickLogIn();
        homeForm.enterLogInUsername(username);
        homeForm.enterLogInPassword(wrongPassw);
        homeForm.clickLogInButton();
        System.out.println(switchToAlert().getText());
        Assert.assertTrue(switchToAlert().getText().contains("Wrong password."));
    }

    @AfterSuite
    public void cleanUp(){
        try {
            // Manejar alertas antes de realizar acciones de limpieza
            Alert alert = driver.switchTo().alert();
            if (alert != null) {
                alert.dismiss();
                driver.manage().deleteAllCookies();
                driver.close();
            }
        } catch (WebDriverException e) {
            driver.manage().deleteAllCookies();
            driver.close();
        }
    }

}