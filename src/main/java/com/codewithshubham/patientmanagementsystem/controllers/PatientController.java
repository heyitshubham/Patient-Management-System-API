package com.codewithshubham.patientmanagementsystem.controllers;

import com.codewithshubham.patientmanagementsystem.Service.PatientService;
import com.codewithshubham.patientmanagementsystem.entities.Doctor;
import com.codewithshubham.patientmanagementsystem.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("")
    public ResponseEntity<Patient> addPatient(@Valid @RequestBody Patient patient) {
        Patient newPatient = patientService.addPatient(patient);
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/byCity/{city}")
    public ResponseEntity<List<Patient>> getPatientsByCity(@PathVariable("city") String city) {
        List<Patient> patients = patientService.getPatientsByCity(city);
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PutMapping("/{id}/phone")
    public ResponseEntity<Patient> updatePatientPhone(@PathVariable("id") Long id, @RequestParam("phone") String phone) {
        Patient patient = patientService.updatePatientPhone(id, phone);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{patientId}/suggestDoctors")
    public ResponseEntity<List<Doctor>> suggestDoctors(@PathVariable("patientId") Long patientId) {
        List<Doctor> doctors = patientService.suggestDoctors(patientId);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

}
