package page_object_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPassPage {

    private WebDriver driver;

    public ForgotPassPage (WebDriver driver) {
        this.driver = driver;
    }

    private By logInLinkForgotPassPage = By.xpath(".//p/a[text() = 'Войти']");

    private By headerForgotPassPage = By.className("AppHeader_header__nav__g5hnF");


    public void waitForLoadHomePage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(headerForgotPassPage));
    }

    public void clickAuthLink () {
        driver.findElement(logInLinkForgotPassPage).click();
    }
}
