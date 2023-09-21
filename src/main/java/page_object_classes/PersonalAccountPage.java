package page_object_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalAccountPage {
    private WebDriver driver;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    private By headerLinkConstructor = By.xpath(".//li/a/p[text() = 'Конструктор']");
    private By headerLinkLogo = By.className("AppHeader_header__logo__2D0X2");

    private By headerPersonalAcc = By.className("AppHeader_header__nav__g5hnF");
    private By logOutButton = By.xpath(".//button[text() = 'Выход']");





    public void clickLinkConstructor() {
        driver.findElement(headerLinkConstructor).click();
    }
    public void clickLinkLogo() {
        driver.findElement(headerLinkLogo).click();
    }

    public void clickLogOutButton() {
        driver.findElement(logOutButton).click();
    }

    public void waitForLoadPersonalAccPage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(logOutButton));
    }


}
