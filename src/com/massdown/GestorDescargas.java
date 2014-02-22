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
import javax.swing.Timer;

public class GestorDescargas {
    private ArrayList<UnaDescarga> descargasEnCurso; 

    public GestorDescargas() {
        descargasEnCurso = new ArrayList<>();
    }
   
    public void addDescarga(String urlADescarga, String nombreArchivo) throws MalformedURLException, IOException {
        
        UnaDescarga descarga = new UnaDescarga(new File("C:\\Users\\Guillermo\\Videos"));
        
        descarga.loadURL(urlADescarga);        
        URLConnection con = descarga.openConexion();                                
        descarga.descarga(nombreArchivo);
      
        descargasEnCurso.add(descarga);
    }

    public ArrayList<UnaDescarga> getDescargasEnCurso() {
        return descargasEnCurso;
    }
    
    
}
