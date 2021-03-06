package com.algocrafts.locators;

import com.algocrafts.selenium.Element;
import com.algocrafts.selenium.Locator;
import com.algocrafts.selenium.SearchScope;
import org.openqa.selenium.By;

import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Locators<Where extends SearchScope<Where>, What>
        implements Locator<Where, What> {

    public static <Where extends SearchScope<Where>> Locators<Where, Element> element(Supplier<By> selector) {
        return new ElementLocator<>(selector);
    }

    public static <Where extends SearchScope<Where>> Locators<Where, Stream<Element>> elements(Supplier<By> selector) {
        return new ElementsLocator<>(selector);
    }

    public static <Where extends SearchScope<Where>> Locators<Where, Optional<Element>> optionalElement(Supplier<By> selector) {
        return new OptionalElementLocator<>(selector);
    }

    public static SelectLocator select(Supplier<By> selector) {
        return new SelectLocator(selector);
    }

    private final Locator<Where, What> locator;

    public Locators(Locator<Where, What> locator) {
        this.locator = locator;
    }

    @Override
    public What locate(Where where) {
        return locator.locate(where);
    }
}