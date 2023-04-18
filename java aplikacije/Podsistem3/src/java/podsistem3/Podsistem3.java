/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem3;

import entities.Artikal;
import entities.Korisnik;
import entities.Narudzbina;
import entities.Stavka;
import entities.Transakcija;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jms.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import pomoc.PomocArtikal;
import pomoc.PomocKorisnik;
import pomoc.PomocListaKorpa;

/**
 *
 * @author DJOLE
 */
public class Podsistem3 extends Thread{

    /**
     * @param args the command line arguments
     */
    
    
    @Resource(lookup="jms/__defaultConnectionFactory")
    static ConnectionFactory connectionFactory;
   
    
    @Resource(lookup="ServerTopic")
    static Topic topic;
    
    @Resource(lookup="Podsistem3Queue")
    static Queue queue3;
    
    @Resource(lookup="Podsistem2Queue")
    static Queue queue2;
    
    @Resource(lookup="Podsistem1Queue")
    static Queue queue1;
    
    
    
    
    

    
    
    

    EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem3");
    EntityManager em=emf.createEntityManager();
    
    @Override
    public void run() {
        
        JMSContext context=connectionFactory.createContext();
        JMSConsumer consumer=context.createConsumer(queue3);
        JMSProducer producer=context.createProducer();
        
        while(true){
            try {
                Message txt=consumer.receive();
                int id=txt.getIntProperty("id");
                
                
                if (id==11){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocKorisnik pk=(PomocKorisnik) obj.getObject();
                    String povratna=kreirajTransakciju(pk);

                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                }
                else if (id==17){
                    TextMessage obj=(TextMessage)txt;
                    String povratna=dohvatiNarudzbineZaKorisnika(obj.getText());
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                    
                }
                else if (id==18){
                    TextMessage obj=(TextMessage)txt;
                    String povratna=dohvatiNarudzbine(obj.getText());
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                    
                }
                else if (id==19){
                    
                    TextMessage obj=(TextMessage)txt;
                    String povratna=dohvatiTransakcije(obj.getText());
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
                    PomocArtikal pk=(PomocArtikal) obj.getObject();
                    String povratna=napraviArtikal(pk);
                }
                
                else if (id==24){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocArtikal pk=(PomocArtikal) obj.getObject();
                    String povratna=promeniCenu(pk);
                }
                else if (id==25){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocArtikal pk=(PomocArtikal) obj.getObject();
                    String povratna=promeniPopust(pk);
                }
                
                
            } catch (JMSException ex) {
                Logger.getLogger(Podsistem3.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }
    }
    
    public static void main(String[] args) {
        
        Podsistem3 p=new Podsistem3();
        p.start();
        
        
    }

    private String dohvatiNarudzbineZaKorisnika(String text) {
        List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", text).getResultList();
        if (korisnici.size()!=1) return "Korisnik sa datim korisnickim imenom ne postoji.";
        List<Narudzbina> narudzbine=em.createQuery("SELECT n FROM Narudzbina n WHERE n.idKor.idKor= :idKor",Narudzbina.class).setParameter("idKor", korisnici.get(0).getIdKor()).getResultList();
        StringBuilder sb=new StringBuilder();
        if (narudzbine.size()==0) return "Korisnik sa datim korisnickim imenom nema nijednu narudzbinu.";
        sb.append(text);
        sb.append(":\n");
        for (int i=0;i<narudzbine.size();i++){
            sb.append(narudzbine.get(i).toString());
            sb.append("\n");
        }
        return (sb.toString());
    
    
    }

    private String dohvatiNarudzbine(String text) {
        List<Narudzbina> narudzbine=em.createNamedQuery("Narudzbina.findAll").getResultList();
        StringBuilder sb=new StringBuilder();

        for (int i=0;i<narudzbine.size();i++){
            sb.append(narudzbine.get(i).toString());
            sb.append("\n");
        }
        return (sb.toString());
    }
    
    private String dohvatiTransakcije(String text) {
        List<Transakcija> transakcije=em.createNamedQuery("Transakcija.findAll").getResultList();
        StringBuilder sb=new StringBuilder();

        for (int i=0;i<transakcije.size();i++){
            sb.append(transakcije.get(i).toString());
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

    private String napraviArtikal(PomocArtikal pa) {
        
        Artikal artikal=new Artikal();
        artikal.setCena(pa.getCena());
        artikal.setIdKat(pa.getKategorija());
        artikal.setNaziv(pa.getNaziv());
        artikal.setOpis(pa.getOpis());
        artikal.setPopust(pa.getPopust());
        
        em.getTransaction().begin();
        em.persist(artikal);
        em.getTransaction().commit();
        
        return "Uspesno kreiran artikal.";
    }

    private String promeniCenu(PomocArtikal pa) {
        Artikal a=em.find(Artikal.class, pa.getId());
        if (a==null) return "Ne postoji artikal sa zadatim id.";
        a.setCena(pa.getCena());
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        
        return "Uspesno promenjena cena artiklu.";
        
    }

    private String promeniPopust(PomocArtikal pa) {
        Artikal a=em.find(Artikal.class, pa.getId());
        if (a==null) return "Ne postoji artikal sa zadatim id.";
        a.setPopust(pa.getPopust());
        
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        
        return "Uspesno promenjen popust artiklu.";

    
    }

    private String kreirajTransakciju(PomocKorisnik pk) {
        try {
            List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", pk.getKorIme()).getResultList();
            if (korisnici.size()!=1) return "Korisnik sa tim korisnickim imenom ne postoji.";
            JMSContext context=connectionFactory.createContext();
            JMSProducer producer=context.createProducer();
            pk.setId(korisnici.get(0).getIdKor());
            ObjectMessage om=context.createObjectMessage(pk);
            
            
            om.setIntProperty("id", 23);
            producer.send(queue2, om);
            JMSConsumer consumer=context.createConsumer(topic, "id=27");
            ObjectMessage obj=(ObjectMessage) consumer.receive();
            
            PomocListaKorpa plk=(PomocListaKorpa) obj.getObject();
            if (plk.lista().size()==0) return "Korpa je prazna.";
            ArrayList<Artikal> artikli=new ArrayList();
            double cena=0;
            for (int i=0;i<plk.lista().size();i++){
                Artikal a=em.find(Artikal.class, plk.lista().get(i).getIdArt());
                artikli.add(a);
                cena+=(a.getCena()-a.getCena()*(a.getPopust()/100))*plk.lista().get(i).getKolicina();
            }
            if (cena>korisnici.get(0).getNovac()) return "Korisnik nema dovoljno novca za kupovinu.";
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now();  
            Transakcija t=new Transakcija();
            t.setDatumVreme(dtf.format(now));
            t.setIznos(cena);
            Narudzbina n=new Narudzbina();
            dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
            now = LocalDateTime.now();  
            n.setDatumVreme(dtf.format(now));
            n.setIdGrad(pk.getGrad());
            n.setIdKor(korisnici.get(0));
            n.setIznos(cena);
            n.setAdresa(pk.getAdresa());
            
            t.setIdNar(n);
            
            em.getTransaction().begin();
            em.persist(n);
            em.persist(t);
            em.getTransaction().commit();
            
            for (int i=0;i<artikli.size();i++){
                
                Stavka s=new Stavka();
                s.setIdArt(artikli.get(i));
                s.setIdNar(n);
                s.setIznos(artikli.get(i).getCena());
                s.setKolicina(plk.lista().get(i).getKolicina());
                
                em.getTransaction().begin();
                em.persist(s);
                em.getTransaction().commit();
                
            }
                        
            Korisnik korisnik=korisnici.get(0);
  
            double nova=korisnik.getNovac()-cena;
            korisnik.setNovac(nova);
            em.getTransaction().begin();
            em.flush();
            em.getTransaction().commit();
            
            pk.setNovac(nova);
            
            om=context.createObjectMessage(pk);
            om.setIntProperty("id", 24);
            
            producer.send(queue2, om);
            producer.send(queue1, om);
            
            
            
            return "Kreirana narudzbina i transakcija.";
            
                    
            
            
        } catch (JMSException ex) {
            Logger.getLogger(Podsistem3.class.getName()).log(Level.SEVERE, null, ex);
            return "Greska";
        }
        

    }
    
    
}
