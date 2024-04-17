package com.aui.doctors.controller;

import com.aui.doctors.dto.*;
import com.aui.doctors.model.Doctor;
import com.aui.doctors.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.List;
import java.util.UUID;
@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final RestTemplate restTemplate;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody CreateDoctorDto createDoctorDto) {
        Doctor createdDoctor = doctorService.createDoctor(createDoctorDto);
        Mapper mapper = new Mapper();
        DoctorDTO d = mapper.convertDoctorToDoctorDTO(createdDoctor);
        return ResponseEntity.ok(d);
    }

    @PatchMapping("/{doctor_id}")
    public ResponseEntity<DoctorDTO> updateDoctor(
            @PathVariable UUID doctor_id, @RequestBody UpdateDoctorDto doctorUpdateDTO) {
        Doctor updatedDoctor = doctorService.updateDoctor(doctor_id, doctorUpdateDTO);
        Mapper mapper = new Mapper();
        DoctorDTO d = mapper.convertDoctorToDoctorDTO(updatedDoctor);
        return ResponseEntity.ok(d);
    }

    @DeleteMapping("/{doctor_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDoctor(@PathVariable UUID doctor_id) {
        String patientServiceUrl = "http://localhost:8083/patients/"+doctor_id+"/patients";
        restTemplate.delete(patientServiceUrl);
        doctorService.deleteByID(doctor_id);

    }

    @GetMapping("/{doctor_id}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable UUID doctor_id) {
        Doctor doctor = doctorService.findDoctorById(doctor_id);

        if (doctor != null) {
            Mapper mapper = new Mapper();
            DoctorDTO d = mapper.convertDoctorToDoctorDTO(doctor);
            return ResponseEntity.ok(d);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<CollectionDoctorDto> getAllDoctors() {
        CollectionDoctorDto doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }


}