import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
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
    @DisplayName("Check successfully click on constructor tab Sauce")
    @Description("Checking the possibility of switching tab on Sauce")
    public void testConstructorSectionTabSauce()  {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(BASE_URI);

        Homepage homepage = new Homepage(driver);

        homepage.clickSauseLink();
        WebElement webElement = homepage.getWebElement();
        assertThat(webElement.getText(), is("Соусы"));

    }
    @Test
    @DisplayName("Check successfully click on constructor tab Filling")
    @Description("Checking the possibility of switching tab on Filling")
    public void testConstructorSectionTabFilling()  {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(BASE_URI);

        Homepage homepage = new Homepage(driver);

        homepage.clickFillingLink();
        WebElement webElement = homepage.getWebElement();
        assertThat(webElement.getText(), is("Начинки"));

    }

    @Test
    @DisplayName("Check successfully click on constructor tab Bun")
    @Description("Checking the possibility of switching tab on Bun")
    public void testConstructorSectionTabBun()  {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(BASE_URI);

        Homepage homepage = new Homepage(driver);


        //кликаем сначала на Начинки, чтобы проверить переход обратно на булки, т.к. булки выбраны по дефолту
        homepage.clickFillingLink();
        homepage.clickBunLink();
        WebElement webElement = homepage.getWebElement();
        assertThat(webElement.getText(), is("Булки"));

    }

    @After
    public void teardown() {
        driver.quit();
    }
}
