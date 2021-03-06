package com.algocrafts.clickables;


import com.algocrafts.selenium.SearchScope;

public class ImageButton<Where extends SearchScope<Where>> extends Button<Where> {

    public ImageButton(Where where, String fileName, int index) {
        super(where, finder -> finder.image(fileName, index));
    }
}
