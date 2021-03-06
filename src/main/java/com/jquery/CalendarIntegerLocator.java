package com.jquery;


import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.Ordinal.ORDINAL;
import static com.algocrafts.converters.StringToInt.PARSE_INT;
import static com.algocrafts.converters.StringToMonth.TO_MONTH;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.selectors.ClassName.*;
import static com.algocrafts.selectors.Id.UI_DATEPICKER_DIV;

public enum CalendarIntegerLocator implements Locator<Page, Integer> {

    /**
     * Locate the integer value representing current year on a calendar
     */
    CURRENT_YEAR(
            Locators.<Page>element(UI_DATEPICKER_DIV)
                    .and(element(UI_DATEPICKER_HEADER))
                    .and(element(UI_DATEPICKER_YEAR))
                    .and(TEXT)
                    .and(PARSE_INT)
    ),

    /**
     * Locate the integer value representing current year on a calendar
     */
    CURRENT_MONTH(
            Locators.<Page>element(UI_DATEPICKER_DIV)
                    .and(element(UI_DATEPICKER_MONTH))
                    .and(TEXT)
                    .and(TO_MONTH)
                    .and(ORDINAL)
    );

    private final Locator<Page, Integer> locator;

    private CalendarIntegerLocator(Locator<Page, Integer> locator) {
        this.locator = locator;
    }

    @Override
    public Integer locate(Page page) {
        return locator.locate(page);
    }
}
