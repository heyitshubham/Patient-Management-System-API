package com.codewithshubham.patientmanagementsystem.Service;

import com.codewithshubham.patientmanagementsystem.entities.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor addDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();

    List<Doctor> getDoctorsByCity(String city);

    List<Doctor> getDoctorsByCityAndSpeciality(String city, String speciality);

    Doctor updateDoctorPhone(Long id, String phone);

    void deleteDoctorById(Long id);

    List<Doctor> suggestDoctors(Long patientId);
}

