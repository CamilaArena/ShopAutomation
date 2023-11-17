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
import java.util.concurrent.TimeUnit;
import static services.randoms.generateRandomPassword;
import static services.randoms.generateRandomUsername;

public class LoginTest {
    public static final WebDriver driver = new ChromeDriver();
    private static final String username = generateRandomUsername();
    private static final String password = generateRandomPassword();

    @BeforeSuite
    public static void main (String[]args){
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    private static Alert switchToAlert(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());
        return driver.switchTo().alert();
    }

    private static HomeForm init(){
        driver.get(Utils.BASE_URL);
        HomeForm homeForm = new HomeForm(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return homeForm;
    }

    @Test(testName = "Successful sign up")
    public static void successfulSignIn() {
        HomeForm homeForm = init();
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
        HomeForm homeForm = init();
        homeForm.clickLogIn();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homeForm.enterLogInUsername(username);
        homeForm.enterLogInPassword(password);
        homeForm.clickLogInButton();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("contenido: "+homeForm.getWelcomeContent());
        Assert.assertEquals(homeForm.getWelcomeContent(), "Welcome "+username); //doesn't  work, it's always empty
    }

    @Test(testName = "Unsuccessful log in with inexisting username")
    public static void unsuccessfulLogInInexistingUser() {
        String wrongUser = generateRandomUsername();
        HomeForm homeForm = init();
        homeForm.clickLogIn();
        homeForm.enterLogInUsername(wrongUser);
        homeForm.enterLogInPassword(password);
        homeForm.clickLogInButton();
        Assert.assertTrue(switchToAlert().getText().contains("User does not exist."));
    }

    @Test(testName = "Unsuccessful log in with incorrect password")
    public static void unsuccessfulLogInWrongPassword() {
        HomeForm homeForm = init();
        String wrongPassw = generateRandomPassword();
        homeForm.clickLogIn();
        homeForm.enterLogInUsername(username); //me toma otro usuario, idk why
        System.out.println(username);
        homeForm.enterLogInPassword(wrongPassw);
        homeForm.clickLogInButton();
        System.out.println(switchToAlert().getText());
        Assert.assertTrue(switchToAlert().getText().contains("Wrong password."));
    }

    @AfterSuite
    public void cleanUp(){
        try {
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