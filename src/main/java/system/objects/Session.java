/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.objects;

/**
 *
 * @author marte
 */
public class Session {
    
    private static String userEnrollment;
    
    public Session(){
        
    }

    public static String getUserEnrollment() {
        return userEnrollment;
    }

    public static void setUserEnrollment(String userEnrollment) {
        Session.userEnrollment = userEnrollment;
    }
    
    
}
