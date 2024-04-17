package com.aui.doctors.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CollectionPatientDto {
    private List<PatientDTO> patients = new ArrayList<>();

    public void add(PatientDTO patientDTO) {
        patients.add(patientDTO);
    }

    public boolean isEmpty() {
        return patients.isEmpty();
    }
}