package tests;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DemoPage;
import utils.ExtentReportManager;

import java.time.Duration;

public class DemoPageTests {

    WebDriver driver;
    DemoPage demoPage;

    @BeforeClass
    public void setup() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://seleniumbase.io/demo_page");
        demoPage = new DemoPage(driver);
    }

    @Test
    public void testTextInputField() {
        ExtentReportManager.createTest("Test Text Input Field");
        try {
            String inputText = "MAPS is boring";
            demoPage.enterTextInput(inputText);
            Assert.assertEquals(demoPage.getTextInputValue(), inputText, "Text input value doesn't match");
            ExtentReportManager.getTest().log(Status.PASS, "Text input filled and verified successfully: " + demoPage.getTextInputValue());
        }
        catch (Exception e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testSVGColorExtraction() {
        ExtentReportManager.createTest("Test SVG Rect Color");
        try {
            String color = demoPage.getSvgRectColor();
            System.out.println("Captured SVG Color (fill): " + color);
            Assert.assertNotNull(color, "SVG Color should not be null");
            ExtentReportManager.getTest().log(Status.PASS, "SVG Rect Color retrieved: " + color);
        }
        catch (Exception e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCheckBoxInIframe() {
        ExtentReportManager.createTest("Test CheckBox In iFrame");
        try {
            demoPage.toggleIframeCheckbox();
            Assert.assertTrue(demoPage.isIframeCheckboxChecked(), "Checkbox is not selected!");
            driver.switchTo().defaultContent();
            ExtentReportManager.getTest().log(Status.PASS, "Iframe checkbox toggled and verified as checked");
        }
        catch (Exception e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void testDropdownAndMeterUpdate() {
        ExtentReportManager.createTest("Test Dropdown and Meter Update");
        try {
            String option = "Set to 50%";
            demoPage.selectDropdownOption(option);
            String expectedMeterValue = "0.5";
            Assert.assertEquals(demoPage.getMeterValue(), expectedMeterValue, "Meter value doesn't match selected option");
            ExtentReportManager.getTest().log(Status.PASS, "Dropdown set to '" + option + "' and meter value verified: " + demoPage.getMeterValue());
        } catch (Exception e) {
            ExtentReportManager.getTest().log(Status.FAIL, "Test failed: " + e.getMessage());
            throw e;
        }
    }

    @AfterClass
    public void teardown() {
        ExtentReportManager.flush();
        if (driver != null) {
            driver.quit();
        }
    }
}

