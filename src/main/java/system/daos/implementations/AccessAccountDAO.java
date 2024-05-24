/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.daos.implementations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import system.controllers.App;
import system.objects.AccessAccountDTO;
import system.objects.Status;
import system.daos.contracts.IAccessAccount;

/**
 *
 * @author marte
 */
public class AccessAccountDAO implements IAccessAccount {

    private final String GET_ACCESSACCOUNT_QUERY = "SELECT * FROM cuentaacceso WHERE cuentaacceso.correoUsuario = ? AND cuentaacceso.contraseña = ?";
    private final String GET_STUDENT_ENROLLMENT_QUERY = "SELECT estudiante.matriculaEstudiante FROM cuentaacceso JOIN estudiante ON estudiante.correoUsuario = cuentaacceso.correoUsuario WHERE cuentaacceso.cuentaAcceso = ?;";
    private final String GET_TYPE_ACCOUNT_QUERY = " SELECT cuentaacceso.cuentaAcceso, CASE WHEN estudiante.matriculaEstudiante IS NOT NULL THEN 'estudiante' WHEN profesor.numeroPersonalProfesor IS NOT NULL THEN 'profesor' ELSE 'otro' END AS tipoUsuario FROM cuentaacceso LEFT JOIN estudiante ON estudiante.correoUsuario = cuentaacceso.correoUsuario LEFT JOIN profesor ON profesor.correoUsuario = cuentaacceso.correoUsuario WHERE cuentaacceso.cuentaAcceso = ? ;";
    
    @Override
    public AccessAccountDTO getAccessAccount(String correoUsuario,String contraseña) throws DAOException {
        try {
            AccessAccountDTO validatedAccessAccount = createValidatedAccessAccountForLogin(correoUsuario, contraseña, correoUsuario);
            return executeAccessAcountQuery(validatedAccessAccount);
        } catch (IllegalArgumentException ex) {
            throw new DAOException(ex.getMessage(), Status.WARNING);
        } 
    }
    
    @Override
    public String getStudentEnrollment(String email) throws DAOException{
        try {
            return executeGetStudentEnrollment(email);
        } catch (IllegalArgumentException ex) {
            throw new DAOException(ex.getMessage(), Status.WARNING);
        } 
    }
    
    @Override
    public String getTypeAccount(String email) throws DAOException{
        try {
            return executeGetTypeAccount(email);
        } catch (IllegalArgumentException ex) {
            throw new DAOException(ex.getMessage(), Status.WARNING);
        } 
    }

    
    private AccessAccountDTO createValidatedAccessAccountForLogin(String accessAccount, String password, String UserEmail) {
    AccessAccountDTO accessAccountDTO = new AccessAccountDTO();
    accessAccountDTO.setAccessAccount(accessAccount);
    accessAccountDTO.setPassword(password);
    accessAccountDTO.setUserEmail(UserEmail);
    return accessAccountDTO;
    }


    
    private AccessAccountDTO executeAccessAcountQuery(AccessAccountDTO validatedAccessAccount) throws DAOException {
        try {
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_ACCESSACCOUNT_QUERY);
            statement.setString(1, validatedAccessAccount.getUserEmail());
            statement.setString(2, validatedAccessAccount.getPassword());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                validatedAccessAccount.setAccessAccount(rs.getString("cuentaAcceso"));
                validatedAccessAccount.setPassword(rs.getString("contraseña"));
                validatedAccessAccount.setUserEmail(rs.getString("correoUsuario"));
            }else{
                validatedAccessAccount.setUserEmail("default_mail@gmail.com");
            }    
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            throw new DAOException("No se encontró la cuenta en la base de datos", Status.ERROR);
        } finally {
            DBConnection.close();
        }
        return validatedAccessAccount;
    }
    
    private String executeGetStudentEnrollment(String email) throws DAOException{
        String enrollment = "";
        try {
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_STUDENT_ENROLLMENT_QUERY);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                enrollment = rs.getString("matriculaEstudiante");
            }    
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            throw new DAOException("No se encontró un estudiante con esa cuenta en la base de datos", Status.ERROR);
        } finally {
            DBConnection.close();
        }
        return enrollment;
    }
    
    private String executeGetTypeAccount(String email) throws DAOException{
        String typeAccount = "";
        try {
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_TYPE_ACCOUNT_QUERY);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                typeAccount = rs.getString("tipoUsuario");
            }    
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            throw new DAOException("No se encontró una cuenta de acceso vinculada en la base de datos", Status.ERROR);
        } finally {
            DBConnection.close();
        }
        return typeAccount;
    }
    
}

