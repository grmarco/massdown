/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.massdown.servidores;

import com.massdown.Servidor;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Guillermo
 */
public class StreamCloud extends Servidor {

    public StreamCloud(Servidor servidor) throws MalformedURLException {
        super(servidor);        
    }

    @Override
    public URL ObtenerEnlaceDescarga() throws MalformedURLException {
        String urlDelServidor = "";
        try {
            urlDelServidor = ObtenerEnlaceServidor(this.enlaceServidor);
        } catch (IOException ex) {
            Logger.getLogger(StreamCloud.class.getName()).log(Level.SEVERE, null, ex);
        }                
        
        System.out.println(urlDelServidor);
        
        return new URL(urlDelServidor);                
    }
    
}
