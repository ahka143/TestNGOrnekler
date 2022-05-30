package testNGornekTestler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utilities.Driver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FreeWork1 {
    @Test
    public void test01() throws InterruptedException {
        // 1."https://react-shopping-cart-67954.firebaseapp.com/" adresine gidin
        Driver.getDriver().get("https://react-shopping-cart-67954.firebaseapp.com/");
// 2.Web sitesindeki tüm öğeleri listeleyin ve yazdirin
        // (sitede 16 urun var, 1.urun : 2.urun :.....seklinde yazdirin )
        // 3.Stringlerden olusan bir ArrayList oluşturun ve Ürün Adlarını ArrayList'e yerleştirin
        ArrayList<String> urunIsimleriArrayList = new ArrayList<>();
        List<WebElement> urunlerList = Driver.getDriver().findElements(By.xpath("//p[@class='sc-124al1g-4 eeXMBo']"));
        for (int i = 0; i < urunlerList.size(); i++) {
            System.out.println((i + 1) + ". urun : " + urunlerList.get(i).getText());
            urunIsimleriArrayList.add(urunlerList.get(i).getText());
        }


// 4.Siteden Rastgele 5 öğe seçin, sepete ekleyin ve sectiginiz öğelerin adlarını yazdırın
// (Her ürün 1 defadan fazla eklenemez!)
        List<WebElement> sepeteEkleList = new ArrayList<>();
        Random random = new Random();
        int randomSayi;
        for (int i = 0; i < 5; i++) {
            randomSayi = random.nextInt(15);
            WebElement sepeteEkleElementi = Driver.getDriver().findElement(By.xpath("(//button[@class='sc-124al1g-0 jCsgpZ'])[" + (randomSayi + 1) + "]"));
            sepeteEkleElementi.click();
            Thread.sleep(1000);
            Driver.getDriver().findElement(By.xpath("//button[@class='sc-1h98xa9-0 gFkyvN']")).click();

            sepeteEkleList.add(Driver.getDriver().findElement(By.xpath("(//p[@class='sc-124al1g-6 ljgnQL'])[" + (randomSayi + 1) + "]")));
        }
        Driver.getDriver().findElement(By.xpath("//div[@class='sc-1h98xa9-2 fGgnoG']")).click();
// 5.Her bir öğenin fiyatını toplayın ve sonucunuzu web sitesiyle karşılaştırın
        double actualToplam = (float) Double.parseDouble(Driver.getDriver().findElement(By.xpath("//p[@class='sc-1h98xa9-9 jzywDV']")).getText().replace("$", ""));
        double toplam = 0;
        for (WebElement each : sepeteEkleList
        ) {
            toplam += Double.parseDouble(each.getText().replace("$", ""));
        }
        float expectedToplam = (float) toplam;
        System.out.println(expectedToplam);

// 6.Sonuçlar eşleşiyorsa  konsola test pass yazirin
        if (actualToplam == expectedToplam) System.out.println("Test PASSED");

// 7.Checkout'a tıklayın
        Driver.getDriver().findElement(By.xpath("//*[text()='Checkout']")).click();
        Driver.getDriver().switchTo().alert().accept();
        Driver.closeDriver();
    }
}
