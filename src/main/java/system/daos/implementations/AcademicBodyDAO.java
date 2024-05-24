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
import system.daos.contracts.IAcademicBody;
import system.objects.AcademicBodyDTO;
import system.objects.Status;

/**
 *
 * @author marte
 */
public class AcademicBodyDAO implements IAcademicBody{

    private final String GET_ALL_ACADEMIC_BODY_QUERY = "SELECT * FROM cuerpoacademico ";
    
    @Override
    public List<AcademicBodyDTO> getAllAcademicBody() throws DAOException {
        List<AcademicBodyDTO> ListAcademicBody = new ArrayList();
        try{
        PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_ALL_ACADEMIC_BODY_QUERY);
        ResultSet result = statement.executeQuery(); 
        while (result.next()){
            AcademicBodyDTO academicBody = new AcademicBodyDTO();
            academicBody.setIdAcademicBody(result.getInt("claveCuerpoAcademico"));
            academicBody.setNameAcademicBody(result.getString("nombreCuerpoAcademico"));
            academicBody.setDescription(result.getString("descripcion"));
            ListAcademicBody.add(academicBody);
        }
        }catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            throw new DAOException("No fue posible obtener la información del cuerpo académico", Status.ERROR);
        } finally {
            DBConnection.close();
        }
        return ListAcademicBody;
    }
    
}
