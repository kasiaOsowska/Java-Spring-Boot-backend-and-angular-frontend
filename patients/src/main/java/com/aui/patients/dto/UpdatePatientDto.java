package com.aui.patients.dto;
import com.aui.patients.model.Doctor;
import com.aui.patients.model.Patient;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UpdatePatientDto {
    String name;
    UUID id;
}