package com.aui.patients.dto;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CreatePatientDto {
    String name;
    UUID doctorId;
}
