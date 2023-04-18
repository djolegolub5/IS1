package entities;

import entities.Prodaje;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-16T23:01:06")
@StaticMetamodel(Recenzija.class)
public class Recenzija_ { 

    public static volatile SingularAttribute<Recenzija, Integer> idRec;
    public static volatile SingularAttribute<Recenzija, Prodaje> prodaje;
    public static volatile SingularAttribute<Recenzija, Integer> ocena;
    public static volatile SingularAttribute<Recenzija, String> opis;

}