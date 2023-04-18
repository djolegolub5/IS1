/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author DJOLE
 */
@Embeddable
public class ProdajePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IdKor")
    private int idKor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdArt")
    private int idArt;

    public ProdajePK() {
    }

    public ProdajePK(int idKor, int idArt) {
        this.idKor = idKor;
        this.idArt = idArt;
    }

    public int getIdKor() {
        return idKor;
    }

    public void setIdKor(int idKor) {
        this.idKor = idKor;
    }

    public int getIdArt() {
        return idArt;
    }

    public void setIdArt(int idArt) {
        this.idArt = idArt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idKor;
        hash += (int) idArt;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdajePK)) {
            return false;
        }
        ProdajePK other = (ProdajePK) object;
        if (this.idKor != other.idKor) {
            return false;
        }
        if (this.idArt != other.idArt) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProdajePK[ idKor=" + idKor + ", idArt=" + idArt + " ]";
    }
    
}
