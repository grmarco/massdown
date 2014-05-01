
package com.massdown.servidores;

import com.massdown.core.Servidor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class AllMyVideosYVidSpot extends Servidor {

    public AllMyVideosYVidSpot(Servidor servidor) {
        super(servidor);
    }
    
    @Override
    public void ObtenerEnlaceDescarga() {
         String urlDelServidor = "";
        
        try {
            System.out.println(urlDelServidor);
            urlDelServidor = ObtenerEnlaceServidor(this.enlaceServidor);
            System.out.println("------------\n"+enlaceServidor);
            domPagServidor = Jsoup.connect(urlDelServidor).get();
            
            Map<String, String> datosParaElSubmit = new HashMap<>();
            
            String idCapitulo = domPagServidor.getElementsByAttributeValue("name", "id").attr("value");
            String nombreCapitulo = domPagServidor.getElementsByAttributeValue("name", "fname").attr("value");

            datosParaElSubmit.put("F1", "");
            datosParaElSubmit.put("op", "download1");
            datosParaElSubmit.put("usr_login", "");
            datosParaElSubmit.put("hash", "");
            datosParaElSubmit.put("referer", "");
            datosParaElSubmit.put("method_free", "1");
             datosParaElSubmit.put("confirm", "Continue as Free User");
            datosParaElSubmit.put("id", idCapitulo);
            datosParaElSubmit.put("fname", nombreCapitulo);
                        
            domPagServidor = 
            Jsoup.connect(urlDelServidor)
            .data(datosParaElSubmit)
            .method(Connection.Method.POST)
            .execute().parse();
                        
            String htmlPagina = domPagServidor.body().html();
            int inicio = htmlPagina.indexOf("\"file\" : \"http://");
            int fin = htmlPagina.indexOf("\",", inicio + 1);
            
            enlaceDeDescarga = htmlPagina.substring(inicio + 1, fin).replace("file\" : \"", "");
            
            
            System.out.println(enlaceDeDescarga);
//            
            
        } catch (IOException ex) {
            Logger.getLogger(StreamCloud.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
}
