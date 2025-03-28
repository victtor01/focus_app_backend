package com.focus.app.shared.utils;

import lombok.Getter;

public class MessageResponse {
    @Getter
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

}
