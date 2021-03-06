package com.jquery;

import com.algocrafts.calendar.DayLocatorFactory;
import com.algocrafts.conditions.Equals;
import com.algocrafts.converters.FirstMatch;
import com.algocrafts.locators.Locators;
import com.algocrafts.pages.Page;
import com.algocrafts.selenium.Locator;

import static com.algocrafts.converters.ElementFunctions.CLICK;
import static com.algocrafts.converters.GetText.TEXT;
import static com.algocrafts.converters.OptionalGetter.GET;
import static com.algocrafts.locators.Locators.element;
import static com.algocrafts.locators.Locators.elements;
import static com.algocrafts.selectors.ClassName.UI_DATEPICKER_CALENDAR;
import static com.algocrafts.selectors.Id.UI_DATEPICKER_DIV;
import static com.algocrafts.selectors.TagName.TD;

public class JQueryDayLocatorFactory implements DayLocatorFactory {

    public Locator<Page, Void> forDay(int day) {
        return new JQueryDayLocator(
                Locators.<Page>element(UI_DATEPICKER_DIV)
                        .and(element(UI_DATEPICKER_CALENDAR))
                        .and(elements(TD))
                        .and(new FirstMatch<>(TEXT.and(new Equals(day))))
                        .and(GET)
                        .and(CLICK)
        );
    }


}
