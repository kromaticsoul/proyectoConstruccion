/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logic;

import java.util.Date;


/**
 *
 * @author miriam
 */
public class AdvanceDTO {
    
    private int idAdvance;
    private Date dateDelivery;
    private String description;
    private String title;

    public AdvanceDTO(int idAdvance, Date dateDelivary, String description, String title) {
        this.idAdvance = idAdvance;
        this.dateDelivery = dateDelivary;
        this.description = description;
        this.title = title;
    }

    public AdvanceDTO(){
        
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    

    public int getIdAdvance() {
        return idAdvance;
    }

    public void setIdAdvance(int idAdvance) {
        this.idAdvance = idAdvance;
    }

    public Date getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(Date dateDelivary) {
        this.dateDelivery = dateDelivary;
    }
    
    


}
