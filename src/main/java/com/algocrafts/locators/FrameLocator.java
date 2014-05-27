package com.algocrafts.locators;

import com.algocrafts.pages.AbstractPage;
import com.algocrafts.pages.Locator;

public class FrameLocator implements Locator<AbstractPage, AbstractPage> {

    private final int index;

    public FrameLocator(int index) {
        this.index = index;
    }

    @Override
    public AbstractPage locate(AbstractPage page) {
        return page.frame(index);
    }

    @Override
    public String toString() {
        return "frames[" + index + "]";
    }
}