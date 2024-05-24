/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package system.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import system.objects.AlertMessage;
import system.objects.Status;
import system.objects.StudentDTO;


/**
 * FXML Controller class
 *
 * @author marte
 */
public class StudentsCardController implements Initializable {

    @FXML
    private Button btnVisualizeAdvances;

    @FXML
    private ImageView imgStudentLogo;

    @FXML
    private Label lblName;

    @FXML
    private Label lblNameStudent;

    @FXML
    private Label lblSemester;

    @FXML
    private Label lblStudentEmail;

    @FXML
    private Label lblStudentID;

    @FXML
    private Label lblStudentSemester;

    @FXML
    void showAdvances(ActionEvent event) {
        try {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/system/views/VAdvancesStudentsProfesor.fxml"));
        Parent root = loader.load();
        VAdvancesStudentsProfesorController controllerAdvancesStudents = loader.getController();
        controllerAdvancesStudents.setEnrollmentAndName(lblStudentID.getText(), lblNameStudent.getText());
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        } catch (IOException ex) {
            App.getLogger().fatal(ex.getMessage());
            DialogGenerator.getDialog(new AlertMessage("No se pudieron cargar los eventos agendados", Status.ERROR));
        } 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void setStudentData(String studentName, StudentDTO student){
        lblNameStudent.setText(studentName);
        lblStudentEmail.setText(student.getEmail());
        lblStudentID.setText(student.getRegistrationNumber());
        lblStudentSemester.setText(student.getSemester());
    }
    
}
