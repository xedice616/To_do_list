package com.qwasar.todolist.mapper;

import com.qwasar.todolist.dto.request.TaskRequestDto;
import com.qwasar.todolist.dto.response.TaskResponseDto;
import com.qwasar.todolist.entity.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "favorite", ignore = true)
    @Mapping(target = "archived", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    Task toEntity(TaskRequestDto dto);

    @Mapping(source = "user.id", target = "userId")
    TaskResponseDto toResponse(Task task);
}
//Niyə ignore = true yazırıq?
//
//Çünki bunlar request-dən gəlməməlidir.
//
//Məsələn:
//
//İstifadəçi belə request göndərməməlidir:
//
//{
//  "deleted": true
//}
//
//və ya
//
//{
//  "createdAt": "2025..."
//}
//
//Bunları sistem özü idarə etməlidir.