package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class loginController {

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/dashboard.fxml"))));
        stage.setTitle("DashBoard ");
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/dashboard.fxml")))); //register form
        stage.setTitle("DashBoard ");
        stage.centerOnScreen();
        stage.show();

    }

}

/*
package org.example.controller;

import com.sun.javafx.tk.quantum.PaintRenderJob;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class loginController {


    public TextField txtPassword;
    public TextField txtName;
    public AnchorPane root;
    ;

    public void btnRegisterOnAction(ActionEvent event) {

    }

    public void btnLoginOnAction(ActionEvent event) {


    }
}
*/
