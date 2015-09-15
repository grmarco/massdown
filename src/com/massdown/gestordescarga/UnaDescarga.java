
package com.massdown.gestordescarga;

import com.massdown.views.SeriePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public final class UnaDescarga {

    private URL url;
    private URLConnection conexion;
    private File file;
    private boolean terminate = false;
    
    private double porcentajeDescargado;
    private double tamanoDescargado;
    private int velocidadDescarga;
    private double tamanoTotal;
    private final Timer timer;
    private double tiempoRestante;
    public int time;
    private String nombreArchivo;
    private ImageIcon thumbNail;
    
    public UnaDescarga(File file) {

        setFile(file);        
        
        tamanoDescargado = 0;
        tamanoTotal = 0;
        tiempoRestante = 0;
        velocidadDescarga = 0;
        porcentajeDescargado = -1;
        
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
        file.mkdir();
        this.file = file.getAbsoluteFile();
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

    public URL getUrl() {
        return url;
    }
    
    
    
    public ImageIcon getThumbNail() {
        return thumbNail;
    }

    public void setThumbNail(ImageIcon thumbNail) {
        this.thumbNail = thumbNail;
    }
    
    
    
    public URLConnection openConexion() throws IOException {
        conexion = url.openConnection();
        conexion.connect();
        
        return conexion;
    }
    
    
    public void descargar() throws IOException {
        
        String array[] = url.toString().split("/");
        final String salida = array[array.length - 1].replaceAll("%20", " ");
                
        final BufferedInputStream buffer = new BufferedInputStream(conexion.getInputStream());
        String nombreArchivoDescargando = nombreArchivo+".mp4";
        tamanoTotal = (double) ((double) (conexion.getContentLength() / 1000) / 1000);
        final BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(String.valueOf(file + "/" + nombreArchivoDescargando).trim()));
                               
        
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
                        porcentajeDescargado = (tamanoDescargado/tamanoTotal) * 100;
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
                    JOptionPane.showMessageDialog(new JOptionPane(),
                    "Massdown message", 
                    "the download "+nombreArchivo+" has stoped",
                    JOptionPane.INFORMATION_MESSAGE);
                   Logger.getLogger(SeriePanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }

    public static void descargarDeOtraForma () throws IOException {
//        String fileName = "video.mp4"; //The file that will be saved on your computer
//        URL link = new URL("http://cdn1.streamcloud.eu:8080/2pv75zrzpwoax3ptxzkifdh2vb65cmiq442kyuejryyo7rdykwq4ctuami/video.mp4"); //The file that you want to download
//
//        //Code to download
//        InputStream in = new BufferedInputStream(link.openStream());
//        
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        byte[] buf = new byte[1024];
//        int n = 0;
//        
//        System.out.println(link.openConnection().getContentLength());
//        
//        while (-1!=(n=in.read(buf)))
//        {
//            out.write(buf, 0, n);
//            
//        }
//        out.close();
//        in.close();
//        byte[] response = out.toByteArray();
//
//        FileOutputStream fos = new FileOutputStream(fileName);
//        fos.write(response);
//        fos.close();
//        //End download code
//
//        System.out.println("Finished");
        
        URL website = new URL("http://cdn1.streamcloud.eu:8080/2pv75zrzpwoax3ptxzkifdh2vb65cmiq442kyuejryyo7rdykwq4ctuami/video.mp4");
        ReadableByteChannel rbc = Channels.newChannel(website.openStream());
        FileOutputStream fos = new FileOutputStream("video.mp4");
        long go = fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        System.out.println(go);
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

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public void setPorcentajeDescargado(double porcentajeDescargado) {
        this.porcentajeDescargado = porcentajeDescargado;
    }
    
    
    
}
