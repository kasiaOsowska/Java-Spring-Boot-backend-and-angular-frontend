package com.aui.doctors.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CollectionDoctorDto {
    private List<DoctorDTO> doctors = new ArrayList<>();

    public void add(DoctorDTO doctorDTO) {
        doctors.add(doctorDTO);
    }
}
