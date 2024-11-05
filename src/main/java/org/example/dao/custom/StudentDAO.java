package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dto.StudentDTO;
import org.example.entity.Student;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {

    public  String getCurrentId() throws SQLException, IOException;

    public Student searchId(String id) throws IOException;



}