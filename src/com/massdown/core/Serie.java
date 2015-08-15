

package com.massdown.core;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class Serie {
    
    private String nombreSerie;
    private String cadenaSerie;
    private String descripcionSerie;
    private String urlImagen;    
    private ArrayList<String[]> capitulos;    
    
    private final Document domPagSerie;

    
    public Serie(String urlSerie) throws IOException {
        this.cadenaSerie = urlSerie;
        this.domPagSerie = Jsoup.connect("http://seriesblanco.com"+urlSerie)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/39.0")
                .get();
        this.nombreSerie = getNombreSerie();
        this.descripcionSerie = getDescripcionSerie();
        this.urlImagen = getUrlImagen();                     
    }

    public Capitulo CrearObjetoCapitulo(int indiceCapitulo) throws IOException {        
        String[] datosCapituloSeleccionado = capitulos.get(indiceCapitulo);
        Capitulo capitulo = new Capitulo(datosCapituloSeleccionado[0], datosCapituloSeleccionado[1], this);
        capitulo.tituloSeriePerteneciente = this.nombreSerie;
        return capitulo;        
    }

    public String getNombreSerie() {
        if(nombreSerie == null) { 
            String nombre = domPagSerie.select(".post-title").text();    
            this.nombreSerie = nombre;
        }
        return nombreSerie;
    }

    public String getDescripcionSerie() {
        if(descripcionSerie == null) { 
            String descripcion = domPagSerie.select(".selected").text();    
            this.descripcionSerie = descripcion;
        }
        return descripcionSerie;
    }

    public String getUrlImagen() {
        if(urlImagen == null) {
            String imagen = domPagSerie.select("#port_serie").attr("src");    
            this.urlImagen = imagen;
        }
        return urlImagen;
    }

    public ArrayList<String[]> getCapitulos() {        
        if(capitulos == null) {                    
            capitulos = new ArrayList<>();
            Elements todosLosCapitulosEnDOM = domPagSerie.select(".zebra tr");        

            for(int i = 0 ; i < todosLosCapitulosEnDOM.size() ; i++) {
                Element unCapituloEnDOM = todosLosCapitulosEnDOM.get(i);    
                // Los datos del capitulo se almacenan en un array donde 0 es el nombre y 1 el enlace
                String[] datosCapitulo = {unCapituloEnDOM.text(), "http://seriesblanco.com/"+unCapituloEnDOM.select("a").attr("href")};
                capitulos.add(datosCapitulo);
            }            
        }
        
        return capitulos;
    }   

    public String getCadenaSerie() {
        return cadenaSerie;
    }
    
    
}
