/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package system.controllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import system.daos.contracts.IAccessAccount;
import system.daos.implementations.AccessAccountDAO;
import system.daos.implementations.DAOException;
import system.objects.AccessAccountDTO;
import system.objects.AlertMessage;
import system.objects.Status;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import system.objects.Session;
/**
 * FXML Controller class
 *
 * @author marte
 */
public class VLoginController implements Initializable {


    @FXML
    private ImageView imgLogin;
    
    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUser;
    
    private final IAccessAccount DATAACCESS_DAO = new AccessAccountDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AccessAccountDTO.cleanSession();
    }    
    
    @FXML
    private void signIn(ActionEvent event) throws IOException {
       try {
            invokeAccessAccountAuthentication(txtUser.getText(),txtPassword.getText(), event);
        } catch (DAOException ex) {
            handleDAOException(ex);
        }
    }
    
    private void invokeAccessAccountAuthentication(String email, String password, ActionEvent event) throws IOException, DAOException {
        AccessAccountDTO accessAccount = DATAACCESS_DAO.getAccessAccount(email, password);
 
        if (accessAccount.getUserEmail().equals("default_mail@gmail.com")) {
             DialogGenerator.getDialog(new AlertMessage(
                "No se encotró ningúna cuenta con ese correo y contraseña",
                Status.WARNING));
        } else {
            String userType = getAccountType(email);
            System.out.println(userType);
            String enrollment = getStudentEnrollment(email);
            Session.setUserEnrollment(enrollment);
            if(userType.equals("estudiante")){
                FXMLLoader loader = new FXMLLoader(App.class.getResource("/system/views/MainScreenStudents.fxml"));
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
            }else{
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
        }
    }
    
    private void handleDAOException(DAOException ex) {
        DialogGenerator.getDialog(new AlertMessage(
                ex.getMessage(),
                ex.getStatus()));
    }

    private String getStudentEnrollment(String email) throws DAOException {
        String enrollment = "";
        enrollment = DATAACCESS_DAO.getStudentEnrollment(email);
        return enrollment;
    }
    
    private String getAccountType(String email) throws DAOException{
        String accountType = null;
        accountType = DATAACCESS_DAO.getTypeAccount(email);
        return accountType;
    }

}
