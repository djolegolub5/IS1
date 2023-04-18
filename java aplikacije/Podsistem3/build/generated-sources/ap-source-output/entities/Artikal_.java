package entities;

import entities.Stavka;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-16T23:01:12")
@StaticMetamodel(Artikal.class)
public class Artikal_ { 

    public static volatile SingularAttribute<Artikal, Integer> idKat;
    public static volatile SingularAttribute<Artikal, String> naziv;
    public static volatile ListAttribute<Artikal, Stavka> stavkaList;
    public static volatile SingularAttribute<Artikal, Double> popust;
    public static volatile SingularAttribute<Artikal, Double> cena;
    public static volatile SingularAttribute<Artikal, Integer> idArt;
    public static volatile SingularAttribute<Artikal, String> opis;

}