package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class insiderHomaPage {

    WebDriver driver;


    public insiderHomaPage(WebDriver driver) {
        this.driver = driver;}
        By acceptCookies = By.id("wt-cli-accept-all-btn");




    public void acceptCookiesFunctionality(){
        driver.findElement(acceptCookies).click();
    }
}
