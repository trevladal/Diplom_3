import API_classes.UserAPI;
import API_classes.UserSession;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page_object_classes.Homepage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConstructorTest {
    private final static String BASE_URI = "https://stellarburgers.nomoreparties.site/";

    private WebDriver driver;

    @Before
    public void startUp() {

        WebDriverManager.chromedriver().setup();
    }
    @Test
    public void testConstructorSection()  {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(BASE_URI);

        Homepage homepage = new Homepage(driver);

        homepage.clickSauseLink();
        WebElement webElement = homepage.getWebElement();
        assertThat(webElement.getText(), is("Соусы"));

        homepage.clickFillingLink();
        webElement = homepage.getWebElement();
        assertThat(webElement.getText(), is("Начинки"));


        homepage.clickBunLink();
        webElement = homepage.getWebElement();
        assertThat(webElement.getText(), is("Булки"));

    }

    @After
    public void teardown() {
        driver.quit();
    }
}
