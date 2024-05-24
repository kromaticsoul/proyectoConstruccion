/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.objects;

/**
 *
 * @author marte
 */
public class MemberAcademicBodyDTO {
    
    private static MemberAcademicBodyDTO member;
    private int Id; 
    private String rol;
    private int numeroPersonalProfesor;
    private int claveCuerpoAcademico;
    
    public static MemberAcademicBodyDTO getSession(){
        if (member == null) {
            member = new MemberAcademicBodyDTO();
            member.setId(-1);
        }
        return member;
    }

    
    public static MemberAcademicBodyDTO CleanSession(){
        getSession();
        member.setId(-1);
        member.setRol("indefinido");
        member.setNumeroPersonalProfesor(-1);
        member.setClaveCuerpoAcademico(-1);
        return member;
    }

    public int getId() {
        return Id;
    }

    public void setId(int ID_Miembro) {
        this.Id = ID_Miembro;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getNumeroPersonalProfesor() {
        return numeroPersonalProfesor;
    }

    public void setNumeroPersonalProfesor(int numeroPersonalProfesor) {
        this.numeroPersonalProfesor = numeroPersonalProfesor;
    }

    public int getClaveCuerpoAcademico() {
        return claveCuerpoAcademico;
    }

    public void setClaveCuerpoAcademico(int claveCuerpoAcademico) {
        this.claveCuerpoAcademico = claveCuerpoAcademico;
    }
    
    
    
}
