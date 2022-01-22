package com.self.practice.assignment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Sports {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Integer id;
    String name;
    String description;
    @OneToMany(fetch = FetchType.LAZY, mappedBy="sports")
    //@JsonBackReference
    List<Student> students;
}
