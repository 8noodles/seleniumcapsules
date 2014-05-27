package com.algocrafts.pages;

import com.algocrafts.clickables.Button;
import com.algocrafts.clickables.Link;
import com.algocrafts.conditions.StringContains;
import com.algocrafts.converters.ElementAtIndex;
import com.algocrafts.converters.Filter;
import com.algocrafts.converters.FirstItem;
import com.algocrafts.converters.StreamToList;
import com.algocrafts.locators.ElementLocator;
import com.algocrafts.locators.ElementsLocator;
import org.openqa.selenium.By;

import java.util.function.Supplier;
import java.util.stream.Stream;

import static com.algocrafts.conditions.ElementPredicates.DISPLAYED;
import static com.algocrafts.converters.GetText.SRC;
import static com.algocrafts.selectors.TagName.IMG;


public interface Searchable<Where extends Searchable<Where>> extends Waitable<Where> {

    /**
     * Find the first element or return null if nothing found.
     *
     * @param by selector
     * @return the first element or return null if nothing found.
     */
    Element findElement(By by);

    /**
     * Find the first element or throw TimeoutException
     *
     * @param by selector
     * @return the first element or throw TimeoutException
     */
    Element untilFound(By by);

    /**
     * Find all elements within the area using the given search method.
     *
     * @param by selector
     * @return A stream of all {@link Element}s, or an empty stream if nothing matches.
     * @see org.openqa.selenium.By
     */
    Stream<Element> findElements(Supplier<By> by);

    /**
     * Find the first button meeting the By method.
     * method to find the button.
     *
     * @param by selector
     * @return
     */
    default public Clickable button(Supplier<By> by) {
        return button(by, 0);
    }

    /**
     * If there are multiple buttons with the same name on the same page, use this
     * method to find the button.
     *
     * @param by    selector
     * @param index
     * @return
     */
    @SuppressWarnings("unchecked")
    default public Clickable button(Supplier<By> by, int index) {
        return new Button<>((Where) this,
                new ElementsLocator<Where>(by)
                        .and(new StreamToList<>())
                        .and(new ElementAtIndex<>(index))
        );
    }

    /**
     * If the button can't be found using the previous two methods, use this.
     *
     * @param locator
     * @return
     */
    @SuppressWarnings("unchecked")
    default public Clickable button(Locator<Where, Element> locator) {
        return new Button<>((Where) this, locator);
    }

    /**
     * The first image using the image file.
     *
     * @param fileName
     * @return
     */
    default public Element image(String fileName) {
        return new FirstItem<Element>().locate(images(fileName));
    }

    /**
     * The image at the given index using the same image file.
     *
     * @param fileName
     * @param index
     * @return
     */
    default public Element image(String fileName, int index) {
        return new StreamToList<Element>()
                .and(new ElementAtIndex<>(index))
                .locate(images(fileName));
    }

    /**
     * Find the images  using the same image file.
     *
     * @param fileName
     * @return the images  using the same image file.
     */
    default public Stream<Element> images(String fileName) {
        return until(new ElementsLocator<Where>(IMG)
                        .and(new Filter<>(DISPLAYED.and(SRC.and(new StringContains(fileName)))))
        );
    }

    /**
     * Find the link using the selector.
     *
     * @param selector
     * @return
     */
    @SuppressWarnings("unchecked")
    default public Clickable link(Supplier<By> selector) {
        return new Link<>((Where) this, new ElementLocator<>(selector));
    }
}
