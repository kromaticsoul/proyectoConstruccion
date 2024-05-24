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
public class UserDTO {
    
    private String correoUsuario;
    private String nombre; 
    private String apellidoPaterno;
    private String apellidoMaterno;
    
    private final String PASSWORD_REGEX = "^.{8,100}$";
    private final String EMAIL_REGEX = "^(?=.{1,256}$)[^\\s@]+@(?:uv\\.mx|estudiantes\\.uv\\.mx|gmail\\.com|hotmail\\.com|outlook\\.com|edu\\.mx)$";
    private final String NAME_REGEX = "^(?!.*[\\!\\#\\$%\\&'\\(\\)\\*\\+\\-\\.,\\/\\:\\;<\\=\\>\\?\\@\\[\\\\\\]\\^_`\\{\\|\\}\\~])(?!.*  )(?!^ $)(?!.*\\d)^.{3,300}$";

    public UserDTO(String correoUsuario, String nombre, String apellidoPaterno, String apellidoMaterno) {
        this.correoUsuario = correoUsuario;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
    }

    public UserDTO() {
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setCorreoUsuario(String correoUsuario) {
        checkEmail(correoUsuario);
        this.correoUsuario = correoUsuario;
    }

    public void setNombre(String nombre) {
        checkName(nombre);
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        checkName(apellidoPaterno);
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        checkName(apellidoPaterno);
        this.apellidoMaterno = apellidoMaterno;
    }
    
    private void checkName(String name) {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("El nombre debe tener las siguientes características:\n"
                    + "1.- Debe contener de 3 a 300 caractéres como máximo\n"
                    + "2.- No puede contener más de 2 espacios en blanco juntos\n"
                    + "3.- No puede tener solo espacios en blanco\n"
                    + "4.- No debe contener los siguientes símbolos: (!, \", #, $, %, &, ', (, ), *, +, ,, -, ., /, :, ;, <, =, >, ?, @, [, \\, ], ^, _, `, {, |, }, ~)\n");
        }
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
    
    private void checkPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("La contraseña debe contener las siguientes características:\n"
                    + "1.- Debe contener de 8 a 100 caractéres como máximo\n");
        }
    }
}
