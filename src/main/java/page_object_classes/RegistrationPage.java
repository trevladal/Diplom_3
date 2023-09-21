package page_object_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPage {
    private WebDriver driver;


    public RegistrationPage(WebDriver driver) {

        this.driver = driver;
    }
    private By nameField = By.xpath(".//div/form/fieldset[1]/div/div/input");
    private By emailField = By.xpath(".//div/form/fieldset[2]/div/div/input");
    private By passwordField = By.xpath(".//div/form/fieldset[3]/div/div/input");
    private By registrationButton = By.xpath(".//button[text() = 'Зарегистрироваться']");
    private By headerRegisterPage = By.className("Auth_login__3hAey");

    private By errorMessageField = By.xpath(".//p[text() = 'Некорректный пароль']");

    private By buttonAuthRegisterPage = By.xpath(".//p/a[text() = 'Войти']");



    //ждём прогрузки страницы
    public void waitForLoadRegisterPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(headerRegisterPage));
    }

    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    public void setEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }
    public void setPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickRegisterButton() {
        driver.findElement(registrationButton).click();
    }

    public void clickAuthButton() {
        driver.findElement(buttonAuthRegisterPage).click();
    }

    public void waitForLoadErrorMessageFieldIncorrectPass() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(errorMessageField));
    }
}
