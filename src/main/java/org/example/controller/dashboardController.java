package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class dashboardController {
    public Label stucont;
    public Label usercont;
    public Label corscont;
    public AnchorPane child;
    public AnchorPane parent;
    public Label lbltime;
    public Label lbldate;

    public void btnUserOnAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/view/userForm.fxml"));
            loadWindow(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void btnStudentOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/view/studentForm.fxml"));
            loadWindow(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void btnCourseOnAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/view/courseForm.fxml"));
            loadWindow(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void btnPaymentOnAction(ActionEvent event) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/view/paymentForm.fxml"));
            loadWindow(anchorPane);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void btnLogoutOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) child.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/login.fxml"))));
        stage.setTitle("DashBoard ");
        stage.centerOnScreen();
        stage.show();

    }
    private void loadWindow(AnchorPane anchorPane) {
        child.getChildren().clear();
        child.getChildren().add(anchorPane);
    }
}
