
package com.massdown;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.Timer;

public class UnaDescarga {

    private URL url;
    private URLConnection conexion;
    private File file;
    private boolean terminate = false;
    
    private String nombreArchivoDescargando;
    private double porcentajeDescargado;
    private double tamanoDescargado;
    private int velocidadDescarga;
    private double tamanoTotal;
    private final Timer timer;
    private double tiempoRestante;
    public int time;

    public UnaDescarga(File file) {
        this.file = file;
        
        
        timer = new Timer(1000, new ActionListener() {
            
            public double tamanoDescagadoHaceUnSegundo = tamanoDescargado;
            
            @Override
            public void actionPerformed(ActionEvent e) {
                int minutos = (time > 60) ? time / 60 : 0 ;
                int segundo = time - minutos * 60;
                int horas = 0;
                
                velocidadDescarga = (int) ((tamanoDescargado - tamanoDescagadoHaceUnSegundo) * 1000);
                
                tiempoRestante = ((tamanoTotal - tamanoDescargado) / velocidadDescarga) * 60; 
                
                tamanoDescagadoHaceUnSegundo = tamanoDescargado;
                time++;
            }
        });
        
    }
    
    public void setFile(File file) {
        this.file = file;
    }

    public boolean isTerminate() {
        return terminate;
    }

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }

    public void loadURL(String sURL) throws MalformedURLException {
        this.url = new URL(sURL);
    }

    public URLConnection openConexion() throws IOException {
        conexion = url.openConnection();
        conexion.connect();
        return conexion;
    }
    
    
    public void descarga(final String nombreArchivoFinal) throws IOException {
        
        String array[] = url.toString().split("/");
        final String salida = array[array.length - 1].replaceAll("%20", " ");
        
        System.out.println(salida);
        
        tamanoTotal = (double) ((double) (conexion.getContentLength() / 1000) / 1000);
        final BufferedInputStream buffer = new BufferedInputStream(conexion.getInputStream());
        nombreArchivoDescargando = nombreArchivoFinal+".mp4";
        final BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file + "/" + nombreArchivoDescargando));
                       
        timer.start();
        final byte datos[] = new byte[1000];
        
        new Thread() {
            @Override
            public void run() {
                try {
                    
                    int read;
                    int length = 0;
                    
                    read = buffer.read(datos);
                    
                    while (read > 0) {
                        
                        length += read;                        
                        out.write(datos, 0, read);
                        
                        tamanoDescargado = (double) ((double) (length / 1000) / 1000);
                        porcentajeDescargado = tamanoDescargado/tamanoTotal  * 100;
                                                
                        read = buffer.read(datos);
                        
                        if(terminate) {
                            buffer.close();
                            out.close();
                            timer.stop();
                            interrupt();
                        }
                        
                    }
                    
                    buffer.close();
                    out.close();
                    timer.stop();
                    
                } catch (IOException ex) {
                    System.out.println("ERROR --> " + ex);
                }
            }
        }.start();
    }

    public String getNombreArchivoDescargando() {
        return nombreArchivoDescargando;
    }

    public double getPorcentajeDescargado() {
        return porcentajeDescargado;
    }

    public double getTamanoDescargado() {
        return tamanoDescargado;
    }

    public double getTamanoTotal() {
        return tamanoTotal;
    }

    public int getTime() {
        return time;
    }

    public double getVelocidadDescarga() {
        return velocidadDescarga;
    }

    public double getTiempoRestante() {
        return tiempoRestante;
    }
    
    
    
}
