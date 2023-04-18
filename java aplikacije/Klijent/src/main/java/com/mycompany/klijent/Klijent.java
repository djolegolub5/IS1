/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.klijent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rest.Zahtev;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
/**
 *
 * @author DJOLE
 */
public class Klijent {
    
    public static void main(String[] args){
        try {
            
             OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100,TimeUnit.SECONDS).build();
            
            Gson gson = new GsonBuilder()
            .setLenient()
            .create();
            
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                     
                    .baseUrl("http://localhost:8080/Server/zahtev/server/")
                    .client(client)
                    .build();
            
            Zahtev zahtev = retrofit.create(Zahtev.class);
            Scanner scanner = new Scanner(System.in);

            
            boolean log=false;
            String username=null;
            String password=null;
            
            
            while (true){
                
                if (!log){
                    System.out.println("Uloguj se.");
                    System.out.println("Unesi username:");
                    username=scanner.nextLine();
                    System.out.println("Unesi password:");
                    password=scanner.nextLine();
                    
                    
                    retrofit2.Response r=zahtev.logovanje(username,password).execute();
                    System.out.println(r.body());
                    
                    if (r.body().equals("Uspesno ste se ulogovali.")) log=true;
                    
                    
                }
                
                else {
                    int choice;
                    System.out.println("Izaberi stavku.");
                    System.out.println("----------------");
                    System.out.println(""
                            + "1. Kreiranje grada.\n" +
                            "2. Kreiranje korisnika.\n" +
                            "3. Dodavanje novca korisniku.\n" +
                            "4. Promena adrese i grada za korisnika.\n" +
                            "5. Kreiranje kategorije.\n" +
                            "6. Kreiranje artikla.\n" +
                            "7. Menjanje cene artikla.\n" +
                            "8. Postavljanje popusta za artikal.\n" +
                            "9. Dodavanje artikala u odredjenoj kolicini u korpu.\n" +
                            "10. Brisanje artikla u odredjenoj kolicini iz korpe.\n" +
                            "11. Placanje, koje obuhvata kreiranje transakcije, kreiranje narudzbine sa njenim stavkama, i " +
                            "brisanje sadrzaja iz korpe.\n" +
                            "12. Dohvatanje svih gradova.\n" +
                            "13. Dohvatanje svih korisnika.\n" +
                            "14. Dohvatanje svih kategorija.\n" +
                            "15. Dohvatanje svih artikala koje prodaje korisnik koji je poslao zahtev.\n" +
                            "16. Dohvatanje sadrzaja korpe korisnika koji je poslao zahtev.\n" +
                            "17. Dohvatanje svih narudzbina korisnika koji je poslao zahtev.\n" +
                            "18. Dohvatanje svih narudzbina.\n" +
                            "19. Dohvatanje svih transakcija.\n" +
                            "20. Izloguj se.");
                    System.out.println("----------------");
                    System.out.println("Izbor: ");
                    choice=Integer.parseInt(scanner.nextLine());
                    
                    if (choice==1){
                        System.out.println("Unesi naziv grada:");
                        String grad=scanner.nextLine();
                        retrofit2.Response r=zahtev.kreirajGrad(grad).execute();
                        System.out.println(r.body());
                        
                    }
  
                    else if (choice==2){
                        System.out.println("Unesi korisnicko ime korisnika:");
                        String korIme=scanner.nextLine();
                        System.out.println("Unesi sifru korisnika:");
                        String sifra=scanner.nextLine();
                        System.out.println("Unesi ime korisnika:");
                        String ime=scanner.nextLine();
                        System.out.println("Unesi prezime korisnika:");
                        String prezime=scanner.nextLine();
                        System.out.println("Unesi adresu grada:");
                        String adresa=scanner.nextLine();
                        System.out.println("Unesi naziv grada:");
                        String grad=scanner.nextLine();
                        System.out.println("Unesi novac korisnika:");
                        Double novac=Double.parseDouble(scanner.nextLine());
                        
                        retrofit2.Response r=zahtev.kreirajKorisnika(korIme, sifra, ime, prezime, adresa, novac, grad).execute();
                        System.out.println(r.body());
                        
                    }
                    else if (choice==3){
                        System.out.println("Unesi korisnicko ime korisnika:");
                        String korIme=scanner.nextLine();
                        System.out.println("Unesi koliko novaca zelite dodati korisniku:");
                        Double novac=Double.parseDouble(scanner.nextLine());
                        
                        retrofit2.Response r=zahtev.dodajNovac(korIme, novac).execute();
                        System.out.println(r.body());
                        
                    }
                    else if (choice==4){
                        
                        System.out.println("Unesi korisnicko ime korisnika:");
                        String korIme=scanner.nextLine();
                        
                        System.out.println("Unesi adresu grada:");
                        String adresa=scanner.nextLine();
                        System.out.println("Unesi naziv grada:");
                        String grad=scanner.nextLine();
                        
                        
                        retrofit2.Response r=zahtev.promeniAdresa(korIme, adresa, grad).execute();
                        System.out.println(r.body());
                        
                    }
                    else if (choice==5){
                        
                        System.out.println("Unesi kategoriju:");
                        String kategorija=scanner.nextLine();
                        
                        System.out.println("Unesi nadkategoriju kategorije (null ako ona ne postoji):");
                        String nadkategorija=scanner.nextLine();
                        
                        retrofit2.Response r=zahtev.kreirajKategoriju(kategorija, nadkategorija).execute();
                        System.out.println(r.body());
                        
                    }
                    else if (choice==6){
                        
                                                
                        System.out.println("Unesi naziv artikla:");
                        String naziv=scanner.nextLine();
                        
                        System.out.println("Unesi opis za artikal:");
                        String opis=scanner.nextLine();
                        
                        System.out.println("Unesi cenu artikla:");
                        Double cena=Double.parseDouble(scanner.nextLine());
                        
                        System.out.println("Unesi popust za artikal:");
                        Double popust=Double.parseDouble(scanner.nextLine());
                        
                        System.out.println("Unesi kategoriju artikla:");
                        String kategorija=scanner.nextLine();
                        
                        retrofit2.Response r=zahtev.kreirajArtikal(username,naziv, opis, cena, popust, kategorija).execute();
                        System.out.println(r.body());
                        
                        
                        
                    }
                    else if (choice==7){
                        retrofit2.Response r=zahtev.prodaje(username).execute();
                        System.out.println(r.body());
                        System.out.println("Izaberi id artikla kom menjas cenu:");
                        int idArt=Integer.parseInt(scanner.nextLine());
                        System.out.println("Unesi novu cenu artikla:");
                        Double cena=Double.parseDouble(scanner.nextLine());
                        
                        r=zahtev.promeniCenu(idArt, cena).execute();
                        System.out.println(r.body());

                        
                        
                    }
                    else if (choice==8){
                        retrofit2.Response r=zahtev.prodaje(username).execute();
                        System.out.println(r.body());
                        System.out.println("Izaberi id artikla kom menjas popust:");
                        int idArt=Integer.parseInt(scanner.nextLine());
                        System.out.println("Unesi novi popust artikla:");
                        Double popust=Double.parseDouble(scanner.nextLine());
                        
                        r=zahtev.promeniPopust(idArt, popust).execute();
                        System.out.println(r.body());
                    }
                    else if (choice==9){
                        retrofit2.Response r=zahtev.artikli().execute();
                        System.out.println(r.body());
                        System.out.println("Izaberi id artikla koji dodajes u korpu:");
                        int idArt=Integer.parseInt(scanner.nextLine());
                        System.out.println("Unesi kolicinu artikla:");
                        int kolicina=Integer.parseInt(scanner.nextLine());
                        
                        r=zahtev.dodajUKorpu(username, idArt, kolicina).execute();
                        System.out.println(r.body());

                    }
                    else if (choice==10){
                        retrofit2.Response r=zahtev.kupuje(username).execute();
                        System.out.println(r.body());
                        if (r.body().equals("Korisnik sa datim korisnickim imenom nema nijedan artikal u korpi.")) {
                            System.out.println();
                            continue;
                        }
                        System.out.println("Izaberi id artikla kom menjas kolicinu:");
                        int idArt=Integer.parseInt(scanner.nextLine());
                        System.out.println("Unesi kolicinu artikla za koju menjas:");
                        int kolicina=Integer.parseInt(scanner.nextLine());
                        
                        r=zahtev.izbaciIzKorpu(username, idArt, kolicina).execute();
                        System.out.println(r.body());

                        
                    }
                    else if (choice==11){
                        
                        retrofit2.Response r=zahtev.gradovi().execute();
                        System.out.println(r.body());
                        System.out.println("Izaberi id grada za dostavu:");
                        int idGrad=Integer.parseInt(scanner.nextLine());
                        
                        
                        System.out.println("Unesi adresu grada za dostavu:");
                        String adresa=scanner.nextLine();
                        
                        r=zahtev.transakcija(username, adresa, idGrad).execute();
                        System.out.println(r.body());

                        
                        
                        
                    }
                    else if (choice==12){
                        
                        retrofit2.Response r=zahtev.gradovi().execute();
                        System.out.println(r.body());
                        
                        
                        
                    }
                    else if (choice==13){
                        
                        retrofit2.Response r=zahtev.korisnici().execute();
                        System.out.println(r.body());
                        
                    }
                    else if (choice==14){
                        
                        retrofit2.Response r=zahtev.kategorije().execute();
                        System.out.println(r.body());
                        
                    }
                    else if (choice==15){
                        
                        retrofit2.Response r=zahtev.prodaje(username).execute();
                        System.out.println(r.body());
                        
                        
                    }
                    else if (choice==16){
                        
                        retrofit2.Response r=zahtev.kupuje(username).execute();
                        System.out.println(r.body());
                        
                    }
                    else if (choice==17){
                        
                        retrofit2.Response r=zahtev.naruzbineZaKor(username).execute();
                        System.out.println(r.body());
                        
                    }
                    else if (choice==18){
                        
                        retrofit2.Response r=zahtev.naruzbine().execute();
                        System.out.println(r.body());
                        
                    }
                    else if (choice==19){
                        retrofit2.Response r=zahtev.transakcije().execute();
                        System.out.println(r.body());
                    }
                    else if (choice==20){
                        log=false;     
                    }
                    
                    else{
                        System.out.println("Izbor nije validan");

                    
                    }
                    
                }
                System.out.println();
                
                
            }
            
            //retrofit2.Response r=zahtev.kreirajGrad("Rudo").execute();
            
            //System.out.println(r.body());
        } catch (Exception e) {
            Logger.getLogger(Klijent.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Greska.");
        }
        
        
    }
    
    
    
}
