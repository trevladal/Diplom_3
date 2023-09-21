import API_classes.User;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import page_object_classes.Homepage;
import page_object_classes.LoginPage;
import page_object_classes.RegistrationPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegistrationPageTest {
    private UserSession userSession;
    private UserAPI userAPI = new UserAPI();
    private final static String REGISTER_URI = "https://stellarburgers.nomoreparties.site/register";
    private final static String LOGIN_URI = "https://stellarburgers.nomoreparties.site/login";
    private final static String BASE_URI = "https://stellarburgers.nomoreparties.site/";

    private WebDriver driver;

    private String email = "testUser077mn@test.com";
    private String password = "123456";
    private String name = "testName";

    @Before
    public void startUp() {
        RestAssured.baseURI = BASE_URI;
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void testSuccessfulRegistration() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(REGISTER_URI);

        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.setName(name);
        registrationPage.setEmail(email);
        registrationPage.setPassword(password);
        registrationPage.clickRegisterButton();

        loginPage.waitForLoadLoginPage();

        assertThat(driver.getCurrentUrl(), is(LOGIN_URI));

        User user = new User(email, password, name);
        userSession = new UserSession(user, "", "");

        Response responseLogin = userAPI.loginUser(userSession);
        userSession = responseLogin.body().as(UserSession.class);

    }

    @Test
    public void testFailureRegistration() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(REGISTER_URI);

        RegistrationPage registrationPage = new RegistrationPage(driver);

        registrationPage.waitForLoadRegisterPage();
        registrationPage.setName(name);
        registrationPage.setEmail(email);
        registrationPage.setPassword("123");
        registrationPage.clickRegisterButton();

        registrationPage.waitForLoadErrorMessageFieldIncorrectPass();

    }

    @After
    public void teardown() {
        driver.quit();
        if (userSession != null) {
            userAPI.deletingUser(userSession);
        }
    }
}
