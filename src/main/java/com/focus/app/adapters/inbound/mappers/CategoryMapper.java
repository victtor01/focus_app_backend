package com.focus.app.adapters.inbound.mappers;

import com.focus.app.adapters.inbound.dtos.request.CreateTaskCategoryRecord;
import com.focus.app.application.commands.CreateTaskCategoryCommand;

public class CategoryMapper {
    public static CreateTaskCategoryCommand toCommand(CreateTaskCategoryRecord record) {
        return new CreateTaskCategoryCommand(record.name(), record.color());
    }
}
