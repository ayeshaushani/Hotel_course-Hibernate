package org.example.dao;

import org.example.dao.custom.impl.StudentDAOImpl;
import org.example.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getDaoFactory(){
        return (daoFactory == null)? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        STUDENT,USER,COURSE,REGISTRATION
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case STUDENT:
                return  new StudentDAOImpl();
            case USER:
                return new UserDAOImpl();
            /*case Item:
                return new ItemDAOImpl();

            case Orders:
                return new OrderDAOImpl();

            case OrderDetails:
                return new OrderDetailsDAOImpl();*/

            default:
                return null;
        }
    }
}
