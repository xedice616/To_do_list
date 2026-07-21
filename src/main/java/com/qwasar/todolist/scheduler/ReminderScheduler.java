package com.qwasar.todolist.scheduler;

import com.qwasar.todolist.entity.Task;
import com.qwasar.todolist.enums.Status;
import com.qwasar.todolist.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReminderScheduler {

    private final TaskRepository taskRepository;

    @Scheduled(fixedRate = 60000)
    public void remindTasks() {

        List<Task> tasks =
                taskRepository.findByDeletedFalseAndArchivedFalseAndStatusNot(Status.DONE);

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        for (Task task : tasks) {

            if (task.getDueDate().equals(today)
                    && !task.getDueTime().isAfter(now)) {

                System.out.println("-----------------------------------");
                System.out.println(" Reminder!");
                System.out.println("Task : " + task.getTitle());
                System.out.println("Due  : " + task.getDueDate()
                        + " " + task.getDueTime());
                System.out.println("-----------------------------------");
            }
        }

    }

}

//Bu necə işləyəcək?
//
//Tutaq ki, database-də belə task var:
//
//title	dueDate	dueTime
//Finish Java Project	2026-07-21	22:00
//
//Saat 22:00 olduqda konsolda belə görünəcək:
//
//-----------------------------------
//Reminder!
//Task : Finish Java Project
//Due  : 2026-07-21 22:00
//-----------------------------------