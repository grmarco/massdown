

package com.massdown.core;
import com.massdown.servidores.AllMyVideosYVidSpot;
import com.massdown.servidores.MagnoVideo;
import com.massdown.servidores.StreamCloud;
import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Capitulo {
    
    public ArrayList<Servidor> servidoresConElCapitulo;
    public String tituloCapitulo;    
    public String tituloSeriePerteneciente;
    private Document domPagCapitulo;
    private final String enlaceCapitulo;    
    final private Serie seriePerteneciente;
    
    public Capitulo(String tituloCapitulo, String enlaceCapitulo, Serie seriePerteneciente) throws IOException {       
        this.enlaceCapitulo = enlaceCapitulo;
        this.tituloCapitulo = tituloCapitulo;  
        this.seriePerteneciente = seriePerteneciente;
    }
    
    public ArrayList<Servidor> ObtenerServidoresCapitulo() throws IOException {
        this.domPagCapitulo = Jsoup.connect(enlaceCapitulo)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/39.0")
                .get();
        Elements listaServidoresEnDom = domPagCapitulo.select(".tenlaces tr");
        
        ArrayList<Servidor> arrayServidores = new ArrayList<>();
        
        for(int u = 1 ; u < listaServidoresEnDom.size() ; u++) {
            
            Element servidorEnDOM = listaServidoresEnDom.get(u);                       
            String servidorDelCapitulo = servidorEnDOM.select(".tdservidor").text().replaceAll(" ", "");
            
           // if("magnovideo".equals(servidorDelCapitulo) || "streamcloud".equals(servidorDelCapitulo) || "allmyvideos".equals(servidorDelCapitulo) || "vidspot".equals(servidorDelCapitulo)) {
            if("vidspot".equalsIgnoreCase(servidorDelCapitulo) || "Allmyvideos".equalsIgnoreCase(servidorDelCapitulo)) {
                String idioma = ObtenerIdiomaDelCapitulo(Integer.parseInt(servidorEnDOM.select(".tdidioma").text()));
                String subtitulos = ""; 
                String enlaceAlServidor = servidorEnDOM.select(".enlace_link").attr("href");

                Servidor servidor = new Servidor(idioma, subtitulos, enlaceAlServidor);

                switch(servidorDelCapitulo) {
                    case "Magnovideo":
                        servidor = new MagnoVideo(servidor);
                        break;
                    case "streamcloud":
                        servidor = new StreamCloud(servidor);
                        break;
                    case "Allmyvideos":
                        servidor = new AllMyVideosYVidSpot(servidor);
                        break;
                    case "vidspot":
                        servidor = new AllMyVideosYVidSpot(servidor);
                        break;
                }
                
                arrayServidores.add(servidor);
            }
            
        }
                
        this.servidoresConElCapitulo = arrayServidores;
        
        return arrayServidores;
        
    }

    public Serie getSeriePerteneciente() {
        return seriePerteneciente;
    }
    
    private String ObtenerIdiomaDelCapitulo(int numeroIdioma) {
        
        String idioma = "";
        
        switch(numeroIdioma) {
            case 0:
                idioma = "Spanish";
                break;
            case 1:
                idioma = "Latin Spanish";
                break;
                
            case 2:
                idioma = "English";
                break;
            case 3:
                idioma = "English with spanish subtitles";
                break;                
        }
        
        return idioma;
    }
    
}
