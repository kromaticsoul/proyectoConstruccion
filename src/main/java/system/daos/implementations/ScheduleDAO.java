/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.daos.implementations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import system.controllers.App;
import system.daos.contracts.ISchedule;
import system.objects.ScheduleDTO;
import system.objects.Status;

/**
 *
 * @author marte
 */
public class ScheduleDAO implements ISchedule{
    
    private final String GET_STUDENT_EVENTS = "Select titulo, descripcion, fecha, fechaLimite, evento.matriculaEstudiante from evento inner join estudiante on estudiante.matriculaEstudiante =  evento.matriculaEstudiante WHERE evento.matriculaEstudiante = ?";
    private final String GET_FIRST_TWO_STUDENT_EVENTS = "Select titulo, descripcion, fecha, fechaLimite, evento.matriculaEstudiante from evento inner join estudiante on estudiante.matriculaEstudiante =  evento.matriculaEstudiante WHERE evento.matriculaEstudiante = ? LIMIT 2";

    
    @Override
    public List<ScheduleDTO> getAllEventsOfStudents(String enrollment) throws DAOException {
        
        List<ScheduleDTO> events = new ArrayList();
        
        try{
        PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_STUDENT_EVENTS);
        statement.setString(1, enrollment);
        ResultSet result = statement.executeQuery(); 
        while (result.next()){
            ScheduleDTO scheduledEvent = new ScheduleDTO();
            scheduledEvent.setTitle(result.getString("titulo"));
            scheduledEvent.setDescription(result.getString("descripcion"));
            scheduledEvent.setCreationDate(result.getDate("fechaLimite"));
            scheduledEvent.setLimitDate(result.getDate("fechaLimite"));
            scheduledEvent.setStudentEnrollment(result.getString("matriculaEstudiante"));
            events.add(scheduledEvent);
        }
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            throw new DAOException("No fue posible obtener los eventos de los estudiantes", Status.ERROR);
        } finally {
            DBConnection.close();
        }
        return events;
    }
    
    public List<ScheduleDTO> getFirstTwoEventsOfStudents(String enrollment) throws DAOException {   //para realizar el test correctamente.
        
        List<ScheduleDTO> events = new ArrayList();
        
        try{
        PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_FIRST_TWO_STUDENT_EVENTS);
        statement.setString(1, enrollment);
        ResultSet result = statement.executeQuery(); 
        while (result.next()){
            ScheduleDTO scheduledEvent = new ScheduleDTO();
            scheduledEvent.setTitle(result.getString("titulo"));
            scheduledEvent.setDescription(result.getString("descripcion"));
            scheduledEvent.setCreationDate(result.getDate("fechaLimite"));
            scheduledEvent.setLimitDate(result.getDate("fechaLimite"));
            scheduledEvent.setStudentEnrollment(result.getString("matriculaEstudiante"));
            events.add(scheduledEvent);
        }
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            throw new DAOException("No fue posible obtener los eventos de los estudiantes", Status.ERROR);
        } finally {
            DBConnection.close();
        }
        return events;
    }

    
}
