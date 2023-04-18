/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DJOLE
 */
@Entity
@Table(name = "prodaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prodaje.findAll", query = "SELECT p FROM Prodaje p"),
    @NamedQuery(name = "Prodaje.findByIdKor", query = "SELECT p FROM Prodaje p WHERE p.prodajePK.idKor = :idKor"),
    @NamedQuery(name = "Prodaje.findByIdArt", query = "SELECT p FROM Prodaje p WHERE p.prodajePK.idArt = :idArt")})
public class Prodaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProdajePK prodajePK;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "prodaje")
    private List<Recenzija> recenzijaList;
    @JoinColumn(name = "IdArt", referencedColumnName = "IdArt", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Artikal artikal;
    @JoinColumn(name = "IdKor", referencedColumnName = "IdKor", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Korisnik korisnik;

    public Prodaje() {
    }

    public Prodaje(ProdajePK prodajePK) {
        this.prodajePK = prodajePK;
    }

    public Prodaje(int idKor, int idArt) {
        this.prodajePK = new ProdajePK(idKor, idArt);
    }

    public ProdajePK getProdajePK() {
        return prodajePK;
    }

    public void setProdajePK(ProdajePK prodajePK) {
        this.prodajePK = prodajePK;
    }

    @XmlTransient
    public List<Recenzija> getRecenzijaList() {
        return recenzijaList;
    }

    public void setRecenzijaList(List<Recenzija> recenzijaList) {
        this.recenzijaList = recenzijaList;
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
        hash += (prodajePK != null ? prodajePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prodaje)) {
            return false;
        }
        Prodaje other = (Prodaje) object;
        if ((this.prodajePK == null && other.prodajePK != null) || (this.prodajePK != null && !this.prodajePK.equals(other.prodajePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Prodaje[ prodajePK=" + prodajePK + " ]";
    }
    
}
