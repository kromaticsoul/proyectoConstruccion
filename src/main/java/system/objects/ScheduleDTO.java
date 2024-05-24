/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.objects;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author marte
 */
public class ScheduleDTO {
    
    private String title;
    private String description;
    private Date creationDate;
    private Date limitDate;
    private String studentEnrollment;

    public ScheduleDTO() {
    }

    public ScheduleDTO(String title, String description, Date creationDate, Date limitDate, String studentEnrollment) {
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        this.limitDate = limitDate;
        this.studentEnrollment = studentEnrollment;
    }

    
    
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(Date limitDate) {
        this.limitDate = limitDate;
    }

    public String getStudentEnrollment() {
        return studentEnrollment;
    }

    public void setStudentEnrollment(String studentEnrollment) {
        this.studentEnrollment = studentEnrollment;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ScheduleDTO other = (ScheduleDTO) obj;
        return Objects.equals(title, other.title)
            && Objects.equals(description, other.description)
            && Objects.equals(creationDate, other.creationDate)
            && Objects.equals(limitDate, other.limitDate)
            && Objects.equals(studentEnrollment, other.studentEnrollment);
    }

   
}
