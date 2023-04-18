/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podsistem1;

import entities.Grad;
import entities.Korisnik;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import jdk.nashorn.internal.runtime.regexp.joni.constants.SyntaxProperties;
import pomoc.PomocGrad;
import pomoc.PomocKorisnik;

/**
 *
 * @author DJOLE
 */
public class Podsistem1 extends Thread{

    
    @Resource(lookup="jms/__defaultConnectionFactory")
    static ConnectionFactory connectionFactory;
    
    
    @Resource(lookup="ServerTopic")
    static Topic topic;
    
    @Resource(lookup="Podsistem1Queue")
    static Queue queue1;
    
    @Resource(lookup="Podsistem2Queue")
    static Queue queue2;
    
    @Resource(lookup="Podsistem3Queue")
    static Queue queue3;
    

    EntityManagerFactory emf=Persistence.createEntityManagerFactory("podsistem1");
    EntityManager em=emf.createEntityManager();
    
    @Override
    public void run() {
        
        JMSContext context=connectionFactory.createContext();
        JMSConsumer consumer=context.createConsumer(queue1);
        JMSProducer producer=context.createProducer();
        
        while(true){
            try {
                Message txt=consumer.receive();
                int id=txt.getIntProperty("id");
                if (id==1){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocGrad pg=(PomocGrad) obj.getObject();
                    String povratna=kreirajGrad(pg);
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                    
                   
                    
               }
                else if (id==2){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocKorisnik pk=(PomocKorisnik) obj.getObject();
                    String povratna=kreirajKorisnika(pk);
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                    
                    
                    
                }
                else if (id==3){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocKorisnik pk=(PomocKorisnik) obj.getObject();
                    String povratna=dodajNovac(pk);
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                }
                else if (id==4){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocKorisnik pk=(PomocKorisnik) obj.getObject();
                    String povratna=promeniAdresu(pk);
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                }
                else if (id==12){

                    String povratna=dohvatiGradove();
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                }
                else if (id==13){
                    String povratna=dohvatiKorisnike();
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                    
                    
                }
                else if (id==24){
                    
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocKorisnik pk=(PomocKorisnik) obj.getObject();
                    String povratna=korpaKorisnik(pk);
                    
                    
                }
                else if (id==26){
                    ObjectMessage obj=(ObjectMessage)txt;
                    PomocKorisnik pk=(PomocKorisnik) obj.getObject();
                    String povratna=proveriPostojanje(pk);
                    txt=context.createTextMessage(povratna);
                    txt.setIntProperty("id", id);
                    producer.send(topic, txt);
                }
            } catch (JMSException ex) {
                Logger.getLogger(Podsistem1.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
                    
            
            
        }
        
        
        
        
        
    }

    
    public String dohvatiGradove(){
        List<Grad> gradovi=em.createQuery("SELECT g FROM Grad g",Grad.class).getResultList();
        
        String svi="";
        for (int i=0;i<gradovi.size();i++){
            svi+=gradovi.get(i).toString();
            svi+="\n";
        }
        return svi;
    }
    
    public String kreirajGrad(PomocGrad pg){
        List<Grad> gradovi=em.createNamedQuery("Grad.findByNaziv").setParameter("naziv", pg.getNaziv()).getResultList();
        if (gradovi.size()!=0) return "Grad sa tim nazivom vec postoji.";

        Grad g=new Grad();
        g.setNaziv(pg.getNaziv());
        em.getTransaction().begin();
        em.persist(g);
        em.getTransaction().commit();
        return "Uspesno kreiran grad.";
    }
    
    
    
    public static void main(String[] args) {
        Podsistem1 p1=new Podsistem1();
        
        p1.start();
    }

    private String kreirajKorisnika(PomocKorisnik pk) {
        try {
            List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", pk.getKorIme()).getResultList();
            if (korisnici.size()!=0) return "Korisnik sa tim korisnickim imenom vec postoji.";
            List<Grad> gradovi=em.createNamedQuery("Grad.findByNaziv").setParameter("naziv",pk.getIdGrad()).getResultList();
            if (gradovi.size()!=1) return "Zadati grad ne postoji.";
            Korisnik k=new Korisnik();
            k.setAdresa(pk.getAdresa());
            k.setIdGrad(gradovi.get(0));
            k.setIme(pk.getIme());
            k.setPrezime(pk.getPrezime());
            k.setKorisnickoIme(pk.getKorIme());
            k.setSifra(pk.getSifra());
            k.setNovac(pk.getNovac());
            em.getTransaction().begin();
            em.persist(k);
            em.getTransaction().commit();
            
            JMSContext context=connectionFactory.createContext();
            JMSProducer producer=context.createProducer();
            
            pk.setGrad(gradovi.get(0).getIdGrad());
            ObjectMessage om=context.createObjectMessage(pk);
            om.setIntProperty("id", 20);
            producer.send(queue2, om);
            producer.send(queue3, om);
            
            
            return "Uspesno kreiran korisnik.";
        } catch (JMSException ex) {
            Logger.getLogger(Podsistem1.class.getName()).log(Level.SEVERE, null, ex);
            return "Greska.";
        }
    }

    private String dohvatiKorisnike() {
        List<Korisnik> korisnici=em.createQuery("SELECT k FROM Korisnik k",Korisnik.class).getResultList();
        
        String svi="";
        for (int i=0;i<korisnici.size();i++){
            svi+=korisnici.get(i).toString();
            svi+="\n";
        }
        return svi;
    }

    private String dodajNovac(PomocKorisnik pk) {
        try {
            List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", pk.getKorIme()).getResultList();
            if (korisnici.size()!=1) return "Korisnik sa tim korisnickim imenom ne postoji.";
            Korisnik k=korisnici.get(0);
            k.setNovac(k.getNovac()+pk.getNovac());
            em.getTransaction().begin();
            em.flush();
            em.getTransaction().commit();
            
            
            JMSContext context=connectionFactory.createContext();
            JMSProducer producer=context.createProducer();
            
            ObjectMessage om=context.createObjectMessage(pk);
            om.setIntProperty("id", 21);
            producer.send(queue2, om);
            producer.send(queue3, om);
            
            
            return "Uspesno dodat novac korisniku.";
        } catch (JMSException ex) {
            Logger.getLogger(Podsistem1.class.getName()).log(Level.SEVERE, null, ex);
            return "Greska.";
        }
    }
    
    private String promeniAdresu(PomocKorisnik pk){
        try {
            List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", pk.getKorIme()).getResultList();
            if (korisnici.size()!=1) return "Korisnik sa tim korisnickim imenom ne postoji.";
            List<Grad> gradovi=em.createNamedQuery("Grad.findByNaziv").setParameter("naziv",pk.getIdGrad()).getResultList();
            if (gradovi.size()!=1) return "Zadati grad ne postoji.";
            Korisnik k=korisnici.get(0);
            k.setAdresa(pk.getAdresa());
            k.setIdGrad(gradovi.get(0));
            em.getTransaction().begin();
            em.flush();
            em.getTransaction().commit();
            
            pk.setGrad(gradovi.get(0).getIdGrad());
            
            JMSContext context=connectionFactory.createContext();
            JMSProducer producer=context.createProducer();
            
            ObjectMessage om=context.createObjectMessage(pk);
            om.setIntProperty("id", 22);
            producer.send(queue2, om);
            producer.send(queue3, om);
            
            
            
            return "Uspesno promenjena adresa korisniku.";
        } catch (JMSException ex) {
            Logger.getLogger(Podsistem1.class.getName()).log(Level.SEVERE, null, ex);
            return "Greska.";
        }
    }

    private String korpaKorisnik(PomocKorisnik pk) {
        
        List<Korisnik> korisnici=em.createNamedQuery("Korisnik.findByKorisnickoIme").setParameter("korisnickoIme", pk.getKorIme()).getResultList();
        Korisnik k=korisnici.get(0);
        k.setNovac(pk.getNovac());
        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();
        return "Uspesno smanjena kolicina novca korisniku.";
    }

    private String proveriPostojanje(PomocKorisnik pk) {
        String ki=pk.getKorIme();
        String sifra=pk.getSifra();
        List<Korisnik> korisnici=em.createQuery("SELECT k FROM Korisnik k WHERE k.korisnickoIme= :ki AND k.sifra= :sifra",Korisnik.class).setParameter("ki", ki)
                .setParameter("sifra", sifra).getResultList();
        
        if (korisnici.size()!=1) return "Nije dobro koisnicko ime ili lozinka.";
        else return "Uspesno ste se ulogovali.";
    }
    
}
