package com.example.pmcourse.repository;

import com.example.pmcourse.model.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    List<Task> findAll();
    Optional<Task> findById(Long id);

    @Modifying
    @Query("UPDATE Task t set t.done = TRUE  where  t.id = :id")
    void markAsDone(@Param("id") Long id);
}
