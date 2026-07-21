package com.qwasar.todolist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponseDto {

    private long totalTasks;

    private long completedTasks;

    private long pendingTasks;

    private long inProgressTasks;

    private long cancelledTasks;

    private long favoriteTasks;

    private long archivedTasks;

}