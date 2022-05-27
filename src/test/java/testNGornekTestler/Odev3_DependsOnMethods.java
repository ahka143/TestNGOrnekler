package testNGornekTestler;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Odev3_DependsOnMethods {
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
    //●Bir class oluşturun: D ependsOnTest






    @Test
    public void test01() {
        //●https://www.amazon.com/ adresine gidin.
        //1. Test : Amazon ana sayfaya gittiginizi test edin
        driver.get("https://www.amazon.com/");
        String expectedUrl="https://www.amazon.com";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertEquals(actualUrl,expectedUrl);
    }



    @Test(dependsOnMethods = "test01")
    public void test02() {
        //2.Test : 1.Test basarili ise search Box’i kullanarak “Nutella” icin
        //arama yapin ve aramanizin gerceklestigini Test edin

        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Nutella"+ Keys.ENTER);
        String sonucYazisi=driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']")).getText();
        Assert.assertTrue(sonucYazisi.contains("Nutella"));
    }





    @Test(dependsOnMethods = "test02")
    public void test03() {
        //3.Test : “Nutella” icin arama yapildiysa ilk urunu tiklayin ve fiyatinin
        //$16.83 oldugunu test edin
        driver.findElement(By.xpath("//div[@class='a-section aok-relative s-image-square-aspect']")).click();
        String ilkUrunFiyatElementi=driver.findElement(By.xpath("//span[@class='a-size-base a-color-price']")).getText();
        Assert.assertTrue(ilkUrunFiyatElementi.contains("$16.83"),"Urun fiyati farkli");
    }
}
