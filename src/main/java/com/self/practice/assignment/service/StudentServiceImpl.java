package com.self.practice.assignment.service;

import com.self.practice.assignment.entity.Sports;
import com.self.practice.assignment.entity.Student;
import com.self.practice.assignment.entity.SubjectStream;
import com.self.practice.assignment.repo.SportsRepo;
import com.self.practice.assignment.repo.StudentRepo;
import com.self.practice.assignment.repo.SubjectStreamRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class StudentServiceImpl implements StudentService, SportsService, SubjectStreamService {
    private final StudentRepo studentrepo;
    private final SportsRepo sportsrepo;
    private final SubjectStreamRepo subjectStreamrepo;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudents() {
        return studentrepo.findAll();
    }

    @Override
    @Transactional
    public Student getStudentById(Integer id) {
        return studentrepo.findById(id).get();
    }


    @Override
    public void addNewStudent(Student student) {
        studentrepo.save(student);
    }

    @Override
    public void deleteStudentbyId(Integer id) {
        studentrepo.deleteById(id);
    }

    @Override
    public void modifyStudent(Student student) {
        studentrepo.save(student);
    }

    @Override
    public List<Sports> getAllSports() {
        return sportsrepo.findAll();
    }

    @Override
    public Sports getSportById(Integer id) {
        return sportsrepo.findById(id).get();
    }


    @Override
    public void addNewSport(Sports sport) {
        sportsrepo.save(sport);
    }

    @Override
    public void deleteSportbyId(Integer id) {
        sportsrepo.deleteById(id);
    }

    @Override
    public void modifySport(Sports sport) {
        sportsrepo.save(sport);
    }

    @Override
    public List<SubjectStream> getAllSubjectStream() {
        return subjectStreamrepo.findAll();
    }

    @Override
    public SubjectStream getSubjectStreamById(Integer id) {
        return subjectStreamrepo.findById(id).get();
    }


    @Override
    public void addNewSubjectStream(SubjectStream subject) {
        subjectStreamrepo.save(subject);
    }

    @Override
    public void deleteSubjectStreambyId(Integer id) {
        subjectStreamrepo.deleteById(id);
    }

    @Override
    public void modifySubjectStream(SubjectStream subject) {
        subjectStreamrepo.save(subject);
    }


    public List<Student> getStudentsBasedOnSubjectAndSport(Integer sub, Integer sport){
        return studentrepo.getStudentsBasedOnSubjectAndSport(sub,sport);
    }

    //just for practice writing named query using jpql
    public List<Student> getByEMStudentsBasedOnSubjectAndSport(Integer sub, Integer sport){
        Query query = entityManager.createNamedQuery("queryStudentsBasedOnSubjectAndSports",Student.class);
        query.setParameter(1,sub);
        query.setParameter(2,sport);
        List<Student> studentList = query.getResultList();
        return studentList;
    }
}
