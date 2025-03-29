package com.focus.app.adapters.inbound.dtos.response;

import java.util.UUID;

public record TaskCategoryResponse(
    UUID id,
    String name,
    String color
) {

}
