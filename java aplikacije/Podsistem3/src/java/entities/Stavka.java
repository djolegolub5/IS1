/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "stavka")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stavka.findAll", query = "SELECT s FROM Stavka s"),
    @NamedQuery(name = "Stavka.findByIdSta", query = "SELECT s FROM Stavka s WHERE s.idSta = :idSta"),
    @NamedQuery(name = "Stavka.findByKolicina", query = "SELECT s FROM Stavka s WHERE s.kolicina = :kolicina"),
    @NamedQuery(name = "Stavka.findByIznos", query = "SELECT s FROM Stavka s WHERE s.iznos = :iznos")})
public class Stavka implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdSta")
    private Integer idSta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Kolicina")
    private int kolicina;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Iznos")
    private double iznos;
    @JoinColumn(name = "IdArt", referencedColumnName = "IdArt")
    @ManyToOne(optional = false)
    private Artikal idArt;
    @JoinColumn(name = "IdNar", referencedColumnName = "IdNar")
    @ManyToOne(optional = false)
    private Narudzbina idNar;

    public Stavka() {
    }

    public Stavka(Integer idSta) {
        this.idSta = idSta;
    }

    public Stavka(Integer idSta, int kolicina, double iznos) {
        this.idSta = idSta;
        this.kolicina = kolicina;
        this.iznos = iznos;
    }

    public Integer getIdSta() {
        return idSta;
    }

    public void setIdSta(Integer idSta) {
        this.idSta = idSta;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Artikal getIdArt() {
        return idArt;
    }

    public void setIdArt(Artikal idArt) {
        this.idArt = idArt;
    }

    public Narudzbina getIdNar() {
        return idNar;
    }

    public void setIdNar(Narudzbina idNar) {
        this.idNar = idNar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSta != null ? idSta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stavka)) {
            return false;
        }
        Stavka other = (Stavka) object;
        if ((this.idSta == null && other.idSta != null) || (this.idSta != null && !this.idSta.equals(other.idSta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Stavka[ idSta=" + idSta + " ]";
    }
    
}
