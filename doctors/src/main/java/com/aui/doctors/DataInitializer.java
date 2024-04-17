package com.aui.doctors;

import com.aui.doctors.service.DoctorService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import com.aui.doctors.model.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class DataInitializer {
    private final DoctorService doctorService;

    @Autowired
    public DataInitializer(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostConstruct
    public void initializeData() {
        initData();
    }
    @PreDestroy
    public void clean(){
    }

    public void initData() {
        Doctor doctor1 = Doctor.builder()
                .name("Dr. John Doe")
                .title("Cardiologist")
                .id(UUID.fromString("550e8400-e29b-41d4-a716-446655440002"))
                .build();
        doctorService.save(doctor1);
        Doctor doctor2 = Doctor.builder()
                .name("Dr. Bill")
                .title("Surgeon")
                .id(UUID.fromString("550e8400-e29b-41d4-a716-446655440001"))
                .build();
        doctorService.save(doctor2);

        Doctor doctor3 = Doctor.builder()
                .name("Dr. Empty")
                .title("Surgeon")
                .id(UUID.fromString("550e8400-e29b-41d4-a716-446655440003"))
                .build();
        doctorService.save(doctor3);

    }
}