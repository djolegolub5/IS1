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
public class PomocKorpa implements Serializable{
    
    private String korisnik;
    private int idArt;
    private int kolicina;

    public PomocKorpa(String korisnik, int idArt, int kolicina) {
        this.korisnik = korisnik;
        this.idArt = idArt;
        this.kolicina = kolicina;
    }

    
    
    public PomocKorpa(int idArt, int kolicina) {
       
        this.idArt=idArt;
        this.kolicina=kolicina;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public int getIdArt() {
        return idArt;
    }

    public int getKolicina() {
        return kolicina;
    }
    
    
    
    
}
