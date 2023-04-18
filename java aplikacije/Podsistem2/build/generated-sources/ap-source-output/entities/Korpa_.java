package entities;

import entities.Artikal;
import entities.Korisnik;
import entities.KorpaPK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-16T23:01:06")
@StaticMetamodel(Korpa.class)
public class Korpa_ { 

    public static volatile SingularAttribute<Korpa, KorpaPK> korpaPK;
    public static volatile SingularAttribute<Korpa, Artikal> artikal;
    public static volatile SingularAttribute<Korpa, Integer> kolicina;
    public static volatile SingularAttribute<Korpa, Korisnik> korisnik;

}