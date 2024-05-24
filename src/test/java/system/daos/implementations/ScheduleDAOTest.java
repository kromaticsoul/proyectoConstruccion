/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package system.daos.implementations;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import system.objects.ScheduleDTO;

/**
 *
 * @author marte
 */
public class ScheduleDAOTest {
    
    private static ScheduleDAO SCHEDULE_DAO;
    private ArrayList<ScheduleDTO> expectedList;
    
    public ScheduleDAOTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        SCHEDULE_DAO = new ScheduleDAO();
        expectedList = new ArrayList<>();
        
        String creationDateString = "2023-05-08 00:00:00";
        Timestamp timestamp = Timestamp.valueOf(creationDateString);
        Date creationDate = new Date(timestamp.getTime());
        
        String limitDeliverDate = "2023-05-15 00:00:00";
        timestamp = Timestamp.valueOf(limitDeliverDate);
        Date limitDate = new Date(timestamp.getTime());
 
        expectedList.add(new ScheduleDTO("prueba","r",creationDate,limitDate,"s21013903"));
        expectedList.add(new ScheduleDTO("prueba 4","n/a",creationDate,limitDate,"s21013903"));
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllEventsOfStudents method, of class ScheduleDAO.
     */
    @Test
    public void testGetAllEventsOfStudents() throws Exception {
        System.out.println("getAllEventsOfStudents");
        String enrollment = "s21013903";
        List<ScheduleDTO> result = SCHEDULE_DAO.getFirstTwoEventsOfStudents(enrollment);
        
        List<String> expectedTitles = expectedList.stream()
            .map(ScheduleDTO::getTitle)
            .collect(Collectors.toList());
    List<String> actualTitles = result.stream()
            .map(ScheduleDTO::getTitle)
            .collect(Collectors.toList());
    
    assertIterableEquals(expectedTitles, actualTitles);
    }
    
}
