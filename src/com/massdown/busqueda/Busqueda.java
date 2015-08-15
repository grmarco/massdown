package com.massdown.busqueda;

import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Busqueda {
    
    private ArrayList<String[]> resultadosDeLaBusqueda;
    private Elements domSeriesBuscadas;
    private Document domPaginaBusqueda;    
    private String consulta;
    
    public Busqueda(String consulta) throws IOException {
        this.consulta = consulta;
        this.resultadosDeLaBusqueda = new ArrayList<>();
        this.ObtenerSerieMasVistas();
        if(!consulta.equals("")) 
            this.ObtenerResultadosBusqueda();        
    }
    
    private void ObtenerResultadosBusqueda() throws IOException {        
        System.out.println(consulta);
        domPaginaBusqueda = Jsoup.connect("http://seriesblanco.com/finder.php")
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/39.0")
                .data("query", consulta)
                .post();
        domSeriesBuscadas = domPaginaBusqueda.select("a");  
        System.out.println(domSeriesBuscadas);
        this.CargarDatosSeriesEnArray(false);
    }
            
    private void ObtenerSerieMasVistas() throws IOException {
        domPaginaBusqueda = Jsoup.connect("http://www.seriesblanco.com/")
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/39.0")
                .get();
        domSeriesBuscadas = domPaginaBusqueda.select("#PopularPosts1 li");
        
        this.CargarDatosSeriesEnArray(true);
    }
    
    private void CargarDatosSeriesEnArray(boolean serieDestacada) {
        for(int i = 0 ; i < domSeriesBuscadas.size() ; i++) {           
            Element capitulo = domSeriesBuscadas.get(i);   
            String titulo = (serieDestacada) ? capitulo.select("img").attr("title") : capitulo.text();
            String urlSerie = capitulo.select("a").attr("href");
            String urlImagenSerie = capitulo.select("img").attr("src");  
            String descripcionSerie = "";
            //capitulo.select(".content").text(); 
            this.AgregarResultadoDeBusqueda(titulo, urlSerie, urlImagenSerie, descripcionSerie, (serieDestacada) ? "destacada" : "noDestacada" );
        }
        
    }
    
    public void AgregarResultadoDeBusqueda(String titulo, String urlSerie, String urlImagenSerie, String descripcionSerie, String tipoSerie) {
        String[] datosCapitulo = {titulo, urlSerie, urlImagenSerie, descripcionSerie, tipoSerie};
        resultadosDeLaBusqueda.add(datosCapitulo);
    }
    
    public ArrayList<String[]> getResultadosDeLaBusqueda() {
        return resultadosDeLaBusqueda;
    }
      
}
