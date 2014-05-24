package com.algocrafts.browsers;

import org.openqa.selenium.safari.SafariDriver;

import java.io.File;

import static org.openqa.selenium.OutputType.FILE;

public class Safari implements WebDriverSupplier<SafariDriver> {
    @Override
    public SafariDriver get() {
        return new SafariDriver();
    }

    @Override
    public File takeScreenShot(WebDriverSupplier<SafariDriver> webDriver) {
        return webDriver.get().getScreenshotAs(FILE);
    }
}
