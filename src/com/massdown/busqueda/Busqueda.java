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
        domPaginaBusqueda = Jsoup.connect("http://www.seriespepito.com/buscador/"+consulta.replaceAll(" ", "-")).get();
        domSeriesBuscadas = domPaginaBusqueda.select(".lista_series li");  
        this.CargarDatosSeriesEnArray(false);
    }
            
    private void ObtenerSerieMasVistas() throws IOException {
        domPaginaBusqueda = Jsoup.connect("http://www.seriespepito.com/").get();
        domSeriesBuscadas = domPaginaBusqueda.select("#ulfblista li");
        this.CargarDatosSeriesEnArray(true);
    }
    
    private void CargarDatosSeriesEnArray(boolean serieDestacada) {
        for(int i = 0 ; i < domSeriesBuscadas.size() ; i++) {           
            Element capitulo = domSeriesBuscadas.get(i);     
            String titulo = capitulo.select("a").attr("title");
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
