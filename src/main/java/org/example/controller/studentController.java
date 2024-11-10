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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.example.bo.BOFactory;
import org.example.bo.custom.StudentBO;
import org.example.dto.StudentDTO;
import org.example.viweTm.StudentTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Integer.parseInt;

public class studentController {

    @FXML
    private AnchorPane child;

    @FXML
    private TableColumn<?, ?> coladdress;

    @FXML
    private TableColumn<?, ?> colcontact;

    @FXML
    private TableColumn<?, ?> colemail;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableView<StudentTm> tblStudent;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Student);

    public void initialize(){
        /*getCurrentStudentId();*/
        setCellValueFactory();
        loadAllStudents();
    }
    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }



    private void loadAllStudents() {
        ObservableList<StudentTm> obList = FXCollections.observableArrayList();
        try {
            List<StudentDTO> studentList = studentBO.getAllCustomer();

            for (int i = 0; i < studentList.size(); i++) {
                StudentTm studentTM = new StudentTm(
                        studentList.get(i).getId(),
                        studentList.get(i).getName(),
                        studentList.get(i).getAddress(),
                        studentList.get(i).getContact(),
                        studentList.get(i).getEmail()
                );

                obList.add(studentTM);
            }

            tblStudent.setItems(obList);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws IOException {
        int stuId = parseInt(txtId.getText());
        boolean isDeleted = studentBO.deleteStudent(stuId);
        if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"customer deleted").show();
            loadAllStudents();
            clearFields();
           /* getCurrentStudentId();*/
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        // Get the ID input as a string
        String idText = txtId.getText();

        // Check if the ID input is empty
        if (idText.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Student ID cannot be empty").show();
            return; // Exit the method early
        }

        // Try to parse the student ID
        int stuId;
        try {
            stuId = Integer.parseInt(idText);
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Student ID. Please enter a valid number.").show();
            return; // Exit the method early if there's a parsing error
        }

        String stuName = txtName.getText();
        String stuContact = txtContact.getText();
        String stuEmail = txtEmail.getText();
        String stuAddress = txtAddress.getText();

        StudentDTO studentDTO = new StudentDTO(stuId, stuName, stuAddress, stuContact, stuEmail);

        try {
            boolean isSaved = studentBO.saveStudent(studentDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Student saved").show();
                //loadAllCustomers();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        loadAllStudents();

        clearFields();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        int stuId = parseInt(txtId.getText());
        String stuName = txtName.getText();
        String stuContact = txtContact.getText();
        String stuEmail = txtEmail.getText();
        String stuAddress = txtAddress.getText();

        StudentDTO studentDTO = new StudentDTO(stuId,stuName,stuAddress,stuContact,stuEmail);

        try {
            boolean isUpdated = studentBO.updateStudent(studentDTO);
            if (isUpdated ) {
                new Alert(Alert.AlertType.CONFIRMATION,"Student updated").show();
                loadAllStudents();
            }
        } catch (Exception e) {
            new Alert( Alert.AlertType.ERROR,e.getMessage()).show();
        }
        clearFields();
    }
    void clearFields(){
        txtName.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
    }


    @FXML
    void rowOnMouseClicked(MouseEvent event) {
        int id = tblStudent.getSelectionModel().getSelectedItem().getId();

        try {
            StudentDTO studentDTO = studentBO.searchStudentId(id);//searchProductId(id);
            if (studentDTO != null) {
                txtId.setText(String.valueOf(studentDTO.getId()));
                txtName.setText(studentDTO.getName());
                txtContact.setText(studentDTO.getContact());
                txtAddress.setText(studentDTO.getAddress());
                txtEmail.setText(studentDTO.getEmail());

            } else {
                new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


}


