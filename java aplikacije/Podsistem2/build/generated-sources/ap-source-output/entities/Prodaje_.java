package entities;

import entities.Artikal;
import entities.Korisnik;
import entities.ProdajePK;
import entities.Recenzija;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-16T23:01:06")
@StaticMetamodel(Prodaje.class)
public class Prodaje_ { 

    public static volatile ListAttribute<Prodaje, Recenzija> recenzijaList;
    public static volatile SingularAttribute<Prodaje, ProdajePK> prodajePK;
    public static volatile SingularAttribute<Prodaje, Artikal> artikal;
    public static volatile SingularAttribute<Prodaje, Korisnik> korisnik;

}