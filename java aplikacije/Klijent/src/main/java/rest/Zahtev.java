/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import javax.ws.rs.core.Response;


/**
 *
 * @author DJOLE
 */
public interface Zahtev {
    
    
    //podsistem1
    
    @POST("grad/{naziv}")
    Call<String> kreirajGrad(@Path("naziv") String naziv);
    
    @POST("korisnik/{korIme}/{sifra}/{ime}/{prezime}/{adresa}/{novac}/{grad}")
    public Call<String> kreirajKorisnika(@Path("korIme") String korIme,@Path("sifra")String sifra,
            @Path("ime") String ime,@Path("prezime")String prezime,@Path("adresa") String adresa,@Path("novac")double novac,
            @Path("grad") String grad);
    
    @POST("novac/{korIme}/{novac}")
    public Call<String> dodajNovac(@Path("korIme")String korIme, @Path("novac") double novac);
    
    @POST("adresa/{korIme}/{adresa}/{grad}")
    public Call<String> promeniAdresa(@Path("korIme")String korIme, @Path("adresa") String adresa, @Path("grad") String grad);
    
    @GET("gradovi")
    public Call<String> gradovi();
    
    @GET("korisnici")
    public Call<String> korisnici();
    
    
    //podsistem2
    
    @POST("kategorija/{naziv}/{nadKat}")
    public Call<String> kreirajKategoriju(@Path("naziv") String naziv, @Path("nadKat") String nadKat);
    
    @POST("artikal/{korIme}/{naziv}/{opis}/{cena}/{popust}/{kategorija}")
    public Call<String> kreirajArtikal(@Path("korIme") String korIme,@Path("naziv") String naziv, @Path("opis") String opis, 
            @Path("cena") double cena, @Path("popust") double popust, @Path("kategorija") String kategorija);
    
    @POST("cena/{id}/{cena}")
    public Call<String> promeniCenu(@Path("id") int id, @Path("cena") double cena);
    
    @POST("popust/{id}/{popust}")
    public Call<String> promeniPopust(@Path("id") int id, @Path("popust") double popust);
    
    @POST("korpa/{korIme}/{idArt}/{kolicina}")
    public Call<String> dodajUKorpu(@Path("korIme")String korIme, @Path("idArt") int idArt, @Path("kolicina") int kolicina);
    
    @DELETE("korpa/{korIme}/{idArt}/{kolicina}")
    public Call<String> izbaciIzKorpu(@Path("korIme")String korIme, @Path("idArt") int idArt, @Path("kolicina") int kolicina);
    
    @GET("kategorija")
    public Call<String> kategorije();
    
    @GET("prodaje/{korIme}")
    public Call<String> prodaje(@Path("korIme") String korIme);
    
    @GET("kupuje/{korIme}")
    public Call<String> kupuje(@Path("korIme") String korIme);
    
    //podsistem3
    
    @POST("transakcija/{korIme}/{adresa}/{grad}")
    public Call<String> transakcija(@Path("korIme") String korIme, @Path("adresa") String adresa, @Path("grad") int grad);
    
    @GET("narudzbine/{korIme}")
    public Call<String> naruzbineZaKor(@Path("korIme") String korIme);
    
    @GET("narudzbine")
    public Call<String> naruzbine();
    
    @GET("transakcije")
    public Call<String> transakcije();
    
    
    //logovanje i svi artikli
    
    @GET("logovanje/{korIme}/{sifra}")
    public Call<String> logovanje(@Path("korIme") String korIme, @Path("sifra") String sifra);
    
    @GET("artikli")
    public Call<String> artikli();
    
}
