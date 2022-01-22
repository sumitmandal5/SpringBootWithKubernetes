package com.self.practice.assignment.repo;

import com.self.practice.assignment.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Integer> {
    @Query("select s from Student s where s.stream.id = ?1 and s.sports.id = ?2")
    public List<Student> getStudentsBasedOnSubjectAndSport(Integer streamId, Integer sportId);
}
