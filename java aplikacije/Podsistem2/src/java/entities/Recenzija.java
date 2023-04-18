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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DJOLE
 */
@Entity
@Table(name = "recenzija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recenzija.findAll", query = "SELECT r FROM Recenzija r"),
    @NamedQuery(name = "Recenzija.findByIdRec", query = "SELECT r FROM Recenzija r WHERE r.idRec = :idRec"),
    @NamedQuery(name = "Recenzija.findByOcena", query = "SELECT r FROM Recenzija r WHERE r.ocena = :ocena"),
    @NamedQuery(name = "Recenzija.findByOpis", query = "SELECT r FROM Recenzija r WHERE r.opis = :opis")})
public class Recenzija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdRec")
    private Integer idRec;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Ocena")
    private int ocena;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Opis")
    private String opis;
    @JoinColumns({
        @JoinColumn(name = "IdKor", referencedColumnName = "IdKor"),
        @JoinColumn(name = "IdArt", referencedColumnName = "IdArt")})
    @ManyToOne(optional = false)
    private Prodaje prodaje;

    public Recenzija() {
    }

    public Recenzija(Integer idRec) {
        this.idRec = idRec;
    }

    public Recenzija(Integer idRec, int ocena, String opis) {
        this.idRec = idRec;
        this.ocena = ocena;
        this.opis = opis;
    }

    public Integer getIdRec() {
        return idRec;
    }

    public void setIdRec(Integer idRec) {
        this.idRec = idRec;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Prodaje getProdaje() {
        return prodaje;
    }

    public void setProdaje(Prodaje prodaje) {
        this.prodaje = prodaje;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRec != null ? idRec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recenzija)) {
            return false;
        }
        Recenzija other = (Recenzija) object;
        if ((this.idRec == null && other.idRec != null) || (this.idRec != null && !this.idRec.equals(other.idRec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Recenzija[ idRec=" + idRec + " ]";
    }
    
}
