package com.aui.patients.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import com.aui.patients.model.Doctor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "patients")
public class Patient implements Serializable, Comparable<Patient> {
    @Id
    @Column(name = "patient_id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "patient_name", nullable = false)
    private String name;

    @Column(name = "doctor_id")
    private UUID doctorID;

    @Override
    public int compareTo(Patient otherPatient) {
        if (name.compareTo(otherPatient.name) != 0){
            return id.compareTo(otherPatient.id);
        }
        return this.id.compareTo(otherPatient.id);
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Patient patient = (Patient) obj;
        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    public static PatientBuilder builder() {
        return new PatientBuilder();
    }

    public static class PatientBuilder {
        private UUID id;
        private String name;
        private UUID doctorID;
        public PatientBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public PatientBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PatientBuilder doctorID(UUID doctorID) {
            this.doctorID = doctorID;
            return this;
        }

        public Patient build() {
            Patient patient = new Patient();
            patient.setId(id);
            patient.setName(name);
            patient.setDoctorID(doctorID);
            return patient;
        }
    }

}
