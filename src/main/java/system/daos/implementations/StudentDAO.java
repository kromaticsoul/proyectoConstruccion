/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.daos.implementations;

import Logic.AdvanceDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import system.controllers.App;
import system.daos.contracts.IStudent;
import system.objects.Status;
import system.objects.StudentDTO;

/**
 *
 * @author Naomi
 */
public class StudentDAO implements IStudent{

    private final String INSERT_STUDENT_QUERY = "insert into Estudiante(matriculaEstudiante, semestre, correoUsuario) values (?,?,?)";
    private final String GET_ALL_STUDENTS_QUERY = "Select * FROM Estudiante";
    private final String GET_NAME_STUDENT_BY_EMAIL = "Select CONCAT(usuario.nombre, ' ',usuario.apellidoPaterno, ' ', usuario.apellidoMaterno) AS nombre_completo  from usuario inner join estudiante on estudiante.correoUsuario =  usuario.correoUsuario WHERE usuario.correoUsuario = ?";
    private final String GET_STUDENT_ADVANCES = "Select * from avance inner join estudiante on estudiante.matriculaEstudiante =  avance.matriculaEstudiante WHERE avance.matriculaEstudiante = ?";
    
    @Override
    public int addStudent(StudentDTO student) throws DAOException {
       int result;
       try{
        PreparedStatement statement = DBConnection.getInstance().prepareStatement(INSERT_STUDENT_QUERY);
        statement.setString(1,student.getRegistrationNumber());
        statement.setString(2,student.getSemester());
        statement.setString(3,student.getEmail());
        result = statement.executeUpdate();
       }catch(SQLException ex){
           App.getLogger().fatal(ex.getMessage());
            DBConnection.rollback();
            throw new DAOException("No fue posible registrar al Estudiante", Status.ERROR);
       }finally{
           DBConnection.close();
       }
        return result;
    }

    @Override
    public List<StudentDTO> getAllStudents() throws DAOException {
        List<StudentDTO> students = new ArrayList();
        try{
        PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_ALL_STUDENTS_QUERY);
        ResultSet result = statement.executeQuery(); 
        while (result.next()){
            StudentDTO student = new StudentDTO();
            student.setEmail(result.getString("correoUsuario"));
            student.setRegistrationNumber(result.getString("matriculaEstudiante"));
            student.setSemester(result.getString("semestre"));
            students.add(student);
        }
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            throw new DAOException("No fue posible obtener la información de los estudiantes", Status.ERROR);
        } finally {
            DBConnection.close();
        }
        return students;
    } 
    
    
    public String getNameStudentByEmail(String email) throws DAOException{
        String fullName = "";
        try {
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_NAME_STUDENT_BY_EMAIL);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                fullName = rs.getString("nombre_completo");
            }
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            DBConnection.close();
            throw new DAOException("No fue posible obtener la información del académico", Status.ERROR);
        }
        return fullName;
    }
    
    public List<AdvanceDTO> getStudentAdvances(String enrollment) throws DAOException{
        List<AdvanceDTO> advances = new ArrayList();
        try{
        PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_STUDENT_ADVANCES);
        statement.setString(1, enrollment);
        ResultSet result = statement.executeQuery(); 
        while (result.next()){
            AdvanceDTO advance = new AdvanceDTO();
            advance.setDateDelivery(result.getDate("fechaEntrega"));
            advance.setDescription(result.getString("Descripcion"));
            advance.setTitle(result.getString("Titulo"));
            advances.add(advance);
        }
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            throw new DAOException("No fue posible obtener los avances de los estudiantes", Status.ERROR);
        } finally {
            DBConnection.close();
        }
        return advances;
    }
    
   }
