/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package system.daos.implementations;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import system.objects.AccessAccountDTO;

/**
 *
 * @author marte
 */
public class AccessAccountDAOTest {
    
    private static AccessAccountDAO ACCESSACCOUNT_DAO;
    private AccessAccountDTO expectedAccessAccount;
    private String correoUsuario = "alvaro@gmail.com";
    private String contraseña = "alvaro1234";

    
    public AccessAccountDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        ACCESSACCOUNT_DAO = new AccessAccountDAO();
        expectedAccessAccount = new AccessAccountDTO();
        expectedAccessAccount.setAccessAccount(correoUsuario);
        expectedAccessAccount.setUserEmail(correoUsuario);
        expectedAccessAccount.setPassword(contraseña);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAccessAccount method, of class AccessAccountDAO.
     */
    @Test
    public void testGetAccessAccount() throws Exception {
        System.out.println("getAccessAccount");
        AccessAccountDTO result = ACCESSACCOUNT_DAO.getAccessAccount(correoUsuario, contraseña);
        assertEquals(expectedAccessAccount, result);
    }
    
    @Test
    public void testGetStudentEnrollment() throws Exception{
        System.out.println("getStudentEnrollment");
        
    }

    
}
