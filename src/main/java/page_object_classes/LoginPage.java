package page_object_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.xpath(".//div/form/fieldset[1]/div/div/input");
    private By passwordField = By.xpath(".//div/form/fieldset[2]/div/div/input");
    private By loginButton = By.xpath(".//button[text() = 'Войти']");
    private By headerLoginPage = By.className("AppHeader_header__nav__g5hnF");


    public void waitForLoadLoginPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }
}
