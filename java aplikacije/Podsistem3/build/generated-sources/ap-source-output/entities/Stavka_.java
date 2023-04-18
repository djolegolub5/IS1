package entities;

import entities.Artikal;
import entities.Narudzbina;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-16T23:01:12")
@StaticMetamodel(Stavka.class)
public class Stavka_ { 

    public static volatile SingularAttribute<Stavka, Double> iznos;
    public static volatile SingularAttribute<Stavka, Narudzbina> idNar;
    public static volatile SingularAttribute<Stavka, Integer> idSta;
    public static volatile SingularAttribute<Stavka, Integer> kolicina;
    public static volatile SingularAttribute<Stavka, Artikal> idArt;

}