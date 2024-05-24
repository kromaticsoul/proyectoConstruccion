/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.objects;

import java.util.Objects;

/**
 *
 * @author marte
 */
public class AcademicBodyDTO {
    
    private String nameAcademicBody;
    private String description;
    private int idAcademicBody;

    public AcademicBodyDTO() {
    }

    public AcademicBodyDTO(String nameAcademicBody, String description, int idAcademicBody) {
        this.nameAcademicBody = nameAcademicBody;
        this.description = description;
        this.idAcademicBody = idAcademicBody;
    }
    
    

    public String getNameAcademicBody() {
        return nameAcademicBody;
    }

    public void setNameAcademicBody(String nameAcademicBody) {
        this.nameAcademicBody = nameAcademicBody;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdAcademicBody() {
        return idAcademicBody;
    }

    public void setIdAcademicBody(int idAcademicBody) {
        this.idAcademicBody = idAcademicBody;
    }
    
    
    
    @Override
    public String toString() {
    return this.nameAcademicBody; //para poder usar un string al mostrar el nombre;
    }
    
       @Override
    public boolean equals(Object obj) {     //método para poder realizar correctamente el test;
        if (obj instanceof AcademicBodyDTO) {
            AcademicBodyDTO other = (AcademicBodyDTO) obj;
            return Objects.equals(this.nameAcademicBody, other.nameAcademicBody)
                && Objects.equals(this.description, other.description)
                && this.idAcademicBody == other.idAcademicBody;
        }
        return false;
    }
    
}
