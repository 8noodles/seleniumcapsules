package com.algocrafts.browsers;

import com.algocrafts.pages.Browser;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

import static org.openqa.selenium.OutputType.FILE;

public class FirefoxOnWindows implements  Browser<FirefoxDriver>, WebDriverSupplier<FirefoxDriver> {

    public FirefoxDriver init() {
        FirefoxBinary binary = new FirefoxBinary(new File("src/main/resources/Firefox/Contents/MacOS/firefox-bin"));
        FirefoxProfile profile = new FirefoxProfile(new File("src/main/resources/Firefox/Profiles/default"));
        return new FirefoxDriver(binary, profile);
    }

    @Override
    public File takeScreenShot(WebDriverSupplier<FirefoxDriver> driver) {
        return driver.get().getScreenshotAs(FILE);
    }

    @Override
    public WebDriverSupplier<FirefoxDriver> getSupplier() {
        return this;
    }

}