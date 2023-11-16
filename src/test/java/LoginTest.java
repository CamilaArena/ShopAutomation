import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class LoginTest {
    public static final WebDriver driver = new ChromeDriver();

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


    @BeforeSuite
    public static void main (String[]args){
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Successful sign in")
    public static void successfulSignIn() {
        driver.get(Utils.BASE_URL);
        String username = generateRandomUsername();
        HomeForm homeForm = new HomeForm(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homeForm.clickSignIn();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homeForm.enterSignInUsername(username);
        homeForm.enterSignInPassword(generateRandomPassword());
        homeForm.clickSignUp();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertEquals(homeForm.getWelcomeContent(), "Welcome "+username);
    }

    @AfterSuite
    public void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}