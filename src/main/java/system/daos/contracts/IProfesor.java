/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package system.daos.contracts;


import java.util.List;
import system.daos.implementations.DAOException;
import system.objects.ProfesorDTO;

/**
 *
 * @author marte
 */
public interface IProfesor {
    
    public ProfesorDTO getProfesor(String correoUsuario, String cubiculo, int numeroPersonalProfesor) throws DAOException;
     
    public int updateTeacher(int idTeacher, ProfesorDTO teacher) throws DAOException;

    public int addProfesor(ProfesorDTO teacher) throws DAOException;
    
    public List<ProfesorDTO> getAllProfesor() throws DAOException;
    
    public ProfesorDTO getProfesorByEmail(String correoUsuario) throws DAOException;
    
}
