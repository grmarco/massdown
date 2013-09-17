
package es.gmarco.massdown.clases_escalables;

import es.gmarco.massdown.recursos.Configuracion;
import es.gmarco.massdown.superclases.Main;
import java.awt.Dimension;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import javax.swing.*;
import javax.swing.JLabel;
import javax.swing.JSeparator;

public class MetodosUtiles {
    
    public static String doSubmit(String url, Map<String, String> data) throws Exception {

        URL siteUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);

        DataOutputStream out = new DataOutputStream(conn.getOutputStream());

        Set keys = data.keySet();
        Iterator keyIter = keys.iterator();
        String content = "";

        for(int i=0; keyIter.hasNext(); i++) {            
            Object key = keyIter.next();
            if(i!=0) {
                content += "&";
            }
            content += key + "=" + URLEncoder.encode(data.get(key), "UTF-8");
        }

        out.writeBytes(content);
        out.flush();
        out.close();
        
        
        StringBuilder Fuente = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;

        while((line = in.readLine())!=null) {
            Fuente.append(line);
        }
        in.close();

        return Fuente.toString();        
    }
    
    public static String[] ObtenerCodigoFuente(String url) throws MalformedURLException, IOException {
        
        URL siteUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
       
        StringBuilder Fuente = new StringBuilder();
        while((line = in.readLine()) != null) {
            Fuente.append(line);
        }
        in.close();
        
        //Devuelve dos valores, el codigo fuente y la url del codigo fuente.
        String valoresADevolver[] = {String.valueOf(conn.getURL().toString().getBytes("ISO-8859-1")), Fuente.toString()};

                
        return valoresADevolver;
    }
    
//    public void CargarConfiguracion() {
//        InputStreamReader fl = new InputStreamReader(getClass().getResourceAsStream("/es/gmarco/massdown/recursos/DatosConfiguracion.xml"));
//        BufferedReader br;
//        try {
//            br = new BufferedReader(fl);
//            
//            String linea;
//            while((linea=br.readLine()) != null) {
//                if (linea.contains("<DirectorioDeDescarga>")) {
//                    Configuracion.directorioDeDescarga = ObtenerCadenaEntreTags(linea, "<DirectorioDeDescarga>", "</DirectorioDeDescarga>", 22, 0);
//                } else if(linea.contains("<DescargaEnCola>")) {
//                    Configuracion.descargaEnCola = Boolean.parseBoolean(ObtenerCadenaEntreTags(linea, "<DescargaEnCola>", "</DescargaEnCola>", 16, 0));
//                } else if(linea.contains("<DescargasSimultaneas>")) {
//                    Configuracion.descargasSimultaneas = Integer.parseInt(ObtenerCadenaEntreTags(linea, "<DescargasSimultaneas>", "</DescargasSimultaneas>", 22, 0));
//                }
//            }
//            
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//    }
    
    public void CargarConfiguracion() {
        Preferences prefs = Preferences.userRoot().node(this.getClass().getName());
        
        Configuracion.descargaEnCola = prefs.getBoolean("DescargaEnCola", false);
        Configuracion.directorioDeDescarga = prefs.get("DirectorioDeDescarga", ".");
        Configuracion.descargasSimultaneas = prefs.getInt("DescargasSimultaneas", 30);
                        
    }
    
    public void EscribirConfiguracion() {
        Preferences prefs = Preferences.userRoot().node(this.getClass().getName());
        
        prefs.putBoolean("DescargaEnCola", Configuracion.descargaEnCola);
        prefs.put("DirectorioDeDescarga", Configuracion.directorioDeDescarga);
        prefs.putInt("DescargasSimultaneas", Configuracion.descargasSimultaneas);
                        
    }
    
