package com.massdown.busqueda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
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
        Map<String, String> data = new HashMap();
        data.put("keyword", consulta);
        data.put("search_type", "serie");
        domPaginaBusqueda = Jsoup.connect("http://www.seriesyonkis.com/buscar/serie").data(data).post();
        domSeriesBuscadas = domPaginaBusqueda.select("#series_results_wrapper .results .nth-child1n");  
        this.CargarDatosSeriesEnArray(false);
    }
            
    private void ObtenerSerieMasVistas() throws IOException {
        domPaginaBusqueda = Jsoup.connect("http://www.seriesyonkis.com/series-mas-vistas").get();
        domSeriesBuscadas = domPaginaBusqueda.select("#tabs-1 .covers-list .thumb-episode"); 
        this.CargarDatosSeriesEnArray(true);
    }
    
    private void CargarDatosSeriesEnArray(boolean serieDestacada) {
        for(int i = 0 ; i < domSeriesBuscadas.size() ; i++) {           
            Element capitulo = domSeriesBuscadas.get(i);            
            String titulo = capitulo.select("a").attr("title");
            String urlSerie = capitulo.select("a").attr("href");
            String urlImagenSerie = capitulo.select("img").attr("src");    
            String descripcionSerie = capitulo.select(".content").text(); 
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
