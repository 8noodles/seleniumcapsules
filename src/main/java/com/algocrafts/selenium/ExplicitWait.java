package com.algocrafts.selenium;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public interface ExplicitWait<Where> {

    /**
     * Save the screenshot if possible.
     */
    void onTimeout();

    /**
     * @param locator locator
     * @param <What>  generic parameter
     * @return the element found by using the locator
     * @throws NoSuchElementException not found
     */
    default public <What> What until(Locator<Where, What> locator) throws NoSuchElementException {
        return until(30, SECONDS, locator);
    }

    /**
     * @param predicate predicate
     * @throws TimeoutException timeout
     */
    default public void until(Predicate<Where> predicate) throws TimeoutException {
        until(30, SECONDS, predicate);
    }

    /**
     * @param duration timeout duration
     * @param timeUnit unit
     * @param locator  locator
     * @param <What>   generic parameter
     * @return the element found by using the locator
     * @throws NoSuchElementException not found
     */
    default public <What> What until(int duration, TimeUnit timeUnit, Locator<Where, What> locator) throws NoSuchElementException {
        try {
            return explicitWait(duration, timeUnit).until(locator::locate);
        } catch (TimeoutException e) {
            onTimeout();
            throw new NoSuchElementException("Nothing found by " + locator, e);
        }
    }

    /**
     * @param duration  timeout duration
     * @param timeUnit  unit
     * @param predicate predicate
     * @throws TimeoutException timeout
     */
    default public void until(int duration, TimeUnit timeUnit, Predicate<Where> predicate) throws TimeoutException {
        try {
            explicitWait(duration, timeUnit).until(predicate::test);
        } catch (TimeoutException e) {
            onTimeout();
            throw e;
        }
    }

    /**
     * @param duration timeout duration
     * @param timeUnit unit
     * @return the FluentWait instance
     */
    @SuppressWarnings("unchecked")
    default public FluentWait<Where> explicitWait(int duration, TimeUnit timeUnit) {
        return new FluentWait<>((Where) this)
                .withTimeout(duration, timeUnit)
                .pollingEvery(5, MILLISECONDS)
                .ignoring(Exception.class);
    }
}
