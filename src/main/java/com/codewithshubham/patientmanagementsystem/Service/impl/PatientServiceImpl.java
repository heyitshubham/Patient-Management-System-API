package com.codewithshubham.patientmanagementsystem.Service.impl;

import com.codewithshubham.patientmanagementsystem.Exceptions.InvalidRequestException;
import com.codewithshubham.patientmanagementsystem.Repositories.DoctorRepository;
import com.codewithshubham.patientmanagementsystem.Repositories.PatientRepository;
import com.codewithshubham.patientmanagementsystem.Service.DoctorService;
import com.codewithshubham.patientmanagementsystem.Service.PatientService;
import com.codewithshubham.patientmanagementsystem.entities.Doctor;
import com.codewithshubham.patientmanagementsystem.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codewithshubham.patientmanagementsystem.Exceptions.*;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorService doctorService;

    @Override
    public Patient addPatient(Patient patient) {
        validatePatient(patient);
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> getPatientsByCity(String city) {
        validateCity(city);
        return patientRepository.findByCity(city);
    }

    @Override
    public Patient updatePatientPhone(Long id, String phone) {
        validatePhoneNumber(phone);
        Patient patient = getPatientById(id);
        patient.setPhone(phone);
        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = getPatientById(id);
        patientRepository.delete(patient);
    }

    @Override
    public List<Doctor> suggestDoctors(Long patientId) {
        Patient patient = getPatientById(patientId);
        String symptom = patient.getSymptom();
        String city = patient.getCity();
        List<Doctor> doctors = doctorService.getDoctorsByCityAndSpeciality(city, getSpecialityFromSymptom(symptom));
        if (doctors.isEmpty()) {
            throw new NotFoundException("There isn't any doctor present at your location for your symptom");
        }
        return doctors;
    }

    @Override
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }

    private Patient getPatientById(Long id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isEmpty()) {
            throw new NotFoundException("Patient not found with id: " + id);
        }
        return optionalPatient.get();
    }

    private void validatePatient(Patient patient) {
        validateName(patient.getName());
        validateCity(patient.getCity());
        validateEmail(patient.getEmail());
        validatePhoneNumber(patient.getPhone());
    }

    private void validateName(String name) {
        if (name == null || name.length() < 3) {
            throw new InvalidRequestException("Name should be at least 3 characters");
        }
    }

    private void validateCity(String city) {
        if (city == null || city.length() > 20) {
            throw new InvalidRequestException("City should be at max 20 characters");
        }
    }

    private void validateEmail(String email) {
        if (email == null || !email.matches("\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b")) {
            throw new InvalidRequestException("Invalid email format");
        }
    }

    private void validatePhoneNumber(String phone) {
        if (phone == null || phone.length() < 10) {
            throw new InvalidRequestException("Phone number should be at least 10 numbers");
        }
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
                throw new InvalidRequestException("Invalid symptom");
        }
    }
}