//    public void EscribirConfiguracion()  {
//        //Borrando el xml de configuracion
//        new File(getClass().getResource("/es/gmarco/massdown/recursos/DatosConfiguracion.xml").getFile()).delete();
//        //Lo creamos de nuevo
//        
//        try {
//            OutputStream os = new BufferedOutputStream(new FileOutputStream(new File(getClass().getResource("/es/gmarco/massdown/recursos/DatosConfiguracion.xml").getFile())));
//            os.write(Byte.parseByte("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
//                    + "<root>"
//                    + "<DirectorioDeDescarga>" + Configuracion.directorioDeDescarga + "</DirectorioDeDescarga>"
//                    + "<DescargaEnCola>" + Configuracion.descargaEnCola + "</DescargaEnCola>"
//                    + "<DescargasSimultaneas>" + Configuracion.descargasSimultaneas + "</DescargasSimultaneas>"
//                    + "</root>"));
//                    System.out.println("exito");
//        } catch (IOException ex) {
//            Logger.getLogger(MetodosUtiles.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        
//    }
    
    public static String ObtenerCadenaEntreTags(String codigoAAnalizar, String primerTag, String segundoTag, int ajustePrimerElemento, int ajusteSegundoElemento) {
        
        String elementoEntreTags;
        int primerNodo ;
        int segundoNodo;
        
        primerNodo = codigoAAnalizar.indexOf(primerTag);
        segundoNodo = codigoAAnalizar.indexOf(segundoTag, primerNodo);

        elementoEntreTags = codigoAAnalizar.substring(primerNodo + ajustePrimerElemento, segundoNodo - ajusteSegundoElemento);
        return elementoEntreTags; 
    }
    
    public static Thread AnimacionDeCargando(final Object dondePonerAnimacion, final Dimension vtnDondeAnimar) {
                 
        System.out.println(dondePonerAnimacion.getClass().getName());
        final Thread animacionDeCargando = new Thread() {
         
                @Override
                public void run() {
                    int numeroDePuntos = 0;                    
                    JLabel lblAAnimar = new JLabel();
                    DefaultListModel lstDondePonerAnimacion = new DefaultListModel();
                    JSeparator separadorAAnimar = new JSeparator();
                    boolean isAnimacionEnCurso = true;
                    int widthVentana = 0;
                    int tiempoAnimacion = 500;
                    boolean agrandar = true;
                    
                    if(dondePonerAnimacion.getClass().getName().equals("javax.swing.JLabel")) {
                        lblAAnimar = (JLabel) dondePonerAnimacion;
                    } else if (dondePonerAnimacion.getClass().getName().equals("javax.swing.DefaultListModel")) {
                        lstDondePonerAnimacion = (DefaultListModel) dondePonerAnimacion;
                    }  else if (dondePonerAnimacion.getClass().getName().equals("javax.swing.JSeparator")) {
                        separadorAAnimar = (JSeparator) dondePonerAnimacion;
                        widthVentana = vtnDondeAnimar.width - 64;
                        tiempoAnimacion = 1;
                    }
                    
                    
                    while(true) {
                        
                        
                        try {
                            sleep(tiempoAnimacion);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                            
                            break;
                        }
                       
                        if(dondePonerAnimacion.getClass().getName().equals("javax.swing.JLabel")) {
                            if (numeroDePuntos >= 4) {
                                numeroDePuntos = 0;
                            }
                            switch(numeroDePuntos) {
                                case 0:
                                    lblAAnimar.setText("Cargando");
                                    break;
                                case 1:
                                    lblAAnimar.setText("Cargando.");
                                    break;
                                case 2:
                                    lblAAnimar.setText("Cargando..");
                                    break;
                                case 3:
                                    lblAAnimar.setText("Cargando...");
                                    break;                            
                            }
                            numeroDePuntos++;
                        } else if (dondePonerAnimacion.getClass().getName().equals("javax.swing.DefaultListModel")) {
                            if (numeroDePuntos >= 4) {
                                numeroDePuntos = 0;
                            }
                            lstDondePonerAnimacion.clear();
                            switch(numeroDePuntos) {
                                case 0:
                                    lstDondePonerAnimacion.addElement("Cargando");
                                    break;
                                case 1:
                                    lstDondePonerAnimacion.addElement("Cargando.");
                                    break;
                                case 2:
                                    lstDondePonerAnimacion.addElement("Cargando..");
                                    break;
                                case 3:
                                    lstDondePonerAnimacion.addElement("Cargando...");
                                    break;                            
                            }
                            
//                            switch(numeroDePuntos) {
//                               case 0:                                    
//                                    lstDondePonerAnimacion.addElement("");
//                                    break;
//                                case 1:
//                                    lstDondePonerAnimacion.addElement("####");
//                                    break;
//                                case 2:
//                                    lstDondePonerAnimacion.addElement("########");
//                                    break;
//                                case 3:
//                                    lstDondePonerAnimacion.addElement("###########");
//                                    break; 
//                                case 4:
//                                    lstDondePonerAnimacion.addElement("################");
//                                    break;
//                                case 5:
//                                    lstDondePonerAnimacion.addElement("####################");
//                                    break;
//                                case 6:
//                                    lstDondePonerAnimacion.addElement("######################");
//                                    break;                                              
//                            } 
                            numeroDePuntos++;
                        } else if (dondePonerAnimacion.getClass().getName().equals("javax.swing.JSeparator")) {
                            
                                                        
                            
                            if(numeroDePuntos >= widthVentana) {
                                agrandar = false;
                                
                            } else if(numeroDePuntos <= 0) {
                                agrandar = true;
                            }
                            
                            separadorAAnimar.setSize(numeroDePuntos, 10);
                            
                            
                            if(agrandar) {
                                numeroDePuntos++;
                            } else {
                                numeroDePuntos--;
                            }
                            
                        }
                        
                        
                    }
                    
                }
        
               
        };
       return animacionDeCargando; 
    }
}
   
