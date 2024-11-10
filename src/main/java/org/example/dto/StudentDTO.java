package org.example.dto;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class StudentDTO {
    @Id
    private int id;
    private String name;
    private String email;
    private String contact;
    private String address;


}