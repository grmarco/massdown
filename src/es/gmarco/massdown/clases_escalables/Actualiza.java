
package es.gmarco.massdown.clases_escalables;

import es.gmarco.massdown.recursos.Constantes;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.Timer;


public class Actualiza {
    
    private double versionActual;
    private double versionNueva;
    
    public Actualiza(boolean comprobacionSilenciosa) throws MalformedURLException, IOException {
        this.versionNueva = CompruebaVersion();
        this.versionActual = Constantes.version;
        
        if(this.versionActual != this.versionNueva) {
            int siono = JOptionPane.showConfirmDialog(new JOptionPane(), "Nueva actualización de MassDown encontrada? ¿Deseas actualizar");
            if (siono == 0) {
                Actualizar();
            }   
            
        } else {
            if(!comprobacionSilenciosa)
                JOptionPane.showMessageDialog(new JOptionPane(), "MassDown está actualizado");
        }
    }
    
    public static double CompruebaVersion() throws MalformedURLException, IOException {
        String codigoFuente;        
        double version;
        
        codigoFuente = MetodosUtiles.ObtenerCodigoFuente("https://github.com/grmarco/massdown/blob/master/src/es/gmarco/massdown/recursos/Constantes.java")[1];
        version = Double.parseDouble(MetodosUtiles.ObtenerCadenaEntreTags(codigoFuente, 
                "n\">version</span> <span class=\"o\">=</span> <span class=\"mf\">", 
                "<span class=\"o\">;</span>", 60, 7));        
        
        return version;
    }
    
    
    public static void Actualizar() {        
        try{
        String urlADescargar = "https://github.com/grmarco/massdown/blob/master/dist/massdown.jar?raw=true";
        File rutaDeDescarga = new File("/");
        if(!rutaDeDescarga.exists()) {            
            rutaDeDescarga.mkdirs();
        }
        JFrame contenedorDescargas = new JFrame("Descargando actualización");
        contenedorDescargas.setVisible(true);
        contenedorDescargas.setSize(200,200);
        contenedorDescargas.setLayout(new FlowLayout());
        
        Download download = new Download(rutaDeDescarga);
        
        JProgressBar pbDescarga = new JProgressBar();
        JLabel lblTiempo = new JLabel();
        JLabel lblTituloCap = new JLabel();
        JLabel lblEstatus = new JLabel();
        int timer = 0;
        
        contenedorDescargas.add(lblTituloCap);
        contenedorDescargas.add(lblEstatus);
        contenedorDescargas.add(lblTiempo); 
        contenedorDescargas.add(pbDescarga);
                                
        download.loadURL(urlADescargar);
        
        URLConnection con = download.openConexion();
        
        pbDescarga.setMaximum(con.getContentLength());                                                              
            
        download.descarga(pbDescarga, lblEstatus, new Timer(timer, null), "massdown1.jar", new JLabel());
            
        while(download.isTerminate()) {
            Runtime.getRuntime().exec("java -jar massdown.jar");
            System.exit(0);
        }

        } catch(Exception ex) {
            System.err.println(ex.getMessage());   
            System.err.println("ERROR EN LA ACTUALIZACION");
        } finally {

            

        }
    }
    
}
