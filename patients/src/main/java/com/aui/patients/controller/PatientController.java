package com.aui.patients.controller;

import com.aui.patients.dto.*;
import com.aui.patients.model.Doctor;
import com.aui.patients.model.Patient;
import com.aui.patients.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    private final RestTemplate restTemplate;

    // Create a new patient
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PatientDTO> createPatient(@RequestBody CreatePatientDto patientCreateDTO) {
        UUID doctorID = patientCreateDTO.getDoctorId();
        String doctorServiceUrl = "http://localhost:8083/doctors/" + doctorID;

        ResponseEntity<DoctorDTO> doctorResponse = restTemplate.getForEntity(doctorServiceUrl, DoctorDTO.class);

        if (doctorResponse.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Doctor exists, proceed with creating the patient
        Patient createdPatient = patientService.createPatient(patientCreateDTO, doctorID);
        Mapper mapper = new Mapper();
        PatientDTO p = mapper.convertPatientToPatientDTO(createdPatient);
        return ResponseEntity.ok(p);
    }

    // Update an existing patient
    @PutMapping("/{patientId}")
    public ResponseEntity<PatientDTO> updatePatient(
            @PathVariable UUID patientId, @RequestBody UpdatePatientDto patientUpdateDTO) {
        Patient updatedPatient = patientService.updatePatient(patientId, patientUpdateDTO);
        Mapper mapper = new Mapper();
        PatientDTO p = mapper.convertPatientToPatientDTO(updatedPatient);
        if (updatedPatient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(p);
    }

    // Delete a patient
    @DeleteMapping("/{patientId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatient(@PathVariable UUID patientId) {
        patientService.deleteByID(patientId);
    }

    @DeleteMapping("/{doctorId}/patients")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePatientsByDoctor(@PathVariable UUID doctorId) {
        CollectionPatientDto p = patientService.getPatientsByDoctorId(doctorId);
        List<UUID> patientIds = p.getPatientIds();
        for (UUID id : patientIds) {
            patientService.deleteByID(id);
        }
    }

    // Get a single patient
    @GetMapping("/{patientId}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable UUID patientId) {
        Patient patient = patientService.findPatientById(patientId);
        System.out.println("Received request for /patients/" + patientId);
        Mapper mapper = new Mapper();
        PatientDTO p = mapper.convertPatientToPatientDTO(patient);
        if (patient != null) {
            return ResponseEntity.ok(p);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get a list of all patients
    @GetMapping()
    public ResponseEntity<CollectionPatientDto> getAllPatients() {
        CollectionPatientDto patients = patientService.getAllPatients();
        if (patients.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patients);
    }
    @GetMapping("/{doctorId}/patients")
    @DeleteMapping("/{doctorId}/patients")
    public ResponseEntity<CollectionPatientDto> getPatientsInDoctorCategory(@PathVariable("doctorId") UUID doctorId) {
        CollectionPatientDto p = patientService.getPatientsByDoctorId(doctorId);
        return ResponseEntity.ok(p);
    }
}