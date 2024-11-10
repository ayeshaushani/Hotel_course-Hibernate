package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    public boolean saveStudent(StudentDTO studentDTO) throws IOException;

    public boolean updateStudent(StudentDTO studentDTO) throws IOException;

    StudentDTO searchStudentId(int id) throws IOException;

    List<StudentDTO> getAllCustomer() throws SQLException, ClassNotFoundException, IOException;

    boolean deleteStudent(int id) throws IOException;
}