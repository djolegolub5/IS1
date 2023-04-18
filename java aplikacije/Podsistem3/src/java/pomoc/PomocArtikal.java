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
public class PomocArtikal implements Serializable{
 
    
    
        private String naziv;
    private String opis;
        private double cena;
            private double popust;
    private String idKat;
    private int id;
    private int kategorija;

    public PomocArtikal(String naziv, String opis, double cena, double popust, String idKat) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.popust = popust;
        this.idKat = idKat;
    }

    public int getKategorija() {
        return kategorija;
    }

    public void setKategorija(int kategorija) {
        this.kategorija = kategorija;
    }

    
    
    
    public PomocArtikal(int id,double cena, boolean staJe) {
        if (staJe) this.cena = cena;
        else this.popust=cena;
        this.id=id;
    }

    public int getId(){
        return id;
    }
    
    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public double getCena() {
        return cena;
    }

    public double getPopust() {
        return popust;
    }

    public String getIdKat() {
        return idKat;
    }

    
    
    
    
}
