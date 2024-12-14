package com.ahsmus.court.utils;

import com.ahsmus.court.core.enums.MessageColor;

public class TextBuilder {

    public static String bm(String text, MessageColor color) {
        return color.getCode() + text;
    }
}
