package TestScenarios;

import Objects.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;


public class TestMethods_googleSearchPage {

    WebDriver driver;
    WebDriverWait wait;


    public static void Wait(int second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeTest
    public void beforeTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait=new WebDriverWait(driver, Duration.ofSeconds(50));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(50)); // sadece ana sayfa yüklenirken en başta
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // bütün webElement için geçerli
        //driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://useinsider.com/");


    }
    @Test
    public void assertInsiderHomaPage(){
        insiderHomaPage insiderPage = new insiderHomaPage(driver);
        insiderPage.acceptCookiesFunctionality();
        Assert.assertTrue(driver.getTitle().contains("Cross-Channel CX — Insider"));
    }
    @Test(dependsOnMethods = "assertInsiderHomaPage")
    public void checkCareersPagesBlocks() {
        careersPage careerPage = new careersPage(driver);
        careerPage.clickMoreMenu();
        careerPage.clickCareer();
        careerPage.assertCareerPage();
        careerPage.assertLocations();
        careerPage.assertLife();
        careerPage.assertTeam();
        }
    @Test(dependsOnMethods = "checkCareersPagesBlocks")
    public void QAJobs(){
        QAJobPage QaJobsPage = new QAJobPage(driver);
        QaJobsPage.clickAllTeams();
        QaJobsPage.clickQA();
        QaJobsPage.clickAllQaJobs();
        QaJobsPage.clickFilterLocation();
        QaJobsPage.selectIstanbul();
        QaJobsPage.checkJobList();
    }
    @Test(dependsOnMethods = "QAJobs")
    public void checkPositionsContainsQA(){
        QAJobPage QaJobsPage = new QAJobPage(driver);
        QaJobsPage.checkPositionContainsQA();
        QaJobsPage.departmentCheck();
        QaJobsPage.checkLocation();
        QaJobsPage.checkApplyButton();
    }
    @Test(dependsOnMethods = "checkPositionsContainsQA")
    public void applyButtonClick(){
        QAJobPage QaJobsPage = new QAJobPage(driver);
        QaJobsPage.clickOnApplyButton();
        QaJobsPage.assertLeverPage();
    }


    @AfterTest
    public void afterTest(){
        Wait(5);
        driver.quit();
    }

}
