import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
public class TransferenciaForm extends PageObject{
    @FindBy(xpath = "//td[@class='tg-0lax']//select[@id='cuentao']")
    private WebElement cuentaOrigen;

    @FindBy(id="cuentad")
    private WebElement cuentadestino;

    @FindBy(xpath = "//body//h2")
    private WebElement subtitle;

    @FindBy(xpath = "//body//h1")
    private WebElement title;

    @FindBy(id="monto")
    private WebElement monto;

    @FindBy(id="codigo")
    private WebElement codigoseguridad;

    @FindBy(xpath = "//button[contains(text(),'Agregar cuenta')]")
    private WebElement botonagregarcuenta;

    @FindBy(xpath = "//button[contains(text(),'Transferir')]")
    private WebElement botontransferir;


    public TransferenciaForm(WebDriver driver) {
        super (driver);
    }

    public String getSubtitulo(){
        return subtitle.getText();
    }

    public String getTitulo(){
        return title.getText();
    }

    public void agregarCA() {
        Select select = new Select(cuentaOrigen);
        // Selecciona la primera opción (CA)
        select.selectByIndex(0);
    }

   public void agregarCC() {
       Select select = new Select(cuentaOrigen);
        // Selecciona la segunda opción (CC)
        select.selectByIndex(1);
    }

    public void agregarCuentaDestino(Integer index) {
        Select select = new Select(cuentadestino);
        select.selectByIndex(index);
        botonagregarcuenta.click();
    }

    public void agregarMonto(String valorMonto) { 
        this.monto.sendKeys(valorMonto);
    }

    public void agregarCodigo(String cod) { 
        this.codigoseguridad.sendKeys(cod);
    }

    public void clickTransferir(){
        this.botontransferir.click();
    }

}
