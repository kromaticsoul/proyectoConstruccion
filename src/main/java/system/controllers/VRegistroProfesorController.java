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
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import system.daos.contracts.IProfesor;
import system.daos.implementations.DAOException;
import system.daos.implementations.ProfesorDAO;
import system.objects.AlertMessage;
import system.objects.ProfesorDTO;
import system.objects.Status;
import static system.objects.Status.ERROR;
import static system.objects.Status.FATAL;

/**
 * FXML Controller class
 *
 * @author marte
 */
public class VRegistroProfesorController implements Initializable {

    
     
    @FXML
    private TextField txtNumPersonal;

    @FXML
    private TextField txtCorreoUsuario;

    @FXML
    private TextField txtCubiculo;
    
    private final IProfesor TEACHER_DAO = new ProfesorDAO();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void signUp(ActionEvent event) throws IOException {
        try {
            invokeTeacherRegistration(getDataFromForm());
        } catch (IllegalArgumentException ex) {
            handleValidationException(ex);
        } catch (DAOException ex) {
            handleDAOException(ex);
        }
    }
    
    private void invokeTeacherRegistration(ProfesorDTO teacher) throws IOException, DAOException {
        int idTeacher = TEACHER_DAO.addProfesor(teacher);
        if (idTeacher > 0) {
            ProfesorDTO.getSession().setNumeroPersonalProfesor(idTeacher);
            DialogGenerator.getDialog(new AlertMessage("Se registró correctamente", Status.SUCCESS));
            //App.changeView("/system/views/VCoursesList", 600, 400);
        }else {
            DialogGenerator.getDialog(new AlertMessage(
                    "No fue posible registrar al académico",
                    Status.WARNING));
        }
    }
    
    private ProfesorDTO getDataFromForm() {
        ProfesorDTO.getSession().setNumeroPersonalProfesor(Integer.parseInt(txtNumPersonal.getText()));
        ProfesorDTO.getSession().setCorreoUsuario(txtCorreoUsuario.getText());
        ProfesorDTO.getSession().setCubiculo(txtCubiculo.getText());
        return ProfesorDTO.getSession();
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

    private void handleValidationException(IllegalArgumentException ex) throws IOException {
        DialogGenerator.getDialog(new AlertMessage(
                ex.getMessage(),
                Status.WARNING));
    }
    
}
