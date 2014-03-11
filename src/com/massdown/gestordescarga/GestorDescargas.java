package com.massdown.gestordescarga;

import com.massdown.core.Capitulo;
import com.massdown.core.Servidor;
import es.gmarco.massdown.recursos.Configuracion;
import es.gmarco.massdown.recursos.MetodosUtiles;
import java.awt.Button;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class GestorDescargas {
    private ArrayList<UnaDescarga> descargasEnCurso; 
    private String[] descargasPendientes;
    
    public GestorDescargas() {
        descargasEnCurso = new ArrayList<>();
        
    }
   
    public void addDescarga(Servidor servidor, Capitulo capitulo) {
                           
        try {  
            // Obtenemos el enlace de mp4 del servidor
            servidor.ObtenerEnlaceDescarga();            
            
            // Instanciamos la descarga
            UnaDescarga descarga = new UnaDescarga(new File(Configuracion.getDirectorioDeDescarga()+"/"+capitulo.tituloSeriePerteneciente+"/"));  
            
            // Seteamos los datos de la descarga
            descarga.loadURL(servidor.enlaceDeDescarga);                                   
            descarga.setNombreArchivo(capitulo.tituloCapitulo);
            descarga.setThumbNail(MetodosUtiles.escalarImagen(ImageIO.read(new URL(capitulo.getSeriePerteneciente().getUrlImagen())), 0.315, new Button()));
            URLConnection con = descarga.openConexion();  
            
            
                        
            if(descargasEnCurso.isEmpty()) {
                new GestionarCola().start();
            }                
            
            descargasEnCurso.add(descarga);
                          
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(new JOptionPane(),
            
            "There has been an error while adding the download, please try again. \n ERROR->"+ex.getLocalizedMessage(),
            "Massdown message",
            JOptionPane.INFORMATION_MESSAGE);
            Logger.getLogger(GestorDescargas.class.getName()).log(Level.SEVERE, null, ex);
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
                                } else if(estaDescarga.getPorcentajeDescargado() == -1 && !Configuracion.isDescargaEnCola()) {
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
