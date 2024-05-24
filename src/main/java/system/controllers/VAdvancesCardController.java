/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package system.controllers;

import Logic.AdvanceDTO;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author marte
 */
public class VAdvancesCardController implements Initializable {

    @FXML
    private Button btnDownload;

    @FXML
    private Label lblDeliveryDate;

    @FXML
    private Label lblDescriptionAdvance;

    @FXML
    private Label lblNameAdvance;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setStudentData(AdvanceDTO advance){
        lblNameAdvance.setText(advance.getTitle());
        lblDeliveryDate.setText(setDateToString(advance.getDateDelivery()));
        lblDescriptionAdvance.setText(advance.getDescription());
    }
    
    private String setDateToString(Date date){
        String datePattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDataformat = new SimpleDateFormat(datePattern);
        String stringDate = simpleDataformat.format(date);
        return stringDate;
    }
}
