package testNGornekTestler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.TestBase;

import java.time.Duration;

public class Odev2_Priorty  {
    WebDriver driver;

    @BeforeClass

    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    //1)
    //Bir class oluşturun: Youtube Assertions
    //2)
    //https://www.youtube.com adresine gidin

    //3)
    //Aşağıdaki adları kullanarak 4 test metodu oluşturun ve gerekli testleri yapin
    //○
    //titleTest => Sayfa başlığının YouTube ” oldugunu test edin
    @Test(priority = 1)
    public void titleTest() {
        driver.get("https://www.youtube.com");
        String  expectedTitle="YouTube";
        String actualTitle= driver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle);
    }
    //○
    //imageTest => YouTube resminin görüntülendiğini isDisplayed()) test edin
    @Test(priority = 2)
    public void imageTest() {
        WebElement youtuberResmiElementi=driver.findElement(By.xpath("(//yt-icon[@id='logo-icon'])[1]"));
        Assert.assertTrue(youtuberResmiElementi.isDisplayed());
    }
    //○
    //SearchBox 'in erisilebilir oldugunu test edin (isEnabled())
    @Test(priority = 3)
    public void SearchBoxTest() {

        WebElement searchboxElementi= driver.findElement(By.xpath("//input[@name='search_query']"));
        Assert.assertTrue(searchboxElementi.isEnabled());
    }
    //○
    //wrongTitleTest => Sayfa basliginin “youtube” olmadigini dogrulayin
    @Test(priority = 4)
    public void wrongTitleTest() {
        String expectedTitle="youtube";
        String actualTitle= driver.getTitle();
        Assert.assertNotEquals(actualTitle,expectedTitle);

    }
}
