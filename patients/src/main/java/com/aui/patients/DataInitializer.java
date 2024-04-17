package com.aui.patients;

import com.aui.patients.service.PatientService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import com.aui.patients.model.Doctor;
import com.aui.patients.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class DataInitializer {
    private final PatientService patientService;

    @Autowired
    public DataInitializer( PatientService patientService) {
        this.patientService = patientService;
    }

    @PostConstruct
    public void initializeData() {
        initData();
    }
    @PreDestroy
    public void clean(){
    }

    public void initData() {

        Patient patient1 = Patient.builder()
                .name("Alice")
                .doctorID(UUID.fromString("550e8400-e29b-41d4-a716-446655440002"))
                .id(UUID.fromString("550e8400-e29b-41d4-a716-446655440011"))
                .build();
        patientService.save(patient1);

        Patient patient2 = Patient.builder()
                .name("Bob")
                .doctorID(UUID.fromString("550e8400-e29b-41d4-a716-446655440001"))
                .id(UUID.fromString("550e8400-e29b-41d4-a716-446655440010"))
                .build();
        patientService.save(patient2);

        Patient patient3 = Patient.builder()
                .name("Carol")
                .doctorID(UUID.fromString("550e8400-e29b-41d4-a716-446655440003"))
                .id(UUID.fromString("550e8400-e29b-41d4-a716-446655440012"))
                .build();
        patientService.save(patient3);
    }
}