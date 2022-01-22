package com.self.practice.assignment.service;

import com.self.practice.assignment.entity.SubjectStream;

import java.util.List;

public interface SubjectStreamService {
    public List<SubjectStream> getAllSubjectStream();

    public SubjectStream getSubjectStreamById(Integer id);


    public void addNewSubjectStream(SubjectStream subject);

    public void deleteSubjectStreambyId(Integer id);

    public void modifySubjectStream(SubjectStream subject);
}
