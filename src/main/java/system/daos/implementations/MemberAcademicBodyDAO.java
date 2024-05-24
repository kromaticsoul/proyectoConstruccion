/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.daos.implementations;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import system.controllers.App;
import system.daos.contracts.IMemberAcademicBody;
import system.objects.MemberAcademicBodyDTO;
import system.objects.Status;

/**
 *
 * @author marte
 */
public class MemberAcademicBodyDAO implements IMemberAcademicBody {
    
    private final String GET_MEMBERS_QUERY = "SELECT * FROM miembroscuerpoacademico WHERE miembroscuerpoacademico.claveCuerpoAcademico = ?";
    private final String GET_TEACHER_BY_NUMPERSONAL_QUERY = "SELECT * FROM miembroscuerpoacademico WHERE miembroscuerpoacademico.numeroPersonalProfesor = ?";
    private final String ADD_MEMBERS_QUERY = "INSERT into miembroscuerpoacademico(rol,numeroPersonalProfesor,claveCuerpoAcademico) values(?,?,?)";

    @Override
    public MemberAcademicBodyDTO getMemberAcademicBody(int idMemberAcademicBody, MemberAcademicBodyDTO member) throws DAOException {
        /*try {
            MemberAcademicBodyDTO validatedMember = createMemberAcademic(member.getRol(),member.getClaveCuerpoAcademico(),member.getClaveCuerpoAcademico());
            return executeAccessAcountQuery(validatedAccessAccount);
        } catch (IllegalArgumentException ex) {
            throw new DAOException(ex.getMessage(), Status.WARNING);
        } */
        return null;
    }
    
    @Override
    public int updateMemberAcademicBody(int idMemberAcademicBody, MemberAcademicBodyDTO member) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int addMemberAcademicBody(MemberAcademicBodyDTO member) throws DAOException {
        try {
            MemberAcademicBodyDTO memberToAdd = getValidatedTeacherForAdd(member);
            return executeMemberAcademicBodyAdditionTransaction(memberToAdd);
        } catch (IllegalArgumentException ex) {
            throw new DAOException(ex.getMessage(), Status.WARNING);
        }    
    }

    private MemberAcademicBodyDTO createMemberAcademic(String rol, int numeroPersonalProfesor, int claveCuerpoAcademico) {
    MemberAcademicBodyDTO member = new MemberAcademicBodyDTO();
    member.setId(-1);
    member.setClaveCuerpoAcademico(claveCuerpoAcademico);
    member.setNumeroPersonalProfesor(numeroPersonalProfesor);
    member.setRol(rol);
    return member;
    }
    
    private int executeMemberAcademicBodyAdditionTransaction(MemberAcademicBodyDTO member) throws DAOException {
        int response = -1;
        try {
            DBConnection.getInstance().setAutoCommit(false);
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(
                    ADD_MEMBERS_QUERY,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, member.getRol());
            statement.setInt(2, member.getNumeroPersonalProfesor());
            statement.setInt(3, member.getClaveCuerpoAcademico());
            statement.executeUpdate();
            DBConnection.getInstance().commit();
            ResultSet rs = statement.getGeneratedKeys();
            while (rs.next()) {
                response = rs.getInt(1);
            }
        } catch (SQLException ex) {
            App.getLogger().fatal(ex.getMessage());
            DBConnection.rollback();
            throw new DAOException("No fue posible registrar al miembro del cuerpo académico", Status.ERROR);
        } finally {
            DBConnection.close();
        }
        return response;
    }

    private MemberAcademicBodyDTO getValidatedTeacherForAdd(MemberAcademicBodyDTO teacher) throws DAOException { 
        checkIDDuplication(teacher.getNumeroPersonalProfesor());
        return teacher;
    }
    
    
    private void checkIDDuplication(int IdProfesor) throws DAOException {
        try {
            PreparedStatement statement = DBConnection.getInstance().prepareStatement(GET_TEACHER_BY_NUMPERSONAL_QUERY);
            statement.setInt(1, IdProfesor);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                throw new IllegalArgumentException("El profesor ya se encuentra en el cuerpo académico");
            }
        }catch (SQLException ex) {
            DBConnection.close();
            throw new DAOException("No fue posible validar tu información con la base de datos del sistema", Status.ERROR);
        }
    }
    
    
}
