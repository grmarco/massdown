package com.massdown.gestordescarga;

import com.massdown.core.Capitulo;
import com.massdown.core.Servidor;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class GestorDescargas {
    private ArrayList<UnaDescarga> descargasEnCurso; 
    private String[] descargasPendientes;
    
    public GestorDescargas() {
        descargasEnCurso = new ArrayList<>();
    }
   
    public void addDescarga(Servidor servidor, Capitulo capitulo) {
                           
        try {  
            servidor.ObtenerEnlaceDescarga();
            UnaDescarga descarga = new UnaDescarga(new File(capitulo.tituloSeriePerteneciente+"/"));
            descarga.loadURL(servidor.enlaceDeDescarga);
            URLConnection con = descarga.openConexion();  
            descarga.setNombreArchivo(capitulo.tituloCapitulo);
            descargasEnCurso.add(descarga);
            
            new GestionarCola().start();
                          
        } catch (MalformedURLException ex) {
            Logger.getLogger(GestorDescargas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JOptionPane(),
            "Error adding the download",
            "There has been an error while adding the download",
            JOptionPane.ERROR_MESSAGE);
        }          
    }
    
    class GestionarCola extends Thread {

        @Override
        public void run() {
            super.run(); 
            
            int vueltas = 0;
            while(true) {         
                   
                    try {
                        sleep(1000);
                        
                        if (descargasEnCurso.size() == 1 && vueltas == 0) {
                            descargasEnCurso.get(0).descargar();
                            vueltas++;
                        } else if(descargasEnCurso.size() == 1) {
                        } else {
                            for(int i = 1 ; i < descargasEnCurso.size() ; i++) {
                                UnaDescarga descargaPreviaAEsta = descargasEnCurso.get(i - 1);
                                UnaDescarga estaDescarga = descargasEnCurso.get(i);
                                
                                if(descargaPreviaAEsta.getPorcentajeDescargado() == 100 && estaDescarga.getPorcentajeDescargado() == -1 && estaDescarga.time == 0) {
                                    estaDescarga.setPorcentajeDescargado(0);
                                    estaDescarga.descargar();
                                }
                                
                            }
                        }                        
   
                    } catch (IOException ex) {
                        
                    } catch (InterruptedException ex) {
                    Logger.getLogger(GestorDescargas.class.getName()).log(Level.SEVERE, null, ex);
                    }       
                        
            }            
        }        
    }
    
    public ArrayList<UnaDescarga> getDescargasEnCurso() {
        return descargasEnCurso;
    }
    
    
}
