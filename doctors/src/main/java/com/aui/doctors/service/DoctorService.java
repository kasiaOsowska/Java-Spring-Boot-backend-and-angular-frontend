package com.aui.doctors.service;

import com.aui.doctors.dto.CollectionDoctorDto;
import com.aui.doctors.dto.CreateDoctorDto;
import com.aui.doctors.dto.Mapper;
import com.aui.doctors.dto.UpdateDoctorDto;
import com.aui.doctors.model.Doctor;
import com.aui.doctors.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private DoctorRepository doctorRepository;
    @Autowired
    public DoctorService(DoctorRepository doctorRepository1){
        this.doctorRepository = doctorRepository1;
    }

    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }

    public Doctor findDoctorById(UUID doctorId) {
        List<Doctor> allById = doctorRepository.findAllById(Collections.singleton(doctorId));
        if (allById.isEmpty() ){
            return null;
        }
        return allById.get(0);
    }


    public Doctor createDoctor(CreateDoctorDto createDoctorDto){
        Doctor doctorToSave = Doctor.builder()
                .id(UUID.randomUUID())
                .name(createDoctorDto.getName())
                .title(createDoctorDto.getTitle())
                .build();

        return doctorRepository.save(doctorToSave);
    }

    public Doctor updateDoctor(UUID doctorId, UpdateDoctorDto doctorUpdateDTO) {
        Doctor doc = (Doctor) findDoctorById(doctorId);
        doc.setTitle(doctorUpdateDTO.getTitle());
        doc.setName(doctorUpdateDTO.getName());
        return doc;
    }
    public void deleteByID(UUID id){
        doctorRepository.deleteById(id);
    }

    public CollectionDoctorDto getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        Mapper mapper = new Mapper();
        CollectionDoctorDto doctorCollectionDTO = new CollectionDoctorDto();
        for (Doctor d : doctors){
            doctorCollectionDTO.add(mapper.convertDoctorToDoctorDTO(d));
        }
        return doctorCollectionDTO;

    }
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }
    public void clearData() {
        doctorRepository.deleteAll();
    }
}