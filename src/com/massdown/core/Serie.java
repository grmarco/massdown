

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

    
    public Serie(String nombre) throws IOException {
        this.cadenaSerie = nombre.replaceAll("-", " ");
        this.domPagSerie = Jsoup.connect(String.valueOf("http://"+ cadenaSerie.replaceAll(" ", "-")+".seriespepito.com")).get();
        //this.domPagSerie = Jsoup.connect(String.valueOf("http://www.seriesyonkis.com/serie" + nombre)).get();
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
            String nombre = domPagSerie.select(".dtitulo h1").text();    
            this.nombreSerie = nombre;
        }
        return nombreSerie;
    }

    public String getDescripcionSerie() {
        if(descripcionSerie == null) { 
            String descripcion = domPagSerie.select(".dcuerpo").text();    
            this.descripcionSerie = descripcion;
        }
        return descripcionSerie;
    }

    public String getUrlImagen() {
        if(urlImagen == null) {
            String imagen = domPagSerie.select(".imgcolserie").attr("src");    
            this.urlImagen = imagen;
        }
        return urlImagen;
    }

    public ArrayList<String[]> getCapitulos() {
        
        if(capitulos == null) {                    
            capitulos = new ArrayList<>();
            Elements todosLosCapitulosEnDOM = domPagSerie.select(".accordion tr td");        

            for(int i = 0 ; i < todosLosCapitulosEnDOM.size() ; i++) {
                Element unCapituloEnDOM = todosLosCapitulosEnDOM.get(i);    
                // Los datos del capitulo se almacenan en un array donde 0 es el nombre y 1 el enlace

                String[] datosCapitulo = {unCapituloEnDOM.text(), unCapituloEnDOM.select("a").attr("href")};
                capitulos.add(datosCapitulo);
            }            
        }
        
        return capitulos;
    }   

    public String getCadenaSerie() {
        return cadenaSerie;
    }
    
    
}
