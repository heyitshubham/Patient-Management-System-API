package com.codewithshubham.patientmanagementsystem.Repositories;

import com.codewithshubham.patientmanagementsystem.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByCity(String city);
}
