package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Methods {

    public static String key_control = Keys.CONTROL.toString();
    public static String key_shift = Keys.SHIFT.toString();

    public static List<String> allWindows;
    public static int opened_window;

    public static String downloadPath;


    public static void setSystemProperty(){
        System.setProperty("web driver.gecko.driver", "src/main/java/geckodriver.exe");

        String home = System.getProperty("user.home");
        File downloads = new File(home, "Downloads");
        downloadPath = downloads.getPath() + "\\";
    }

    public static void pause(float pauseSec){
        int pauseMilliseconds = Math.round(pauseSec * 1000);
        try {
            Thread.sleep(pauseMilliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void openInNewTabByButton(WebDriver driver, WebElement button_element){
        Actions act = new Actions(driver);
        act
                .keyDown(key_control)
                .keyDown(key_shift)
                .click(button_element)
                .keyUp(key_control)
                .keyUp(key_shift)
                .build().perform();
    }

    public static void doubleClick(WebDriver driver, WebElement element){
        new Actions(driver).doubleClick(element).perform();
    }

    public static void addNewTab(WebDriver driver, JavascriptExecutor js, int tab_num){
        opened_window = tab_num;
        js.executeScript("window.open()");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        switchToTab(driver, tab_num);
    }

    public static void switchToTab(WebDriver driver, int tab_num){
        allWindows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(allWindows.get(tab_num));
    }

    public static void waitForElem(WebDriver driver, String xpath){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    public static void moveToElem(JavascriptExecutor js, WebElement element){
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
}
