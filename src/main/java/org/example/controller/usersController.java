package org.example.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.example.bo.BOFactory;
import org.example.bo.custom.UserBO;
import org.example.dto.StudentDTO;
import org.example.dto.UserDTO;
import org.example.viweTm.StudentTm;
import org.example.viweTm.UserTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static jdk.internal.agent.Agent.getText;

public class usersController {

    public AnchorPane child;
    @FXML
    private TableView<UserTm> UserTable;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableColumn<?, ?> colUserPassword;

    @FXML
    private TableColumn<?, ?> colUserRole;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> deletebtnrow;

    @FXML
    private Text idtext;

    @FXML
    private Text lntext;

    @FXML
    private TextField passwroddtxt;

    @FXML
    private Text role;

    @FXML
    private TextField roletxt;

    @FXML
    private Text topic;

    @FXML
    private Text un;

    @FXML
    private TableColumn<?, ?> updatebtnrow;

    @FXML
    private TextField userid;

    @FXML
    private TextField usernametxt;
    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize(){
        /*getCurrentStudentId();*/
        setCellValueFactory();
        loadAllUsers();
    }
    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("userID"));
        colUserName.setCellValueFactory(new PropertyValueFactory<>("userName"));
        colUserPassword.setCellValueFactory(new PropertyValueFactory<>("userPassword"));
        colUserRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));
    }

    @FXML
    void DeleteOnActionStudent(ActionEvent event) {

    }

    @FXML
    void UpdateOnActionStudent(ActionEvent event) {

    }

    @FXML
    void loadTheTextField(KeyEvent event) {

    }


    @FXML
    void saveOnActionStudent(ActionEvent event) {
        // Get the ID input as a string
        String idText = userid.getText();

        // Check if the ID input is empty
        if (idText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "User ID cannot be empty").show();
            return; // Exit the method early
        }

        // Try to parse the student ID
        int userId;
        try {
            userId = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid user ID. Please enter a valid number.").show();
            return; // Exit the method early if there's a parsing error
        }

        String usName = usernametxt.getText();
        String usPassword = passwroddtxt.getText();
        String role = roletxt.getText();

        UserDTO userDTO = new UserDTO(userId, usName, usPassword, role);

        try {
            boolean isSaved = userBO.saveUser(userDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student saved").show();
                //loadAllCustomers();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllUsers();
        clearFields();

    }

    private void loadAllUsers() {
        ObservableList<UserTm> obList = FXCollections.observableArrayList();
        try {
            List<UserDTO> userList = userBO.getAllUser();

            for (int i = 0; i < userList.size(); i++) {
                UserTm userTm = new UserTm(
                        userList.get(i).getId(),
                        userList.get(i).getUsername(),
                        userList.get(i).getPassword(),
                        userList.get(i).getRole()
                );
                obList.add(userTm);
            }

            UserTable.setItems(obList);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }
    void clearFields(){
        idtext.setText("");
        usernametxt.setText("");
        passwroddtxt.setText("");
        roletxt.setText("");
    }
}
