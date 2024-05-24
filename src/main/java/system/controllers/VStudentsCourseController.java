/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package system.controllers;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import system.daos.contracts.IStudent;
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
public class VStudentsCourseController implements Initializable {

    
    private List<StudentDTO> allStudents = new ArrayList();
    private final StudentDAO studentDAO = new StudentDAO();
    
    
    @FXML
    private Button btnShowStudents;
    
    @FXML
    private VBox vBoxStudentsLayout;
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
       startPaneStudents(); 
    }    
        
    public void startPaneStudents(){
        try{
        initializePaneStudents();
        }catch(IOException ex){
            App.getLogger().fatal(ex.getMessage());
            DialogGenerator.getDialog(new AlertMessage("No se pudieron cargar los eventos agendados", Status.ERROR));
        }
    }
    
       
    public void initializePaneStudents() throws IOException{
        
        try {
            allStudents = getAllStudents();
            for(int student =0; student<allStudents.size(); student++){
                FXMLLoader fxmlloader = new FXMLLoader(App.class.getResource("/system/views/StudentsCard.fxml"));
                HBox studentCard = fxmlloader.load();                
                StudentsCardController studentCardController = fxmlloader.getController();
                String studentName = studentDAO.getNameStudentByEmail(allStudents.get(student).getEmail());
                studentCardController.setStudentData(studentName, allStudents.get(student));
                
                vBoxStudentsLayout.getChildren().add(studentCard);
            }
        } catch (DAOException ex) {
            handleDAOException(ex);
        }
    }
    
    public List<StudentDTO> getAllStudents() throws IOException{
        List<StudentDTO> allStudents = new ArrayList();
        try{
            allStudents = studentDAO.getAllStudents();
        }catch(DAOException ex){
            handleDAOException(ex);
        }
        return allStudents;
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
    
}
