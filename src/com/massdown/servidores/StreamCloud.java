/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.massdown.servidores;

import com.massdown.core.Servidor;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;

/**
 *
 * @author Guillermo
 */
public class StreamCloud extends Servidor {

    public StreamCloud(Servidor servidor) throws MalformedURLException {
        super(servidor);        
    }

    @Override
    public void ObtenerEnlaceDescarga() throws MalformedURLException {
        String urlDelServidor = "";
        
        try {
            urlDelServidor = ObtenerEnlaceServidor(this.enlaceServidor);
            domPagServidor = Jsoup.connect(urlDelServidor).get();
            
            Map<String, String> datosParaElSubmit = new HashMap<>();
            
            String idCapitulo = domPagServidor.getElementsByAttributeValue("name", "id").attr("value");
            String nombreCapitulo = domPagServidor.getElementsByAttributeValue("name", "fname").attr("value");

            
            datosParaElSubmit.put("op", "download1");
            datosParaElSubmit.put("usr_login", "");
            datosParaElSubmit.put("hash", "");
            datosParaElSubmit.put("referer", "");
            datosParaElSubmit.put("imhuman", "Watch+video+now");
            datosParaElSubmit.put("id", idCapitulo);
            datosParaElSubmit.put("fname", nombreCapitulo);
                        
            System.out.println(urlDelServidor);
            domPagServidor = 
            Jsoup.connect(urlDelServidor)
            .data(datosParaElSubmit)
            .method(Method.POST)
            .execute().parse();
            
            System.out.println(domPagServidor);
            
            String htmlPagina = domPagServidor.html();
            System.out.println("\n \n \n ---------------------------------------");
            int inicio = htmlPagina.indexOf("file: \"http://stor5.streamcloud.eu:8080/");
            int fin = htmlPagina.indexOf("\",", inicio + 1);
            
            enlaceDeDescarga = htmlPagina.substring(inicio + 1, fin);
            
            
            System.out.println(enlaceDeDescarga);
//            
            
        } catch (IOException ex) {
            Logger.getLogger(StreamCloud.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }
    
}
