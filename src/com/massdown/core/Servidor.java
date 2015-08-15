package com.massdown.core;

import java.io.IOException;
import java.net.MalformedURLException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


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
        System.out.println(url);
        Document seriesCocoDOM = Jsoup.connect(enlaceServidor).get();
        String enlaceRedireccionSeriescoco = seriesCocoDOM.select(".enlace_link").attr("href");
        
        System.out.println(enlaceRedireccionSeriescoco);
        System.out.println("---------------");
        
        this.enlaceServidor = enlaceRedireccionSeriescoco;
        //this.enlaceServidor = MetodosUtiles.getFinalRedirectedUrl(enlaceRedireccionSeriescoco);
        
        return this.enlaceServidor;        
    }
    
    public void ObtenerEnlaceDescarga() throws MalformedURLException{ }        
    
}
