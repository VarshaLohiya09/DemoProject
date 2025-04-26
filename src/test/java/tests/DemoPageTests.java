package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.DemoPage;

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

    @Test(priority = 1)
    public void testTextInputField() {
        String inputText = "MAPS is boring";
        demoPage.enterTextInput(inputText);
        Assert.assertEquals(demoPage.getTextInputValue(), inputText);

    }

    @Test(priority = 2)
    public void testSVGColorExtraction() {
        String color = demoPage.getSvgRectColor();
        System.out.println("Captured SVG Color (fill): " + color);
        Assert.assertNotNull(color, "SVG Color should not be null");
    }

    @Test(priority = 3)
    public void testCheckBoxInIframe() {
        demoPage.toggleIframeCheckbox();
        Assert.assertTrue(demoPage.isIframeCheckboxChecked(), "Checkbox is not selected!");
        driver.switchTo().defaultContent();  // Always switch back
    }

    @Test(priority = 4)
    public void testDropdownAndMeterUpdate() {
        String option = "Set to 50%";
        demoPage.selectDropdownOption(option);
        String expectedMeterValue = "0.5";
        Assert.assertEquals(demoPage.getMeterValue(), expectedMeterValue, "Meter value doesn't match selected option");

    }

    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

