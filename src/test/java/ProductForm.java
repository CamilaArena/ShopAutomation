import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductForm extends PageObject{

    public ProductForm(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@id=\"tbodyid\"]/div[2]/div/a")
    private WebElement addToCart;

    @FindBy(className = "name")
    private WebElement title;

    @FindBy(id = "cartur")
    private WebElement cartButton;

    public void clickAddToCart() {
        addToCart.click();
    }

    public void clickCart() {
        cartButton.click();
    }

    public String getTitle(){
        return title.getText();
    }

}
