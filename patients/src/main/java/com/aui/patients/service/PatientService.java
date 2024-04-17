package com.aui.patients.service;

import com.aui.patients.dto.CollectionPatientDto;
import com.aui.patients.dto.CreatePatientDto;
import com.aui.patients.dto.Mapper;
import com.aui.patients.dto.UpdatePatientDto;
import com.aui.patients.model.Doctor;
import com.aui.patients.model.Patient;
import com.aui.patients.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;


    @Autowired
    public PatientService(PatientRepository patientRepository) {

        this.patientRepository = patientRepository;
    }
    public Patient findPatientById(UUID patientId) {
        List<Patient> allById = patientRepository.findAllById(Collections.singleton(patientId));
        if (allById.isEmpty() ){
            return null;
        }
        return allById.get(0);
    }
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
    public void save(Patient patient){patientRepository.save(patient);}
    public void deleteByID(UUID id){
        patientRepository.deleteById(id);
    }

    public Patient createPatient(CreatePatientDto patientCreateDTO, UUID doctorID) {
        Patient patientToSave = Patient.builder()
                .id(UUID.randomUUID())
                .name(patientCreateDTO.getName())
                .doctorID(doctorID)
                .build();
        return patientRepository.save(patientToSave);

    }

    public Patient updatePatient(UUID patientId, UpdatePatientDto patientUpdateDTO) {
        Patient pat = findPatientById(patientId);
        if (pat == null) {
            return null;
        }
        pat.setName(patientUpdateDTO.getName());
        return patientRepository.save(pat);
    }
    public CollectionPatientDto getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        Mapper mapper = new Mapper();
        CollectionPatientDto patientCollectionDTO = new CollectionPatientDto();
        for (Patient p : patients){
            patientCollectionDTO.add(mapper.convertPatientToPatientDTO(p));
        }
        return patientCollectionDTO;
    }
    public CollectionPatientDto getListOfPatients(List<Patient> patients)
    {
        Mapper mapper = new Mapper();
        CollectionPatientDto patientCollectionDTO = new CollectionPatientDto();
        for (Patient p : patients){
            patientCollectionDTO.add(mapper.convertPatientToPatientDTO(p));
        }
        return patientCollectionDTO;
    }


    public void clearData() {
        patientRepository.deleteAll();
    }
    public CollectionPatientDto getPatientsByDoctorId(UUID doctorId) {
        List<Patient> patients = patientRepository.findAll();
        CollectionPatientDto patientsByDoctorId = new CollectionPatientDto();
        Mapper mapper = new Mapper();
        for (Patient p : patients){
            if (p.getDoctorID().equals(doctorId)){
                patientsByDoctorId.add(mapper.convertPatientToPatientDTO(p));
            }
        }

        return patientsByDoctorId;
    }
}