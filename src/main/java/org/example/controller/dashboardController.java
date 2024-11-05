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

    public void btnUserOnAction(ActionEvent event) {

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

    }

    public void btnPaymentOnAction(ActionEvent event) {

    }

    public void btnLogoutOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) child.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/login.fxml"))));
        stage.setTitle("DashBoard ");
        stage.centerOnScreen();
        stage.show();

    }
    private void loadWindow(AnchorPane anchorPane) {
        parent.getChildren().clear();
        parent.getChildren().add(anchorPane);
    }
}
