/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.massdown;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class GestorDescargas {
    private ArrayList<UnaDescarga> descargasEnCurso; 

    public GestorDescargas() {
        descargasEnCurso = new ArrayList<>();
    }
   
    public void addDescarga(String urlADescarga, String nombreArchivo) throws IOException  {
               
        try {
            UnaDescarga descarga = new UnaDescarga(new File("C:\\Users\\Guillermo\\Videos"));
            descarga.loadURL(urlADescarga);  
            URLConnection con = descarga.openConexion();                                
            descarga.descarga(nombreArchivo);
      
            descargasEnCurso.add(descarga);        
            
            
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JOptionPane(), "Error");
            
            String urlCaptcha = Jsoup.connect(urlADescarga).get().select("noscript>iframe").attr("src");
            
            System.out.println(urlCaptcha);
            
//            if (codigoStream.indexOf("SeriesCoco") > 0) {
//                urlCaptcha = ObtenerCadenaEntreTags(codigoStream, "<noscript>", "\" height=\"300\" width=\"500\" frameborder=\"0\"></iframe>", 0, 0, 28, 0, "");
//                codigoCaptcha = MetodosUtiles.ObtenerCodigoFuente(urlCaptcha)[1];
//                urlImagenCaptcha = "http://www.google.com/recaptcha/api/" + ObtenerCadenaEntreTags(codigoCaptcha, "<img width=\"300\" height=\"57\" alt=\"\" src=\"", "></center>", 0, 0, 41, 1,"");
//                hashCaptcha = ObtenerCadenaEntreTags(codigoCaptcha, "recaptcha_challenge_field\" value=\"", "<center><img width=\"300", 0, 0, 34, 2,"");
//                System.out.println(hashCaptcha);
//                validador = new ValidaCaptcha(urlCaptcha, hashCaptcha, urlCapituloPaso2, urlImagenCaptcha);
//
//                while(validador.getCodigoStream() == null) {
//                    Thread.sleep(1);
//                }
//
//                codigoStream = MetodosUtiles.ObtenerCodigoFuente(validador.getCodigoStream())[1];
//                currentUrl = MetodosUtiles.ObtenerCodigoFuente(validador.getCodigoStream())[0];
//                errorIndexandoDatosCapitulos = false;
//            } 
        } 
        
    }

    public ArrayList<UnaDescarga> getDescargasEnCurso() {
        return descargasEnCurso;
    }
    
    
}
