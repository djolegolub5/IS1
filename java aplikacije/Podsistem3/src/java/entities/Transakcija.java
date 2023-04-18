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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DJOLE
 */
@Entity
@Table(name = "transakcija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Transakcija.findAll", query = "SELECT t FROM Transakcija t"),
    @NamedQuery(name = "Transakcija.findByIdTran", query = "SELECT t FROM Transakcija t WHERE t.idTran = :idTran"),
    @NamedQuery(name = "Transakcija.findByIznos", query = "SELECT t FROM Transakcija t WHERE t.iznos = :iznos"),
    @NamedQuery(name = "Transakcija.findByDatumVreme", query = "SELECT t FROM Transakcija t WHERE t.datumVreme = :datumVreme")})
public class Transakcija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdTran")
    private Integer idTran;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Iznos")
    private double iznos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DatumVreme")
    private String datumVreme;
    @JoinColumn(name = "IdNar", referencedColumnName = "IdNar")
    @ManyToOne(optional = false)
    private Narudzbina idNar;

    public Transakcija() {
    }

    public Transakcija(Integer idTran) {
        this.idTran = idTran;
    }

    public Transakcija(Integer idTran, double iznos, String datumVreme) {
        this.idTran = idTran;
        this.iznos = iznos;
        this.datumVreme = datumVreme;
    }

    public Integer getIdTran() {
        return idTran;
    }

    public void setIdTran(Integer idTran) {
        this.idTran = idTran;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public String getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(String datumVreme) {
        this.datumVreme = datumVreme;
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
        hash += (idTran != null ? idTran.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Transakcija)) {
            return false;
        }
        Transakcija other = (Transakcija) object;
        if ((this.idTran == null && other.idTran != null) || (this.idTran != null && !this.idTran.equals(other.idTran))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Transakcija[idTran=" + idTran + "]:::[(iznos="+iznos+"), (vreme="+datumVreme+"), (narudzbina="+idNar+")]";
    }
    
}
