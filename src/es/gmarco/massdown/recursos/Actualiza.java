
package es.gmarco.massdown.recursos;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Actualiza {
    
    private double versionActual;
    private double versionNueva;
    
    public Actualiza(boolean comprobacionSilenciosa) throws MalformedURLException, IOException {

        this.versionNueva = CompruebaVersion();
        this.versionActual = Configuracion.version;
        
        if(this.versionActual < this.versionNueva) {
            int siono = JOptionPane.showConfirmDialog(new JOptionPane(), "Nueva actualización de MassDown encontrada? ¿Deseas actualizar");
            if (siono == 0) {
                DescargarActualizacion();
            }
            
        } else {
            if(!comprobacionSilenciosa)
                JOptionPane.showMessageDialog(new JOptionPane(), "MassDown está actualizado");
        }
    }
    
    public static double CompruebaVersion() throws MalformedURLException, IOException {
        String codigoFuente;
        double version;
        
        codigoFuente = MetodosUtiles.ObtenerCodigoFuente("https://github.com/grmarco/massdown/blob/master/src/es/gmarco/massdown/recursos/Configuracion.java")[1];
        version = Double.parseDouble(MetodosUtiles.ObtenerCadenaEntreTags(codigoFuente,
                "n\">version</span> <span class=\"o\">=</span> <span class=\"mf\">",
                "<span class=\"o\">;</span>", 60, 7, 0)[0]);
        
        return version;
    }
    
    
    public void DescargarActualizacion() {
        try{
        String urlADescargar = "https://github.com/grmarco/massdown/blob/master/dist/massdown.jar?raw=true";
        File rutaDeDescarga = new File(".");
        if(!rutaDeDescarga.exists()) {
            rutaDeDescarga.mkdirs();
        }
        JFrame vtnPrincipal = new JFrame("Descargando actualización");
        Download download = new Download(rutaDeDescarga);
        JPanel contenedorConMargen = new JPanel(new GridLayout(3, 1, 8 , 8));
        final JProgressBar pbDescarga = new JProgressBar();
        JLabel lblEstatus = new JLabel();
        final JButton btnAplicarActu = new JButton("Aplicar actualización");
        int timer = 0;
        
        btnAplicarActu.setEnabled(false);
                                
        vtnPrincipal.setVisible(true);
        vtnPrincipal.setSize(310,150);
        vtnPrincipal.setIconImage(new ImageIcon(getClass().getResource("/es/gmarco/massdown/recursos/icon.png")).getImage());
        vtnPrincipal.add(contenedorConMargen);
        vtnPrincipal.setResizable(false);
        
        contenedorConMargen.setBorder(new EmptyBorder(10, 10, 10, 10));
        contenedorConMargen.add(lblEstatus);
        contenedorConMargen.add(pbDescarga);
        contenedorConMargen.add(btnAplicarActu);
        
        download.loadURL(urlADescargar);
        
        URLConnection con = download.openConexion();
        
        pbDescarga.setMaximum(con.getContentLength());
            
        
        
        pbDescarga.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                
                System.out.println(pbDescarga.getPercentComplete());
                if (pbDescarga.getPercentComplete() >= 1.0) {
                btnAplicarActu.setEnabled(true);
                btnAplicarActu.addMouseListener(new MouseListener() {
                    
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        
                        try {
                            Runtime.getRuntime().exec("java -jar massdown" + versionNueva + ".jar " + versionNueva + " aplicarActualizacion");
                            System.exit(0);
                        } catch (IOException ex) {
                            Logger.getLogger(Actualiza.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }

                } );
            }
            }
        });
        
        
        download.descarga(pbDescarga, lblEstatus, new Timer(timer, null), "massdown" + versionNueva + ".jar", new JLabel());
        } catch(HeadlessException | IOException ex) {
            System.err.println(ex.getMessage());
            System.err.println("ERROR EN LA ACTUALIZACION");
        } finally {

        }
    }
    
    public static void AplicarActualizacion(double version) {
        File f = new File(".");
        String archivo[] = f.list();
        //me devuelve una lista del directorio actual
        for(int i=0;i<archivo.length;i++) {
            if(archivo[i].endsWith(".jar")) {
                if(!archivo[i].startsWith("massdown" + version)) {
                    f = new File(archivo[i]);
                    f.delete();
                }
            }
        }
    }
    
}
