/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.daos.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import system.controllers.App;
import system.daos.contracts.IUser;
import system.objects.AcademicBodyDTO;
import system.objects.Status;
import system.objects.UserDTO;

/**
 *
 * @author Naomi
 */
public class UserDAO implements IUser{
    
     String ADD_USER_QUERY = "insert into Usuario(correoUsuario, nombre, apellidoPaterno, apellidoMaterno) values (?,?,?,?)";
     String GET_USER_THAT_IS_TEACHER = "select * from usuario inner join profesor on profesor.correoUsuario = usuario.correoUsuario";

    @Override
    public int AddUser(UserDTO user) throws DAOException {
        int result = -1;/*
        DataBaseManager dataBaseManager = new DataBaseManager();
        Connection connection = dataBaseManager.getConnection();
        PreparedStatement statement = connection.prepareCall(query);
        statement.setString(1,user.getUserEmail());
        statement.setString(2, user.getUserName());
        statement.setString(3, user.getUserLastName());
        statement.setString(4, user.getUserSecondLastName());
        result = statement.executeUpdate();
        */
        return result;              
    }

    @Override
    public List<UserDTO> getUsersThatAreTeachers() throws DAOException{
        List<UserDTO> usersThatAreTeachers = new ArrayList();
        try{
        PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_USER_THAT_IS_TEACHER);
        ResultSet result = statement.executeQuery(); 
        while (result.next()){
            UserDTO user = new UserDTO();
            user.setNombre(result.getString("nombre"));
            user.setApellidoPaterno(result.getString("apellidoPaterno"));
            user.setApellidoMaterno(result.getString("apellidoMaterno"));
            user.setCorreoUsuario(result.getString("correoUsuario"));
            usersThatAreTeachers.add(user);
        }
        }catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            throw new DAOException("No fue posible obtener la información del cuerpo académico", Status.ERROR);
        } finally {
            DBConnection.close();
        }
        return usersThatAreTeachers;
    }
    
}
