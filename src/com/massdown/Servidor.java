package com.massdown;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Servidor {
    
    public String idiomaCapitulo;
    public String tieneSubtitulos;
    public String enlaceServidor;
    protected Document domPagServidor;
    public String enlaceDeDescarga;
        
    public Servidor(String idioma, String subtitulos, String enlace) throws IOException {
        this.enlaceServidor = enlace;
        this.idiomaCapitulo = idioma;
        this.tieneSubtitulos = subtitulos;
    }

    public Servidor(Servidor servidor) {
        this.enlaceServidor = servidor.enlaceServidor;
        this.idiomaCapitulo = servidor.idiomaCapitulo;
        this.tieneSubtitulos = servidor.tieneSubtitulos;
    }
    
    protected String ObtenerEnlaceServidor(String url) throws IOException {
        
        Document seriesCocoDOM = Jsoup.connect("http://www.seriescoco.com"+url).get();
        String enlaceRedireccionSeriescoco = "http://www.seriescoco.com"+seriesCocoDOM.select(".episodes tr.down>td a").attr("href");
        
        this.enlaceServidor = Utilidades.getFinalRedirectedUrl(enlaceRedireccionSeriescoco);
        
        return  this.enlaceServidor;        
    }
    
    public void ObtenerEnlaceDescarga() throws MalformedURLException{ }        
    
}
