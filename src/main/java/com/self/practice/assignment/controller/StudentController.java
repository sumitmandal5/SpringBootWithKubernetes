package com.self.practice.assignment.controller;

import com.self.practice.assignment.entity.Sports;
import com.self.practice.assignment.entity.Student;
import com.self.practice.assignment.entity.SubjectStream;
import com.self.practice.assignment.exception.SportNotFoundException;
import com.self.practice.assignment.exception.StudentNotFoundException;
import com.self.practice.assignment.exception.SubjectStreamNotFoundException;
import com.self.practice.assignment.service.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    @Autowired
    private final StudentServiceImpl studentServiceImpl;

    @GetMapping("/getAll")
    public ResponseEntity<CollectionModel> getAllStudents() {
        List<Student> students = studentServiceImpl.getAllStudents();
        CollectionModel<Student> studentCollection = CollectionModel.of(students, linkTo(methodOn(StudentController.class).getAllStudents()).withSelfRel());
        return ResponseEntity.ok(studentCollection);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<EntityModel<Student>> getStudent(@PathVariable Integer id) {
        Student s;
        try {
            s = studentServiceImpl.getStudentById(id);
        } catch (NoSuchElementException ex) {
            throw new StudentNotFoundException("Student not found id = " + id);
        }
        //https://stackoverflow.com/questions/25352764/hateoas-methods-not-found
        //https://spring.io/guides/tutorials/rest/
        EntityModel<Student> studentEntity = EntityModel.of(s, linkTo(methodOn(StudentController.class).getStudent(id)).withSelfRel(),
                linkTo(methodOn(StudentController.class).getAllStudents()).withRel("getAll")
        );
        return ResponseEntity.ok(studentEntity);
        //return ResponseEntity.ok().body(s);
    }

    @PostMapping("/addStudent")
    public void addStudent(@Validated  @RequestBody Student std) {
        //check if the student already exists using the id. If it does throw a customized exception

        studentServiceImpl.addNewStudent(std);
    }

    @DeleteMapping("/deleteStudent/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        Student s;
        try {
            s = studentServiceImpl.getStudentById(id);
        } catch (NoSuchElementException ex) {
            throw new StudentNotFoundException("Student not found id = " + id);
        }
        studentServiceImpl.deleteStudentbyId(id);
    }

    @PutMapping("/modifyStudent")
    public void modifyStudent(@RequestBody Student std) {
        Student s;
        try {
            s = studentServiceImpl.getStudentById(std.getId());
        } catch (NoSuchElementException ex) {
            throw new StudentNotFoundException("Student not found id = " + std.getId());
        }
        studentServiceImpl.modifyStudent(std);
    }

    @GetMapping("/getAllSports")
    public ResponseEntity<CollectionModel> getAllSports() {
        List<Sports> sports = studentServiceImpl.getAllSports();
        CollectionModel<Sports> sportsCollection = CollectionModel.of(sports, linkTo(methodOn(StudentController.class).getAllSports()).withSelfRel());
        return ResponseEntity.ok(sportsCollection);
    }

    @GetMapping("/getSport/{id}")
    public ResponseEntity<EntityModel> getSport(@PathVariable int id) {
        Sports s;
        try {
            s = studentServiceImpl.getSportById(id);
        } catch (NoSuchElementException ex) {
            throw new SportNotFoundException("Sport not found id = " + id);
        }
        EntityModel<Sports> sportsEntity = EntityModel.of(s, linkTo(methodOn(StudentController.class).getSport(id)).withSelfRel(),
                linkTo(methodOn(StudentController.class).getAllSports()).withRel("getAllSports")
        );
        return ResponseEntity.ok(sportsEntity);
    }

    @PostMapping("/addSport")
    public void addSport(@RequestBody Sports sport) {
        studentServiceImpl.addNewSport(sport);
    }

    @DeleteMapping("/deleteSport/{id}")
    public void deleteSport(@PathVariable Integer id) {

        Sports s;
        try {
            s = studentServiceImpl.getSportById(id);
        } catch (NoSuchElementException ex) {
            throw new SportNotFoundException("Sport not found id = " + id);
        }
        studentServiceImpl.deleteSportbyId(id);
    }

    @PutMapping("/modifySport")
    public void modifySport(@RequestBody Sports sport) {
        Sports s;
        try {
            s = studentServiceImpl.getSportById(sport.getId());
        } catch (NoSuchElementException ex) {
            throw new SportNotFoundException("Sport not found id = " + sport.getId());
        }
        studentServiceImpl.modifySport(sport);
    }

    @GetMapping("/getAllSubjectStreams")
    public ResponseEntity<List<SubjectStream>> getAllSubjectStreams() {
        return ResponseEntity.ok().body(studentServiceImpl.getAllSubjectStream());
    }

    @GetMapping("/getSubject/{id}")
    public ResponseEntity<SubjectStream> getSubject(@PathVariable Integer id) {
        SubjectStream s;
        try {
            s = studentServiceImpl.getSubjectStreamById(id);
        } catch (NoSuchElementException ex) {
            throw new SubjectStreamNotFoundException("Subject Stream not found id = " + id);
        }
        return ResponseEntity.ok().body(s);
    }

    @PostMapping("/addSubject")
    public void addSubject(@RequestBody SubjectStream subject) {
        studentServiceImpl.addNewSubjectStream(subject);
    }

    @DeleteMapping("/deleteSubject/{id}")
    public void deleteSubject(@PathVariable Integer id) {

        SubjectStream s;
        try {
            s = studentServiceImpl.getSubjectStreamById(id);
        } catch (NoSuchElementException ex) {
            throw new SubjectStreamNotFoundException("Subject Stream not found id = " + id);
        }
        studentServiceImpl.deleteSubjectStreambyId(id);
    }

    @PutMapping("/modifySubject")
    public void modifySubject(@RequestBody SubjectStream subject) {
        SubjectStream s;
        try {
            s = studentServiceImpl.getSubjectStreamById(subject.getId());
        } catch (NoSuchElementException ex) {
            throw new SubjectStreamNotFoundException("Subject Stream not found id = " + subject.getId());
        }
        studentServiceImpl.modifySubjectStream(subject);
    }

    @GetMapping("/getBysubandsports/{subjectId}/{sportsId}")
    public ResponseEntity<List<Student>> getBysubandsports(@PathVariable Integer subjectId, @PathVariable Integer sportsId) {
        Sports sport;
        try {
            sport = studentServiceImpl.getSportById(sportsId);
        } catch (NoSuchElementException ex) {
            throw new SportNotFoundException("Sport not found id = " + sportsId);
        }

        SubjectStream subject;
        try {
            subject = studentServiceImpl.getSubjectStreamById(subjectId);
        } catch (NoSuchElementException ex) {
            throw new SubjectStreamNotFoundException("Subject Stream not found id = " + subjectId);
        }
        return ResponseEntity.ok().body(studentServiceImpl.getStudentsBasedOnSubjectAndSport(subjectId, sportsId));
    }

    @GetMapping("/getByEMsubandsports/{subjectId}/{sportsId}")
    public ResponseEntity<List<Student>> getByEMsubandsports(@PathVariable Integer subjectId, @PathVariable Integer sportsId) {
        Sports sport;
        try {
            sport = studentServiceImpl.getSportById(sportsId);
        } catch (NoSuchElementException ex) {
            throw new SportNotFoundException("Sport not found id = " + sportsId);
        }

        SubjectStream subject;
        try {
            subject = studentServiceImpl.getSubjectStreamById(subjectId);
        } catch (NoSuchElementException ex) {
            throw new SubjectStreamNotFoundException("Subject Stream not found id = " + subjectId);
        }
        return ResponseEntity.ok().body(studentServiceImpl.getByEMStudentsBasedOnSubjectAndSport(subjectId, sportsId));
    }
}
