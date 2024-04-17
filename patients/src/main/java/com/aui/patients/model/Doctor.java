package com.aui.patients.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import com.aui.patients.model.Doctor;
import com.aui.patients.model.Patient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "doctors")
public class Doctor implements Serializable, Comparable<Doctor> {

    @Id
    @Column(name = "doctor_id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Override
    public int compareTo(Doctor otherDoctor) {

        return this.id.compareTo(otherDoctor.id);
    }

    public Doctor() {
        // Initialize the ID to a new UUID
        this.id = UUID.randomUUID();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Doctor doctor = (Doctor) obj;
        return Objects.equals(id, doctor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    public static DoctorBuilder builder() {
        return new DoctorBuilder();
    }
    public static class DoctorBuilder {
        private UUID id;

        public DoctorBuilder id(UUID id) {
            this.id = id;
            return this;
        }


        public Doctor build() {
            Doctor doctor = new Doctor();
            doctor.setId(id);
            return doctor;
        }
    }

}
