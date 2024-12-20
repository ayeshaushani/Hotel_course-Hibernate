package org.example.bo.custom.impl;

import org.example.bo.custom.StudentBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.StudentDAO;
import org.example.entity.Student;
import org.example.dto.StudentDTO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentBOImpl implements StudentBO {

    StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    public boolean saveStudent(StudentDTO studentDTO) throws IOException {
        return studentDAO.save( new Student(studentDTO.getId(),studentDTO.getName(), studentDTO.getAddress(),studentDTO.getContact(),studentDTO.getEmail()));
    }
    public boolean updateStudent(StudentDTO studentDTO) throws IOException {
        return studentDAO.update(new Student(studentDTO.getId(),studentDTO.getName(), studentDTO.getAddress(),studentDTO.getContact(),studentDTO.getEmail()));
    }

    @Override
    public StudentDTO searchStudentId(int id) throws IOException {
        Student student = studentDAO.searchId(id);
        return new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getContact(),student.getEmail());
    }

    @Override
    public List<StudentDTO> getAllCustomer() throws SQLException, ClassNotFoundException, IOException {

        List<StudentDTO> studentDTOS = new ArrayList<>();
        List<Student> students = studentDAO.getAll();

        for (Student student:students) {
            StudentDTO studentDTO = new StudentDTO(student.getId(),student.getName(),student.getAddress(),student.getContact(),student.getEmail() );
            studentDTOS.add(studentDTO);
        }
        return studentDTOS;
    }

    @Override
    public boolean deleteStudent(int id) throws IOException {
        return studentDAO.delete(id);
    }



}