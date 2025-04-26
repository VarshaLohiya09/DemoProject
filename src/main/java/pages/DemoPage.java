package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class DemoPage {

    WebDriver driver;

    @FindBy(id = "myTextInput")
    WebElement textInput;

    @FindBy(css = "svg rect")
    WebElement svgRect;

    @FindBy(id = "iframeResult")
    WebElement iframe;

    @FindBy(id = "checkBox6")
    private WebElement iframeCheckbox;

    @FindBy(id = "mySelect")
    WebElement dropDown;

    @FindBy(id = "meterBar")
    WebElement meterBar;

    public DemoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterTextInput(String text) {
        textInput.clear();
        textInput.sendKeys(text);

    }

    public String getTextInputValue() {
        return textInput.getAttribute("value");
    }

    public String getSvgRectColor() {
        return svgRect.getAttribute("fill");
    }

    public void toggleIframeCheckbox() {
        driver.switchTo().frame("myFrame3");
        iframeCheckbox.click();
        driver.switchTo().defaultContent();
    }

    public void selectDropdownOption(String option) {
            Select select = new Select(dropDown);
            select.selectByVisibleText(option);
    }

    public boolean isIframeCheckboxChecked() {
        driver.switchTo().frame("myFrame3");
        boolean isChecked = iframeCheckbox.isSelected();
        driver.switchTo().defaultContent();
        return isChecked;
    }

    public String getMeterValue() {
            return meterBar.getAttribute("value");
        }
    }

