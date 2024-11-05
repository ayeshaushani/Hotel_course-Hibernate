package org.example.bo;

import org.example.bo.custom.impl.StudentBOImpl;
import org.example.entity.Student;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null)?  boFactory =  new BOFactory():boFactory;

    }

    public enum BOTypes{
        Student, Programs,Registration, Users
    }

    public SuperBO getBO(BOTypes boTypes){
        switch (boTypes){
            case Student:
                return new StudentBOImpl();

            /*case Item:
                return  new ItemBOImpl();

            case Orders:
                return  new OrderBOImpl();*/
            default:
                return null;
        }
    }
}