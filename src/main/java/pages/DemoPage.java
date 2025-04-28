package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class DemoPage {

    WebDriver driver;

    //Page Elements

    private WebElement getElementById(String id) {
        return driver.findElement(By.id(id));
    }
    @FindBy(id = "myTextInput")
    WebElement textInput;

    @FindBy(css = "svg rect")
    WebElement svgRect;

    @FindBy(id = "checkBox6")
    private WebElement iframeCheckbox;

    @FindBy(id = "mySelect")
    WebElement dropDown;

    @FindBy(id = "meterBar")
    WebElement meterBar;

    //Constructor
    public DemoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //PageMethods
    public void enterTextInput(String text) {
        WebElement textInput = getElementById("myTextInput");
        textInput.clear();
        textInput.sendKeys(text);

    }

    public String getTextInputValue() {
        WebElement textInput = getElementById("myTextInput");
        return textInput.getAttribute("value");
    }

    public String getSvgRectColor() {
        return svgRect.getAttribute("fill");
    }

    public void toggleIframeCheckbox() {
        driver.switchTo().frame("myFrame3");
        WebElement iframeCheckbox = getElementById("checkBox6");
        iframeCheckbox.click();
        driver.switchTo().defaultContent();
    }

    public void selectDropdownOption(String option) {
        WebElement dropdown = getElementById("myDropdown");
        Select select = new Select(dropDown);
            select.selectByVisibleText(option);
    }

    public boolean isIframeCheckboxChecked() {
        driver.switchTo().frame("myFrame3");
        WebElement iframeCheckbox = getElementById("checkBox6");
        boolean isChecked = iframeCheckbox.isSelected();
        driver.switchTo().defaultContent();
        return isChecked;
    }

    public String getMeterValue() {
        WebElement meterBar = getElementById("meterBar");
        return meterBar.getAttribute("value");
        }
    }

