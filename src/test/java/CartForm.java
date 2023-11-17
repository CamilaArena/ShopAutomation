import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartForm extends PageObject{

    public CartForm(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"tbodyid\"]/tr/td[2]")
    private WebElement productName;

}
