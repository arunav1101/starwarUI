package swapi.base;

import swapi.utilites.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static final String PROJDIR = System.getProperty("user.dir");
    private static final String CONFIGFILE_PATH = "/src/test/resources/properties/config.properties";

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties objectrepo = new Properties();
    private static FileInputStream fis;
    public static Logger log = Logger.getLogger("arunavLogger");

    public static String browser = "";
    public static WebDriverWait wait;


    public ExtentReports rep = ExtentManager.getInstance();
    public static ExtentTest test;




    @BeforeSuite
    public void setUp(){

        if(driver == null){
            log.debug("Initialize webdriver started");
            try {
                fis = new FileInputStream(PROJDIR + CONFIGFILE_PATH);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                config.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                objectrepo.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(System.getenv("browser") != null && !System.getenv("browser").equals("")){
                browser = System.getenv("browser");
            } else {
                browser = config.getProperty("browser");
            }

            config.setProperty("browser", browser);
            log.debug("Setting browser : " + browser);


            if(browser.equalsIgnoreCase("chrome")){
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if(browser.equalsIgnoreCase("firefox")){
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if(browser.equalsIgnoreCase("ie")){
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            } else if(browser.equalsIgnoreCase("opera")){
                WebDriverManager.operadriver().setup();
                driver = new OperaDriver();
            } else if(browser.equalsIgnoreCase("edge")){
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
            } else if(browser.equalsIgnoreCase("safari")){
                driver = new SafariDriver();
            }

            log.debug("Navigate to : " +  config.getProperty("testurl") + " application");

            driver.get(config.getProperty("testurl"));
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(config.getProperty("pageload.timeout")), TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, Integer.parseInt(config.getProperty("webdriver.wait")));
        }
    }
    @AfterSuite
    public void tearDown(){

        if(driver != null){
            driver.quit();
        }
    }
}
