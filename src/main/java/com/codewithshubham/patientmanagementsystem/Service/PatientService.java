package com.codewithshubham.patientmanagementsystem.Service;

import com.codewithshubham.patientmanagementsystem.entities.Doctor;
import com.codewithshubham.patientmanagementsystem.entities.Patient;

import java.util.List;

public interface PatientService {
    Patient addPatient(Patient patient);
    List<Patient> getAllPatients();
    List<Patient> getPatientsByCity(String city);
    Patient updatePatientPhone(Long id, String phone);
    void deletePatient(Long id);
    List<Doctor> suggestDoctors(Long patientId);

    List<Patient> getPatients();
}