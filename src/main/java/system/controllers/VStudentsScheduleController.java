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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import system.daos.implementations.DAOException;
import system.daos.implementations.ScheduleDAO;
import system.objects.AlertMessage;
import system.objects.ScheduleDTO;
import system.objects.Session;
import system.objects.Status;
import static system.objects.Status.ERROR;
import static system.objects.Status.FATAL;
import system.objects.StudentDTO;

/**
 * FXML Controller class
 *
 * @author marte
 */
public class VStudentsScheduleController implements Initializable {

    
    @FXML
    private Label lblSchedule;

    @FXML
    private Label lblUV;

    @FXML
    private VBox vBoxScheduleLayout;
    
    private List<ScheduleDTO> allStudentEvents = new ArrayList();
    private  ScheduleDAO scheduleDAO = new ScheduleDAO(); 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
    }    
       
    public void initializePaneSchedule(){
        try {
            if(allStudentEvents.size()<1){
                DialogGenerator.getDialog(new AlertMessage("No tienes ningún evento agendado", Status.WARNING));
            }else{
                for(int event =0; event<allStudentEvents.size(); event++){
                    FXMLLoader fxmlloader = new FXMLLoader(App.class.getResource("/system/views/VEventCard.fxml"));
                    HBox eventCard = fxmlloader.load();                
                    VEventCardController eventCardController = fxmlloader.getController();
                    eventCardController.setEventData(allStudentEvents.get(event));
                    vBoxScheduleLayout.getChildren().add(eventCard);
                }
            }    
        } catch (IOException ex) {
            App.getLogger().fatal(ex.getMessage());
            DialogGenerator.getDialog(new AlertMessage("No se pudieron cargar los eventos agendados", Status.ERROR));
        }
    }
    
    public void setEventsData(List<ScheduleDTO> events) {
        allStudentEvents = events;
        initializePaneSchedule();
    }
    
}
