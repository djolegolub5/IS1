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
@Table(name = "narudzbina")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Narudzbina.findAll", query = "SELECT n FROM Narudzbina n"),
    @NamedQuery(name = "Narudzbina.findByIdNar", query = "SELECT n FROM Narudzbina n WHERE n.idNar = :idNar"),
    @NamedQuery(name = "Narudzbina.findByIznos", query = "SELECT n FROM Narudzbina n WHERE n.iznos = :iznos"),
    @NamedQuery(name = "Narudzbina.findByDatumVreme", query = "SELECT n FROM Narudzbina n WHERE n.datumVreme = :datumVreme"),
    @NamedQuery(name = "Narudzbina.findByAdresa", query = "SELECT n FROM Narudzbina n WHERE n.adresa = :adresa"),
    @NamedQuery(name = "Narudzbina.findByIdGrad", query = "SELECT n FROM Narudzbina n WHERE n.idGrad = :idGrad")})
public class Narudzbina implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdNar")
    private Integer idNar;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Iznos")
    private double iznos;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "DatumVreme")
    private String datumVreme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Adresa")
    private String adresa;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdGrad")
    private int idGrad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNar")
    private List<Stavka> stavkaList;
    @JoinColumn(name = "IdKor", referencedColumnName = "IdKor")
    @ManyToOne(optional = false)
    private Korisnik idKor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idNar")
    private List<Transakcija> transakcijaList;

    public Narudzbina() {
    }

    public Narudzbina(Integer idNar) {
        this.idNar = idNar;
    }

    public Narudzbina(Integer idNar, double iznos, String datumVreme, String adresa, int idGrad) {
        this.idNar = idNar;
        this.iznos = iznos;
        this.datumVreme = datumVreme;
        this.adresa = adresa;
        this.idGrad = idGrad;
    }

    public Integer getIdNar() {
        return idNar;
    }

    public void setIdNar(Integer idNar) {
        this.idNar = idNar;
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

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getIdGrad() {
        return idGrad;
    }

    public void setIdGrad(int idGrad) {
        this.idGrad = idGrad;
    }

    @XmlTransient
    public List<Stavka> getStavkaList() {
        return stavkaList;
    }

    public void setStavkaList(List<Stavka> stavkaList) {
        this.stavkaList = stavkaList;
    }

    public Korisnik getIdKor() {
        return idKor;
    }

    public void setIdKor(Korisnik idKor) {
        this.idKor = idKor;
    }

    @XmlTransient
    public List<Transakcija> getTransakcijaList() {
        return transakcijaList;
    }

    public void setTransakcijaList(List<Transakcija> transakcijaList) {
        this.transakcijaList = transakcijaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNar != null ? idNar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Narudzbina)) {
            return false;
        }
        Narudzbina other = (Narudzbina) object;
        if ((this.idNar == null && other.idNar != null) || (this.idNar != null && !this.idNar.equals(other.idNar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Narudzbina[idNar=" + idNar + "]:::[(iznos="+iznos+"), (vreme="+datumVreme+"), (adresa="+adresa+"), (idGrad="+idGrad+"), (korisnik="+idKor+")]";
    }
    
}
