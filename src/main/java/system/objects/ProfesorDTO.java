/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.objects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author marte
 */
public class ProfesorDTO{
    
    private static ProfesorDTO teacher;    
    private int numeroPersonalProfesor;
    private String cubiculo;
    private String correoUsuario;
    
    private final String EMAIL_REGEX = "^(?=.{1,256}$)[^\\s@]+@(?:uv\\.mx|estudiantes\\.uv\\.mx|gmail\\.com|hotmail\\.com|outlook\\.com|edu\\.mx)$";

    public static ProfesorDTO getSession() {
        if (teacher == null) {
            teacher = new ProfesorDTO();
            teacher.setNumeroPersonalProfesor(-1);
        }
        return teacher;
    }

    public static ProfesorDTO cleanSession() {
        getSession();
        teacher.setNumeroPersonalProfesor(-1);
        teacher.setCorreoUsuario("anónimo@uv.mx");
        teacher.setCubiculo("sin_cubiculo");
        return teacher;
    }

    public static ProfesorDTO getNewInstance() {
        return new ProfesorDTO();
    }
    
    public ProfesorDTO(int numeroPersonalProfesor, String cubiculo, String correoUsuario) {
        this.numeroPersonalProfesor = numeroPersonalProfesor;
        this.cubiculo = cubiculo;
        this.correoUsuario = correoUsuario;
    }

    public ProfesorDTO() {
    }

    
    public int getNumeroPersonalProfesor() {
        return numeroPersonalProfesor;
    }

    public String getCubiculo() {
        return cubiculo;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setNumeroPersonalProfesor(int numeroPersonalProfesor) {
        checkBlankSpaces(String.valueOf(numeroPersonalProfesor));
        this.numeroPersonalProfesor = numeroPersonalProfesor;
    }

    public void setCubiculo(String cubiculo) {
        checkBlankSpaces(cubiculo);
        this.cubiculo = cubiculo;
    }

    public void setCorreoUsuario(String correoUsuario) {
        checkEmail(correoUsuario);
        this.correoUsuario = correoUsuario;
    }

    private void checkEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("El email debe contener las siguientes características:\n"
                    + "1.- No debe contener espacios en blanco\n"
                    + "2.- Solo los siguientes dominios son permitidos: (@uv.mx, @estudiantes.uv.mx, @gmail.com, @hotmail.com, @outlook.com, @edu.mx)\n");
        }
    }

    private void checkBlankSpaces(String string){
       if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("Hay algún espacio en blanco\n");
        } 
    }
}
