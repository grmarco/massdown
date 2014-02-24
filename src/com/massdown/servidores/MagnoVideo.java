/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.massdown.servidores;

import com.massdown.core.Servidor;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Guillermo
 */
public class MagnoVideo extends Servidor {

    public MagnoVideo(Servidor servidor) {
        super(servidor);
    }

    @Override
    public void ObtenerEnlaceDescarga() {
        try {
            
        String urlDelServidor = ObtenerEnlaceServidor(this.enlaceServidor);            
        String idDelVideo = urlDelServidor.replace("http://www.magnovideo.com/?v=", "");
        System.out.println(idDelVideo);            
            System.out.println(urlDelServidor);    
        //This will get you the response.
        Response res = Jsoup
            .connect(urlDelServidor)
            .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64; rv:27.0) Gecko/20100101 Firefox/27.0")
            .method(Method.GET)
            .execute();

        //This will get you cookies
        Map<String, String> datosCookies = res.cookies();
        
        datosCookies.put("prepage", idDelVideo); 
        datosCookies.put("before_play_adcash", "1");
        datosCookies.put("previd_adcache", "1");
        datosCookies.put("user_timezone", "1");
       
        
        System.out.println("------------------------");
      for (Map.Entry<String, String> entry : datosCookies.entrySet())
        {
            System.out.println(entry.getKey() + "/" + entry.getValue());
        }

        Response r = Jsoup.connect(enlaceServidor).cookies(datosCookies).execute();

            System.out.println("-----------------");
            System.out.println(r.cookies().toString());
        
            System.out.println(r.body());
        
        } catch (IOException ex) {
            Logger.getLogger(MagnoVideo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
