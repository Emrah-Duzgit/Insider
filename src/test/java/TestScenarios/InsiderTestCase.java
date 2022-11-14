package TestScenarios;

import Objects.*;
import Utils.GeneralWebDriver;
import junit.framework.Assert;
import org.testng.annotations.*;


public class InsiderTestCase {

    @BeforeClass(alwaysRun = true)
    @Parameters("browser")
    public void beforeClass(String browser)
    {
        GeneralWebDriver.threadBrowserName.set(browser);
    }


    @Test
    public void assertInsiderHomaPage(){
        GeneralWebDriver.getDriver().get("https://useinsider.com/");
        insiderHomaPage insiderPage = new insiderHomaPage(GeneralWebDriver.getDriver());
        insiderPage.acceptCookiesFunctionality();
        Assert.assertTrue(GeneralWebDriver.getDriver().getTitle().contains("Cross-Channel CX — Insider"));
    }
    @Test(dependsOnMethods = "assertInsiderHomaPage")
    public void checkCareersPagesBlocks() {
        careersPage careerPage = new careersPage(GeneralWebDriver.getDriver());
        careerPage.clickMoreMenu();
        careerPage.clickCareer();
        careerPage.assertCareerPage();
        careerPage.assertLocations();
        careerPage.assertLife();
        careerPage.assertTeam();
        }
    @Test(dependsOnMethods = "checkCareersPagesBlocks")
    public void QAJobs(){
        QAJobPage QaJobsPage = new QAJobPage(GeneralWebDriver.getDriver());
        QaJobsPage.clickAllTeams();
        QaJobsPage.clickQA();
        QaJobsPage.clickAllQaJobs();
        QaJobsPage.clickFilterLocation();
        QaJobsPage.selectIstanbul();
        QaJobsPage.checkJobList();
    }
    @Test(dependsOnMethods = "QAJobs")
    public void checkPositionsContainsQA(){
        QAJobPage QaJobsPage = new QAJobPage(GeneralWebDriver.getDriver());
        QaJobsPage.checkPositionContainsQA();
        QaJobsPage.departmentCheck();
        QaJobsPage.checkLocation();
        QaJobsPage.checkApplyButton();
    }
    @Test(dependsOnMethods = "checkPositionsContainsQA")
    public void applyButtonClick(){
        QAJobPage QaJobsPage = new QAJobPage(GeneralWebDriver.getDriver());
        QaJobsPage.clickOnApplyButton();
        QaJobsPage.assertLeverPage();
    }

    @AfterTest
    public void afterTest(){
        QAJobPage QaJobsPage = new QAJobPage(GeneralWebDriver.getDriver());
        QaJobsPage.takeScreenShot();
        GeneralWebDriver.Wait(5);
        GeneralWebDriver.getDriver().quit();
    }

}
