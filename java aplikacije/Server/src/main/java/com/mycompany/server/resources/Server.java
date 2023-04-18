/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server.resources;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.ConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import pomoc.PomocArtikal;
import pomoc.PomocGrad;
import pomoc.PomocKategorija;
import pomoc.PomocKorisnik;
import pomoc.PomocKorpa;

/**
 *
 * @author DJOLE
 */

@Path("server")

public class Server {
    
    
    
    @Resource(lookup="jms/__defaultConnectionFactory")
    ConnectionFactory connectionFactory;
    
    
    @Resource(lookup="ServerTopic")
    Topic topic;
    
    @Resource(lookup="Podsistem1Queue")
    Queue queue1;
    
    @Resource(lookup="Podsistem2Queue")
    Queue queue2;
    
    @Resource(lookup="Podsistem3Queue")
    Queue queue3;
    

    
    @GET
    @Path("kategorija")
    public Response kategorije(){
        
        JMSContext context=connectionFactory.createContext();
            JMSProducer producer=context.createProducer();
            
        try {

            

            
            TextMessage msg=context.createTextMessage("Dohvati sve kategorije.");
            msg.setIntProperty("id", 14);
            producer.send(queue2, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=14");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
    }
    
    
    @POST
    @Path("transakcija/{korIme}/{adresa}/{grad}")
    public Response transakcija(@PathParam("korIme") String korIme, @PathParam("adresa") String adresa, @PathParam("grad") int grad){
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();
        try {

            
            
            PomocKorisnik pk=new PomocKorisnik(korIme,adresa,grad);
            ObjectMessage msg=context.createObjectMessage(pk);
            
            msg.setIntProperty("id", 11);
            producer.send(queue3, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=11");
            TextMessage txt=(TextMessage) consumer.receive();
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
        
    }
    
    @GET
    @Path("prodaje/{korIme}")
    public Response prodaje(@PathParam("korIme") String korIme){
        
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();
            
        try {

            

            
            TextMessage msg=context.createTextMessage(korIme);
            msg.setIntProperty("id", 15);
            producer.send(queue2, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=15");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
    }
    
    @GET
    @Path("kupuje/{korIme}")
    public Response kupuje(@PathParam("korIme") String korIme){
        
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();
            
        try {

            

            
            TextMessage msg=context.createTextMessage(korIme);
            msg.setIntProperty("id", 16);
            producer.send(queue2, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=16");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
    }
    
    @POST
    @Path("kategorija/{naziv}/{nadKat}")
    public Response kreirajKategoriju(@PathParam("naziv") String naziv, @PathParam("nadKat") String nadKat){
        
            JMSContext context=connectionFactory.createContext();
            JMSProducer producer=context.createProducer();
            
        try {

            

            PomocKategorija pk=new PomocKategorija(naziv,nadKat);
            
            ObjectMessage msg=context.createObjectMessage(pk);
            msg.setIntProperty("id", 5);
            producer.send(queue2, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=5");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
    }
    
    @POST
    @Path("artikal/{korIme}/{naziv}/{opis}/{cena}/{popust}/{kategorija}")
    public Response kreirajArtikal(@PathParam("korIme")String korIme,@PathParam("naziv") String naziv, @PathParam("opis") String opis, 
            @PathParam("cena") double cena, @PathParam("popust") double popust, @PathParam("kategorija") String kategorija){
        
            JMSContext context=connectionFactory.createContext();
            JMSProducer producer=context.createProducer();
            
        try {

            

            PomocArtikal pa=new PomocArtikal(naziv, opis, cena, popust,kategorija);
            
            ObjectMessage msg=context.createObjectMessage(pa);
            msg.setIntProperty("id", 6);
            msg.setStringProperty("korisnik", korIme);
            producer.send(queue2, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=6");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
    }
    
    @POST
    @Path("cena/{id}/{cena}")
    public Response promeniCenu(@PathParam("id") int id, @PathParam("cena") double cena){
        
            JMSContext context=connectionFactory.createContext();
            JMSProducer producer=context.createProducer();
            
        try {

            

            PomocArtikal pa=new PomocArtikal(id,cena,true);
            
            ObjectMessage msg=context.createObjectMessage(pa);
            msg.setIntProperty("id", 7);
            producer.send(queue2, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=7");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
    }
    
    
    @POST
    @Path("popust/{id}/{popust}")
    public Response promeniPopust(@PathParam("id") int id, @PathParam("popust") double popust){
        
            JMSContext context=connectionFactory.createContext();
            JMSProducer producer=context.createProducer();
            
        try {

            

            PomocArtikal pa=new PomocArtikal(id,popust,false);
            
            ObjectMessage msg=context.createObjectMessage(pa);
            msg.setIntProperty("id", 8);
            producer.send(queue2, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=8");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
    }
    
    
    @POST
    @Path("grad/{naziv}")
    public Response kreirajGrad(@PathParam("naziv") String naziv){
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();
        try {
            PomocGrad pg=new PomocGrad(naziv);
            ObjectMessage msg=context.createObjectMessage(pg);
            msg.setIntProperty("id", 1);
            producer.send(queue1, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=1");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
            
            
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
        
    }
    
    @POST
    @Path("korisnik/{korIme}/{sifra}/{ime}/{prezime}/{adresa}/{novac}/{grad}")
    public Response kreirajKorisnika(@PathParam("korIme") String korIme,@PathParam("sifra")String sifra,
            @PathParam("ime") String ime,@PathParam("prezime")String prezime,@PathParam("adresa") String adresa,@PathParam("novac")double novac,
            @PathParam("grad") String grad){
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();
        try {
            PomocKorisnik pk=new PomocKorisnik(korIme, sifra, ime, prezime, adresa, novac, grad);
            ObjectMessage msg=context.createObjectMessage(pk);
            msg.setIntProperty("id", 2);
            producer.send(queue1, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=2");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
            
            
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();
            
        }
        
    }
    
    
    @POST
    @Path("novac/{korIme}/{novac}")
    public Response dodajNovac(@PathParam("korIme")String korIme, @PathParam("novac") double novac){
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();
        try {

            
            
            PomocKorisnik pk=new PomocKorisnik(korIme,novac);
            ObjectMessage msg=context.createObjectMessage(pk);
            
            msg.setIntProperty("id", 3);
            producer.send(queue1, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=3");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
          
          
    }
    
    
    @POST
    @Path("adresa/{korIme}/{adresa}/{grad}")
    public Response promeniAdresa(@PathParam("korIme")String korIme, @PathParam("adresa") String adresa, @PathParam("grad") String grad){
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();
        try {

            
            
            PomocKorisnik pk=new PomocKorisnik(korIme,adresa,grad);
            ObjectMessage msg=context.createObjectMessage(pk);
            
            msg.setIntProperty("id", 4);
            producer.send(queue1, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=4");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
          
          
    }
    
    @DELETE
    @Path("korpa/{korIme}/{idArt}/{kolicina}")
    public Response izbaciIzKorpu(@PathParam("korIme")String korIme, @PathParam("idArt") int idArt, @PathParam("kolicina") int kolicina){
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();
        try {

            
            
            PomocKorpa pk=new PomocKorpa(korIme,idArt,kolicina);
            ObjectMessage msg=context.createObjectMessage(pk);
            
            msg.setIntProperty("id", 10);
            producer.send(queue2, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=10");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
          
          
    }
    
    
    
    @POST
    @Path("korpa/{korIme}/{idArt}/{kolicina}")
    public Response dodajUKorpu(@PathParam("korIme")String korIme, @PathParam("idArt") int idArt, @PathParam("kolicina") int kolicina){
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();
        try {

            
            
            PomocKorpa pk=new PomocKorpa(korIme,idArt,kolicina);
            ObjectMessage msg=context.createObjectMessage(pk);
            
            msg.setIntProperty("id", 9);
            producer.send(queue2, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=9");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
          
          
    }
    
    
    @GET
    @Path("gradovi")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response gradovi(){
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();

        try {
            
            JMSConsumer consumer=context.createConsumer(topic, "id=12");
            
            
            TextMessage txt=context.createTextMessage("Dohvati sve gradove.");
            txt.setIntProperty("id", 12);
            producer.send(queue1, txt);
            
            
            txt=(TextMessage) consumer.receive();
            
            
            return Response.ok().entity(txt.getText()).build();
            
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();
        }
        
    }
        
    @GET
    @Path("narudzbine/{korIme}")
    public Response naruzbineZaKor(@PathParam("korIme") String korIme){
        
        
                JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();
            
        try {

            

            
            TextMessage msg=context.createTextMessage(korIme);
            msg.setIntProperty("id", 17);
            producer.send(queue3, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=17");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
        
        
        
    }
    
    
    @GET
    @Path("narudzbine")
    public Response naruzbine(){
        
        
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();
            
        try {

            

            
            TextMessage msg=context.createTextMessage("Dohvati sve narudzbine.");
            msg.setIntProperty("id", 18);
            producer.send(queue3, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=18");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
        
        
        
    }
    
    
    @GET
    @Path("logovanje/{korIme}/{sifra}")
    public Response logovanje(@PathParam("korIme") String korIme, @PathParam("sifra") String sifra){
        
        
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();
        
        try {

            
                        
            PomocKorisnik pk=new PomocKorisnik(korIme,sifra);
            ObjectMessage msg=context.createObjectMessage(pk);
            msg.setIntProperty("id", 26);
            producer.send(queue1, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=26");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
            
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
        
    }
    
    
    @GET
    @Path("artikli")
    public Response artikli(){
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();
            
        try {

            

            
            TextMessage msg=context.createTextMessage("Dohvati sve artikle koji se kupuju.");
            msg.setIntProperty("id", 25);
            producer.send(queue2, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=25");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
            
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
    }
    
    @GET
    @Path("transakcije")
    public Response transakcije(){
        
        
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();
            
        try {

            

            
            TextMessage msg=context.createTextMessage("Dohvati sve transakcije.");
            msg.setIntProperty("id", 19);
            producer.send(queue3, msg);
            JMSConsumer consumer=context.createConsumer(topic, "id=19");
            TextMessage txt=(TextMessage) consumer.receive();
            
            return Response.ok().entity(txt.getText()).build();
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();

        }
        
        
        
    }
    
    
    @GET
    @Path("korisnici")
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Response korisnici(){
        JMSContext context=connectionFactory.createContext();
        JMSProducer producer=context.createProducer();

        try {
            
            JMSConsumer consumer=context.createConsumer(topic, "id=13");
            
            
            TextMessage txt=context.createTextMessage("Dohvati sve korisnike.");
            txt.setIntProperty("id", 13);
            producer.send(queue1, txt);
            
            
            txt=(TextMessage) consumer.receive();
            
            
            return Response.ok().entity(txt.getText()).build();
            
        } catch (JMSException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(Response.Status.BAD_REQUEST).entity("Greska.").build();
        }
        
        
        
    }
    
    
}
