package com.focus.app.core.utils;

import lombok.Getter;

public class MessageResponse {
    @Getter
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

}
