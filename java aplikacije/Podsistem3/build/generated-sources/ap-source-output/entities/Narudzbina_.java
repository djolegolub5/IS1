package entities;

import entities.Korisnik;
import entities.Stavka;
import entities.Transakcija;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-16T23:01:12")
@StaticMetamodel(Narudzbina.class)
public class Narudzbina_ { 

    public static volatile SingularAttribute<Narudzbina, Double> iznos;
    public static volatile SingularAttribute<Narudzbina, Integer> idNar;
    public static volatile SingularAttribute<Narudzbina, String> datumVreme;
    public static volatile SingularAttribute<Narudzbina, Korisnik> idKor;
    public static volatile SingularAttribute<Narudzbina, String> adresa;
    public static volatile SingularAttribute<Narudzbina, Integer> idGrad;
    public static volatile ListAttribute<Narudzbina, Stavka> stavkaList;
    public static volatile ListAttribute<Narudzbina, Transakcija> transakcijaList;

}