package com.hms.te.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.te.entity.TrainingProgram;

public interface TrainingProgramRepository extends JpaRepository<TrainingProgram, String> {

}