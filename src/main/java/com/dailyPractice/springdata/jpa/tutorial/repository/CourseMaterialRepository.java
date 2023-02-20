package com.dailyPractice.springdata.jpa.tutorial.repository;

import com.dailyPractice.springdata.jpa.tutorial.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMaterialRepository extends JpaRepository<CourseMaterial,Long> {
}
