/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.objects;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Naomi
 */
  
public class AccessAccountDTO {
    
    private String accessAccount;
    private String password;
    private String UserEmail; 
    
    private final String EMAIL_REGEX = "^(?=.{1,256}$)[^\\s@]+@(?:uv\\.mx|estudiantes\\.uv\\.mx|gmail\\.com|hotmail\\.com|outlook\\.com|edu\\.mx)$";
    private final String PASSWORD_REGEX = "^.{8,100}$";

    
    public static AccessAccountDTO cleanSession() {
    AccessAccountDTO accessAccountDTO = new AccessAccountDTO();
    accessAccountDTO.setUserEmail("anónimo@uv.mx");
    accessAccountDTO.setAccessAccount("anónimo@uv.mx");
    accessAccountDTO.setPassword("123456789"); 
    return accessAccountDTO;
    }
    
    public AccessAccountDTO(String accessAccount, String password, String UserEmail) {
        this.accessAccount = accessAccount;
        this.password = password;
        this.UserEmail = UserEmail;
    }

    public AccessAccountDTO() {
    }

    public String getAccessAccount() {
        return accessAccount;
    }

    public void setAccessAccount(String accessAccount) {
        this.accessAccount = accessAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        checkPassword(password);
        this.password = password;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String UserEmail) {
        checkEmail(UserEmail);
        this.UserEmail = UserEmail;
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
    
    @Override
    public boolean equals(Object obj) {   //método para las pruebas unitarias
        if (obj instanceof AccessAccountDTO) {
            AccessAccountDTO other = (AccessAccountDTO) obj;
            return Objects.equals(this.getUserEmail(), other.getUserEmail())
                    && Objects.equals(this.getPassword(), other.getPassword())
                    && Objects.equals(this.getAccessAccount(), other.getAccessAccount());
        }
        return false;
    }

}
