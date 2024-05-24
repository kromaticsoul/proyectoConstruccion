package system.daos.implementations;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import system.daos.implementations.DAOException;
import system.daos.implementations.DBConnection;
import system.daos.implementations.ProfesorDAO;
import system.objects.ProfesorDTO;

/**
 *
 * @author marte
 */
public class ProfesorDAOTest {
    
    private static ProfesorDAO TEACHER_DAO;
    private static ProfesorDTO teacherForTesting;
    private static ProfesorDTO auxTeacherForTesting;


    private static final String DEFAULT_EMAIL = "alvaro2@gmail.com";
    private static final String DEFAULT_CUBICULO = "999";
    private static final int DEFAULT_NUMEROPERSONALPROFESOR = 856;
    
    private static final String auxDEFAULT_EMAIL = "alemarin@gmail.com";
    private static final String auxDEFAULT_CUBICULO = "999";
    private static final int auxDEFAULT_NUMEROPERSONALPROFESOR = 12345;
    
    
    @BeforeAll
    public static void setUpClass() {
        TEACHER_DAO = new ProfesorDAO();
        teacherForTesting = ProfesorDTO.getNewInstance();
        teacherForTesting.setNumeroPersonalProfesor(DEFAULT_NUMEROPERSONALPROFESOR);
        teacherForTesting.setCubiculo(DEFAULT_CUBICULO);
        teacherForTesting.setCorreoUsuario(DEFAULT_EMAIL);
        
        auxTeacherForTesting = ProfesorDTO.getNewInstance();
        auxTeacherForTesting.setNumeroPersonalProfesor(auxDEFAULT_NUMEROPERSONALPROFESOR);
        auxTeacherForTesting.setCubiculo(auxDEFAULT_CUBICULO);
        auxTeacherForTesting.setCorreoUsuario(auxDEFAULT_EMAIL);
        
        try {
            int id = TEACHER_DAO.addProfesor(auxTeacherForTesting);
            auxTeacherForTesting.setNumeroPersonalProfesor(id);
        } catch (DAOException ex) {
            Logger.getLogger(ProfesorDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterEach
    void tearDown() {
       try {
            TEACHER_DAO.deleteTeacher(teacherForTesting.getNumeroPersonalProfesor());
            DBConnection.close();
        } catch (DAOException ex) {
            Logger.getLogger(ProfesorDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @AfterAll
    public static void done() {
        try {
            TEACHER_DAO.deleteTeacher(auxTeacherForTesting.getNumeroPersonalProfesor());
            DBConnection.close();
        } catch (DAOException ex) {
            Logger.getLogger(ProfesorDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Test
    public void testAddProfesor() throws Exception {
        System.out.println("Test registerProfesor");
        int idTeacher = -1;
        try {
            idTeacher = TEACHER_DAO.addProfesor(teacherForTesting);
            teacherForTesting.setNumeroPersonalProfesor(idTeacher);
        } catch (DAOException ex) {
           Logger.getLogger(ProfesorDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assertions.assertTrue(idTeacher > 0);    
    }
    
    @Test
    public void testAddDuplicatedProfesor() throws Exception{
        System.out.println("Test DuplicatedProfessor");
        int idTeacher = -1;
        try {
            idTeacher = TEACHER_DAO.addProfesor(auxTeacherForTesting);
            auxTeacherForTesting.setNumeroPersonalProfesor(idTeacher);
        } catch (DAOException ex) {
           Logger.getLogger(ProfesorDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assertions.assertTrue(idTeacher <= 0); 
    }

    /**
     * Test of updateTeacher method, of class ProfesorDAO.
     */
    /*
    @Test
    public void testUpdateTeacher() throws Exception {
        System.out.println("updateTeacher");
        int idTeacher = 0;
        ProfesorDTO teacher = null;
        ProfesorDAO instance = new ProfesorDAO();
        int expResult = 0;
        int result = instance.updateTeacher(idTeacher, teacher);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
