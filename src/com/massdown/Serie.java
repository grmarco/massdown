

package com.massdown;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Serie {
    public String nombreSerie;
    public String descripcionSerie;
    public String urlImagen;
    public ArrayList<Capitulo> capitulos;
    public ArrayList<String> nombreCapitulos;
    public ArrayList<String> urlsCapitulos;
    private final Document domPagSerie;

    
    public Serie(String nombre) throws IOException {
        this.domPagSerie = Jsoup.connect(String.valueOf("http://www.seriesyonkis.com/serie/" + nombre.replaceAll(" ", "-"))).get();
        this.nombreSerie = ObtenerNombreSerie();
        this.descripcionSerie = ObtenerDescripcionSerie();
        this.urlImagen = ObtenerImgenSerie();      
        this.urlsCapitulos = new ArrayList<>();
        this.nombreCapitulos = new ArrayList<>();
               
    }

    @Override
    public String toString() {
        return "Serie{" + "nombreSerie=" + nombreSerie + ", descripcionSerie=" + descripcionSerie + ", urlImagen=" + urlImagen + ", capitulos=" + capitulos + ", domPagSerie=" + domPagSerie + '}';
    }
    
    
    
    private String ObtenerNombreSerie() {        
        String nombre = domPagSerie.select("#section-header .underline").attr("title");    
        return this.nombreSerie = nombre;        
    }
    
    private String ObtenerDescripcionSerie() {
        String descripcion = domPagSerie.select("#section-header #description").text();    
        return this.descripcionSerie = descripcion;
    }
    
    private String ObtenerImgenSerie() {
        String imagen = domPagSerie.select(".profile-img").attr("src");    
        return this.urlImagen = imagen;        
    }
    
    public void ObtenerCapitulos() throws IOException {
        
        Elements todosLosCapitulosEnDOM = domPagSerie.select("td.episode-title a[href]").select("[href]");        
        
        for(int i = 0 ; i < todosLosCapitulosEnDOM.size() ; i++) {
            Element unCapituloEnDOM = todosLosCapitulosEnDOM.get(i);            
            //arrayConLosCapitulos.add(new Capitulo(unCapituloEnDOM.text(), unCapituloEnDOM.attr("href")));    
           this.nombreCapitulos.add(unCapituloEnDOM.text());
           this.urlsCapitulos.add(unCapituloEnDOM.attr("href"));
        }        
    }
    
    public Capitulo CrearObjetoCapitulo(int indiceCapitulo) throws IOException {        
        return new Capitulo(nombreCapitulos.get(indiceCapitulo), urlsCapitulos.get(indiceCapitulo));        
    }
}
