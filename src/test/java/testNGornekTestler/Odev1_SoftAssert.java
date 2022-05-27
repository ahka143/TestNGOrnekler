package testNGornekTestler;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utilities.TestBase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Odev1_SoftAssert extends TestBase {
    @Test
    public void test01() {

        // 1. “http://zero.webappsecurity.com/” Adresine gidin
        driver.get("http://zero.webappsecurity.com/");
        // 2. Sign in butonuna basin
        driver.findElement(By.id("signin_button")).click();
        // 3. Login kutusuna “username” yazin
        driver.findElement(By.xpath("//input[@id='user_login']")).sendKeys("username");
        // 4. Password kutusuna “password” yazin
        driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys("password" + Keys.ENTER);
        // 5. Sign in tusuna basin
        driver.navigate().back();
        // 6. Online banking menusu icinde Pay Bills sayfasina gidin
        driver.findElement(By.xpath("//strong[text()='Online Banking']")).click();
        // 7. “Purchase Foreign Currency” tusuna basin
        driver.findElement(By.xpath("//span[@id='pay_bills_link']")).click();
        driver.findElement(By.linkText("Purchase Foreign Currency")).click();
        // 8. “Currency” drop down menusunden Eurozone’u secin
        WebElement ddm = driver.findElement(By.xpath("//select[@id='pc_currency']"));
        Select select = new Select(ddm);
        select.selectByVisibleText("Eurozone (euro)");
        // 9. soft assert kullanarak "Eurozone (Euro)" secildigini test edin
        SoftAssert softAssert = new SoftAssert();
        String secilenOption = select.getFirstSelectedOption().getText();
        String expectedOption = "Eurozone (euro)";
        softAssert.assertEquals(secilenOption, expectedOption, "Eurozone (Euro) secilmedi");
        // 10. soft assert kullanarak DropDown listesinin su secenekleri oldugunu test edin
        List<WebElement> optionList = select.getOptions();
        //Webelementlerden olusan listi lambda expression kullanarak string'e donusturup, sort ettik
        List<String> optionsListString = optionList.stream().map(WebElement::getText).sorted().collect(Collectors.toList());

        List<String> expectedList = new ArrayList<>(Arrays.asList("Select One",
                "Australia (dollar)", "Canada (dollar)", "Switzerland (franc)", "China (yuan)",
                "Denmark (krone)", "Eurozone (euro)", "Great Britain (pound)", "Hong Kong (dollar)", "Japan (yen)", "Mexico (peso)", "Norway (krone)", "" +
                        "New Zealand (dollar)", "Sweden (krona)", "Singapore (dollar)", "Thailand (baht)"));
        Collections.sort(expectedList);//expectedlisteyi sort ettik
        for (int i = 0; i < optionList.size(); i++) {
            softAssert.assertEquals(optionsListString.get(i), expectedList.get(i), "DropDownListesi eslesmiyor");
        }

        softAssert.assertAll();
        // "Select One", "Australia (dollar)", "Canada (dollar)","Switzerland (franc)","China (yuan)",
        // "Denmark (krone)","Eurozone (euro)","Great Britain (pound)","Hong Kong (dollar)","Japan (yen)",
        // "Mexico (peso)","Norway (krone)","New Zealand (dollar)","Sweden (krona)","Singapore (dollar)","Thailand (baht)"


    }
}
