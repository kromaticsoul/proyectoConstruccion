/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package system.daos.implementations;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import system.objects.AcademicBodyDTO;

/**
 *
 * @author marte
 */
public class AcademicBodyDAOTest {
    
    private static AcademicBodyDAO ACADEMICBODY_DAO;
    private ArrayList<AcademicBodyDTO> expectedList;

    
    public AcademicBodyDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        ACADEMICBODY_DAO = new AcademicBodyDAO();
        expectedList = new ArrayList<>();
        expectedList.add(new AcademicBodyDTO("Cuerpo1", "Ejemplo2", 1));
        expectedList.add(new AcademicBodyDTO("Cuerpo2", "Ejemplo", 2));
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllAcademicBody method, of class AcademicBodyDAO.
     */
    @Test
    public void testGetAllAcademicBody() throws Exception {
        System.out.println("getAllAcademicBody");
        List<AcademicBodyDTO> resultList = ACADEMICBODY_DAO.getAllAcademicBody();
        assertIterableEquals(expectedList, resultList);
    }
    
    
    
    
}
