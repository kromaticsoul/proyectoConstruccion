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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import system.daos.implementations.DAOException;
import system.daos.implementations.ScheduleDAO;
import system.objects.AlertMessage;
import system.objects.ScheduleDTO;
import system.objects.Session;
import system.objects.Status;
import static system.objects.Status.ERROR;
import static system.objects.Status.FATAL;

/**
 * FXML Controller class
 *
 * @author marte
 */
public class MainScreenStudentsController implements Initializable {

    @FXML
    private Button btnVisualizeCronogram;

    private List<ScheduleDTO> allStudentEvents = new ArrayList();
    private  ScheduleDAO scheduleDAO = new ScheduleDAO();

    @FXML
    void visualizeCronogram(ActionEvent event) {
        
        try {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/system/views/VStudentsSchedule.fxml"));
        Parent root = loader.load();
        VStudentsScheduleController scheduleController = loader.getController();
        
        try{
            allStudentEvents = scheduleDAO.getAllEventsOfStudents(Session.getUserEnrollment());
        }catch(DAOException ex){
            handleDAOException(ex);
        }
        
        scheduleController.setEventsData(allStudentEvents);
        
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
        // TODO
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
