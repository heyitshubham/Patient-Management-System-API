package com.codewithshubham.patientmanagementsystem.controllers;

import com.codewithshubham.patientmanagementsystem.Exceptions.InvalidRequestException;
import com.codewithshubham.patientmanagementsystem.Exceptions.NotFoundException;
import com.codewithshubham.patientmanagementsystem.Service.DoctorService;
import com.codewithshubham.patientmanagementsystem.entities.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        Doctor savedDoctor = doctorService.addDoctor(doctor);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<Doctor>> getDoctorsByCity(@PathVariable String city) {
        List<Doctor> doctors = doctorService.getDoctorsByCity(city);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/city/{city}/speciality/{speciality}")
    public ResponseEntity<List<Doctor>> getDoctorsByCityAndSpeciality(@PathVariable String city, @PathVariable String speciality) {
        List<Doctor> doctors = doctorService.getDoctorsByCityAndSpeciality(city, speciality);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PutMapping("/{id}/phone")
    public ResponseEntity<Doctor> updateDoctorPhone(@PathVariable Long id, @RequestParam String phone) throws NotFoundException {
        Doctor updatedDoctor = doctorService.updateDoctorPhone(id, phone);
        return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorById(@PathVariable Long id) throws NotFoundException {
        doctorService.deleteDoctorById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/suggest/{patientId}")
    public ResponseEntity<List<Doctor>> suggestDoctors(@PathVariable Long patientId) throws NotFoundException, InvalidRequestException {
        List<Doctor> doctors = doctorService.suggestDoctors(patientId);
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

}
