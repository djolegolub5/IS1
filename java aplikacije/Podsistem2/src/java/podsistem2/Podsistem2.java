/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem2;

import entities.Artikal;
import entities.Kategorija;
import entities.Korisnik;
import entities.Korpa;
import entities.KorpaPK;
import entities.Prodaje;
import entities.ProdajePK;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pomoc.PomocArtikal;
import pomoc.PomocKategorija;
import pomoc.PomocKorisnik;
import pomoc.PomocKorpa;
import pomoc.PomocListaKorpa;

/**
 *
 * @author DJOLE
 */
public class Podsistem2 extends Thread{

    /**
     * @param args the command line arguments
     */
    
    @Resource(lookup="jms/__defaultConnectionFactory")
    static ConnectionFactory connectionFactory;
    
    
    @Resource(lookup="ServerTopic")
    static Topic topic;
    
    @Resource(lookup="Podsistem2Queue")
    static Queue queue2;
    
    @Resource(lookup="Podsistem3Queue")
    static Queue queue3;
    
    

    EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem2");
    EntityManager em=emf.createEntityManager();
    
    
    
       @Override
    public void run() {
        
        JMSContext context=connectionFactory.createContext();
        JMSConsumer consumer=context.createConsumer(queue2);
        JMSProducer producer=context.createProducer();
        
        while(true){
            try {
                Message txt=consumer.receive();
                int id=txt.getIntProperty("id");
                if (id==5){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocKategorija pk=(PomocKategorija) obj.getObject();
                    String povratna=kreirajKategoriju(pk);
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                }
                else if (id==6){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocArtikal pa=(PomocArtikal) obj.getObject();
                    String text=obj.getStringProperty("korisnik");
                    String povratna=kreirajArtikal(pa,text);
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                }
                else if (id==7){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocArtikal pa=(PomocArtikal) obj.getObject();
                    String povratna=promeniCenu(pa);
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                }
                else if (id==8){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocArtikal pa=(PomocArtikal) obj.getObject();
                    String povratna=promeniPopust(pa);
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                }
                else if (id==9){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocKorpa pa=(PomocKorpa) obj.getObject();
                    String povratna=dodajUKorpu(pa);
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                }
                else if (id==10){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocKorpa pa=(PomocKorpa) obj.getObject();
                    String povratna=brisiIzKorpe(pa);
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                }
                else if (id==14){
                    TextMessage obj=(TextMessage)txt;
                    String povratna=dohvatiKategorije();
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                    
                    
                }
                else if (id==15){
                    TextMessage obj=(TextMessage)txt;
                    String povratna=dohvatiArtikle(obj.getText());
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                    
                    
                    
                }
                else if (id==16){
                    TextMessage obj=(TextMessage)txt;
                    String povratna=dohvatiSadrzajKorpe(obj.getText());
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                }
                else if (id==20){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocKorisnik pk=(PomocKorisnik) obj.getObject();
                    String povratna=kreirajKorisnika(pk);

                }
                
                else if (id==21){
                    
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocKorisnik pk=(PomocKorisnik) obj.getObject();
                    String povratna=dodajNovac(pk);
                    
                    
                    
                }
                
                else if (id==22){
                    
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocKorisnik pk=(PomocKorisnik) obj.getObject();
                    String povratna=promeniAdresu(pk);
                    
                    
                }
                else if (id==23){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocKorisnik pk=(PomocKorisnik) obj.getObject();
                    PomocListaKorpa plk=korpe(pk);
                    obj=context.createObjectMessage(plk);
                    obj.setIntProperty("id", 27);
                    producer.send(topic,obj);
                    
                }
                else if (id==24){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocKorisnik pk=(PomocKorisnik) obj.getObject();
                    String povratna=korpaKorisnik(pk);

                    
                    
                    
                }
                
                else if (id==25){
                    TextMessage obj=(TextMessage)txt;
                    String povratna=artikli(obj.getText());
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                }
                
                
                
            } catch (JMSException ex) {
                Logger.getLogger(Podsistem2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public static void main(String[] args) {
        Podsistem2 p2=new Podsistem2();
        p2.start();

    }

    private String kreirajKategoriju(PomocKategorija pk) {
        Kategorija kat=null;
        if (!pk.getNadKat().equals("null")){
            List<Kategorija> kategorije=em.createNamedQuery("Kategorija.findByNaziv").setParameter("naziv", pk.getNadKat()).getResultList();
            if (kategorije.size()!=1) return "Ne postoji nadkategorija sa tim nazivom.";
            kat=kategorije.get(0);
            
            
        }
        Kategorija kategorija=new Kategorija();
        if (kat!=null)kategorija.setIdKatNad(kat);
        kategorija.setNaziv(pk.getNaziv());
       
        em.getTransaction().begin();
        em.persist(kategorija);
        em.getTransaction().commit();
        
        return "Uspesno kreirana kategorija.";
    }

    private String kreirajArtikal(PomocArtikal pa, String text) throws JMSException {
        List<Kategorija> kategorije=em.createNamedQuery("Kategorija.findByNaziv").setParameter("naziv", pa.getIdKat()).getResultList();
        if (kategorije.size()!=1) return "Ne postoji nadkategorija sa tim nazivom.";
        List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", text).getResultList();
        Korisnik k=korisnici.get(0);

        Artikal artikal=new Artikal();
        artikal.setCena(pa.getCena());
        artikal.setIdKat(kategorije.get(0));
        artikal.setNaziv(pa.getNaziv());
        artikal.setOpis(pa.getOpis());
        artikal.setPopust(pa.getPopust());
        
        em.getTransaction().begin();
        em.persist(artikal);
        em.flush();
        em.getTransaction().commit();
        

        ProdajePK pk=new ProdajePK();
        pk.setIdArt(artikal.getIdArt());
        pk.setIdKor(k.getIdKor());
        
        Prodaje p=new Prodaje();
        p.setProdajePK(pk);
        p.setArtikal(artikal);
        p.setKorisnik(k);
        
        em.getTransaction().begin();
        em.persist(p);
        em.flush();
        em.getTransaction().commit();
        
        pa.setKategorija(kategorije.get(0).getIdKat());
        
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();

        ObjectMessage om=context.createObjectMessage(pa);
        
        om.setIntProperty("id", 23);
        producer.send(queue3, om);
        
        
        
        return "Uspesno kreiran artikal.";
        
        
    }

    private String promeniCenu(PomocArtikal pa) throws JMSException {
        Artikal a=em.find(Artikal.class, pa.getId());
        if (a==null) return "Ne postoji artikal sa zadatim id.";
        a.setCena(pa.getCena());
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();

        ObjectMessage om=context.createObjectMessage(pa);
        
        om.setIntProperty("id", 24);
        producer.send(queue3, om);
        
        return "Uspesno promenjena cena artiklu.";
        
    }
    
    
    private String promeniPopust(PomocArtikal pa) throws JMSException {
        Artikal a=em.find(Artikal.class, pa.getId());
        if (a==null) return "Ne postoji artikal sa zadatim id.";
        a.setPopust(pa.getPopust());
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
                JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();

        ObjectMessage om=context.createObjectMessage(pa);
        
        om.setIntProperty("id", 25);
        
        producer.send(queue3, om);
        return "Uspesno promenjen popust artiklu.";
        
    }

    private String dodajUKorpu(PomocKorpa pa) {
        List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", pa.getKorisnik()).getResultList();
        if (korisnici.size()!=1) return "Ne postoji korisnik sa tim korisnickim imenom.";
        List<Prodaje> prodaje=em.createNamedQuery("Prodaje.findByIdArt").setParameter("idArt", pa.getIdArt()).getResultList();
        if (prodaje.size()<=0) return "Ovaj artikal se ne prodaje.";
        Artikal artikal=prodaje.get(0).getArtikal();
        Korisnik korisnik=korisnici.get(0);
        
        List<Korpa> korpe=em.createQuery("SELECT k FROM Korpa k WHERE k.artikal= :artikal AND k.korisnik= :korisnik",Korpa.class).setParameter("artikal", artikal)
                .setParameter("korisnik", korisnik).getResultList();

        if (korpe.size()!=0){
            Korpa k=korpe.get(0);
            k.setKolicina(k.getKolicina()+pa.getKolicina());
            
            em.getTransaction().begin();
            em.flush();
            em.getTransaction().commit();
            
            return "Uspesno dodata odredjena kolicina artikla u korpu.";
        }
        Korpa k=new Korpa();
        KorpaPK kpk=new KorpaPK();
        kpk.setIdArt(artikal.getIdArt());
        kpk.setIdKor(korisnik.getIdKor());
        k.setKorpaPK(kpk);
        k.setKolicina(pa.getKolicina());
        k.setArtikal(artikal);
        k.setKorisnik(korisnik);
        
        em.getTransaction().begin();
        em.persist(k);
        em.getTransaction().commit();

        return "Uspesno dodata odredjena kolicina artikla u korpu.";
    }

    private String brisiIzKorpe(PomocKorpa pa) {
        List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", pa.getKorisnik()).getResultList();
        if (korisnici.size()!=1) return "Ne postoji korisnik sa tim korisnickim imenom.";
        List<Artikal> artikli=em.createNamedQuery("Artikal.findByIdArt").setParameter("idArt", pa.getIdArt()).getResultList();
        if (artikli.size()<=0) return "Ovaj artikal se ne postoji.";
        Artikal artikal=artikli.get(0);
        Korisnik korisnik=korisnici.get(0);
        List<Korpa> korpe=em.createQuery("SELECT k FROM Korpa k WHERE k.artikal= :artikal AND k.korisnik= :korisnik",Korpa.class).setParameter("artikal", artikal)
                .setParameter("korisnik", korisnik).getResultList();        
        if (korpe.size()!=1) return "Ne postoji u korpi taj artikal.";
        Korpa k=korpe.get(0);
        if (k.getKolicina()-pa.getKolicina()<=0){
            em.getTransaction().begin();
            em.remove(k);
            em.getTransaction().commit();
            
            return "Kako je kolicina artikla<0, uklonjen je iz korpe.";
        }
        
        k.setKolicina(k.getKolicina()-pa.getKolicina());
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        
        return "Uspesno smanjena kolicina artikla.";
        
    }

    private String dohvatiKategorije() {
        List<Kategorija> kategorije=em.createNamedQuery("Kategorija.findAll").getResultList();
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<kategorije.size();i++){
            sb.append(kategorije.get(i).toString());
            sb.append("\n");
        }
        return sb.toString();
        
        
        
    }

    private String dohvatiArtikle(String text) {
        List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", text).getResultList();
        if (korisnici.size()!=1) return "Korisnik sa datim korisnickim imenom ne postoji.";
        List<Prodaje> prodaje=em.createNamedQuery("Prodaje.findByIdKor").setParameter("idKor", korisnici.get(0).getIdKor()).getResultList();
        StringBuilder sb=new StringBuilder();
        if (prodaje.size()==0) return "Korisnik sa datim korisnickim imenom ne prodaje nijedan artikal.";
        sb.append(text);
        sb.append(":\n");
        for (int i=0;i<prodaje.size();i++){
            sb.append(prodaje.get(i).getArtikal().toString());
            sb.append("\n");
        }
        return (sb.toString());
    }

    private String dohvatiSadrzajKorpe(String text) {
        List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", text).getResultList();
        if (korisnici.size()!=1) return "Korisnik sa datim korisnickim imenom ne postoji.";
        List<Korpa> korpe=em.createNamedQuery("Korpa.findByIdKor").setParameter("idKor", korisnici.get(0).getIdKor()).getResultList();
        StringBuilder sb=new StringBuilder();
        if (korpe.size()==0) return "Korisnik sa datim korisnickim imenom nema nijedan artikal u korpi.";
        sb.append(text);
        sb.append(":\n");
        for (int i=0;i<korpe.size();i++){
            sb.append(korpe.get(i).getArtikal().toString());
            sb.append("---");
            sb.append(korpe.get(i).getKolicina());
            sb.append("\n");

        }
        return (sb.toString());
    }

    private String kreirajKorisnika(PomocKorisnik pk) {
        List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", pk.getKorIme()).getResultList();
        if (korisnici.size()!=0) return "Korisnik sa tim korisnickim imenom vec postoji.";
        Korisnik k=new Korisnik();
        k.setAdresa(pk.getAdresa());
        k.setIdGrad(pk.getGrad());
        k.setIme(pk.getIme());
        k.setPrezime(pk.getPrezime());
        k.setKorisnickoIme(pk.getKorIme());
        k.setSifra(pk.getSifra());
        k.setNovac(pk.getNovac());
        em.getTransaction().begin();
        em.persist(k);
        em.getTransaction().commit();
        
        return "Uspesno kreiran korisnik.";
    }

    private String dodajNovac(PomocKorisnik pk) {

            List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", pk.getKorIme()).getResultList();
            if (korisnici.size()!=1) return "Korisnik sa tim korisnickim imenom ne postoji.";
            Korisnik k=korisnici.get(0);
            k.setNovac(k.getNovac()+pk.getNovac());
            em.getTransaction().begin();
            em.flush();
            em.getTransaction().commit();
            return "Uspesno dodat novac korisniku.";
    }
    
    private String promeniAdresu(PomocKorisnik pk){
        
        
        List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", pk.getKorIme()).getResultList();
        if (korisnici.size()!=1) return "Korisnik sa tim korisnickim imenom ne postoji.";
        
        Korisnik k=korisnici.get(0);
        k.setAdresa(pk.getAdresa());
        k.setIdGrad(pk.getGrad());
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();

        
        
        return "Uspesno promenjena adresa korisniku.";
        
        
    }

    private PomocListaKorpa korpe(PomocKorisnik pk) {
                List<Korpa> korpe=em.createNamedQuery("Korpa.findByIdKor").setParameter("idKor", pk.getId()).getResultList();
                PomocListaKorpa plk=new PomocListaKorpa();
                for (int i=0;i<korpe.size();i++){
                    PomocKorpa p=new PomocKorpa(korpe.get(i).getArtikal().getIdArt(),korpe.get(i).getKolicina());
                    plk.dodajPK(p);
                }
                return plk;

        
    }

    private String korpaKorisnik(PomocKorisnik pk) {
        List<Korpa> korpe=em.createNamedQuery("Korpa.findByIdKor").setParameter("idKor", pk.getId()).getResultList();
        List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", pk.getKorIme()).getResultList();
        Korisnik k=korisnici.get(0);
        k.setNovac(pk.getNovac());
        em.getTransaction().begin();
        for (int i=0;i<korpe.size();i++){
            em.remove(korpe.get(i));
            
        }
        em.flush();
        em.getTransaction().commit();
        return "Uspesno izbrisane stavke iz korpe i smanjena kolicina novca korisniku.";
        
        
        
        
    }

    private String artikli(String text) {
        List<Artikal> artikli=em.createQuery("SELECT p.artikal FROM Prodaje p GROUP BY p.artikal",Artikal.class).getResultList();
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<artikli.size();i++){
            sb.append(artikli.get(i).toString());
            sb.append("\n");
        }
        return (sb.toString());
        
    }

  
    
    
}
