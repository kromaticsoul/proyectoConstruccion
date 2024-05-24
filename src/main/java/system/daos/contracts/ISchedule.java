/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package system.daos.contracts;

import java.util.List;
import system.daos.implementations.DAOException;
import system.objects.ScheduleDTO;

/**
 *
 * @author marte
 */
public interface ISchedule {
    
    public List<ScheduleDTO> getAllEventsOfStudents(String enrollment) throws DAOException;
    
}
