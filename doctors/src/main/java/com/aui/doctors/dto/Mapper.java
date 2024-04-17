package com.aui.doctors.dto;

import com.aui.doctors.model.Doctor;

public class Mapper {

    public DoctorDTO convertDoctorToDoctorDTO(Doctor doctor){
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setName(doctor.getName());
        doctorDTO.setId(doctor.getId());
        return doctorDTO;
    }
}
