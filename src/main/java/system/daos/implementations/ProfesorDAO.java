/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.daos.implementations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import system.controllers.App;
import system.daos.contracts.IProfesor;
import system.objects.ProfesorDTO;
import system.objects.Status;

/**
 *
 * @author marte
 */
public class ProfesorDAO implements IProfesor {
    
    private final String GET_TEACHER_QUERY = "SELECT * FROM Profesor WHERE Profesor.correoUsuario = ? AND Profesor.numeroPersonalProfesor = ?";
    private final String ADD_TEACHER_COMMAND = "insert into Profesor(numeroPersonalProfesor,cubiculo,correoUsuario) values(?,?,?)";
    private final String UPDATE_TEACHER_COMMAND = "UPDATE Profesor SET `numeroPersonalProfesor` = ?, `cubiculo` = ?, `correoUsuario` = ? WHERE (`numeroPersonalProfesor` = ?)";
    private final String DELETE_TEACHER_COMMAND = "DELETE FROM Profesor WHERE Profesor.numeroPersonalProfesor = ?";
    private final String GET_TEACHER_BY_EMAIL_QUERY = "SELECT * FROM Profesor WHERE Profesor.correoUsuario = ?";
    private final String GET_TEACHER_BY_NUMPERSONAL_QUERY = "SELECT * FROM Profesor WHERE Profesor.numeroPersonalProfesor =+ ?";
    private final String GET_ALL_TEACHER_QUERY = "SELECT * FROM Profesor ";

    @Override
    public ProfesorDTO getProfesor(String correoUsuario, String cubiculo, int numeroPersonalProfesor) throws DAOException {
        try {
            ProfesorDTO validatedTeacher = createValidatedTeacherForLogin(correoUsuario, cubiculo, numeroPersonalProfesor);
            return executeGetTeacherQuery(validatedTeacher);
        } catch (IllegalArgumentException ex) {
            throw new DAOException(ex.getMessage(), Status.WARNING);
        } 
    }

    @Override
    public int addProfesor(ProfesorDTO teacher) throws DAOException {
        try {
            ProfesorDTO validatedTeacher = getValidatedTeacherForAdd(teacher);
            return executeTeacherAdditionTransaction(validatedTeacher);
        } catch (IllegalArgumentException ex) {
            throw new DAOException(ex.getMessage(), Status.WARNING);
        }    
    }

    
    @Override
    public int updateTeacher(int idTeacher, ProfesorDTO teacher) throws DAOException {
        try {
            ProfesorDTO validatedTeacher = getValidatedTeacherForUpdate(teacher);
            return executeTeacherUpdateTransaction(idTeacher, validatedTeacher);
        } catch (IllegalArgumentException ex) {
            throw new DAOException(ex.getMessage(), Status.WARNING);
        }
    }
    
    @Override
    public List<ProfesorDTO> getAllProfesor() throws DAOException{
        return executeGetAllProfesor();       
    }
     
    @Override
    public ProfesorDTO getProfesorByEmail(String correoUsuario) throws DAOException {
        try {
            return getTeacherByEmail(correoUsuario);
        } catch (IllegalArgumentException ex) {
            throw new DAOException(ex.getMessage(), Status.WARNING);
        } 
    }
    
