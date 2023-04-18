package entities;

import entities.Korpa;
import entities.Prodaje;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-16T23:01:06")
@StaticMetamodel(Korisnik.class)
public class Korisnik_ { 

    public static volatile SingularAttribute<Korisnik, String> ime;
    public static volatile SingularAttribute<Korisnik, String> prezime;
    public static volatile ListAttribute<Korisnik, Korpa> korpaList;
    public static volatile SingularAttribute<Korisnik, Integer> idKor;
    public static volatile SingularAttribute<Korisnik, Double> novac;
    public static volatile ListAttribute<Korisnik, Prodaje> prodajeList;
    public static volatile SingularAttribute<Korisnik, String> adresa;
    public static volatile SingularAttribute<Korisnik, Integer> idGrad;
    public static volatile SingularAttribute<Korisnik, String> sifra;
    public static volatile SingularAttribute<Korisnik, String> korisnickoIme;

}