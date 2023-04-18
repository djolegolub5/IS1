package entities;

import entities.Narudzbina;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2023-02-16T23:01:12")
@StaticMetamodel(Transakcija.class)
public class Transakcija_ { 

    public static volatile SingularAttribute<Transakcija, Double> iznos;
    public static volatile SingularAttribute<Transakcija, Narudzbina> idNar;
    public static volatile SingularAttribute<Transakcija, Integer> idTran;
    public static volatile SingularAttribute<Transakcija, String> datumVreme;

}