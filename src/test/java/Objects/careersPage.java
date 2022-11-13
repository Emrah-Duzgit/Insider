package Objects;


import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import static TestScenarios.TestMethods_googleSearchPage.Wait;

public class careersPage {

    WebDriver driver;
    WebDriverWait wait;

    public careersPage(WebDriver driver) {
        this.driver = driver;}

    By moreMenu = By.xpath("//span[.='More']");
    By career = By.xpath("//h5[.='Careers']");
    By locations = By.id("location-slider");
    By life = By.xpath("/html/body/div[1]/div/div/section[4]");
    By team = By.cssSelector("[class='col-12 d-flex flex-wrap p-0 career-load-more']");





    public void clickMoreMenu()
    {
        Wait(1);
        driver.findElement(moreMenu).click();
    }
    public void clickCareer() {
        Wait(1);
        driver.findElement(career).click();
    }
    public void assertCareerPage(){
        Wait(1);
        Assert.assertTrue(driver.getTitle().contains("Insider Careers"));}

    public void assertLocations() {
        Wait(1);
        Assert.assertTrue(driver.findElement(locations).isDisplayed());
    }
    public void assertLife() {
        Wait(1);
        Assert.assertTrue(driver.findElement(life).isDisplayed());
    }
    public void assertTeam(){
        Wait(1);
        Assert.assertTrue(driver.findElement(team).isDisplayed());
    }






}
