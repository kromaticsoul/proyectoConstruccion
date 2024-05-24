/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.objects;

/**
 *
 * @author Naomi
 */
public class StudentDTO {
    private String registrationNumber;
    private String semester; 
    private String email; 

    public StudentDTO(String registrationNumber, String semester, String email) {
        this.registrationNumber = registrationNumber;
        this.semester = semester;
        this.email = email;
    }

    public StudentDTO() {
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
