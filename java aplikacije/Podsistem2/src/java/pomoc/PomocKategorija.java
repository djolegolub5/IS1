/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomoc;

import java.io.Serializable;

/**
 *
 * @author DJOLE
 */
public class PomocKategorija implements Serializable{
    private String nadKat;
    private String naziv;
  
    
    public PomocKategorija(String n, String nK){
        nadKat=nK;
        naziv=n;
    }

    public String getNadKat() {
        return nadKat;
    }

    public String getNaziv() {
        return naziv;
    }
    
    
    
    
}
