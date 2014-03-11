package es.gmarco.massdown.recursos;

import com.massdown.main.Main;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSeparator;



public class MetodosUtiles {
    
    public static ImageIcon escalarImagen(Image src, double scale, Component sitioDondeSeColocaraImagen) {
        int w = (int)(scale*src.getWidth(sitioDondeSeColocaraImagen));
        int h = (int)(scale*src.getHeight(sitioDondeSeColocaraImagen));
        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage dst = new BufferedImage(w, h, type);
        Graphics2D g2 = dst.createGraphics();
        g2.drawImage(src, 0, 0, w, h, sitioDondeSeColocaraImagen);
        g2.dispose();
        return new ImageIcon(dst);
    }
    
    public static String[] ObtenerCodigoFuente(String url) throws MalformedURLException, IOException {
        URL siteUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) siteUrl.openConnection();
        conn.setRequestProperty("Accept-Charset", "ISO-8859-1");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), Charset.forName("ISO-8859-1")));
        String line;
       
        StringBuilder Fuente = new StringBuilder();
        while((line = in.readLine()) != null) {
            line = new String(line.getBytes(), "ISO-8859-1");
            Fuente.append(line);
        }
        in.close();
        
        //Devuelve dos valores, el codigo fuente y la url del codigo fuente.
        String valoresADevolver[] = {String.valueOf(conn.getURL().toString().getBytes("ISO-8859-1")), Fuente.toString()};

                
        return valoresADevolver;
    }
    
    public static String[] ObtenerCadenaEntreTags(String codigoAAnalizar, String primerTag, String segundoTag, int ajustePrimerElemento, int ajusteSegundoElemento, int nodoInicio) {
        
        String elementoEntreTags[] = {"", ""};
        int primerNodo ;
        int segundoNodo;
        
        primerNodo = codigoAAnalizar.indexOf(primerTag, nodoInicio);
        segundoNodo = codigoAAnalizar.indexOf(segundoTag, primerNodo);

        
        elementoEntreTags[0] = codigoAAnalizar.substring(
                primerNodo + ajustePrimerElemento, 
                segundoNodo - ajusteSegundoElemento);
        elementoEntreTags[1] = String.valueOf(segundoNodo);
        
        return elementoEntreTags; 
    }
    
    
    

    public static String getFinalRedirectedUrl(String url) throws MalformedURLException, ProtocolException, IOException
    {
        HttpURLConnection connection;
        String finalUrl = url;
        do {
                connection = (HttpURLConnection) new URL(finalUrl)
                .openConnection();
                connection.setInstanceFollowRedirects(false);
                connection.setUseCaches(false);
                connection.setRequestMethod("GET");
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode >=300 && responseCode <400)
                {
                    String redirectedUrl =    connection.getHeaderField("Location");
                    if(null== redirectedUrl)
                        break;
                    finalUrl =redirectedUrl;
                    System.out.println( "redirected url: " + finalUrl);
                }
                else
                    break;
            }while (connection.getResponseCode() != HttpURLConnection.HTTP_OK);
            connection.disconnect();
        
      
        return finalUrl;
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
                    } else if (dondePonerAnimacion.getClass().getName().equals("javax.swing.JSeparator")) {
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
                            
// switch(numeroDePuntos) {
// case 0:
// lstDondePonerAnimacion.addElement("");
// break;
// case 1:
// lstDondePonerAnimacion.addElement("####");
// break;
// case 2:
// lstDondePonerAnimacion.addElement("########");
// break;
// case 3:
// lstDondePonerAnimacion.addElement("###########");
// break;
// case 4:
// lstDondePonerAnimacion.addElement("################");
// break;
// case 5:
// lstDondePonerAnimacion.addElement("####################");
// break;
// case 6:
// lstDondePonerAnimacion.addElement("######################");
// break;
// }
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
            System.out.println(line);
        }
        in.close();

        return Fuente.toString();
    }
    
}
