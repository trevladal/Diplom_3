import API_classes.UserAPI;
import API_classes.UserSession;
import io.github.bonigarcia.wdm.WebDriverManager;
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

public class LogOutTest {
    private UserSession userSession;
    private UserAPI userAPI = new UserAPI();
    private final static String BASE_URI = "https://stellarburgers.nomoreparties.site/";
    private final static String LOGIN_URI = "https://stellarburgers.nomoreparties.site/login";

    private WebDriver driver;

    private String email = "testUser0757@test.com";
    private String password = "123456";
    private String name = "testName";

    @Before
    public void startUp() {
        RestAssured.baseURI = BASE_URI;
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void testSuccessfulLogOut() {

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
        personalAccountPage.clickLogOutButton();

        loginPage.waitForLoadLoginPage();

        assertThat(driver.getCurrentUrl(), is(LOGIN_URI));

    }

    @After
    public void teardown() {
        driver.quit();
        if (userSession != null) {
            userAPI.deletingUser(userSession);
        }
    }
}
