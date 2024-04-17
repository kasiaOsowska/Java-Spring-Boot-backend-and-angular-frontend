package com.aui.patients.dto;

import com.aui.patients.model.Doctor;
import com.aui.patients.model.Patient;
public class Mapper {
    public PatientDTO convertPatientToPatientDTO(Patient patient) {
        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setName(patient.getName());
        patientDTO.setId(patient.getId());
        return patientDTO;
    }

}
