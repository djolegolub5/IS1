package entities;

import entities.Kategorija;
import entities.Korpa;
import entities.Prodaje;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-16T23:01:06")
@StaticMetamodel(Artikal.class)
public class Artikal_ { 

    public static volatile ListAttribute<Artikal, Korpa> korpaList;
    public static volatile SingularAttribute<Artikal, Kategorija> idKat;
    public static volatile ListAttribute<Artikal, Prodaje> prodajeList;
    public static volatile SingularAttribute<Artikal, String> naziv;
    public static volatile SingularAttribute<Artikal, Double> popust;
    public static volatile SingularAttribute<Artikal, Double> cena;
    public static volatile SingularAttribute<Artikal, Integer> idArt;
    public static volatile SingularAttribute<Artikal, String> opis;

}