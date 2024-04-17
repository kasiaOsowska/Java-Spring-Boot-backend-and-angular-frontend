package com.aui.doctors.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "doctors")
public class Doctor implements Serializable, Comparable<Doctor> {

    @Id
    @Column(name = "doctor_id", updatable = false, nullable = false, unique = true)
    private UUID id;

    @Column(name = "doctor_name", nullable = false)
    private String name;

    @Column(name = "doctor_title")
    private String title;


    @Override
    public int compareTo(Doctor otherDoctor) {
        if (name.compareTo(otherDoctor.name) != 0){
            return id.compareTo(otherDoctor.id);
        }
        return this.id.compareTo(otherDoctor.id);
    }
    @Override
    public String toString() {
        return "doctor name: "+this.name+", id:"+this.id+", title:"+this.title;
    }
    public void print(Doctor doc){
        System.out.println(doc.toString());
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
        private String name;
        private String title;

        public DoctorBuilder id(UUID id) {
            this.id = id;
            return this;
        }

        public DoctorBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DoctorBuilder title(String title) {
            this.title = title;
            return this;
        }


        public Doctor build() {
            Doctor doctor = new Doctor();
            doctor.setId(id);
            doctor.setName(name);
            doctor.setTitle(title);
            return doctor;
        }
    }

}
