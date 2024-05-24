/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package system.daos.contracts;

import java.sql.SQLException;
import java.util.List;
import system.daos.implementations.DAOException;
import system.objects.StudentDTO;

/**
 *
 * @author Naomi
 */
public interface IStudent {
    
    public int addStudent(StudentDTO student) throws DAOException;
    public List<StudentDTO> getAllStudents() throws DAOException; 
    
}
