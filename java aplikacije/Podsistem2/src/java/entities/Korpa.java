/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DJOLE
 */
@Entity
@Table(name = "korpa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korpa.findAll", query = "SELECT k FROM Korpa k"),
    @NamedQuery(name = "Korpa.findByIdKor", query = "SELECT k FROM Korpa k WHERE k.korpaPK.idKor = :idKor"),
    @NamedQuery(name = "Korpa.findByIdArt", query = "SELECT k FROM Korpa k WHERE k.korpaPK.idArt = :idArt"),
    @NamedQuery(name = "Korpa.findByKolicina", query = "SELECT k FROM Korpa k WHERE k.kolicina = :kolicina")})
public class Korpa implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KorpaPK korpaPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Kolicina")
    private int kolicina;
    @JoinColumn(name = "IdArt", referencedColumnName = "IdArt", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Artikal artikal;
    @JoinColumn(name = "IdKor", referencedColumnName = "IdKor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Korisnik korisnik;

    public Korpa() {
    }

    public Korpa(KorpaPK korpaPK) {
        this.korpaPK = korpaPK;
    }

    public Korpa(KorpaPK korpaPK, int kolicina) {
        this.korpaPK = korpaPK;
        this.kolicina = kolicina;
    }

    public Korpa(int idKor, int idArt) {
        this.korpaPK = new KorpaPK(idKor, idArt);
    }

    public KorpaPK getKorpaPK() {
        return korpaPK;
    }

    public void setKorpaPK(KorpaPK korpaPK) {
        this.korpaPK = korpaPK;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (korpaPK != null ? korpaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korpa)) {
            return false;
        }
        Korpa other = (Korpa) object;
        if ((this.korpaPK == null && other.korpaPK != null) || (this.korpaPK != null && !this.korpaPK.equals(other.korpaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Korpa[ korpaPK=" + korpaPK + " ]";
    }
    
}
