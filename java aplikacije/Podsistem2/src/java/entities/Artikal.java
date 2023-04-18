/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DJOLE
 */
@Entity
@Table(name = "artikal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artikal.findAll", query = "SELECT a FROM Artikal a"),
    @NamedQuery(name = "Artikal.findByIdArt", query = "SELECT a FROM Artikal a WHERE a.idArt = :idArt"),
    @NamedQuery(name = "Artikal.findByNaziv", query = "SELECT a FROM Artikal a WHERE a.naziv = :naziv"),
    @NamedQuery(name = "Artikal.findByOpis", query = "SELECT a FROM Artikal a WHERE a.opis = :opis"),
    @NamedQuery(name = "Artikal.findByCena", query = "SELECT a FROM Artikal a WHERE a.cena = :cena"),
    @NamedQuery(name = "Artikal.findByPopust", query = "SELECT a FROM Artikal a WHERE a.popust = :popust")})
public class Artikal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdArt")
    private Integer idArt;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Naziv")
    private String naziv;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Opis")
    private String opis;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Cena")
    private double cena;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Popust")
    private double popust;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artikal")
    private List<Korpa> korpaList;
    @JoinColumn(name = "IdKat", referencedColumnName = "IdKat")
    @ManyToOne(optional = false)
    private Kategorija idKat;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artikal")
    private List<Prodaje> prodajeList;

    public Artikal() {
    }

    public Artikal(Integer idArt) {
        this.idArt = idArt;
    }

    public Artikal(Integer idArt, String naziv, String opis, double cena, double popust) {
        this.idArt = idArt;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.popust = popust;
    }

    public Integer getIdArt() {
        return idArt;
    }

    public void setIdArt(Integer idArt) {
        this.idArt = idArt;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    @XmlTransient
    public List<Korpa> getKorpaList() {
        return korpaList;
    }

    public void setKorpaList(List<Korpa> korpaList) {
        this.korpaList = korpaList;
    }

    public Kategorija getIdKat() {
        return idKat;
    }

    public void setIdKat(Kategorija idKat) {
        this.idKat = idKat;
    }

    @XmlTransient
    public List<Prodaje> getProdajeList() {
        return prodajeList;
    }

    public void setProdajeList(List<Prodaje> prodajeList) {
        this.prodajeList = prodajeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArt != null ? idArt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artikal)) {
            return false;
        }
        Artikal other = (Artikal) object;
        if ((this.idArt == null && other.idArt != null) || (this.idArt != null && !this.idArt.equals(other.idArt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Artikal[idArt=" + idArt+"]" + ":::[(naziv="+naziv+"), (opis="+opis+"), (cena="+cena+"), (popust="+popust+"%), (kategorija="+idKat+")]";
    }
    
}
