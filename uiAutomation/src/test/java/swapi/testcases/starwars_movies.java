package swapi.testcases;
import swapi.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;


public class starwars_movies extends TestBase {
    public static String movieName = "ARENA LOVE PLEDGE";
    @Test
    public void searchMovie(){


        driver.findElement(By.cssSelector("ul#section-links li:nth-of-type(2)")).click();

        driver.findElement(By.xpath(("//*[@id=\"ref-1-0\"]/div/form/div[2]/input[1]"))).sendKeys("ARENA LOVE PLEDGE");
        driver.findElement(By.className("search-button")).click();

        WebElement searchValue = driver.findElement(By.xpath("//*[@id='main']/div/div/div[2]/div[2]/section/ul/li/div[1]/a/h3"));
        System.out.println(searchValue.getText());

        // correct movie is search
        assert(searchValue.getText()).equals(movieName);

        searchValue.click();
        String URL = driver.getCurrentUrl();
        assert(URL).equals("https://www.starwars.com/video/arena-love-pledge" );

        WebElement movieFrame = driver.findElement(By.xpath("//*[@id='frame']/div[1]/meta[5]"));

        movieFrame.getAttribute("content");
        assert(movieFrame.getAttribute("content").toUpperCase().contains(movieName.toUpperCase()));

        WebElement fullScreen = driver.findElement(By.xpath("//*[@id=\'player-gui\']/div[4]/div[3]/div[3]/button/i[1]"));

        // Check maximize
        fullScreen.click();
        assert(fullScreen.getAttribute("class").equals("playkit-icon playkit-icon-maximize"));

        // Check Minimize

        WebElement minScreen = driver.findElement(By.xpath("//*[@id=\'player-gui\']/div[4]/div[3]/div[3]/button/i[2]"));
        minScreen.click();
        System.out.println(minScreen.getAttribute("class"));
        assert(minScreen.getAttribute("class").equals("playkit-icon playkit-icon-minimize"));

    }
}
