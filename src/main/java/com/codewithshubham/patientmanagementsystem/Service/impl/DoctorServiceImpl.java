package com.codewithshubham.patientmanagementsystem.Service.impl;

import com.codewithshubham.patientmanagementsystem.Repositories.DoctorRepository;
import com.codewithshubham.patientmanagementsystem.Repositories.PatientRepository;
import com.codewithshubham.patientmanagementsystem.Service.DoctorService;
import com.codewithshubham.patientmanagementsystem.entities.Doctor;
import com.codewithshubham.patientmanagementsystem.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> getDoctorsByCity(String city) {
        return doctorRepository.findByCity(city);
    }

    @Override
    public List<Doctor> getDoctorsByCityAndSpeciality(String city, String speciality) {
        return doctorRepository.findByCityAndSpeciality(city, speciality);
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctorById(Long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public Doctor updateDoctorPhone(Long id, String phone) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        doctor.setPhone(phone);
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> suggestDoctors(Long patientId) {
        // Get the patient's city and symptom from the database
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new IllegalArgumentException("Patient with id " + patientId + " does not exist"));
        String city = patient.getCity();
        String symptom = patient.getSymptom();

        // Get all the doctors in the patient's city who specialize in the same area as the patient's symptom
        List<Doctor> doctors = doctorRepository.findByCityAndSpeciality(city, getSpecialityFromSymptom(symptom));

        // Handle edge cases
        if (doctors.isEmpty()) {
            throw new IllegalStateException("There isn't any doctor present at your location for your symptom");
        } else if (!Arrays.asList("Delhi", "Noida", "Faridabad").contains(city)) {
            throw new IllegalStateException("We are still waiting to expand to your location");
        }

        return doctors;
    }

    private String getSpecialityFromSymptom(String symptom) {
        switch (symptom) {
            case "Arthritis":
            case "Backpain":
            case "Tissue injuries":
                return "Orthopedic";
            case "Dysmenorrhea":
                return "Gynecology";
            case "Skin infection":
            case "Skin burn":
                return "Dermatology";
            case "Ear pain":
                return "ENT specialist";
            default:
                throw new IllegalArgumentException("Invalid symptom: " + symptom);
        }
    }
}
