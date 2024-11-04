package org.example.viweTm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public class StudentTm {
    @Id
    private String id;
    private String name;
    private String email;
    private String contact;
    private String address;

    public StudentTm(String id, String name, String email, String contact, String course) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.address = course;
    }

    public StudentTm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
