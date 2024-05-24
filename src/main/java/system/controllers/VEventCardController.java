/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package system.controllers;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import system.objects.ScheduleDTO;

/**
 * FXML Controller class
 *
 * @author marte
 */
public class VEventCardController implements Initializable {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnModify;

    @FXML
    private Label lblCreatedDate;

    @FXML
    private Label lblDueTime;

    @FXML
    private Label lblEventCreatedDate;

    @FXML
    private Label lblEventDueDate;

    @FXML
    private Label lblTitle;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setEventData(ScheduleDTO event){
        lblTitle.setText(event.getTitle());
        lblEventCreatedDate.setText(setDateToString(event.getCreationDate()));
        lblEventDueDate.setText(setDateToString(event.getLimitDate()));
    }
    
    private String setDateToString(Date date){
        String datePattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDataformat = new SimpleDateFormat(datePattern);
        String stringDate = simpleDataformat.format(date);
        return stringDate;
    }
    
}
