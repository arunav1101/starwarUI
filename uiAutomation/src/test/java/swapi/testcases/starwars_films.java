package swapi.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import swapi.base.TestBase;

import java.util.List;


public class starwars_films extends TestBase {
    public static String filmName = "THE FORCE AWAKENS";

    @Test
    public void searchFilms() {

        driver.findElement(By.cssSelector("ul#section-links li:nth-of-type(4)")).click();
        driver.findElement(By.xpath(("//*[@id=\'film-selector\']/div/div/label"))).click();

        driver.findElement(By.cssSelector("#film-selector > div > ul > li:nth-child(8) > span > a")).click();

        WebElement searchValue = driver.findElement(By.xpath("//*[@id=\'film-selector\']/div/div/label"));
        assert (searchValue.getText().trim()).equals(filmName);

        searchValue = driver.findElement(By.xpath(" //*[@id=\'ref-1-1\']/div/div[2]/img"));
        assert (searchValue.getAttribute("alt").toLowerCase().contains(filmName.toLowerCase()));

        WebElement buyButton = driver.findElement(By.xpath("//*[@id=\'ref-1-1\']/div/div[2]/div[2]/div[1]/div/a"));
        System.out.println(buyButton.getText());
        assert (buyButton.getText().equals("BUY MOVIE"));

        WebElement wookie = driver.findElement(By.xpath("//*[@id=\'ref-1-1\']/div/div[2]/div[2]/div[2]/a[2]"));
        assert (wookie.getText()).equals("WOOKIEEPEDIA");
        assert (wookie.getAttribute("href").contains("http://starwars.wikia.com/wiki/The_Force_Awakens"));

    }
}
