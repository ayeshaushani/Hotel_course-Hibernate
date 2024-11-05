package org.example.dao.custom.impl;

import javafx.scene.control.Alert;
import org.example.config.FactoryConfiguration;
import org.example.dao.SQLUtil;
import org.example.dao.custom.StudentDAO;
import org.example.dto.StudentDTO;
import org.example.entity.Student;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public boolean save(Student entity) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Object student = session.save(entity);
        System.out.println(student);

        if (student != null) {
            transaction.commit();
            session.close();
            return true;
        }else{
            return false;
        }
    }


    @Override
    public String getCurrentId() throws SQLException, IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "SELECT MAX(s.id) FROM Student s";
        Query query = session.createQuery(hql);
        Integer maxId = (Integer) query.uniqueResult();

        if (maxId == null) {
            maxId = 0;
        }

        // Return new id in a format like ST001, ST002, etc.
        String newId = "ST" + String.format("%03d", maxId + 1);
        return newId;
    }


    public boolean update(Student entity) {
        try {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException | IOException e) {
            new Alert(Alert.AlertType.ERROR,"student not updated.").show();
            return false;

        }
    }

    @Override
    public Student searchId(String id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Student where id = ?1");
        query.setParameter(1,id);
        Student student = (Student)query.uniqueResult();
        transaction.commit();
        //session.close();
        return student;
    }

    @Override
    public List<Student> getAll() throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Student ");
        List<Student> students = query.list();
        return students;
    }

    public boolean delete(String id) {
        try {
            Session session = FactoryConfiguration.getInstance().getSession();
            Transaction transaction = session.beginTransaction();

            Query query = session.createQuery("delete from Student where id = ?1");
            query.setParameter(1, id);
            boolean isDelete = query.executeUpdate() > 0;
            transaction.commit();
            session.close();
            return true;

        } catch (HibernateException | IOException e) {
            new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
            return false;
        }

    }

}

