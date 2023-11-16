import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeForm extends PageObject{
    public HomeForm(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="signin2")
    private WebElement signin;

    @FindBy(id="login2")
    private WebElement login;

    @FindBy(id="sign-username")
    private WebElement signInUsernameInput;

    @FindBy(id="sign-password")
    private WebElement signInPasswordInput;

    @FindBy(id="loginusername")
    private WebElement loginUsernameInput;

    @FindBy(id="loginpassword")
    private WebElement logInPasswordInput;

    @FindBy(xpath = "//*[@id=\"signInModal\"]/div/div/div[3]/button[2]")
    private WebElement signUpButton;

    @FindBy(id="nameofuser")
    private WebElement welcomeSign;

    public void clickSignIn(){
        signin.click();
    }

    public void clickLogIn(){
        login.click();
    }

    public void enterSignInUsername(String name){
        signInUsernameInput.sendKeys(name);
    }

    public void enterSignInPassword(String password){
        signInPasswordInput.sendKeys(password);
    }

    public void enterLogInUsername(String name){
        loginUsernameInput.sendKeys(name);
    }

    public void enterLogInPassword(String password){
        logInPasswordInput.sendKeys(password);
    }

    public void clickSignUp(){
        signUpButton.click();
    }

    public String getWelcomeContent(){
        return welcomeSign.getText();
    }

}
