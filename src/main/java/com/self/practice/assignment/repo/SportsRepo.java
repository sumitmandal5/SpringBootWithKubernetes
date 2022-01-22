package com.self.practice.assignment.repo;

import com.self.practice.assignment.entity.Sports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SportsRepo extends JpaRepository<Sports,Integer> {
}
