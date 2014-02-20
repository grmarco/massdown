

package com.massdown;
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
    private Document domPagCapitulo;
    private final String enlaceCapitulo;    
    
    public Capitulo(String tituloCapitulo, String enlaceCapitulo) throws IOException {       
        this.enlaceCapitulo = enlaceCapitulo;
        this.tituloCapitulo = tituloCapitulo;       
    }
    
    public ArrayList<Servidor> ObtenerServidoresCapitulo() throws IOException {
        this.domPagCapitulo = Jsoup.connect(enlaceCapitulo).get();
        Elements listaServidoresEnDom = domPagCapitulo.select("h2.veronline + table tr");
        ArrayList<Servidor> arrayServidores = new ArrayList<>();
        
        for(int u = 1 ; u < listaServidoresEnDom.size() ; u++) {
            
            Element servidorEnDOM = listaServidoresEnDom.get(u);                       
            String servidorDelCapitulo = servidorEnDOM.select("span.server").attr("class").replace("server ", "");
            
            
           // if("magnovideo".equals(servidorDelCapitulo) || "streamcloud".equals(servidorDelCapitulo) || "allmyvideos".equals(servidorDelCapitulo) || "vidspot".equals(servidorDelCapitulo)) {
            if("vidspot".equals(servidorDelCapitulo)) {
                String idioma = servidorEnDOM.select(".episode-lang").text();
                String subtitulos = servidorEnDOM.select(".episode-subtitle").text(); 
                String enlaceAlServidor = servidorEnDOM.select(".episode-server a").attr("href");
                
                Servidor servidor = new Servidor(idioma, subtitulos, enlaceAlServidor);

                switch(servidorDelCapitulo) {
                    case "magnovideo":
                        servidor = new MagnoVideo(servidor);
                        break;
                    case "streamcloud":
                        servidor = new StreamCloud(servidor);
                        break;
                    case "allmyvideos":
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
    

    
}
