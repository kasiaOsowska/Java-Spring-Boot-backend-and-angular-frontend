package com.aui.patients.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DoctorDTO {
    private String name;
    private UUID id;
}