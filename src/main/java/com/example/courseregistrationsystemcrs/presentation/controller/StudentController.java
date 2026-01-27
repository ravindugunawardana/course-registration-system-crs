package com.example.courseregistrationsystemcrs.presentation.controller;

import com.example.courseregistrationsystemcrs.business.service.StudentService;
import com.example.courseregistrationsystemcrs.business.serviceImpl.StudentServiceImpl;
import com.example.courseregistrationsystemcrs.entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class StudentController {

    //Text fields

    @FXML
    private TextField txtStudentId;
    @FXML
    private TextField txtStudentName;
    @FXML
    private TextField txtDob;
    @FXML
    private TextField txtStudyProgram;
    @FXML
    private TextField txtYear;
    @FXML
    private TextField txtContact;

    //Table view
    @FXML
    private TableView<Student> tblViewStudents;
    @FXML
    private TableColumn<Student, String> tblColStudentId;
    @FXML
    private TableColumn<Student, String> tblColStudentName;
    @FXML
    private TableColumn<Student, String> tblColStudentDob;
    @FXML
    private TableColumn<Student, Integer> tblColStudentYear;
    @FXML
    private TableColumn<Student, String> tblColStudentContact;

    private final StudentService studentService = new StudentServiceImpl();
    private final ObservableList<Student> studentObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        tblColStudentId.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStudentId()));
        tblColStudentName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        tblColStudentDob.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStudyProgram()));
        tblColStudentYear.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getYear()).asObject());
        tblColStudentContact.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getContact()));

        loadAllStudents();
    }

    //Button Actions

    @FXML
    public void btnAddOnAction(ActionEvent actionEvent){
        try {
            Student student = getStudentFromFields();
            studentService.addNewStudent(student);
            clearFields();
            loadAllStudents();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Student added successfully");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "ERROR", e.getMessage());
        }
    }

    @FXML
    public void btnUpdateOnAction(ActionEvent actionEvent){
        try {
            Student student = getStudentFromFields();
            studentService.updateStudent(student);
            clearFields();
            loadAllStudents();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Student updated successfully");
        } catch (Exception e){
            showAlert(Alert.AlertType.ERROR, "ERROR", e.getMessage());
        }
    }

    @FXML
    public void btnDeleteOnAction(ActionEvent actionEvent){
        try {
            String studentId = txtStudentId.getText();
            studentService.deleteStudent(studentId);
            clearFields();
            loadAllStudents();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Student deleted successfully");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "ERROR", e.getMessage());
        }
    }

    @FXML
    public void btnClearOnAction(ActionEvent actionEvent){
        clearFields();
    }

    //Helper methods

    private Student getStudentFromFields() {
        return new Student(
                txtStudentId.getText(),
                txtStudentName.getText(),
                txtDob.getText(),
                txtStudyProgram.getText(),
                Integer.parseInt(txtYear.getText()),
                txtContact.getText()
        );
    }

    private void clearFields() {
        txtStudentId.setText("");
        txtStudentName.setText("");
        txtDob.setText("");
        txtStudyProgram.setText("");
        txtYear.setText("");
        txtContact.setText("");
    }

    private void loadAllStudents() {
        try {
            studentObservableList.clear();
            studentObservableList.addAll(studentService.getAllStudents());
            tblViewStudents.setItems(studentObservableList);
        } catch (Exception e){
            showAlert(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
}
