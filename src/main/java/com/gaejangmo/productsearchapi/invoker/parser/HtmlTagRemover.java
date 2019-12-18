package com.gaejangmo.productsearchapi.invoker.parser;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HtmlTagRemover {
    public static String parse(String input) {
        return input.replaceAll("<.*?>", "");
    }
}

