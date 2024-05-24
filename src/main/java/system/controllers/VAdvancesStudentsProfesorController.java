/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package system.controllers;

import Logic.AdvanceDTO;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import system.daos.implementations.DAOException;
import system.daos.implementations.StudentDAO;
import system.objects.AlertMessage;
import system.objects.Status;
import static system.objects.Status.ERROR;
import static system.objects.Status.FATAL;
import system.objects.StudentDTO;

/**
 * FXML Controller class
 *
 * @author marte
 */
public class VAdvancesStudentsProfesorController implements Initializable {

    @FXML
    private Label lblStudentFullName;

    @FXML
    private Label lblUV;

    @FXML
    private VBox vBoxLayoutAdvances;
    
    @FXML
    void returnWindow(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("/system/views/VStudentsCourse.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(VAdvancesStudentsProfesorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    private String enrollment; 
    private String fullName;
    private List<AdvanceDTO> allStudentAdvances = new ArrayList();
    private StudentDAO studentDAO = new StudentDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    public void initializePaneStudents() {
         lblStudentFullName.setText(fullName);
        try {
            allStudentAdvances = getStudentAdvances();
            if(allStudentAdvances.size()<1){
                DialogGenerator.getDialog(new AlertMessage("Este usuario no tiene entregas", Status.WARNING));
            }else{
                for(int advance =0; advance<allStudentAdvances.size(); advance++){
                    FXMLLoader fxmlloader = new FXMLLoader(App.class.getResource("/system/views/VAdvancesCard.fxml"));
                    HBox studentCard = fxmlloader.load();                
                    VAdvancesCardController advancesCardController = fxmlloader.getController();
                    advancesCardController.setStudentData(allStudentAdvances.get(advance));
                    vBoxLayoutAdvances.getChildren().add(studentCard);
                }
                } 
        }catch (IOException ex) {
            App.getLogger().fatal(ex.getMessage());
            DialogGenerator.getDialog(new AlertMessage("No se pudieron cargar los eventos agendados", Status.ERROR));
        }
    }
    
    public List<AdvanceDTO> getStudentAdvances() throws IOException{
        List<AdvanceDTO> allStudentAdvances = new ArrayList();
        try{
            allStudentAdvances = studentDAO.getStudentAdvances(enrollment);
            System.out.println(enrollment);
        }catch(DAOException ex){
            handleDAOException(ex);
        }
        return allStudentAdvances;
    }
    
    private void handleDAOException(DAOException ex) throws IOException {
        DialogGenerator.getDialog(new AlertMessage(
                ex.getMessage(),
                ex.getStatus()));
        switch (ex.getStatus()) {
            case ERROR:
            case FATAL:
                //App.setRoot("/system/views/VLogin");
                break;
        }
    }
    
    public void setEnrollmentAndName(String enrrollment, String fullName) {
        this.enrollment = enrrollment;
        this.fullName = fullName;
        initializePaneStudents();                       //debido a que primero se tiene que setear, y luego inicializar.
    }
}
