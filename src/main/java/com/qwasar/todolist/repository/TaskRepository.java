package com.qwasar.todolist.repository;

import com.qwasar.todolist.entity.Task;
import com.qwasar.todolist.enums.Priority;
import com.qwasar.todolist.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findByDeletedFalse(Pageable pageable);

    Page<Task> findByDeletedFalseAndTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Task> findByDeletedFalseAndDescriptionContainingIgnoreCase(String description, Pageable pageable);

    Page<Task> findByDeletedFalseAndStatus(Status status, Pageable pageable);

    Page<Task> findByDeletedFalseAndPriority(Priority priority, Pageable pageable);

    Page<Task> findByDeletedFalseAndDueDate(LocalDate dueDate, Pageable pageable);

    Page<Task> findByDeletedFalseAndFavoriteTrue(Pageable pageable);

    Page<Task> findByDeletedFalseAndArchivedTrue(Pageable pageable);


    List<Task> findByDeletedFalseAndArchivedFalseAndStatusNot(Status status);


    long countByDeletedFalse();

    long countByDeletedFalseAndStatus(Status status);

    long countByDeletedFalseAndFavoriteTrue();

    long countByDeletedFalseAndArchivedTrue();

}


//Niyə Page qaytarırıq?
//
//Çünki sonradan belə endpoint yazacağıq:
//
//GET /tasks?page=0&size=10
//
//və ayrıca pagination kodu yazmağa ehtiyac qalmayacaq.





//Niyə DeletedFalse yazırıq?
//
//Çünki biz Soft Delete istifadə edirik.
//
//Database-də:
//
//id	title	deleted
//1	Java	false
//2	Spring	true
//
//Normal istifadəçi yalnız bunu görəcək:
//
//Java
//
//Silinmiş task isə gizli qalacaq.
//
//STEP 2 — Search Endpoint
//
//Controller-də yeni endpoint yaradaq.
//
//@GetMapping("/search")
//public ResponseEntity<Page<TaskResponseDto>> searchByTitle(
//        @RequestParam String title,
//        Pageable pageable
//) {
//
//    return ResponseEntity.ok(taskService.searchByTitle(title, pageable));
//
//}
//Service Interface
//Page<TaskResponseDto> searchByTitle(String title,
//                                    Pageable pageable);
//ServiceImpl
//@Override
//public Page<TaskResponseDto> searchByTitle(String title,
//                                           Pageable pageable) {
//
//    return taskRepository
//            .findByDeletedFalseAndTitleContainingIgnoreCase(title, pageable)
//            .map(taskMapper::toResponse);
//}
//
//İndi Postman-də belə işləyəcək:
//
//GET
/// api/tasks/search?title=java
//
//və nəticə:
//
//[
//   {
//      "title":"Learn Java"
//   },
//   {
//      "title":"Java Project"
//   }
//]
//STEP 3 — Description Search
//
//Repository-də artıq hazırdır.
//
//Controller:
//
//@GetMapping("/search/description")
//
//Service:
//
//searchByDescription(...)
//
