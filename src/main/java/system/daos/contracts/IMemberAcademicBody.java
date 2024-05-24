/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package system.daos.contracts;

import system.daos.implementations.DAOException;
import system.objects.MemberAcademicBodyDTO;

/**
 *
 * @author marte
 */
public interface IMemberAcademicBody {
    
    public MemberAcademicBodyDTO getMemberAcademicBody(int idMemberAcademicBody, MemberAcademicBodyDTO member) throws DAOException;
     
    public int updateMemberAcademicBody(int idMemberAcademicBody, MemberAcademicBodyDTO member) throws DAOException;

    public int addMemberAcademicBody(MemberAcademicBodyDTO member) throws DAOException;
}
