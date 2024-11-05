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
        getCurrentStudentId();
        setCellValueFactory();
        loadAllStudents();
    }
    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }



    private void loadAllStudents() {
        ObservableList<StudentTm> obList = FXCollections.observableArrayList();
        try {
            List<StudentDTO> studentList = studentBO.getAllCustomer();
            for (StudentDTO studentDTO : studentList) {
                StudentTm tm = new StudentTm(
                        studentDTO.getId(),
                        studentDTO.getName(),
                        studentDTO.getAddress(),
                        studentDTO.getContact(),
                        studentDTO.getEmail()
                );

                obList.add(tm);
            }

            tblStudent.setItems(obList);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String stuId = txtId.getText();
        boolean isDeleted = studentBO.delete(stuId);
        if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"customer deleted").show();
            loadAllStudents();
            clearFields();
            getCurrentStudentId();
        }

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String stuId = txtId.getText();
        String stuName = txtName.getText();
        String stuContact = txtContact.getText();
        String stuEmail = txtEmail.getText();
        String stuAddress = txtAddress.getText();

        StudentDTO studentDTO = new StudentDTO(stuId,stuName,stuAddress,stuContact,stuEmail);

        try {
            boolean isSaved = studentBO.saveStudent(studentDTO);
            if (isSaved ) {
                new Alert(Alert.AlertType.CONFIRMATION,"Student saved").show();
                //loadAllCustomers();
            }
        } catch (Exception e) {
            new Alert( Alert.AlertType.ERROR,e.getMessage()).show();
        }
        clearFields();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String stuId = txtId.getText();
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

    private void getCurrentStudentId() {
        try {
            String currentId = studentBO.getCurrentStudentId();

            if (currentId == null) {
                txtId.setText("S-1"); // Start from S-1 if no current ID is found
            } else {
                String nextOrderId = generateNextOrderId(currentId);
                txtId.setText(nextOrderId);
            }

        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            /*String[] split = currentId.split("S-");  //" ", "2"
            int idNum = Integer.parseInt(split[1]);*/
            int idNum = Integer.parseInt(currentId);
            return "S-" + ++idNum;
        }
        return "S-1";
    }
    @FXML
    void rowOnMouseClicked(MouseEvent event) {
        String id = tblStudent.getSelectionModel().getSelectedItem().getId();

        try {
            StudentDTO studentDTO = studentBO.searchStudentId(id);//searchProductId(id);
            if (studentDTO != null) {
                txtId.setText(studentDTO.getId());
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


/*
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
import org.example.bo.BOFactory;
import org.example.bo.custom.StudentBO;
import org.example.dto.StudentDTO;
import org.example.viweTm.StudentTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class studentController {
    @FXML
    private TableView<StudentTm> tblStudent;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtId;
    @FXML
    private TableColumn<?,?> colid;
    @FXML
    private TableColumn<?,?> colname;
    @FXML
    private TableColumn<?,?> colcontact;
    @FXML
    public TableColumn<?,?> colemail;
    @FXML
    private TableColumn<?,?> coladdress;


    */
/* public void btnSaveOnAction(ActionEvent event)
    public void btnUpdateOnAction(ActionEvent event)
    public void btnDeleteOnAction(ActionEvent event) *//*

   StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Student);

    public void initialize(){
        getCurrentStudentId();
        setCellValueFactory();
        loadAllStudents();
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("s_id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("s_name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }



    private void loadAllStudents() {
        ObservableList<StudentTm> obList = FXCollections.observableArrayList();
        try {
            List<StudentDTO> studentList = studentBO.getAllCustomer();
            for (StudentDTO studentDTO : studentList) {
                StudentTm tm = new StudentTm(
                        studentDTO.getId(),
                        studentDTO.getName(),
                        studentDTO.getAddress(),
                        studentDTO.getContact(),
                        studentDTO.getEmail()
                );

                obList.add(tm);
            }

            tblStudent.setItems(obList);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }



    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String stuId = txtId.getText();
        boolean isDeleted = studentBO.delete(stuId);
        if (isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"customer deleted").show();
            loadAllStudents();
            clearFields();
            getCurrentStudentId();
        }


    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String stuId = txtId.getText();
        String stuName = txtName.getText();
        String stuContact = txtContact.getText();
        String stuEmail = txtEmail.getText();
        String stuAddress = txtAddress.getText();

        StudentDTO studentDTO = new StudentDTO(stuId,stuName,stuAddress,stuContact,stuEmail);

        try {
            boolean isSaved = studentBO.saveStudent(studentDTO);
            if (isSaved ) {
                new Alert(Alert.AlertType.CONFIRMATION,"Student saved").show();
                //loadAllCustomers();
            }
        } catch (Exception e) {
            new Alert( Alert.AlertType.ERROR,e.getMessage()).show();
        }
        clearFields();


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String stuId = txtId.getText();
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

    private void getCurrentStudentId() {
        try {
            String currentId = studentBO.getCurrentStudentId();
            String nextOrderId = generateNextOrderId(currentId);
            txtId.setText(nextOrderId);

        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private String generateNextOrderId(String currentId) {
        if(currentId != null) {

/*String[] split = currentId.split("S-");  //" ", "2"
            int idNum = Integer.parseInt(split[1]);*//*

            int idNum = Integer.parseInt(currentId);
            return "S-" + ++idNum;
        }
        return "S-1";
    }

    @FXML
    void rowOnMouseClicked(MouseEvent event) {
        String id = tblStudent.getSelectionModel().getSelectedItem().getId();

        try {
            StudentDTO studentDTO = studentBO.searchStudentId(id);//searchProductId(id);
            if (studentDTO != null) {
                txtId.setText(studentDTO.getId());
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

    void clearFields(){
        txtName.setText("");
        txtContact.setText("");
        txtEmail.setText("");
        txtAddress.setText("");
    }
}
*/
