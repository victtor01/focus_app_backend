package com.focus.app.shared.utils;

import com.fasterxml.jackson.annotation.JsonValue;
import com.focus.app.shared.exceptions.BadRequestException;

import java.util.regex.Pattern;

public class Color {

    private static final String HEX_PATTERN = "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$";
    private static final String RGB_PATTERN = "^rgb\\((\\d{1,3}),\\s*(\\d{1,3}),\\s*(\\d{1,3})\\)$";
    private static final String RGBA_PATTERN = "^rgba\\((\\d{1,3}),\\s*(\\d{1,3}),\\s*(\\d{1,3}),\\s*(0(\\.\\d+)?|1(\\.0+)?)\\)$";
    private static final String HSL_PATTERN = "^hsl\\(\\d{1,3},\\s*\\d{1,3}%,\\s*\\d{1,3}%\\)$";
    private static final String HSLA_PATTERN = "^hsla\\(\\d{1,3},\\s*\\d{1,3}%,\\s*\\d{1,3}%,\\s*(0(\\.\\d+)?|1(\\.0+)?)\\)$";
    private static final String COLOR_PATTERN = HEX_PATTERN + "|" + RGB_PATTERN + "|" + RGBA_PATTERN + "|" + HSL_PATTERN + "|" + HSLA_PATTERN;

    private String value;

    public Color(String value) {

        if (!Pattern.matches(COLOR_PATTERN, value)) {
            throw new BadRequestException("Formato de cor inválido! Use hexadecimal, RGB(A) ou HSL(A).");
        }

        this.value = value;
    }

    @JsonValue
    @Override
    public String toString() {
        return value;
    }
}
