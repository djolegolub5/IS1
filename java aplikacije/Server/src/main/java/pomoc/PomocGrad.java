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
public class PomocGrad implements Serializable {

    private String naziv;

    public String getNaziv() {
        return naziv;
    }
    
    
    
    
    public PomocGrad(String n) {
        naziv = n;
    }

}
