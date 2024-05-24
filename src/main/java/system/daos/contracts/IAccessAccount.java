/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package system.daos.contracts;

import system.daos.implementations.DAOException;
import system.objects.AccessAccountDTO;

/**
 *
 * @author marte
 */
public interface IAccessAccount {
    
    public AccessAccountDTO getAccessAccount(String correoUsuario, String contraseña) throws DAOException;
    public String getStudentEnrollment(String email) throws DAOException;
    public String getTypeAccount(String email) throws DAOException;
}
