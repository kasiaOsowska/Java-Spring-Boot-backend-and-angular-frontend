package com.aui.patients.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

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

    public List<UUID> getPatientIds() {
        List<UUID> patientIds = new ArrayList<>();
        for (PatientDTO patientDTO : patients) {
            patientIds.add(patientDTO.getId());
        }
        return patientIds;
    }
}