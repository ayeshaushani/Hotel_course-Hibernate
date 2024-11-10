package org.example.dto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class UserDTO {
    @Id
    private int id;


    private String username;


    private String password;


    private String role;

}
