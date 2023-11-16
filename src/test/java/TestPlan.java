import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestPlan {
    public static final WebDriver driver = new ChromeDriver();

    @BeforeSuite
    public static void main (String[]args){
        System.setProperty("webdriver.chrome.driver", Utils.CHROME_DRIVER_LOCATION);
    }

    @Test(testName = "Test 1")
    public static void test1(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.agregarCA();
        transferenciaForm.agregarMonto("50000");
        transferenciaForm.agregarCuentaDestino(1);
        transferenciaForm.agregarCodigo("1112");
        transferenciaForm.clickTransferir();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(transferenciaForm.getSubtitulo(), "La transferencia se realizÃ³ con Ã©xito");
    }

    @Test(testName = "Test 2")
    public static void test2(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.agregarCA();
        transferenciaForm.agregarMonto("500000");
        transferenciaForm.agregarCuentaDestino(1);
        transferenciaForm.agregarCodigo("1112");
        transferenciaForm.clickTransferir();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(transferenciaForm.getSubtitulo(), "Fondos insuficientes para realizar la transferencia");
    }

    @Test(testName = "Test 3")
    public static void test3(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.agregarCA();
        transferenciaForm.agregarMonto("50000");
        transferenciaForm.agregarCuentaDestino(1);
        transferenciaForm.agregarCodigo("1111");
        transferenciaForm.clickTransferir();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(transferenciaForm.getTitulo(), "Not Found");
    }

    @Test(testName = "Test 4")
    public static void test4(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.agregarCA();
        transferenciaForm.agregarMonto("");
        transferenciaForm.agregarCuentaDestino(1);
        transferenciaForm.agregarCodigo("1112");
        transferenciaForm.clickTransferir();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(transferenciaForm.getSubtitulo(), "El monto debe ser un valor mayor a 0");
    }

    @Test(testName = "Test 5")
    public static void test5(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.agregarCA();
        transferenciaForm.agregarMonto("martin");
        transferenciaForm.agregarCuentaDestino(1);
        transferenciaForm.agregarCodigo("1112");
        transferenciaForm.clickTransferir();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(transferenciaForm.getSubtitulo(), "Fondos insuficientes para realizar la transferencia");
    }

    @Test(testName = "Test 6")
    public static void test6(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.agregarCA();
        transferenciaForm.agregarMonto("-50000");
        transferenciaForm.agregarCuentaDestino(1);
        transferenciaForm.agregarCodigo("1112");
        transferenciaForm.clickTransferir();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(transferenciaForm.getSubtitulo(), "El monto debe ser un valor mayor a 0");
    }

    @Test(testName = "Test 7")
    public static void test7(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.agregarCA();
        transferenciaForm.agregarMonto("50000");
        transferenciaForm.agregarCuentaDestino(0); //cuenta destino vacia
        transferenciaForm.agregarCodigo("1112");
        transferenciaForm.clickTransferir();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(transferenciaForm.getSubtitulo(), "Debe seleccionar una cuenta destino");
    }

    @Test(testName = "Test 8")
    public static void test8(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.agregarCA();
        transferenciaForm.agregarMonto("50000");
        transferenciaForm.agregarCuentaDestino(1);
        transferenciaForm.agregarCodigo("111");
        transferenciaForm.clickTransferir();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(transferenciaForm.getTitulo(), "Not Found");
    }

    @Test(testName = "Test 9")
    public static void test9(){
        driver.get(Utils.BASE_URL);
        TransferenciaForm transferenciaForm = new TransferenciaForm(driver);
        transferenciaForm.agregarCA();
        transferenciaForm.agregarMonto("50000");
        transferenciaForm.agregarCuentaDestino(1);
        transferenciaForm.agregarCodigo("");
        transferenciaForm.clickTransferir();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals(transferenciaForm.getSubtitulo(), "Debe ingresar un cÃ³digo de seguridad");
    }

    @AfterSuite
    public void cleanUp(){
        driver.manage().deleteAllCookies();
        driver.close();
    }
}