    private List<ProfesorDTO> executeGetAllProfesor() throws DAOException{
        List<ProfesorDTO> ListProfesor = new ArrayList();
        try {
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_ALL_TEACHER_QUERY);
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ProfesorDTO professor = new ProfesorDTO();
                professor.setNumeroPersonalProfesor(result.getInt("numeroPersonalProfesor"));
                professor.setCubiculo(result.getString("cubiculo"));
                professor.setCorreoUsuario(result.getString("correoUsuario"));
                ListProfesor.add(professor);
            }
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            throw new DAOException("No fue posible obtener la información de los académicos", Status.ERROR);
        } finally {
            DBConnection.close();
        }
        return ListProfesor;
    }
    
    private ProfesorDTO executeGetTeacherQuery(ProfesorDTO validatedTeacher) throws DAOException {
        try {
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_TEACHER_QUERY);
            statement.setString(1, validatedTeacher.getCorreoUsuario());
            statement.setInt(2, validatedTeacher.getNumeroPersonalProfesor());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                validatedTeacher.setNumeroPersonalProfesor(rs.getInt("numeroPersonalProfesor"));
                validatedTeacher.setCorreoUsuario(rs.getString("correoUsuario"));
                validatedTeacher.setCubiculo(rs.getString("cubiculo"));
            }
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            throw new DAOException("No fue posible obtener la información del académico", Status.ERROR);
        } finally {
            DBConnection.close();
        }
        return validatedTeacher;
    }

    private int executeTeacherAdditionTransaction(ProfesorDTO teacher) throws DAOException {
        int response = -1;
        try {
            DBConnection.getInstance().setAutoCommit(false);
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(ADD_TEACHER_COMMAND);
            statement.setInt(1, teacher.getNumeroPersonalProfesor());
            statement.setString(2, teacher.getCubiculo());
            statement.setString(3, teacher.getCorreoUsuario());
            int rowsInserted = statement.executeUpdate();
            DBConnection.getInstance().commit();
            if (rowsInserted > 0 ) {
                response = teacher.getNumeroPersonalProfesor();
                System.out.println("Registro realizado exitosamente");
            }
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            DBConnection.rollback();
            throw new DAOException("No fue posible registrar al académico", Status.ERROR);
        } finally {
            DBConnection.close();
        }
        return response;
    }

    private int executeTeacherUpdateTransaction(int idTeacher, ProfesorDTO teacher) throws DAOException {
        int response = -1;
        try {
            DBConnection.getInstance().setAutoCommit(false);
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(UPDATE_TEACHER_COMMAND);
            statement.setInt(1, teacher.getNumeroPersonalProfesor());
            statement.setString(2, teacher.getCubiculo());
            statement.setString(3, teacher.getCorreoUsuario());
            statement.setInt(4, idTeacher);
            statement.executeUpdate();
            DBConnection.getInstance().commit();
            response = idTeacher;
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            DBConnection.rollback();
            throw new DAOException("No fue posible actualizar un académico", Status.ERROR);
        } finally {
            DBConnection.close();
        }
        return response;
    }

    private ProfesorDTO createValidatedTeacherForLogin(String correoUsuario, String cubiculo, int numeroPersonalProfesor ) {
        ProfesorDTO teacher = ProfesorDTO.cleanSession();
        teacher.setCorreoUsuario(correoUsuario);
        teacher.setCubiculo(cubiculo);
        teacher.setNumeroPersonalProfesor(numeroPersonalProfesor);
        return teacher;
    }
    
    
    private ProfesorDTO getValidatedTeacherForAdd(ProfesorDTO teacher) throws DAOException { 
        checkEmailDuplication(teacher.getCorreoUsuario());
        return teacher;
    }

    private ProfesorDTO getValidatedTeacherForUpdate(ProfesorDTO teacher) throws DAOException { 
        ProfesorDTO oldTeacher = getTeacherById(teacher.getNumeroPersonalProfesor());
        if (oldTeacher.getNumeroPersonalProfesor()<= 0) {
            throw new DAOException("No se encontró ningún académico con ese Id", Status.FATAL);
        } else {
            if (!oldTeacher.getCorreoUsuario().equalsIgnoreCase(teacher.getCorreoUsuario())) {
                checkEmailDuplication(teacher.getCorreoUsuario());
            }
        }
        return teacher;

    }

    private ProfesorDTO getTeacherById(int id) throws DAOException {  //modificar para que quede de acuerdo a mi tabla teacher
        ProfesorDTO teacher = ProfesorDTO.cleanSession();
        try {
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_TEACHER_BY_NUMPERSONAL_QUERY);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                teacher.setNumeroPersonalProfesor(rs.getInt("numeroPersonalProfesor"));
                teacher.setCubiculo(rs.getString("cubiculo"));
                teacher.setCorreoUsuario(rs.getString("correoUsuario"));
            }
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            DBConnection.close();
            throw new DAOException("No fue posible obtener la información del académico", Status.ERROR);
        }
        return teacher;
    }
    
    private ProfesorDTO getTeacherByEmail(String email)throws DAOException {
        ProfesorDTO profesor = ProfesorDTO.cleanSession();
        try {
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_TEACHER_BY_EMAIL_QUERY);
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                profesor.setNumeroPersonalProfesor(result.getInt("numeroPersonalProfesor"));
                profesor.setCubiculo(result.getString("cubiculo"));
                profesor.setCorreoUsuario(result.getString("correoUsuario"));
            }
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            DBConnection.close();
            throw new DAOException("No fue posible obtener la información del académico", Status.ERROR);
        }
        return profesor;
    }

    private void checkEmailDuplication(String email) throws DAOException {
        try {
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_TEACHER_BY_EMAIL_QUERY);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                throw new IllegalArgumentException("El email ya se encuentra registrado en el sistema");
            }
        } catch (SQLException ex) {
            DBConnection.close();
            throw new DAOException("No fue posible validar tu información con la base de datos del sistema", Status.ERROR);
        }
    }

    public void deleteTeacher(int idTeacher) throws DAOException { 
        try {
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(DELETE_TEACHER_COMMAND);
            statement.setInt(1, idTeacher);
            int queryResult = statement.executeUpdate();
            if (queryResult <= 0) {
                throw new DAOException("No fue posible eliminar al académico", Status.ERROR);
            }else{
               System.out.println("Se elimino correctamente al profesor"); 
            }
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            throw new DAOException("No fue posible eliminar al académico", Status.ERROR);
        } finally {
            DBConnection.close();
        }
    }
    
}
