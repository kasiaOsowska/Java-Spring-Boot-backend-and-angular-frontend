package com.aui.patients.dto;

import com.aui.patients.model.Doctor;
import com.aui.patients.model.Patient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter

public class PatientDTO {
    String name;
    UUID id;
}

