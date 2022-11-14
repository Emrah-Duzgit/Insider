package Objects;


import Utils.GeneralWebDriver;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.service.ExtentTestManager;
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static Utils.GeneralWebDriver.Wait;


public class QAJobPage {
    WebDriver driver;

    public QAJobPage(WebDriver driver) {
        this.driver = driver;
    }

    By allTeam = By.xpath("//*[@id=\"career-find-our-calling\"]/div/div/a");
    By QA = By.xpath("//*[@id=\"career-find-our-calling\"]/div/div/div[2]/div[12]/div[2]/a/h3");
    By allQaJobs = By.xpath("//*[text()='See all QA jobs']");
    By filterLocation = By.xpath("//*[@id=\"select2-filter-by-location-container\"]");
    By jobList = By.id("career-position-list");
    By firstJob = By.cssSelector("[id='jobs-list']>div:nth-child(1)");
    By secondJob = By.cssSelector("[id='jobs-list']>div:nth-child(2)");
    By thirdJob = By.cssSelector("[id='jobs-list']>div:nth-child(3)");
    By department = By.cssSelector("span[title='Quality Assurance']");
    By firstJobLocation = By.cssSelector("[id='jobs-list']>div:nth-child(1)>div>div");
    By secondJobLocation = By.cssSelector("[id='jobs-list']>div:nth-child(2)>div>div");
    By thirdJobLocation = By.cssSelector("[id='jobs-list']>div:nth-child(3)>div>div");
    By firstJobApplyButton = By.xpath("(//*[text()='Apply Now'])[1]");
    By secondJobApplyButton = By.xpath("(//*[text()='Apply Now'])[2]");
    By thirdJobApplyButton = By.xpath("(//*[text()='Apply Now'])[3]");


    public void clickAllTeams() {
        Actions actions = new Actions(driver);

        for (int i = 0; i < 3; i++) {
            actions.sendKeys(Keys.PAGE_DOWN).build().perform();
            Wait(1);
        }
        driver.findElement(allTeam).click();
        Wait(2);
    }

    public void clickQA() {
        Actions actions = new Actions(driver);

        for (int i = 0; i < 2; i++) {
            actions.sendKeys(Keys.PAGE_DOWN).build().perform();
            Wait(1);
        }
        driver.findElement(QA).click();
    }

    public void clickAllQaJobs() {
        Wait(2);
        driver.findElement(allQaJobs).click();
    }

    public void clickFilterLocation() {
        Wait(2);
        //Actions action=new Actions(driver);
        //action.sendKeys(Keys.PAGE_DOWN).build().perform();
        driver.findElement(filterLocation).click();

    }

    public void selectIstanbul() {
        Wait(2);
        Actions action = new Actions(driver);
        for (int i = 0; i < 5; i++) {
            action.sendKeys(Keys.ARROW_DOWN).build().perform();
        }
        action.sendKeys(Keys.ENTER).build().perform();
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
        Actions action = new Actions(driver);
        for (int i = 0; i < 2; i++) {
            action.sendKeys(Keys.ARROW_DOWN).build().perform();
        }
        action.moveToElement(driver.findElement(firstJob)).build().perform();
        Assert.assertTrue(driver.findElement(firstJobApplyButton).getText().contains("Apply"));
        action.moveToElement(driver.findElement(secondJob)).build().perform();
        Assert.assertTrue(driver.findElement(secondJobApplyButton).getText().contains("Apply"));
        action.moveToElement(driver.findElement(thirdJob)).build().perform();
        Assert.assertTrue(driver.findElement(thirdJobApplyButton).getText().contains("Apply"));
    }

    public void clickOnApplyButton() {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();
        Wait(1);
        driver.findElement(firstJobApplyButton).click();
        Wait(1);
        driver.findElement(secondJobApplyButton).click();
        Wait(1);
        driver.findElement(thirdJobApplyButton).click();
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

    public void takeScreenShot() {

        TakesScreenshot ts = (TakesScreenshot) driver; // 1.Aşama ekran görünütüsü alma değişkenini tanımladım
        File hafizadakiHali = ts.getScreenshotAs(OutputType.FILE);  // 2.Aşama Saklama tipi seçildi (Dosya), data(Veritabanı) (Byte)

        try {
            FileUtils.copyFile(hafizadakiHali, new File("target/FailedScreenShots/failed.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

