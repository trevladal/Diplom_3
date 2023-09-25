import API_classes.UserAPI;
import API_classes.UserSession;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page_object_classes.Homepage;
import page_object_classes.LoginPage;
import page_object_classes.PersonalAccountPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class EnterToConstructorFromPersonalAccountPageTest {
    private UserSession userSession;
    private UserAPI userAPI = new UserAPI();
    private final static String BASE_URI = "https://stellarburgers.nomoreparties.site/";

    private WebDriver driver;

    private String email = "testUser077ui@test.com";
    private String password = "123456";
    private String name = "testName";

    @Before
    public void startUp() {
        RestAssured.baseURI = BASE_URI;
        WebDriverManager.chromedriver().setup();
    }
    @Test
    @DisplayName("Check successfully entering to constructor from link")
    @Description("Checking successfully entering to constructor from personal acc page link")
    public void testSuccessfulEnterToConstructorFromHeaderLink() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(BASE_URI);

        Response response = userAPI.creatingUser(email, password, name);
        userSession = response.body().as(UserSession.class);

        Homepage homepage = new Homepage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homepage.clickAuthButton();

        loginPage.waitForLoadLoginPage();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        homepage.waitForLoadHomePage();
        homepage.clickPersonalAccountLink();

        personalAccountPage.waitForLoadPersonalAccPage();
        personalAccountPage.clickLinkConstructor();

        homepage.waitForLoadHomePage();

        assertThat(driver.getCurrentUrl(), is(BASE_URI));
    }
    @Test
    @DisplayName("Check successfully entering to constructor from logo")
    @Description("Checking successfully entering to constructor from personal acc page logo")
    public void testSuccessfulEnterToConstructorFromLogo() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(BASE_URI);

        Response response = userAPI.creatingUser(email, password, name);
        userSession = response.body().as(UserSession.class);

        Homepage homepage = new Homepage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homepage.clickAuthButton();

        loginPage.waitForLoadLoginPage();
        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        homepage.waitForLoadHomePage();
        homepage.clickPersonalAccountLink();

        personalAccountPage.waitForLoadPersonalAccPage();
        personalAccountPage.clickLinkLogo();

        homepage.waitForLoadHomePage();

        assertThat(driver.getCurrentUrl(), is(BASE_URI));
    }

    @After
    public void teardown() {
        driver.quit();
        if (userSession != null) {
            userAPI.deletingUser(userSession);
        }
    }
}
