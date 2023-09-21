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
import page_object_classes.ForgotPassPage;
import page_object_classes.Homepage;
import page_object_classes.LoginPage;
import page_object_classes.RegistrationPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LogInPageTest {
    private UserSession userSession;
    private UserAPI userAPI = new UserAPI();
    private final static String BASE_URI = "https://stellarburgers.nomoreparties.site/";
    private final static String REGISTER_URI = "https://stellarburgers.nomoreparties.site/register";
    private final static String LOGIN_URI = "https://stellarburgers.nomoreparties.site/login";
    private final static String FORGOT_PASSWORD_URI = "https://stellarburgers.nomoreparties.site/forgot-password";
    private WebDriver driver;

    private String email = "testUser077hh@test.com";
    private String password = "123456";
    private String name = "testName";

    @Before
    public void startUp() {
        RestAssured.baseURI = BASE_URI;
        WebDriverManager.chromedriver().setup();
    }
    @Test
    public void testSuccessfulLoginFromMainPageButton() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(BASE_URI);

        Response response = userAPI.creatingUser(email, password, name);
        userSession = response.body().as(UserSession.class);

        Homepage homepage = new Homepage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homepage.clickAuthButton();

        loginPage.waitForLoadLoginPage();
        assertThat(driver.getCurrentUrl(), is(LOGIN_URI));

        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        homepage.waitForLoadHomePage();

        assertThat(driver.getCurrentUrl(), is(BASE_URI));
    }

    @Test
    public void testSuccessfulLoginFromHomePageHeaderLinkPersonalAcc() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(BASE_URI);

        Response response = userAPI.creatingUser(email, password, name);
        userSession = response.body().as(UserSession.class);

        Homepage homepage = new Homepage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homepage.clickPersonalAccountLink();

        loginPage.waitForLoadLoginPage();
        assertThat(driver.getCurrentUrl(), is(LOGIN_URI));

        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        homepage.waitForLoadHomePage();

        assertThat(driver.getCurrentUrl(), is(BASE_URI));
    }

    @Test
    public void testSuccessfulLoginFromForgotPasswordForm() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(FORGOT_PASSWORD_URI);

        Response response = userAPI.creatingUser(email, password, name);
        userSession = response.body().as(UserSession.class);

        Homepage homepage = new Homepage(driver);
        LoginPage loginPage = new LoginPage(driver);
        ForgotPassPage forgotPassPage = new ForgotPassPage(driver);

        forgotPassPage.clickAuthLink();
        loginPage.waitForLoadLoginPage();
        assertThat(driver.getCurrentUrl(), is(LOGIN_URI));

        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        homepage.waitForLoadHomePage();

        assertThat(driver.getCurrentUrl(), is(BASE_URI));
    }
    @Test
    public void testSuccessfulLoginFromRegistrationForm() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.get(REGISTER_URI);

        Response response = userAPI.creatingUser(email, password, name);
        userSession = response.body().as(UserSession.class);

        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        Homepage homepage = new Homepage(driver);

        registrationPage.clickAuthButton();
        loginPage.waitForLoadLoginPage();

        assertThat(driver.getCurrentUrl(), is(LOGIN_URI));

        loginPage.setEmail(email);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

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
