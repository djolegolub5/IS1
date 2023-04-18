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
public class PomocKorisnik implements Serializable{
    
    private String korIme;
    private String sifra;
    private String ime;
    private String prezime;
    private String adresa;
    private double novac;
    private String grad;
    private int idGrad;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
    public PomocKorisnik(String korIme, String sifra, String ime, String prezime, String adresa, double novac, String grad) {
        this.korIme = korIme;
        this.sifra = sifra;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.novac = novac;
        this.grad = grad;
    }
    

    public PomocKorisnik(String korIme, double novac) {
        this.korIme=korIme;
        this.novac=novac;
    }
    
    public PomocKorisnik(String korIme, String adresa, String grad) {
        this.korIme=korIme;
        this.adresa=adresa;
        this.grad=grad;
    }
    
    public PomocKorisnik(String korIme, String sifra){
        this.korIme=korIme;
        this.sifra=sifra;
    }

    public PomocKorisnik(String korIme, String adresa, int grad) {
        this.korIme=korIme;
        this.adresa=adresa;
        this.idGrad=grad;
    }
    
    public String getKorIme() {
        return korIme;
    }

    public String getSifra() {
        return sifra;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public double getNovac() {
        return novac;
    }

    public String getIdGrad() {
        return grad;
    }

    public void setGrad(Integer idGrad) {
        this.idGrad=idGrad;
    }
    public int getGrad(){
        return idGrad;
    }
   
    public void setNovac(double n){
        novac=n;
    }
    
    
    
    
}
