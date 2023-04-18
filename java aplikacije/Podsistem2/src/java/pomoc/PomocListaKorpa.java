/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pomoc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DJOLE
 */
public class PomocListaKorpa implements Serializable{
    
    ArrayList<PomocKorpa> lista=new ArrayList();
    
    public void dodajPK(PomocKorpa pk){
        lista.add(pk);
    }
    
    public ArrayList<PomocKorpa> lista(){
        return lista;
    }
    
    
}
