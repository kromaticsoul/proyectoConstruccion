/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package system.daos.contracts;

import java.util.List;
import system.daos.implementations.DAOException;
import system.objects.UserDTO;


/**
 *
 * @author Naomi
 */
public interface IUser {
    public int AddUser (UserDTO user) throws DAOException;
    public List<UserDTO> getUsersThatAreTeachers() throws DAOException;
}
