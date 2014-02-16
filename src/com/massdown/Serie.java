

package com.massdown;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Serie {
    public String nombreSerie;
    public String descripcionSerie;
    public ArrayList<Capitulo> capitulos;        
    private final Document domPagSerie;

    
    public Serie(String nombre) throws IOException {
        this.domPagSerie = Jsoup.connect(String.valueOf("http://www.seriesyonkis.com/serie/" + nombre.replaceAll(" ", "-"))).get();
        this.nombreSerie = ObtenerNombreSerie();
        this.descripcionSerie = ObtenerDescripcionSerie();
        this.capitulos = ObtenerCapitulos();        
    }
    
    private String ObtenerNombreSerie() {        
        String nombre = domPagSerie.select("#section-header .underline").attr("title");    
        return this.nombreSerie = nombre;        
    }
    
    private String ObtenerDescripcionSerie() {
        String descripcion = domPagSerie.select("#section-header #description").text();    
        return this.descripcionSerie = descripcion;
    }
    
    private ArrayList<Capitulo> ObtenerCapitulos() throws IOException {
        Elements todosLosCapitulosEnDOM = domPagSerie.select("td.episode-title a[href]").select("[href]");        
        ArrayList<Capitulo> arrayConLosCapitulos = new ArrayList<>();
        
        for(int i = 0 ; i < todosLosCapitulosEnDOM.size() ; i++) {
            Element unCapituloEnDOM = todosLosCapitulosEnDOM.get(i);            
            arrayConLosCapitulos.add(new Capitulo(unCapituloEnDOM.text(), unCapituloEnDOM.attr("href")));            
        }
        
        return arrayConLosCapitulos;
    }
    
}
