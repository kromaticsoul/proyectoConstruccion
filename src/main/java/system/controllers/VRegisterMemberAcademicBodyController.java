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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import system.daos.contracts.IMemberAcademicBody;
import system.daos.contracts.IProfesor;
import system.daos.implementations.AcademicBodyDAO;
import system.daos.implementations.DAOException;
import system.daos.implementations.MemberAcademicBodyDAO;
import system.daos.implementations.ProfesorDAO;
import system.daos.implementations.UserDAO;
import system.objects.AcademicBodyDTO;
import system.objects.AlertMessage;
import system.objects.MemberAcademicBodyDTO;
import system.objects.ProfesorDTO;
import system.objects.Status;
import static system.objects.Status.ERROR;
import static system.objects.Status.FATAL;
import system.objects.UserDTO;

/**
 * FXML Controller class
 *
 * @author marte
 */
public class VRegisterMemberAcademicBodyController implements Initializable {

    @FXML
    private ComboBox<AcademicBodyDTO> cmbAcademicBody;

    @FXML
    private ComboBox<String> cmbRol;

    @FXML
    private Label lblRol;

    @FXML
    private Label lblSelectProfessor;

    @FXML
    private Label lblTitle;
    
    @FXML
    private TableView<UserDTO> tblProfesor;
    
    @FXML
    private TableColumn<UserDTO, String> fullNameColumn;
    
    @FXML
    private TableColumn<ProfesorDTO, String> idTeacherColumn;
    
    private final IMemberAcademicBody MEMBERACADEMICBODY_DAO = new MemberAcademicBodyDAO();
    private final IProfesor PROFESOR_DAO = new ProfesorDAO();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.initializeComboBoxAcademicBody();
            this.initializeComboBoxRol();
            this.initializeTableProfesor();
        } catch (IOException ex) {
            Logger.getLogger(VRegisterMemberAcademicBodyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void initializeComboBoxAcademicBody() throws IOException{
        AcademicBodyDAO academicBody = new AcademicBodyDAO();
        List<AcademicBodyDTO> listAcademicBody = new ArrayList();
        try{
            listAcademicBody = academicBody.getAllAcademicBody();
        }catch (DAOException ex){
            handleDAOException(ex);
        }
        
        ObservableList<AcademicBodyDTO> allAcademicBody = FXCollections.observableArrayList(listAcademicBody);
        cmbAcademicBody.setItems(allAcademicBody);
    }
    
    public void initializeComboBoxRol(){
        ObservableList<String> options = cmbRol.getItems();
        options.addAll("Responsable", "Miembro");

    }
    
    public void initializeTableProfesor() throws IOException{
       UserDAO userDAO = new UserDAO(); 
       List<UserDTO> listUserThatAreProfesor = new ArrayList();
       try{
            listUserThatAreProfesor = userDAO.getUsersThatAreTeachers();
        }catch (DAOException ex){
            handleDAOException(ex);
        }
      
        fullNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre() + " " + cellData.getValue().getApellidoPaterno() + " " + cellData.getValue().getApellidoMaterno()));     
        tblProfesor.getColumns().setAll(fullNameColumn);
        tblProfesor.getItems().setAll(listUserThatAreProfesor);

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
    
    @FXML
    private void registerAcademicBody(ActionEvent event) throws IOException {
        try {
            if(getAcademicBodyFromForm().getClaveCuerpoAcademico()!=0){
                invokeRegsitrationAcademicBody(getAcademicBodyFromForm()); 
            }           
        }catch (DAOException ex) {
            handleDAOException(ex);
        }
    }
    
    private void invokeRegsitrationAcademicBody(MemberAcademicBodyDTO memberToRegist) throws IOException, DAOException{
        int idMemberAcademicBody = MEMBERACADEMICBODY_DAO.addMemberAcademicBody(memberToRegist);
        if (idMemberAcademicBody > 0) {
            DialogGenerator.getDialog(new AlertMessage("El miembro del cuerpo académico académico se registró correctamente", Status.SUCCESS));
        }else {
            DialogGenerator.getDialog(new AlertMessage(
                    "No fue posible registrar al académico",
                    Status.WARNING));
        }
    }
    
    public MemberAcademicBodyDTO getAcademicBodyFromForm() throws IOException, DAOException{
        
        MemberAcademicBodyDTO memberToRegist = new MemberAcademicBodyDTO();
        
        AcademicBodyDTO selectedAcademicBody = cmbAcademicBody.getValue();
        
        String selectedRol = cmbRol.getValue();
        
        UserDTO selectedUser = tblProfesor.getSelectionModel().getSelectedItem();
        
        if (selectedUser != null){
            String selectedUserEmail = selectedUser.getCorreoUsuario();
            if(selectedRol != null){
                if(selectedAcademicBody != null){
                    int idSelectedAcademicBody = selectedAcademicBody.getIdAcademicBody();
                    System.out.println("El usuario seleccionado es: " + selectedUser.getCorreoUsuario());
                    int idSelectedProfesor = getProfesorByEmail(selectedUserEmail);
                    memberToRegist.setClaveCuerpoAcademico(idSelectedAcademicBody);
                    memberToRegist.setNumeroPersonalProfesor(idSelectedProfesor);
                    memberToRegist.setRol(selectedRol);
                    return memberToRegist;
                }else{
                    DialogGenerator.getDialog(new AlertMessage(
                "No se ha seleccionado ningún cuerpo académico",
                Status.WARNING));
                } 
            }else{
                DialogGenerator.getDialog(new AlertMessage(
                "No se ha seleccionado ningún rol",
                Status.WARNING));
            }
            
            
        }else {
            DialogGenerator.getDialog(new AlertMessage(
                "No se ha seleccionado a ningún profesor",
                Status.WARNING));
        }
        return memberToRegist;
    }

    public int getProfesorByEmail(String profesorEmail) throws IOException, DAOException{
               
        ProfesorDTO profesor = PROFESOR_DAO.getProfesorByEmail(profesorEmail);
        
        if (profesor.getNumeroPersonalProfesor()>0){
            return profesor.getNumeroPersonalProfesor();
        }else{
            DialogGenerator.getDialog(new AlertMessage(
                "No se encotró ningún profesor asociado a ese nombre",
                Status.WARNING));
        }
       return -1;
    }
    
    
}
