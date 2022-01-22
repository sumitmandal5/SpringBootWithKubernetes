package com.self.practice.assignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Size;

import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQueries(value={
        @NamedQuery(name="queryStudentsBasedOnSubjectAndSports",query="select s from Student s where s.stream.id = ?1 and s.sports.id = ?2"),
        @NamedQuery(name="queryGetAllStudents",query="select s from Student s")})
@SQLDelete(sql="update student set is_deleted=true where  id=?")
@Where(clause="is_deleted = false")
public class Student {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Integer id;
    @Size(min=2, message = "name should have atleast 2 characters")
    @Column(nullable = false)
    String name;
    @ManyToOne(fetch = EAGER)
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnoreProperties("students") //https://stackoverflow.com/questions/16577907/hibernate-onetomany-relationship-causes-infinite-loop-or-empty-entries-in-json
    //@JsonManagedReference
    SubjectStream stream;
    @ManyToOne(fetch = EAGER)
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) //https://stackoverflow.com/questions/55942356/converting-lazy-loaded-object-to-json-in-spring-boot-jpa/55942487
    @JsonIgnoreProperties("students")
    //@JsonManagedReference
    Sports sports;
    private boolean isDeleted;
    @PreRemove
    private void preRemove(){
        this.isDeleted=true;
    }
}
