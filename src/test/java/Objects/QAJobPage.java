package Objects;


import junit.framework.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static TestScenarios.TestMethods_googleSearchPage.Wait;


public class QAJobPage {
    WebDriver driver;

    public QAJobPage(WebDriver driver) {
        this.driver = driver;
    }

    By allTeam = By.xpath("//a[.='See all teams']");
    By QA = By.xpath("//*[@id=\"career-find-our-calling\"]/div/div/div[2]/div[12]/div[1]/a");
    By allQaJobs = By.xpath("//a[.='See all QA jobs']");
    By filterLocation = By.id("select2-filter-by-location-container");
    By istanbul = By.cssSelector("[title='Istanbul']");
    By jobList = By.id("career-position-list");
    By firstJob = By.cssSelector("[id='jobs-list']>div:nth-child(1)");
    By secondJob = By.cssSelector("[id='jobs-list']>div:nth-child(2)");
    By thirdJob = By.cssSelector("[id='jobs-list']>div:nth-child(3)");
    By department = By.cssSelector("span[title='Quality Assurance']");
    By firstJobLocation = By.cssSelector("[id='jobs-list']>div:nth-child(1)>div>div");
    By secondJobLocation = By.cssSelector("[id='jobs-list']>div:nth-child(2)>div>div");
    By thirdJobLocation = By.cssSelector("[id='jobs-list']>div:nth-child(3)>div>div");
    By firstJobButton = By.xpath("//a[.='Apply Now'][1]");
    By secondJobButton = By.xpath("//a[.='Apply Now'][2]");
    By thirdJobButton = By.xpath("//a[.='Apply Now'][3]");


    public void clickAllTeams(){
      Actions actions = new Actions(driver);

        for (int i = 0; i < 19; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
        }
        actions.sendKeys(Keys.ENTER).build().perform();



    }

    public void clickQA() {
        WebElement element = driver.findElement(By.xpath("//*[.='Quality Assurance']/h3"));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView()", element);
        Wait(2);
        executor.executeScript("arguments[0].click();", element);
    }

    public void clickAllQaJobs() {
        Wait(2);
        driver.findElement(allQaJobs).click();
    }

    public void clickFilterLocation() {
        Wait(2);
        driver.findElement(filterLocation).click();
    }

    public void selectIstanbul() {
        Wait(2);
        driver.findElement(istanbul).click();
    }

    public void checkJobList() {
        Wait(2);
        Assert.assertTrue(driver.findElement(jobList).isDisplayed());
    }

    public void checkPositionContainsQA() {
        Wait(1);
        Assert.assertTrue(driver.findElement(firstJob).getText().contains("Quality Assurance"));
        Wait(1);
        Assert.assertTrue(driver.findElement(secondJob).getText().contains("Quality Assurance"));
        Wait(1);
        Assert.assertTrue(driver.findElement(thirdJob).getText().contains("Quality Assurance"));
    }

    public void departmentCheck() {
        Wait(1);
        Assert.assertTrue(driver.findElement(department).isDisplayed());
    }

    public void checkLocation() {
        Wait(1);
        Assert.assertTrue(driver.findElement(firstJobLocation).getText().contains("Istanbul"));
        Wait(1);
        Assert.assertTrue(driver.findElement(secondJobLocation).getText().contains("Istanbul"));
        Wait(1);
        Assert.assertTrue(driver.findElement(thirdJobLocation).getText().contains("Istanbul"));
    }

    public void checkApplyButton() {
        Wait(1);
        Assert.assertTrue(driver.findElement(firstJobButton).getText().contains("Apply"));
        Wait(1);
        Assert.assertTrue(driver.findElement(secondJobButton).getText().contains("Apply"));
        Wait(1);
        Assert.assertTrue(driver.findElement(thirdJobButton).getText().contains("Apply"));
    }

    public void clickOnApplyButton() {
        Wait(1);
        driver.findElement(firstJobButton).click();
        Wait(1);
        driver.findElement(secondJobButton).click();
        Wait(1);
        driver.findElement(thirdJobButton).click();
    }

    public void assertLeverPage() {
        String mainPageWindowId = driver.getWindowHandle();
        Set<String> windowsIds = driver.getWindowHandles();
        for (String id : windowsIds) {
            if (id.equals(mainPageWindowId)) continue;
            driver.switchTo().window(id);
            Wait(2);
            driver.getCurrentUrl().contains("lever");
            Wait(1);
            driver.close();
        }
    }
}
