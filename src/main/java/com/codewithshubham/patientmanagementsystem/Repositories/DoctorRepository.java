package com.codewithshubham.patientmanagementsystem.Repositories;

import com.codewithshubham.patientmanagementsystem.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    List<Doctor> findByCity(String city);

    List<Doctor> findByCityAndSpeciality(String city, String speciality);
}
