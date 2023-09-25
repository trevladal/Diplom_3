package page_object_classes;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Objects;

public class Homepage {

    private WebDriver driver;

    public Homepage(WebDriver driver) {

        this.driver = driver;
    }

    private By headerHomePage = By.className("AppHeader_header__nav__g5hnF");
    private By headerLinkConstructor = By.className("AppHeader_header__link__3D_hX");
    private By buttonAuth = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private By buttonHeaderPersonalAccount = By.xpath(".//p[text() = 'Личный Кабинет']");
    private By headerConstructor = By.xpath(".//section/h1[text() = 'Соберите бургер']");


    private By bunsLink = By.xpath(".//main/section/div/div[1]");

    private By saucesLink = By.xpath(".//main/section/div/div[2]");

    private By fillingLink = By.xpath(".//main/section/div/div[3]");

    private By getTextTabElement = By.xpath("//div[contains(@class, 'tab_tab_type_current__2BEPc')]/span");




    public void clickAuthButton() {
        driver.findElement(buttonAuth).click();
    }

    public void clickPersonalAccountLink() {
        driver.findElement(buttonHeaderPersonalAccount).click();
    }

    public void waitForLoadHomePage() {
        new WebDriverWait(driver, 5)
                .until(ExpectedConditions.visibilityOfElementLocated(headerConstructor));
    }

    public void clickSauseLink() {
        driver.findElement(saucesLink).click();
    }

    public void clickFillingLink() {
        driver.findElement(fillingLink).click();
    }

    public void clickBunLink() {
        driver.findElement(bunsLink).click();
    }

    public WebElement getWebElement() {
        return driver.findElement(getTextTabElement);
    }


}
