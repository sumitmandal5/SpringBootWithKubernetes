package com.self.practice.assignment.service;

import com.self.practice.assignment.entity.Sports;

import java.util.List;

public interface SportsService {
    public List<Sports> getAllSports();

    public Sports getSportById(Integer id);

    public void addNewSport(Sports sport);

    public void deleteSportbyId(Integer id);

    public void modifySport(Sports sport);
}
